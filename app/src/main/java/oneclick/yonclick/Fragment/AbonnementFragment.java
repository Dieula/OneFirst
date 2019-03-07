package oneclick.yonclick.Fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import oneclick.yonclick.Adapter.AbonnementAdapter;
import oneclick.yonclick.ApiService.ApiService;
import oneclick.yonclick.Model.Abonnement;
import oneclick.yonclick.Model.CartItem;
import oneclick.yonclick.ModelList.AbonnementList;
import oneclick.yonclick.R;
import oneclick.yonclick.BaseUrl.RetroClient;
import oneclick.yonclick.dataa.sqlite.CartDBController;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * A simple {@link Fragment} subclass.
 */
public class AbonnementFragment extends Fragment {


   // List<Abonnement>

    private ArrayList<Abonnement> abonnements;
    private ProgressDialog pDialog;
    private RecyclerView recyclerView;
    private AbonnementAdapter eAdapter;

    View v;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_abonnement, container, false);


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
        Call<AbonnementList> call = api.getAbonnementJSON();

        /**
         * Enqueue Callback will be call when get response...
         */

        call.enqueue(new Callback<AbonnementList>() {
            @Override
            public void onResponse(Call<AbonnementList> call, retrofit2.Response<AbonnementList> response) {

                //Dismiss Dialog
                pDialog.dismiss();

                if (response.isSuccessful()) {
                    /**
                     * Got Successfully
                     */
                     abonnements = response.body().getServices_Abonnement();
                     recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
                     eAdapter = new AbonnementAdapter(getActivity(),abonnements);
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
            public void onFailure(Call<AbonnementList> call, Throwable t) {
                pDialog.dismiss();
                Toast.makeText(getActivity(), "Baddd", Toast.LENGTH_SHORT).show();
            }
        });

        return v;



    }
/*
    @Override
    public void onResume() {
        super.onResume();

        loadCartCounter();

    }


    private void loadCartCounter() {
        try {
            CartDBController cartController = new CartDBController(getActivity());
            cartController.open();
            ArrayList<CartItem> cartList = cartController.getAllCartData();
            cartController.close();

           *//* if (cartList.isEmpty()) {
                tvCartCounter.setVisibility(View.GONE);
            } else {
                tvCartCounter.setVisibility(View.VISIBLE);
                tvCartCounter.setText(String.valueOf(cartList.size()));
            }*//*
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
