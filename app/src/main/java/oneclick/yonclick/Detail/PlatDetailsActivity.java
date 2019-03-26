package oneclick.yonclick.Detail;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import oneclick.yonclick.Model.CartList;
import oneclick.yonclick.Model.GetCategoryWithProduit;
import oneclick.yonclick.Model.GetMarqueWithProduit;
import oneclick.yonclick.Model.Plat;
import oneclick.yonclick.Model.Product;
import oneclick.yonclick.R;
import oneclick.yonclick.Uils.AppUtility;
import oneclick.yonclick.activity.BaseActivity;
import oneclick.yonclick.activity.CartListActivity;
import oneclick.yonclick.activity.DetailsCreditCardActivity;
import oneclick.yonclick.activity.MainActivity;
import oneclick.yonclick.activity.MobilePaiementActivity;
import oneclick.yonclick.dataa.sqlite.CartDBController;
import oneclick.yonclick.dataa.sqlite.DbManager;

public class PlatDetailsActivity extends BaseActivity {

    private int quantityCounter = 1;
    List<CartList> dbList;
    DbManager helper;

    Context mContext;
    ImageView imageView;
    Button btnAddToCart,btnBuyNow;

    Plat plat;
    String ImgProduit;
    private String title;
    ImageView imgToolbarCart,imgProfil;
    TextView tvCartCounter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plat_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initToolbar();
        enableBackButton();
        setToolbarTitle(title);

        mContext = getApplicationContext();



        //Variable
        TextView NameProduit = (TextView) findViewById(R.id.tvProductName);
        TextView DescProduit = (TextView) findViewById(R.id.tvDescription);
        TextView tvTextDescription = (TextView) findViewById(R.id.tvTextDescription);
        TextView tvSalesPrice = (TextView) findViewById(R.id.tvPrice);
        imageView = (ImageView) findViewById(R.id.vpImageSlider);

        btnAddToCart = findViewById(R.id.btnAddToCart);
        btnBuyNow = findViewById(R.id.btnBuyNow);



        // cart counter
        imgToolbarCart = (ImageView) findViewById(R.id.cartList);
        imgProfil = (ImageView) findViewById(R.id.imgProfil);

        //tvCartCounter = (TextView) findViewById(R.id.tvCartCounter);

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

        if (getIntent().getSerializableExtra("plat") != null)
        {
            plat = (Plat) getIntent().getSerializableExtra("plat");

        }
        else
            {
            System.out.println("PROD : NO DETAILS");
        }
        System.out.println("PROD INFO : "+plat.getNom_Plats());




        NameProduit.setText(plat.getNom_Plats());
        DescProduit.setText(plat.getDetails_Plats());
        tvTextDescription.setText(plat.getDetails_Plats());
        tvSalesPrice.setText(plat.getPrix());
        ImgProduit = plat.getImage();

        Glide.with(getApplicationContext()).load(plat.getImage()).into(imageView);


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowleft);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Call a differents contenu,not the same in details page
        /*sharedPreferences = getSharedPreferences("PreferencesTAG", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        serv = (Services) getIntent().getSerializableExtra("services");*/
        getSupportActionBar().setTitle("Details Plat");


        Button btnAddToCart = findViewById(R.id.btnAddToCart);
        Button btnBuyNow = findViewById(R.id.btnBuyNow);

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertCartList();
                //Toast.makeText(PlatDetailsActivity.this, "Ajout reussi", Toast.LENGTH_SHORT).show();
            }
        });

        btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog();
            }
        });


    }



    private void InsertCartList() {
        if ( plat!= null)
        {
            // Add to cart list
            CartDBController cartController = new CartDBController(mContext);

            if (cartController.isAlreadyAddedToCart(plat.getID_Plats()))
            {
                AppUtility.showToast(mContext, getString(R.string.already_in_cart));
            }
            else
            {
                // quantityCounter = Integer.valueOf(tvProductQuantity.getText().toString());

                cartController.insertCartItem(plat.getID_Plats(), plat.getPrix(),plat.getNom_Plats(), plat.getImage(), quantityCounter);
                // btnAddToCart.setText(getString(R.string.added_to_cart));
                AppUtility.showToast(mContext, getString(R.string.added_to_cart));


            }
            cartController.close();

        }

    }

    private void ShowDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

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
               /*case R.id.miShare:
                shareInfo();
                return true;*/
        }
        return super.onOptionsItemSelected(item);
    }
}
