package oneclick.yonclick.Fragment;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
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


        if (isNetworkAvailable() == true) {
            //laodCAtegoriePlat();
        } else {
            /*
            new AlertDialog.Builder(MainActivity.this)
                .setTitle("Infos")
                .setMessage("Verifier votre internet.")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setIcon(R.drawable.ic_action_bag)
                .show();
              */

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

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading Data.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        //recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);


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


        return view;
    }

    private boolean isNetworkAvailable() {

        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

/*
    public static String getIdDev(Activity a) {

        TelephonyManager tm = (TelephonyManager) a.getSystemService(Context.TELEPHONY_SERVICE);
        // get IMEI
        @SuppressLint("MissingPermission")
        String imei = tm.getDeviceId();
        return imei;


    }*/

}
/*


        String androidId = Settings.Secure.getString(
                a.getContentResolver(), Settings.Secure.ANDROID_ID);
        return  androidId;*/

/*
    private void laodCAtegoriePlat() {
    //       StaticUser.setRegister(reponse.body().getLogin());

        String url = "http://45.76.247.112/api/oodcategory/get";
        // String url = "http://cristalhotelhaiti.com/rebo/api/plat.php";
    //    AsyncHttpClient client = new AsyncHttpClient();

        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("apiKey", "8484884774837498");
        client.addHeader("Content-Type", "application/json");
        client.addHeader("accessToken", "64e72de411826abbc5557fb1b71d451b");

        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                serviceJsonResults = null;
                try{

                    JSONObject jsonData= response.getJSONObject("data");

                    serviceJsonResults = jsonData.getJSONArray("Category Food");

                    for(int i = 0; i < serviceJsonResults.length(); i++) {
                        JSONObject childrenData = serviceJsonResults.getJSONObject(i);
                       // serviceJsonResults = response.getJSONArray("data");
                        restaurants = Restaurant.fromJSONArray(serviceJsonResults);
                        eAdapter = new RestaurantAdapter(getActivity(), restaurants);

                        // Attach the adapter to the recyclerview to populate items
                        recyclerView.setAdapter(eAdapter);
                        RecyclerView.LayoutManager eLayoutManager = new LinearLayoutManager(getActivity());
                        // Attach the layout manager to the recycler view
                        recyclerView.setLayoutManager(eLayoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        Toast.makeText(getActivity(), "Goood"+ response.toString(), Toast.LENGTH_SHORT).show();
                    }

                  *//*  aServices.addAll(Services.fromJSONArray(serviceJsonResults));
                    serviceAdapter.notifyDataSetChanged();
                    progress.setVisibility(View.GONE);
                    Log.d("DEBUG", aServices.toString());*//*
                }
                catch (JSONException e)
                {

                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Baddd"+ e.toString(), Toast.LENGTH_SHORT).show();
                }

           *//*
                //Toast.makeText(MainActivity.this, ""+response.toString(), Toast.LENGTH_SHORT).show();
                JSONArray arrayCat = response;
                restaurants = Restaurant.fromJSONArray(arrayCat);
                eAdapter = new RestaurantAdapter(getActivity(), restaurants);

                // Attach the adapter to the recyclerview to populate items
                recyclerView.setAdapter(eAdapter);
                RecyclerView.LayoutManager eLayoutManager = new LinearLayoutManager(getActivity());
                // Attach the layout manager to the recycler view
                recyclerView.setLayoutManager(eLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());*//*

                      *//*  //  idProgress.setVisibility(View.GONE);
                        eAdapter.setOnItemClickListener(new RestaurantAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Restaurant categorie = restaurants.get(position);
                                Intent go = new Intent(getActivity(), PlatDetailsActivity.class);
                                go.putExtra("categorie", categorie);
                                startActivity(go);
                                //Toast.makeText(getApplicationContext(), plat.getTitle() + " was clicked!", Toast.LENGTH_SHORT).show();
                            }
                        });*//*
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(getActivity(), "Baddd" + errorResponse.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

                    private boolean isNetworkAvailable() {

                        ConnectivityManager connectivityManager
                                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

                        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
                    }

                    public void fetchTimelineAsync(int page) {
                        // Send the network request to fetch the updated data
                        // `client` here is an instance of Android Async HTTP
                        // getHomeTimeline is an example endpoint.
                        laodCAtegoriePlat();

                    }

                }*/

     /* */



