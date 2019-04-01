package oneclick.yonclick.activity.Detail;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import oneclick.yonclick.Model.Model.GetCategoryWithProduit;
import oneclick.yonclick.Model.Model.GetMarqueWithProduit;
import oneclick.yonclick.Model.Model.Magasin;
import oneclick.yonclick.Model.Model.Product;
import oneclick.yonclick.R;
import oneclick.yonclick.Uils.ActivityUtils;
import oneclick.yonclick.Uils.AppUtility;
import oneclick.yonclick.activity.activity.BaseActivity;
import oneclick.yonclick.activity.activity.CartListActivity;
import oneclick.yonclick.activity.activity.DetailsCreditCardActivity;
import oneclick.yonclick.activity.activity.MainActivity;
import oneclick.yonclick.activity.activity.MobilePaiementActivity;
import oneclick.yonclick.dataa.constant.AppConstants;
import oneclick.yonclick.dataa.preference.AppPreference;
import oneclick.yonclick.dataa.preference.PrefKey;
import oneclick.yonclick.dataa.sqlite.CartDBController;

import static oneclick.yonclick.BaseUrl.InterfaceAPIPost.RestApi.BASE_URL_Image;

public class DetailsProduitActivity extends BaseActivity {

    private int quantityCounter = 1;
    Context mContext;
    private Activity mActivity;

    public Product produit;
    public Magasin magasin;
    public GetMarqueWithProduit getMarqueWithProduit;
    public GetCategoryWithProduit getCategoryWithProduit;


    String ImgProduit,imgCategorie,imgMarque;
    Integer prod,cat,brand;
    ImageView imageView,imgToolbarCart,imgProfil,image;
    TextView tvCartCounter,tvProductQuantity;


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ImageButton btnQuantityPlus,btnQuantityMinus;




    private String title,price;
     int id_cat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_produit);


        mContext = getApplicationContext();

        // cart counter
        imgToolbarCart = (ImageView) findViewById(R.id.cartList);
        imgProfil = (ImageView) findViewById(R.id.imgProfil);

        initToolbar();
        enableBackButton();
        setToolbarTitle(title);
        tvProductQuantity = (TextView) findViewById(R.id.tvProductQuantity);
        btnQuantityPlus = (ImageButton) findViewById(R.id.btnQuantityPlus);
        btnQuantityMinus = (ImageButton) findViewById(R.id.btnQuantityMinus);
        //Variable
        TextView NameProduit = (TextView) findViewById(R.id.tvProductName);
        TextView DescProduit = (TextView) findViewById(R.id.tvDescription);
        TextView tvTextDescription = (TextView) findViewById(R.id.tvTextDescription);
        TextView tvNomMagasin = (TextView) findViewById(R.id.tvNomMagasin);
        TextView tvSalesPrice = (TextView) findViewById(R.id.tvSalesPrice);
        image = (ImageView) findViewById(R.id.vpImageSlider);
      //  imageView = (ImageView) findViewById(R.id.vpImageSlider);

        final Button btnAddToCart = findViewById(R.id.btnAddToCart);
        Button btnBuyNow = findViewById(R.id.btnBuyNow);

        // toolbar cart action listener
        imgToolbarCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 startActivity(new Intent(getApplicationContext(),CartListActivity.class));


            }
        });

        if (getIntent().getSerializableExtra(AppConstants.produit) != null)
        {
            produit = (Product) getIntent().getSerializableExtra(AppConstants.produit);

        }
        else if (getIntent().getSerializableExtra(AppConstants.brand) != null)
        {
            getMarqueWithProduit = (GetMarqueWithProduit) getIntent().getSerializableExtra(AppConstants.brand);

        }
        else if (getIntent().getSerializableExtra(AppConstants.categorie) != null)
        {
            getCategoryWithProduit = (GetCategoryWithProduit) getIntent().getSerializableExtra(AppConstants.categorie);

        }
        else{
            System.out.println("PROD : NO DETAILS");
        }
       // System.out.println("PROD INFO : "+produit.getName_product());


        //image
        if (getIntent().getSerializableExtra(AppConstants.KEY_IMAGE_URL) != null)
        {
            produit = (Product) getIntent().getSerializableExtra(AppConstants.KEY_IMAGE_URL);

        }



        if (getCategoryWithProduit != null){
            NameProduit.setText(getCategoryWithProduit.getNameProduct());
            DescProduit.setText(getCategoryWithProduit.getDetailsProduct());
            tvTextDescription.setText(getCategoryWithProduit.getDetailsProduct());
           // tvNomMagasin.setText(getCategoryWithProduit.getNameProduct());
            tvSalesPrice.setText(getCategoryWithProduit.getPrix());
            imgCategorie = BASE_URL_Image+getCategoryWithProduit.getImage();

            Glide.with(getApplicationContext()).load(imgCategorie).into(image);
        }
        else if (getMarqueWithProduit != null)
        {
            NameProduit.setText(getMarqueWithProduit.getNameProduct());
            DescProduit.setText(getMarqueWithProduit.getDetailsProduct());
            tvTextDescription.setText(getMarqueWithProduit.getDetailsProduct());
            tvSalesPrice.setText(getMarqueWithProduit.getPrix());
            imgMarque = BASE_URL_Image+getMarqueWithProduit.getImage();
            Glide.with(getApplicationContext())
                    .load(imgMarque)
                    .into(image);
        }
        else if(produit != null)
        {

            NameProduit.setText(produit.getName_product());
            DescProduit.setText(produit.getDetails_product());
            tvTextDescription.setText(produit.getDetails_product());
            tvSalesPrice.setText(produit.getPrix());
            ImgProduit = BASE_URL_Image+produit.getImage();

            Glide.with(getApplicationContext()).load(ImgProduit).into(image);

        }

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getApplicationContext(), "Image", Toast.LENGTH_SHORT).show();
                ActivityUtils.getInstance().invokeProductDetailsImage(mContext);
                /*
                Intent intent = new Intent(getApplicationContext(), LargeImageViewActivity.class);
                intent.putExtra(AppConstants.KEY_IMAGE_URL,"id");
                startActivity(intent);*/
            }
        });




        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                if ( produit!= null)
                {

                    CartDBController cartController = new CartDBController(mContext);


                    if (cartController.isAlreadyAddedToCart(produit.getId())) {
                        AppUtility.showToast(mContext, getString(R.string.already_in_cart));
                    } else {
                        quantityCounter = Integer.valueOf(tvProductQuantity.getText().toString());


                        cartController.insertCartItem(produit.getId(), produit.getPrix(),produit.getName_product(), produit.getImage(), quantityCounter);
                        // btnAddToCart.setText(getString(R.string.added_to_cart));
                        AppUtility.showToast(mContext, getString(R.string.added_to_cart));
                    }
                    cartController.close();

                }
                else if (getMarqueWithProduit != null)
                {
                    // Add to cart list
                    CartDBController cartController = new CartDBController(mContext);

                    if (cartController.isAlreadyAddedToCart(getMarqueWithProduit.getId()))
                    {
                        AppUtility.showToast(mContext, getString(R.string.already_in_cart));
                    }
                    else
                    {
                         quantityCounter = Integer.valueOf(tvProductQuantity.getText().toString());

                        cartController.insertCartItem(getMarqueWithProduit.getBrandId(), getMarqueWithProduit.getNameProduct(),
                                getMarqueWithProduit.getImage(),getMarqueWithProduit.getPrix(), quantityCounter);
                       // btnAddToCart.setText(getString(R.string.added_to_cart));
                        AppUtility.showToast(mContext, getString(R.string.added_to_cart));


                    }
                    cartController.close();

                }
              else if (getCategoryWithProduit != null)
                {
                    // Add to cart list
                    CartDBController cartController = new CartDBController(mContext);

                    if (cartController.isAlreadyAddedToCart(getCategoryWithProduit.getId()))
                    {
                        AppUtility.showToast(mContext, getString(R.string.already_in_cart));
                    }
                    else
                    {
                        quantityCounter = Integer.valueOf(tvProductQuantity.getText().toString());

                        cartController.insertCartItem(getCategoryWithProduit.getId(), getCategoryWithProduit.getNameProduct(),
                                getCategoryWithProduit.getImage(),getCategoryWithProduit.getPrix(), quantityCounter);
                        AppUtility.showToast(mContext, getString(R.string.added_to_cart));


                    }
                    cartController.close();

                }

              //  Toast.makeText(DetailsProduitActivity.this, "Ajout reussi", Toast.LENGTH_SHORT).show();
            }
        });

        btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                /*quantityCounter = Integer.valueOf(tvProductQuantity.getText().toString());

                price = produit.getPrix() * quantityCounter;*/

                AppPreference.getInstance(mContext).setString(PrefKey.PAYMENT_TOTAL_PRICE, String.valueOf(price));

                ShowDialog();
            }
        });




        btnQuantityPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantityCounter++;
                tvProductQuantity.setText(String.valueOf(quantityCounter));
            }
        });

        btnQuantityMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantityCounter > 1) {
                    quantityCounter--;
                    tvProductQuantity.setText(String.valueOf(quantityCounter));
                }
            }
        });
    }




    private void ShowDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        // alertDialog.setTitle("    ------ Choix paiement ------");


        //  alertDialog.setMessage("Selectionnez un de ces methodes de paiement!");
        LayoutInflater inflater = this.getLayoutInflater();
        View addLayout = inflater.inflate(R.layout.dialog_paiement,null);

        RelativeLayout btnMensualite = addLayout.findViewById(R.id.CarteCredit);
        RelativeLayout btnEcolage = addLayout.findViewById(R.id.Natcom);

        alertDialog.setView(addLayout);

        btnMensualite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(getApplicationContext(),DetailsCreditCardActivity.class));
            }
        });

        btnEcolage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MobilePaiementActivity.class));
            }
        });


        alertDialog.show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                // Respond to the action bar's Up/Home button
                finish();
                return true;
        /*    case R.id.miShare:
                shareInfo();
                return true;*/
        }
        return super.onOptionsItemSelected(item);
    }
}
