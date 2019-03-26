package oneclick.yonclick.Detail;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import oneclick.yonclick.Model.Brand;
import oneclick.yonclick.Model.Categorie;
import oneclick.yonclick.Model.GetCategoryWithProduit;
import oneclick.yonclick.Model.GetMarqueWithProduit;
import oneclick.yonclick.Model.Magasin;
import oneclick.yonclick.Model.Product;
import oneclick.yonclick.ModelList.BrandList;
import oneclick.yonclick.ModelList.CategorieList;
import oneclick.yonclick.ModelList.ProduitList;
import oneclick.yonclick.R;
import oneclick.yonclick.Uils.ActivityUtils;
import oneclick.yonclick.Uils.AppUtility;
import oneclick.yonclick.activity.BaseActivity;
import oneclick.yonclick.activity.CartListActivity;
import oneclick.yonclick.activity.DetailsCreditCardActivity;
import oneclick.yonclick.activity.LargeImageViewActivity;
import oneclick.yonclick.activity.MainActivity;
import oneclick.yonclick.activity.MobilePaiementActivity;
import oneclick.yonclick.dataa.constant.AppConstants;
import oneclick.yonclick.dataa.preference.AppPreference;
import oneclick.yonclick.dataa.sqlite.CartDBController;
import oneclick.yonclick.dataa.sqlite.DatabaseHelper;

import static oneclick.yonclick.InterfaceAPI.RestApi.BASE_URL_Image;

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
    ImageView imageView,imgToolbarCart,imgProfil;
    TextView tvCartCounter;


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;





    private String title;
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

        //Variable
        TextView NameProduit = (TextView) findViewById(R.id.tvProductName);
        TextView DescProduit = (TextView) findViewById(R.id.tvDescription);
        TextView tvTextDescription = (TextView) findViewById(R.id.tvTextDescription);
        TextView tvNomMagasin = (TextView) findViewById(R.id.tvNomMagasin);
        TextView tvSalesPrice = (TextView) findViewById(R.id.tvSalesPrice);
        imageView = (ImageView) findViewById(R.id.vpImageSlider);

        final Button btnAddToCart = findViewById(R.id.btnAddToCart);
        Button btnBuyNow = findViewById(R.id.btnBuyNow);


        // cart counter

     //  tvCartCounter = (TextView) findViewById(R.id.tvCartCounter);

        // toolbar cart action listener
        imgToolbarCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 startActivity(new Intent(getApplicationContext(),CartListActivity.class));

                /**
                 * if you don't want to show notification then disable
                 * disable previous line and use line given bellow
                 */
               // ActivityUtils.getInstance().invokeActivity(mActivity, CartListActivity.class, false);

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
      //  System.out.println("PROD INFO : "+produit.getName_product());



       /* if(getIntent().getSerializableExtra("prod") != null){
            produit = (Product) getIntent().getSerializableExtra("prod");
        }else{
            System.out.println("PROD : NO DETAILS");
        }
        System.out.println("PROD INFO : "+produit.getName_product());
*/
        //DatabaseHelper db = new DatabaseHelper(this);


        if (getCategoryWithProduit != null){
            NameProduit.setText(getCategoryWithProduit.getNameProduct());
            DescProduit.setText(getCategoryWithProduit.getDetailsProduct());
            tvTextDescription.setText(getCategoryWithProduit.getDetailsProduct());
           // tvNomMagasin.setText(getCategoryWithProduit.getNameProduct());
            tvSalesPrice.setText(getCategoryWithProduit.getPrix());
            imgCategorie = getCategoryWithProduit.getImage();

            Glide.with(getApplicationContext()).load(getCategoryWithProduit.getImage()).into(imageView);
        }
        else if (getMarqueWithProduit != null)
        {
            NameProduit.setText(getMarqueWithProduit.getNameProduct());
            DescProduit.setText(getMarqueWithProduit.getDetailsProduct());
            tvTextDescription.setText(getMarqueWithProduit.getDetailsProduct());
            tvSalesPrice.setText(getMarqueWithProduit.getPrix());
            imgMarque = BASE_URL_Image+getMarqueWithProduit.getImage();
            Glide.with(getApplicationContext()).
                    load(getMarqueWithProduit.getImage())
                    .into(imageView);
        }
        else if(produit != null)
        {

            NameProduit.setText(produit.getName_product());
            DescProduit.setText(produit.getDetails_product());
            tvTextDescription.setText(produit.getDetails_product());
            tvSalesPrice.setText(produit.getPrix());
            ImgProduit = produit.getImage();

            Glide.with(getApplicationContext()).load(produit.getImage()).into(imageView);

        }

        imageView.setOnClickListener(new View.OnClickListener() {
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
                    // Add to cart list
                    CartDBController cartController = new CartDBController(mContext);

                    if (cartController.isAlreadyAddedToCart(produit.getBrand_id()))
                    {
                        AppUtility.showToast(mContext, getString(R.string.already_in_cart));
                    }
                    else
                    {
                       // quantityCounter = Integer.valueOf(tvProductQuantity.getText().toString());

                        cartController.insertCartItem(produit.getBrand_id(), produit.getPrix(),produit.getName_product(), produit.getImage(), quantityCounter);
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
                        // quantityCounter = Integer.valueOf(tvProductQuantity.getText().toString());

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
                        // quantityCounter = Integer.valueOf(tvProductQuantity.getText().toString());

                        cartController.insertCartItem(getCategoryWithProduit.getId(), getCategoryWithProduit.getNameProduct(),
                                getCategoryWithProduit.getImage(),getCategoryWithProduit.getPrix(), quantityCounter);
                        //btnAddToCart.setText(getString(R.string.added_to_cart));
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
                ShowDialog();
            }
        });
    }

    private void passRateData(Integer id, String produit_id) {

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
