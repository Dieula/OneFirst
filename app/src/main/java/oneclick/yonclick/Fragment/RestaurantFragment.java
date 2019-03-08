package oneclick.yonclick.Fragment;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.marcoscg.dialogsheet.DialogSheet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import oneclick.yonclick.Adapter.CategorieAdapter;
import oneclick.yonclick.Adapter.RestaurantAdapter;
import oneclick.yonclick.ApiService.ApiService;
import oneclick.yonclick.Authentification.StaticUser;
import oneclick.yonclick.BaseUrl.RetroClient;
import oneclick.yonclick.Helper.HttpParams;
import oneclick.yonclick.Model.Categorie;
import oneclick.yonclick.Model.Restaurant;
import oneclick.yonclick.ModelList.ProduitList;
import oneclick.yonclick.ModelList.RestaurantList;
import oneclick.yonclick.R;
import oneclick.yonclick.activity.MainActivity;
import oneclick.yonclick.activity.PlatDetailsActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantFragment extends Fragment {


    private ProgressDialog pDialog;
    JSONArray serviceJsonResults;
    StaticUser staticUser;

    private ArrayList<Restaurant> restaurants;
    private RestaurantAdapter eAdapter;
    private RecyclerView recyclerView;


    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        //if the network is available
        if (isNetworkAvailable() == true) {
          laodCAtegoriePlat();
        } else {

            new AlertDialog.Builder(getActivity())
                .setTitle("Infos")
                .setMessage("Verifier votre internet.")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setIcon(R.drawable.ic_notifications_black_24dp)
                .show();


            new DialogSheet(getContext())
                    .setTitle("Infos")
                    .setMessage("Verifier votre internet")
                    .setCancelable(false)
                    .setPositiveButton(android.R.string.ok, new DialogSheet.OnPositiveClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Your action
                        }
                    })
                    .setNegativeButton(android.R.string.cancel, new DialogSheet.OnNegativeClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Your action
                        }
                    })
                    .setBackgroundColor(Color.BLACK) // Your custom background color
                    .setButtonsColorRes(R.color.colorPrimary)  // Default color is accent
                    .show();
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_restaurant, container, false);

        return view;
    }

    private void laodCAtegoriePlat() {

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
        Call<RestaurantList> call = api.getRestoJSON();


        /**
         * Enqueue Callback will be call when get response...
         */

        call.enqueue(new Callback<RestaurantList>() {
            @Override
            public void onResponse(Call<RestaurantList> call, Response<RestaurantList> response) {
                //Dismiss Dialog
                pDialog.dismiss();

                if (response.isSuccessful()) {

                    restaurants = response.body().getRestaurants();
                    recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
                    eAdapter = new RestaurantAdapter(getActivity(), restaurants);
                    RecyclerView.LayoutManager eLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(eLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(eAdapter);

                    Toast.makeText(getActivity(), "Good" + response, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RestaurantList> call, Throwable t) {
                pDialog.dismiss();
                Toast.makeText(getActivity(), "Badddd" + t, Toast.LENGTH_SHORT).show();
            }
        });

    }


    //check the network

    private boolean isNetworkAvailable() {

        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}