package oneclick.yonclick.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import oneclick.yonclick.Authentification.LoginActivity;
import oneclick.yonclick.Authentification.StaticPhone;
import oneclick.yonclick.Authentification.StaticUser;
import oneclick.yonclick.InterfaceAPI.ApiEndPointInterface;
import oneclick.yonclick.InterfaceAPI.RestApi;
import oneclick.yonclick.ModelAuth.RequestLogin;
import oneclick.yonclick.ModelAuth.ResponseLogin;
import oneclick.yonclick.Natcom.RequestComptepayem;
import oneclick.yonclick.Natcom.ResponseComptepayem;
import oneclick.yonclick.R;
import retrofit2.Call;
import retrofit2.Response;

import static cz.msebera.android.httpclient.client.methods.RequestBuilder.post;
import static oneclick.yonclick.Authentification.StaticUser.register;

public class MobilePaiementActivity extends AppCompatActivity {

    EditText tvPayer, tvPin;
    Button Iv_Payer;


    private Call<ResponseComptepayem> call;
    private ApiEndPointInterface apiComptePayem;

    private int status;
    private Response<ResponseComptepayem> reponse;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_paiement);

        progressDialog = new ProgressDialog(MobilePaiementActivity.this);
        sharedPreferences = getSharedPreferences("Phone", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();


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
                if (tvPayer.getText().toString() != null) {
                    if (validate()) {
                        VerifyNumber();
                    }

                } else

                {
                    Toast.makeText(MobilePaiementActivity.this, "Verifier vos champs!", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void VerifyNumber() {

        String number = tvPayer.getText().toString();

        if (number.startsWith("40") || number.startsWith("41") || number.startsWith("42") || number.startsWith("32") || number.startsWith("33") || number.startsWith("22")) {


            PostData();


        } else {
            Toast.makeText(this, "Please enter a valid Natcom number", Toast.LENGTH_SHORT).show();
        }
    }

    private void PostData() {

        MobileAsyncTask mobileAsyncTask = new MobileAsyncTask();
        mobileAsyncTask.execute();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));

        Toast.makeText(this, "Transaction reussie", Toast.LENGTH_SHORT).show();
        //notify the user after register
        NotifiUser(tvPayer.getText().toString());

        ShowDialog();
    }


        private void ShowDialog() {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
             alertDialog.setTitle(getString(R.string.pinText));

            LayoutInflater inflater = this.getLayoutInflater();
            View addLayout = inflater.inflate(R.layout.activity_pin_natcom,null);

            RelativeLayout btnMensualite = addLayout.findViewById(R.id.rvTextPin);
            EditText editText = addLayout.findViewById(R.id.edPin);


            alertDialog.setView(addLayout);

            btnMensualite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getApplicationContext(),DetailsCreditCardActivity.class));
                }
            });


            alertDialog.show();
        }

    private void NotifiUser(String s) {
    }

    private boolean validate() {
        boolean valid = true;
        String mName = tvPayer.getText().toString();


        //Verify the name
        if (mName.isEmpty() || mName.length() < 8) {

            tvPayer.setError("at least 8 characters");
            valid = false;





        } else {
            tvPayer.setError(null);
        }

        return valid;

    }

    public class MobileAsyncTask extends AsyncTask {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Object o) {
            if (status == 200) {
                Toast.makeText(getApplicationContext(), "Good", Toast.LENGTH_SHORT).show();
                StaticPhone.setPhoneNatcom(reponse.body().getComptePayem());

            } else {
                Toast.makeText(getApplicationContext(), "Not good", Toast.LENGTH_SHORT).show();

            }
            super.onPostExecute(o);
            super.onPostExecute(o);
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                apiComptePayem = RestApi.getApi();
                RequestComptepayem requestComptepayem = new RequestComptepayem();
                requestComptepayem.setNumero_tel(tvPayer.getText().toString());
                requestComptepayem.getPin();
                requestComptepayem.getNif();
                requestComptepayem.getStatus();
                requestComptepayem.getUser_id();

                //save the user info in prference
                editor.putString("Phone", tvPayer.getText().toString());
                editor.putString("pin",requestComptepayem.getPin());
                editor.putString("nif",requestComptepayem.getNif());
                editor.putString("status",requestComptepayem.getStatus());
                editor.putString("User_id",requestComptepayem.getUser_id());
                editor.apply();

                call = apiComptePayem.Comptepayem(requestComptepayem);
                reponse = call.execute();
                status = reponse.code();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
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