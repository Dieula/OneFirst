package oneclick.yonclick.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import oneclick.yonclick.Model.Product;
import oneclick.yonclick.R;
import oneclick.yonclick.Uils.ActivityUtils;
import oneclick.yonclick.Uils.AppUtility;
import oneclick.yonclick.dataa.constant.AppConstants;
import oneclick.yonclick.dataa.sqlite.CartDBController;
import oneclick.yonclick.dataa.sqlite.DatabaseHelper;

public class DetailsProduitActivity extends AppCompatActivity {

    private int quantityCounter = 1;
    Context mContext;
    private Product product = null;
    private Activity mActivity;
    Product produit;
    String viewType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_produit);


        //get the current intent
        Intent intent = getIntent();

        //get the attached extras from the intent
       //we should use the same key as we used to attach the data.
       // viewType = intent.getStringExtra("id");

        DatabaseHelper db = new DatabaseHelper(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Display the Up button home
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowleft);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //produit = (Product) getIntent().getSerializableExtra("id");

        ImageView ImgProduit = (ImageView) findViewById(R.id.vpImageSlider);
        ImgProduit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LargeImageViewActivity.class);
                intent.putExtra(AppConstants.KEY_IMAGE_URL,"id");
                startActivity(intent);
            }
        });


      /*
        TextView NameProduit = (TextView) findViewById(R.id.tvProductName);
        NameProduit.setText(produit.getName_product().toString());

        TextView DescProduit = (TextView) findViewById(R.id.tvDescription);
        DescProduit.setText(produit.getDetails_product());

        TextView tvTextDescription = (TextView) findViewById(R.id.tvTextDescription);
        tvTextDescription.setText(produit.getDetails_product().toString());

        TextView tvSalesPrice = (TextView) findViewById(R.id.tvSalesPrice);
        tvSalesPrice.setText(produit.getPrix().toString());*/


        //Call a differents contenu,not the same in details page
        /*sharedPreferences = getSharedPreferences("PreferencesTAG", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        serv = (Services) getIntent().getSerializableExtra("services");*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Paiement mobile");


        final Button btnAddToCart = findViewById(R.id.btnAddToCart);
        Button btnBuyNow = findViewById(R.id.btnBuyNow);

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // startActivity(new Intent(getApplicationContext(),ProductListActivity.class));
                // Add to cart list
                // Add to cart list

                        CartDBController cartController = new CartDBController(mActivity);
                       // cartController.createDB();

                        if (cartController.isAlreadyAddedToCart(product.getId())) {
                            AppUtility.showToast(mContext, getString(R.string.already_in_cart));
                        }
                         else
                            {
                           // quantityCounter = Integer.valueOf(tvProductQuantity.getText().toString());

                            String price;
                            //price = Integer.valueOf(product.getPrix().toString());
                            if (product!=null) {
                                price = product.getPrix();
                            } else {
                                price = product.getPrix();
                            }


                            cartController.insertCartItem(product.getId(), product.getName_product(), product.getImage(),product.getImage(), quantityCounter);
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
