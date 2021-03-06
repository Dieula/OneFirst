package oneclick.yonclick.activity.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import oneclick.yonclick.Authentification.StaticPhone;
import oneclick.yonclick.BaseUrl.InterfaceAPIPost.ApiEndPointInterface;
import oneclick.yonclick.BaseUrl.InterfaceAPIPost.RestApi;
import oneclick.yonclick.Model.NatcomModel.RequestComptepayem;
import oneclick.yonclick.Model.NatcomModel.ResponseComptepayem;
import oneclick.yonclick.R;
import oneclick.yonclick.dataa.preference.AppPreference;
import retrofit2.Call;
import retrofit2.Response;

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



    String login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_paiement);

        progressDialog = new ProgressDialog(MobilePaiementActivity.this);


        sharedPreferences = getSharedPreferences("Phone", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        AppPreference appPreference = AppPreference.getInstance(getApplicationContext());
        login = appPreference.getString("user");






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


            ShowDialog();


        } else {
            Toast.makeText(this, "Please enter a valid Natcom number", Toast.LENGTH_SHORT).show();
        }
    }


    private void PostData() {

        MobileAsyncTask mobileAsyncTask = new MobileAsyncTask();
        mobileAsyncTask.execute();
        //notify the user after register
        NotifiUser(tvPayer.getText().toString());

    }



        private void ShowDialog() {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            // alertDialog.setTitle(getString(R.string.pinText));

            LayoutInflater inflater = this.getLayoutInflater();
            View addLayout = inflater.inflate(R.layout.activity_pin_natcom,null);

           // RelativeLayout btnMensualite = addLayout.findViewById(R.id.rvTextPin);
            tvPin = addLayout.findViewById(R.id.edPin);
           Button btnSubmit = addLayout.findViewById(R.id.btnSubmit);

            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ( tvPin.getText().toString().equals("") ) {

                        Toast.makeText(MobilePaiementActivity.this, "Verifier vos champs!", Toast.LENGTH_SHORT).show();
                    } else

                    {
                        PostData();

                    }
                }
            });


            alertDialog.setView(addLayout);

           /* alertDialog.setPositiveButton("OK",  new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {



                   // startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
            });*/
            alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(getApplicationContext(),MobilePaiementActivity.class));

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
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
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

                String nif = "6080";
                String statut = "1";

                RequestComptepayem requestComptepayem = new RequestComptepayem();
                requestComptepayem.setNumero_tel(tvPayer.getText().toString());
                requestComptepayem.setPin(Integer.valueOf(tvPin.getText().toString()));
                requestComptepayem.setStatus(statut);
                requestComptepayem.setNif(nif);
                requestComptepayem.setUser_id(Integer.valueOf(login));


                //save the user info in prference
                editor.putString("Phone", tvPayer.getText().toString());
                editor.putString("pin",tvPin.getText().toString());
                editor.putString("nif",nif);
                editor.putString("status",requestComptepayem.getStatus());
                editor.putString("User_id",login);
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