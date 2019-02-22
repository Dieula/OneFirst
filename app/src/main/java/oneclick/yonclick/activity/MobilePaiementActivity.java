package oneclick.yonclick.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import oneclick.yonclick.R;

public class MobilePaiementActivity extends AppCompatActivity {

    EditText tvPayer,tvPin;
    Button Iv_Payer;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_paiement);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Display the Up button home
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowleft);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView button = (TextView) findViewById(R.id.IdEtudiant);
        Iv_Payer = (Button) findViewById(R.id.Iv_Payer);
        tvPayer = (EditText) findViewById(R.id.tvTelephone);
      //  tvPin = (EditText) findViewById(R.id.tvPin);
        progressDialog = new ProgressDialog(MobilePaiementActivity.this);

     Iv_Payer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Transaction Reussie", Toast.LENGTH_SHORT).show();
            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Natcom");

    }

    public void OnLogButton(View v) {

        String number = tvPayer.getText().toString();

        if (number.startsWith("40") || number.startsWith("41") || number.startsWith("42") || number.startsWith("32") || number.startsWith("33")) {
            //set the progress dialog when the user click in the button
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Payment loading...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(false);
            progressDialog.show();

            //    Snackbar.make(drawer, "Transaction reussie", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Transaction reussie", Toast.LENGTH_SHORT).show();
            //notify the user after register
            // NotifiUser(tvPayer.getText().toString());

        } else {
            Toast.makeText(this, "Please enter a valid Natcom number", Toast.LENGTH_SHORT).show();
        }
    }
}
