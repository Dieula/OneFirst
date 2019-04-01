package oneclick.yonclick.Fragment;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import oneclick.yonclick.BaseUrl.ApiServiceGetRequest.ApiService;
import oneclick.yonclick.BaseUrl.ApiServiceGetRequest.RetroClient;
import oneclick.yonclick.Model.Model.ClassWithFrai;
import oneclick.yonclick.Model.ModelList.DegreList;
import oneclick.yonclick.R;
import oneclick.yonclick.activity.activity.DetailsCreditCardActivity;
import oneclick.yonclick.activity.activity.MobilePaiementActivity;
import retrofit2.Call;
import retrofit2.Callback;


/**
 * A simple {@link Fragment} subclass.
 */
public class EcolageChoiceFragment extends Fragment  implements AdapterView.OnItemSelectedListener {


    EditText IdEtudiant,edNom,cardNumberEditText;
    Spinner SpiClasse,SpiVersement;
    Button btnPayer;
    TextView etVersement;
    View view;
    private ProgressDialog pDialog;

    Spinner spinner;
    String URL="http://45.76.247.112/api/degre";
    // ArrayList<String> ClasseName;
    List<ClassWithFrai> classWithFrais;

    int id_cat;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_choice_ecolage, container, false);



        SpiClasse = (Spinner) view.findViewById(R.id.SpiClasse);
        loadSpinnerData();
        SpiClasse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String Classe=   SpiClasse.getItemAtPosition(SpiClasse.getSelectedItemPosition()).toString();
                Toast.makeText(getActivity(),Classe,Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });


        IdEtudiant = (EditText) view.findViewById(R.id.IdEtudiant);
        edNom = (EditText) view.findViewById(R.id.edNom);
        etVersement = (TextView) view.findViewById(R.id.etVersement);


        btnPayer = view.findViewById(R.id.btnPayer);
        SpiClasse.setOnItemSelectedListener(this);

       getPriceClasse();


        // Spinner Drop down elements
        List<String> Classe = new ArrayList<String>();
        Classe.add("Classe");
        Classe.add("4eme AF");
        Classe.add("5eme AF");
        Classe.add("6eme AF");
        Classe.add("7eme AF");
        Classe.add("8eme AF");
        Classe.add("9eme AF");
        Classe.add("3eme secondaire");
        Classe.add("Seconde");
        Classe.add("Rheto");
        Classe.add("Philo");

        // Creating adapter for the spinner
        ArrayAdapter<String> ClasseAdater = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Classe);

        // Drop down layout style - list view with radio button
        ClasseAdater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        SpiClasse.setAdapter(ClasseAdater);


        btnPayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IdEtudiant.getText().toString().equals("") && edNom.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Veuillez verifier vos champs", Toast.LENGTH_SHORT).show();
                } else {


                    ShowDialog();
                }
            }
        });


        return view;

    }

    private void getPriceClasse() {

        //Creating an object of our api interface
        ApiService api = RetroClient.getApiService();

        /**
         * Calling JSON
         */
        Call<DegreList> call = api.getDegreJSON();


        /**
         * Enqueue Callback will be call when get response...
         */

        call.enqueue(new Callback<DegreList>() {
            @Override
            public void onResponse(Call<DegreList> call, retrofit2.Response<DegreList> response) {
                //Dismiss Dialog
              //  pDialog.dismiss();

                if (response.isSuccessful()) {


                    List<String> ClasseName=new ArrayList<>();
                  //  List<DegreList> classe = response.body().getData();
                    // String Classe = classe.get(Integer.parseInt("Classe"));;
                   // ClasseName.add(String.valueOf(classe));

                    ClasseName.add(String.valueOf(ClasseName));

                    Toast.makeText(getActivity(), "Good" + response, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DegreList> call, Throwable t) {
             //   pDialog.dismiss();
                Toast.makeText(getActivity(), "Badddd" + t, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadSpinnerData() {

       /* //Creating an object of our api interface
        ApiService api = RetroClient.getApiService();

        *//**
         * Calling JSON
         *//*
        Call<DegreList> call = api.getDegreJSON();


        *//**
         * Enqueue Callback will be call when get response...
         *//*

        call.enqueue(new Callback<DegreList>() {
            @Override
            public void onResponse(Call<DegreList> call, retrofit2.Response<DegreList> response) {
                //Dismiss Dialog
                pDialog.dismiss();

                if (response.isSuccessful()) {



                List<DegreList> classe = response.body().getData();
                    DegreList degreList = response.body();

                    Degre Cat = new Degre();

                    for (Degre categorie : degreList.getData())
                    {

                        if (categorie.getId() == id_cat) {
                            Cat = categorie;
                            break;
                        }

                    }

                    classWithFrais = new ArrayList<>();
                    classWithFrais = Cat.ClassWithFrai();
                    rvProductList = (RecyclerView) findViewById(R.id.rvProductList);
                    mProductListAdapter = new AdapterListCategorie(getApplicationContext(), getCategoryWithProduits,listTypeShow);

                    LinearLayoutManager secondManager = new LinearLayoutManager
                            (getApplication(), LinearLayoutManager.VERTICAL, false);
                    rvProductList.setLayoutManager(secondManager);

                    rvProductList.setItemAnimator(new DefaultItemAnimator());
                    rvProductList.setAdapter(mProductListAdapter);

                    List<String> ClasseName=new ArrayList<>();
                    List<DegreList> classe = response.body().getData();

                    String bed = String.valueOf(classe.get(Integer.parseInt("Classe")));
                 // String Classe = classe.get(Integer.parseInt("Classe"));;
                    ClasseName.add(bed);


                    Toast.makeText(getActivity(), "Good" + response, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DegreList> call, Throwable t) {
                pDialog.dismiss();
                Toast.makeText(getActivity(), "Badddd" + t, Toast.LENGTH_SHORT).show();
            }
        });
*/
    }
        /*RequestQueue requestQueue= Volley.newRequestQueue(getActivity());

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject=new JSONObject(response);
                    if(jsonObject.getInt("success")==1){
                        JSONArray jsonArray=jsonObject.getJSONArray("data");
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject1=jsonArray.getJSONObject(i);
                            String country=jsonObject1.getString("Country");
                            ClasseName.add(country);
                        }
                    }
                    spinner.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, CountryName));
                }catch (JSONException e){e.printStackTrace();}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);*/



    private boolean valide() {
        boolean valid = true;
        String identifiant = IdEtudiant.getText().toString();
        String nom = edNom.getText().toString();
        String versement = etVersement.getText().toString();

        //Verify the name
        if (nom.isEmpty() || nom.length() < 3){

            edNom.setError("at least 3 characters");
            valid = false;
        }
        else
        {
            edNom.setError(null);
        }

        //Verify the id of the student
        if (identifiant.isEmpty() || identifiant.length() < 3){

            IdEtudiant.setError("at least 3 characters");
            valid = false;
        }
        else
        {
            IdEtudiant.setError(null);
        }

        //Verify the name
        if (versement.isEmpty() || versement.length() < 3){

            etVersement.setError("at least 3 characters");
            valid = false;
        }
        else
        {
            etVersement.setError(null);
        }

        return valid;
    }


    private void ShowDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
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
                startActivity(new Intent(getActivity(),DetailsCreditCardActivity.class));
            }
        });

        btnEcolage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),MobilePaiementActivity.class));
            }
        });


        alertDialog.show();
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
