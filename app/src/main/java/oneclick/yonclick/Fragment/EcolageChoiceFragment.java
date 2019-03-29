package oneclick.yonclick.Fragment;


import android.app.AlertDialog;
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

import oneclick.yonclick.R;
import oneclick.yonclick.activity.DetailsCreditCardActivity;
import oneclick.yonclick.activity.EcolageActivity;
import oneclick.yonclick.activity.MobilePaiementActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class EcolageChoiceFragment extends Fragment  implements AdapterView.OnItemSelectedListener{


    EditText IdEtudiant,edNom,cardNumberEditText;
    Spinner SpiClasse,SpiVersement;
    Button btnPayer;
    TextView etVersement;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_choice_ecolage, container, false);




        IdEtudiant = (EditText) view.findViewById(R.id.IdEtudiant);
        edNom = (EditText) view.findViewById(R.id.edNom);
        etVersement = (TextView) view.findViewById(R.id.etVersement);

        SpiClasse = (Spinner) view.findViewById(R.id.SpiClasse);


        btnPayer = view.findViewById(R.id.btnPayer);




        SpiClasse.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) getActivity());


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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
