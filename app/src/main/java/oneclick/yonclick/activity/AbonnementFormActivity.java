package oneclick.yonclick.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.util.ArrayList;
import java.util.List;

import oneclick.yonclick.Detail.DetailsProduitActivity;
import oneclick.yonclick.R;

public class AbonnementFormActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText NomCarte, edNoCarte, edNin, cardNumberEditText, TotalPayer;
    Spinner SpiMOis, SpiType, SpiPlan;


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abonnement_form);



        sharedPreferences = getSharedPreferences("PreferencesTAG", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        progressDialog = new ProgressDialog(AbonnementFormActivity.this);

        NomCarte = (EditText) findViewById(R.id.NomCarte);
        edNoCarte = (EditText) findViewById(R.id.edNoCarte);
        edNin = (EditText) findViewById(R.id.edNin);
        cardNumberEditText = (EditText) findViewById(R.id.cardNumberEditText);
        TotalPayer = (EditText) findViewById(R.id.TotalPayer);


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

        SpiMOis = (Spinner) findViewById(R.id.SpiMOis);

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

                    Toast.makeText(getApplicationContext(), "Mois", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

    public void View() {


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
