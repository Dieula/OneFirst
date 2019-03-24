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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import oneclick.yonclick.Model.Brand;
import oneclick.yonclick.Model.Categorie;
import oneclick.yonclick.Model.Product;
import oneclick.yonclick.ModelList.BrandList;
import oneclick.yonclick.ModelList.CategorieList;
import oneclick.yonclick.ModelList.ProduitList;
import oneclick.yonclick.R;
import oneclick.yonclick.Uils.AppUtility;
import oneclick.yonclick.activity.BaseActivity;
import oneclick.yonclick.activity.DetailsCreditCardActivity;
import oneclick.yonclick.activity.LargeImageViewActivity;
import oneclick.yonclick.activity.MainActivity;
import oneclick.yonclick.activity.MobilePaiementActivity;
import oneclick.yonclick.dataa.constant.AppConstants;
import oneclick.yonclick.dataa.preference.AppPreference;
import oneclick.yonclick.dataa.sqlite.CartDBController;
import oneclick.yonclick.dataa.sqlite.DatabaseHelper;

public class DetailsProduitActivity extends BaseActivity {

    private int quantityCounter = 1;
    Context mContext;
    private Activity mActivity;

    public Product produit;
    ProduitList productsList;
    String viewType;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    String NameProduit ,PRODUIT_ID, DescProduit,images, tvSalesPrice;
     int id_cat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_produit);


        if(getIntent().getSerializableExtra("prod") != null){
            produit = (Product) getIntent().getSerializableExtra("prod");
        }else{
            System.out.println("PROD : NO DETAILS");
        }
        System.out.println("PROD INFO : "+produit.getName_product());

        DatabaseHelper db = new DatabaseHelper(this);
        mContext = getApplicationContext();


       /* AppPreference appPreference = AppPreference.getInstance(getApplicationContext());
        id_cat= appPreference.getInteger("categorieID");
*/

       // ProduitList productsList = produit.getId();



        ImageView ImgProduit = (ImageView) findViewById(R.id.vpImageSlider);


        ImgProduit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), LargeImageViewActivity.class);
                intent.putExtra(AppConstants.KEY_IMAGE_URL,"id");
                startActivity(intent);
            }
        });

        TextView NameProduit = (TextView) findViewById(R.id.tvProductName);
         NameProduit.setText(produit.getName_product());

        TextView DescProduit = (TextView) findViewById(R.id.tvDescription);
        DescProduit.setText(produit.getDetails_product());

        TextView tvTextDescription = (TextView) findViewById(R.id.tvTextDescription);
        tvTextDescription.setText(produit.getDetails_product());

        TextView tvSalesPrice = (TextView) findViewById(R.id.tvSalesPrice);
        tvSalesPrice.setText(produit.getPrix());

        final Button btnAddToCart = findViewById(R.id.btnAddToCart);
        Button btnBuyNow = findViewById(R.id.btnBuyNow);

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // startActivity(new Intent(getApplicationContext(),ProductListActivity.class));
                // Add to cart list

                        CartDBController cartController = new CartDBController(mContext);
                       // cartController.createDB();

              //  Toast.makeText(mContext, "PR "+produit.getId().toString(), Toast.LENGTH_SHORT).show();
                    if (cartController.isAlreadyAddedToCart(produit.getBrand_id()))
                    {
                            AppUtility.showToast(mContext, getString(R.string.already_in_cart));
                    }
                         else
                             {
                           // quantityCounter = Integer.valueOf(tvProductQuantity.getText().toString());

                            String price;
                            //price = Integer.valueOf(produit.getPrix().toString());
                            if (produit!=null) {
                                price = produit.getPrix();
                            } else {
                                price = produit.getPrix();
                            }


                            cartController.insertCartItem(produit.getBrand_id(), produit.getName_product(),
                                    produit.getImage(),produit.getImage(), quantityCounter);
                            btnAddToCart.setText(getString(R.string.added_to_cart));
                            AppUtility.showToast(mContext, getString(R.string.added_to_cart));


                        }
                        cartController.close();

                Toast.makeText(DetailsProduitActivity.this, "Ajout reussi", Toast.LENGTH_SHORT).show();
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
