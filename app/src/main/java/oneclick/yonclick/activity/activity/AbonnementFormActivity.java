package oneclick.yonclick.activity.activity;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import oneclick.yonclick.Authentification.StaticAbonnement;
import oneclick.yonclick.activity.Detail.DetailsProduitActivity;
import oneclick.yonclick.BaseUrl.InterfaceAPIPost.ApiEndPointInterface;
import oneclick.yonclick.BaseUrl.InterfaceAPIPost.RestApi;
import oneclick.yonclick.Model.Model.Abonnement;
import oneclick.yonclick.R;
import oneclick.yonclick.Model.FormAbonnementEcolage.RequestAbonnement;
import oneclick.yonclick.Model.FormAbonnementEcolage.ResponseAbonnement;
import oneclick.yonclick.dataa.constant.AppConstants;
import retrofit2.Call;
import retrofit2.Response;

public class AbonnementFormActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText NomCarte, edNoCarte, edNin, cardNumberEditText, TotalPayer;
    Spinner SpiMOis, SpiType, SpiPlan;


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ProgressDialog progressDialog;

    Abonnement abonnement;


    private Call<ResponseAbonnement> call;
    private ApiEndPointInterface apiRegister;



    private int status;
    private Response<ResponseAbonnement> reponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abonnement_form);



        if (getIntent().getSerializableExtra(AppConstants.abonnement) != null)
        {
            abonnement = (Abonnement) getIntent().getSerializableExtra(AppConstants.abonnement);

        }

        else{
            System.out.println("PROD : NO DETAILS");
        }



        sharedPreferences = getSharedPreferences("PreferencesTAG", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        progressDialog = new ProgressDialog(AbonnementFormActivity.this);

        NomCarte = (EditText) findViewById(R.id.NomCarte);
        edNoCarte = (EditText) findViewById(R.id.edNoCarte);
        edNin = (EditText) findViewById(R.id.edNin);
        cardNumberEditText = (EditText) findViewById(R.id.cardNumberEditText);

       /* SpiMOis = (Spinner) findViewById(R.id.SpiMOis);

        if (SpiMOis.getSelectedItem().equals("") && (cardNumberEditText.getText().toString().equals("")))
        {
            TotalPayer.setVisibility(View.GONE);
        }
        else
        {


            TextView TotalPayer = (TextView) findViewById(R.id.TotalPayer);
            int s1 = Integer.parseInt(SpiMOis.getSelectedItem().toString());
            int s2 = Integer.parseInt(cardNumberEditText.getText().toString());
            int sum = s1 * s2 ;
            TotalPayer.setText(Integer.toString(sum));
            TotalPayer.setVisibility(View.VISIBLE);
        }


*/
        Spinner CompagnieName = findViewById(R.id.CompagnieName);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Display the Up button home
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowleft);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Nom plan");


        Button btnValider = findViewById(R.id.btnValider);
        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog();

            }
        });


        progressDialog = new ProgressDialog(getApplicationContext());
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Sauvegarde en cours...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);

        Button button = (Button) findViewById(R.id.btnValider);



        SpiMOis.setOnItemSelectedListener(this);
        // Spinner Drop down elements
        List<String> Classe = new ArrayList<String>();
        Classe.add("Nombre de mois");
        Classe.add("1");
        Classe.add("2");
        Classe.add("3");
        Classe.add("4");
        Classe.add("5");
        Classe.add("6");
        Classe.add("7");
        Classe.add("8");
        Classe.add("9");
        Classe.add("10");
        Classe.add("11");
        Classe.add("12");


        // Creating adapter for the spinner
        ArrayAdapter<String> ClasseAdater = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Classe);



        // Drop down layout style - list view with radio button
        ClasseAdater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        SpiMOis.setAdapter(ClasseAdater);




        CompagnieName.setOnItemSelectedListener(this);
        // Spinner Drop down elements
        List<String> Compagnie = new ArrayList<String>();
        Compagnie.add("Nom de la Compagnie");
        Compagnie.add("Hainet");
        Compagnie.add("Access Haiti");
        Compagnie.add("Dinepa");
        Compagnie.add("EDH");


        // Creating adapter for the spinner
        ArrayAdapter<String> CompagnieAdater = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Compagnie);



        // Drop down layout style - list view with radio button
        CompagnieAdater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        CompagnieName.setAdapter(CompagnieAdater);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (SpiMOis.getSelectedItem().toString() != "Mois") {
                    if (NomCarte.getText().toString().equals("") && edNin.getText().toString().equals("") && edNoCarte.getText().toString().equals("")
                            && cardNumberEditText.getText().toString().equals("")) {
                        Toast.makeText(getApplicationContext(), "Veuillez verifier vos champs", Toast.LENGTH_SHORT).show();
                    } else {


                    }
                } else {
                    Register();

                    Toast.makeText(getApplicationContext(), "Mois", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

    private void Register() {
        AbonnementAsyncTask registerAsyncTask = new AbonnementAsyncTask();
        registerAsyncTask.execute();
    }

    public void View() {


    }



    private void ShowDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        View addLayout = inflater.inflate(R.layout.dialog_paiement,null);

        RelativeLayout btnMensualite = addLayout.findViewById(R.id.CarteCredit);
        RelativeLayout btnEcolage = addLayout.findViewById(R.id.Natcom);

        alertDialog.setView(addLayout);

        btnEcolage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MobilePaiementActivity.class));
            }
        });

        btnMensualite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),DetailsProduitActivity.class));
            }
        });


        alertDialog.show();
    }

    public class AbonnementAsyncTask extends AsyncTask {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Object o) {

            if (status == 200) {

               StaticAbonnement.setAbonnement(reponse.body().getAbonnement());

               startActivity(new Intent(getApplicationContext(), MainActivity.class));

                Toast.makeText(getApplicationContext(), "Paiment ok", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(getApplicationContext(), "Verifiez vos informations", Toast.LENGTH_SHORT).show();

            }

            super.onPostExecute(o);
            super.onPostExecute(o);
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                apiRegister = RestApi.getApi();

                int Mois = 7;
                int compagnie = 1;
                int plan = 1;


                RequestAbonnement requestAbonnement = new RequestAbonnement();
                requestAbonnement.setNumero_account(edNoCarte.getText().toString());
                requestAbonnement.setTitulaire_account(NomCarte.getText().toString());
                requestAbonnement.setTitulaire_account(TotalPayer.getText().toString());
                requestAbonnement.setNombre_mois(Integer.valueOf(SpiMOis.getSelectedItem().toString()));
                requestAbonnement.setCompagnie_sub_id(compagnie);
                requestAbonnement.setPlan_id(plan);



                call = apiRegister.abonnment(requestAbonnement);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
