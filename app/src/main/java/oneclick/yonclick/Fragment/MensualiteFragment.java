package oneclick.yonclick.Fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import oneclick.yonclick.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MensualiteFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    EditText IdEtudiant,edNom,NomMois;
    Spinner SpiClasse,SpiVersement;
    Button btnPayer;
    TextView etVersement,tvTotalPrice;

    private ProgressDialog pDialog;


    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         v = inflater.inflate(R.layout.fragment_mensualite, container, false);



        IdEtudiant = (EditText) v.findViewById(R.id.IdEtudiant);
        edNom = (EditText) v.findViewById(R.id.edNom);
        etVersement = (TextView) v.findViewById(R.id.etVersement);
        tvTotalPrice = (TextView) v.findViewById(R.id.tvTotalPrice);
        NomMois = (EditText) v.findViewById(R.id.NomMois);


        btnPayer = v.findViewById(R.id.btnValider);
        Spinner  SpiClasse = (Spinner) v.findViewById(R.id.SpiClasse);

        SpiClasse.setOnItemSelectedListener(this);


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



        return  v;


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
