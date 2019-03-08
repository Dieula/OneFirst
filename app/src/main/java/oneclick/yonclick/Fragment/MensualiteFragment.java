package oneclick.yonclick.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import oneclick.yonclick.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MensualiteFragment extends Fragment {


    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         v = inflater.inflate(R.layout.fragment_mensualite, container, false);



      Spinner  SpiClasse = (Spinner) v.findViewById(R.id.SpiClasse);





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

}
