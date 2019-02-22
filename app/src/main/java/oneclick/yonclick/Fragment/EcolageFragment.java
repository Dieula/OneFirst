package oneclick.yonclick.Fragment;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import oneclick.yonclick.Adapter.AbonnementAdapter;
import oneclick.yonclick.Adapter.CategorieAdapter;
import oneclick.yonclick.Adapter.EcolageAdapter;
import oneclick.yonclick.ApiService.ApiService;
import oneclick.yonclick.BaseUrl.RetroClient;
import oneclick.yonclick.Model.Categorie;
import oneclick.yonclick.Model.Ecolage;
import oneclick.yonclick.ModelList.AbonnementList;
import oneclick.yonclick.ModelList.EcolageList;
import oneclick.yonclick.R;
import oneclick.yonclick.activity.EcolageActivity;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * A simple {@link Fragment} subclass.
 */
public class EcolageFragment extends Fragment {

     View view;

    private ArrayList<Ecolage> ecolages;
    private ProgressDialog pDialog;
    private RecyclerView recyclerView;
    private EcolageAdapter eAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_ecolage, container, false);

        Button btn = view.findViewById(R.id.btnBuyNow);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShowDialog();
            }
        });



        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading Data.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        //Creating an object of our api interface
        ApiService api = RetroClient.getApiService();

        /**
         * Calling JSON
         */
        Call<EcolageList> call = api.getEcolageJSON();

        /**
         * Enqueue Callback will be call when get response...
         */

        call.enqueue(new Callback<EcolageList>() {
            @Override
            public void onResponse(Call<EcolageList> call, retrofit2.Response<EcolageList> response) {

                //Dismiss Dialog
                pDialog.dismiss();

                if (response.isSuccessful()) {
                    /**
                     * Got Successfully
                     */
                    ecolages = response.body().getEcoles();
                    recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
                    eAdapter = new EcolageAdapter(getActivity(),ecolages);
                    RecyclerView.LayoutManager eLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(eLayoutManager);

                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(eAdapter);
                    recyclerView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getActivity(), "New Ativityyyyyyyyyy", Toast.LENGTH_SHORT).show();
                        }
                    });

                    Toast.makeText(getActivity(), "Good", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EcolageList> call, Throwable t) {
                pDialog.dismiss();
                Toast.makeText(getActivity(), "Baddd", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
        }

    private void ShowDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
     //   alertDialog.setTitle("    ------ Choix paiement ------");

       // alertDialog.setMessage("Selectionnez un de ces methodes de paiement!");
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View addLayout = inflater.inflate(R.layout.dialog_paiement,null);

        RelativeLayout btnMensualite = addLayout.findViewById(R.id.CarteCredit);
        RelativeLayout btnEcolage = addLayout.findViewById(R.id.Natcom);

        alertDialog.setView(addLayout);

        btnEcolage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),EcolageActivity.class));
            }
        });

        btnMensualite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),EcolageActivity.class));
            }
        });


        alertDialog.show();
    }


}
