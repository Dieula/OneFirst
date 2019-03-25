package oneclick.yonclick.Fragment;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import oneclick.yonclick.R;
import oneclick.yonclick.activity.DetailsCreditCardActivity;
import oneclick.yonclick.activity.EcolageActivity;
import oneclick.yonclick.activity.MobilePaiementActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class EcolageChoiceFragment extends Fragment {


    Button btnPayer;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_choice_ecolage, container, false);




        btnPayer = view.findViewById(R.id.btnPayer);
        btnPayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog();
            }
        });
      //


        return view;
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



}
