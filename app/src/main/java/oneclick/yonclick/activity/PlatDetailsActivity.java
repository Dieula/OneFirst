package oneclick.yonclick.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import oneclick.yonclick.Model.CartList;
import oneclick.yonclick.R;
import oneclick.yonclick.dataa.sqlite.DbManager;

public class PlatDetailsActivity extends AppCompatActivity {

    String mParsedProductID,mParsedProductName;
    String mParsedProductImageUrl;
    String mParsedProductPrice;

    List<CartList> dbList;
    DbManager helper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plat_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Display the Up button home
       // getSupportActionBar().setIcon(R.drawable.arrowleft);
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
                Toast.makeText(PlatDetailsActivity.this, "Ajout reussi", Toast.LENGTH_SHORT).show();
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
        // insert into cart list
        dbList = new ArrayList<>();
        helper = DbManager.getInstance(PlatDetailsActivity.this);
        helper.insertIntoDB(mParsedProductID, mParsedProductName, mParsedProductPrice, mParsedProductImageUrl);

    }
    public void getCartList(){
        helper = DbManager.getInstance(PlatDetailsActivity.this);
        dbList= new ArrayList<CartList>();
        dbList = helper.getDataFromDB();
        Log.i("DemoCart", "DemoCartList: " + dbList.toString());
    }

    public void DeleteFromCartList(){
        dbList= new ArrayList<CartList>();
        helper = DbManager.getInstance(PlatDetailsActivity.this);
        helper.deleteARow(mParsedProductID);
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
