package oneclick.yonclick.Fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import oneclick.yonclick.Adapter.CategorieAdapter;
import oneclick.yonclick.Adapter.ProduitAdapter;
import oneclick.yonclick.ApiService.ApiService;
import oneclick.yonclick.Model.Categorie;
import oneclick.yonclick.Model.Product;
import oneclick.yonclick.ModelList.CategorieList;
import oneclick.yonclick.ModelList.ProduitList;
import oneclick.yonclick.R;
import oneclick.yonclick.BaseUrl.RetroClient;
import oneclick.yonclick.Uils.ActivityUtils;
import oneclick.yonclick.Uils.AppUtility;
import oneclick.yonclick.activity.DetailsProduitActivity;
import oneclick.yonclick.dataa.constant.AppConstants;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * A simple {@link Fragment} subclass.
 */
public class MagasinsFragment extends Fragment {


    private ArrayList<Categorie> categories;
    private ProgressDialog pDialog;
    private RecyclerView recyclerView;
    private CategorieAdapter eAdapter;

    private TextView tvCateorie, tvNouveau, tvProduit, tvListAll,
            tvCartCounter, tvNotificationCounter, textView;
    private ImageView imgToolbarCart, imgNotification, ivSearchIcon;
    //2

    RelativeLayout popularParent;

    private ArrayList<Product> productsList;
    private ProgressDialog dialog;
    private RecyclerView mRecyclerview;
    private ProduitAdapter mAdapter;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    LinearLayout loadingView, noDataView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_magasins, container, false);

        sharedPreferences = getActivity().getSharedPreferences("Register", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();


        //Tcheck the internet
        loadingView = (LinearLayout) v.findViewById(R.id.loadingView);
        noDataView = (LinearLayout) v.findViewById(R.id.noDataView);

        AppUtility.noInternetWarning(v.findViewById(R.id.loadingView), getActivity());
        if (!AppUtility.isNetworkAvailable(getActivity())) {
            showEmptyView();
        }


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
        Call<CategorieList> call = api.getMyJSON();

        /**
         * Enqueue Callback will be call when get response...
         */

        call.enqueue(new Callback<CategorieList>() {
            @Override
            public void onResponse(Call<CategorieList> call, retrofit2.Response<CategorieList> response) {

                //Dismiss Dialog
                pDialog.dismiss();
                // hideLoader();

                if (response.isSuccessful()) {
                    /**
                     * Got Successfully
                     */
                    List<Categorie> categories = response.body().getEmployee();
                    RelativeLayout lytCategoryList = (RelativeLayout) v.findViewById(R.id.lytCategoryList);
                    recyclerView = (RecyclerView) lytCategoryList.findViewById(R.id.homeRecyclerView);
                    TextView tvSampleCategoryTitle = (TextView) lytCategoryList.findViewById(R.id.tvListTitle);
                    tvCateorie = (TextView) lytCategoryList.findViewById(R.id.tvSeeAll);
                    RelativeLayout sampleCatParent = (RelativeLayout) lytCategoryList.findViewById(R.id.parentPanel);
                    eAdapter = new CategorieAdapter(getActivity(), categories);


                    LinearLayoutManager secondManager = new LinearLayoutManager
                            (getActivity(), LinearLayoutManager.HORIZONTAL, false);
                    recyclerView.setLayoutManager(secondManager);

                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(eAdapter);
                }
            }

            @Override
            public void onFailure(Call<CategorieList> call, Throwable t) {
                pDialog.dismiss();
                //  hideLoader();
            }
        });


        ///


        //Creating an object of our api interface
        ApiService prod = RetroClient.getApiService();

        /**
         * Calling JSON
         */
        Call<ProduitList> prodMyJSON = prod.getProduit();

        /**
         * Enqueue Callback will be call when get response...
         */

        prodMyJSON.enqueue(new Callback<ProduitList>() {
            @Override
            public void onResponse(Call<ProduitList> call, retrofit2.Response<ProduitList> response) {

                //Dismiss Dialog
                //  pDialog.dismiss();

                if (response.isSuccessful()) {
                    /**
                     * Got Successfully
                     */
                    List<Product> productsList = response.body().getEmployee();
                    RelativeLayout lytProduitList = (RelativeLayout) v.findViewById(R.id.lytProduitList);
                    textView = (TextView) lytProduitList.findViewById(R.id.tvListTitle);
                    tvListAll = (TextView) lytProduitList.findViewById(R.id.tvSeeAll);
                    popularParent = (RelativeLayout) lytProduitList.findViewById(R.id.parentPanel);
                    mRecyclerview = (RecyclerView) lytProduitList.findViewById(R.id.homeRecyclerView);
                    mAdapter = new ProduitAdapter(getActivity(), productsList);

                  /*  // popular list ui
                    RelativeLayout lytProduitList = (RelativeLayout) v.findViewById(R.id.lytProduitList);
                    mRecyclerview = (RecyclerView) lytProduitList.findViewById(R.id.HomeRecyclerview);
                    TextView textView = (TextView) lytProduitList.findViewById(R.id.tvListTitle);
                    TextView tvPopularListAll = (TextView) lytProduitList.findViewById(R.id.tvSeeAll);
                    RelativeLayout popularParent = (RelativeLayout) lytProduitList.findViewById(R.id.parentPanel);
                    mAdapter = new ProduitAdapter(getActivity(), productsList);*/

                    LinearLayoutManager secondManager = new LinearLayoutManager
                            (getActivity(), LinearLayoutManager.HORIZONTAL, false);
                    mRecyclerview.setLayoutManager(secondManager);

                    mRecyclerview.setItemAnimator(new DefaultItemAnimator());
                    mRecyclerview.setAdapter(mAdapter);

                    Toast.makeText(getActivity(), "Good", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProduitList> call, Throwable t) {
                Toast.makeText(getActivity(), "Bad", Toast.LENGTH_SHORT).show();
                ///  pDialog.dismiss();
            }
        });

        //listener
    /*    tvListAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityUtils.getInstance().invokeProducts(getActivity(), getString(R.string.featured_items), AppConstants.TYPE_FEATURED, AppConstants.NO_CATEGORY);
            }
        });*/


        //Creating an object of our api interface
        ApiService nouveau = RetroClient.getApiService();

        /**
         * Calling JSON
         */
        Call<ProduitList> dMyJSON = prod.getProduit();

        /**
         * Enqueue Callback will be call when get response...
         */

        dMyJSON.enqueue(new Callback<ProduitList>() {
            @Override
            public void onResponse(Call<ProduitList> call, retrofit2.Response<ProduitList> response) {

                //Dismiss Dialog
                //  pDialog.dismiss();

                if (response.isSuccessful()) {
                    /**
                     * Got Successfully
                     */
                    List<Product> productsList = response.body().getEmployee();
                    RelativeLayout lytProduitList = (RelativeLayout) v.findViewById(R.id.lytNouveauList);
                    mRecyclerview = (RecyclerView) lytProduitList.findViewById(R.id.homeRecyclerView);
                    mAdapter = new ProduitAdapter(getActivity(), productsList);

                    LinearLayoutManager secondManager = new LinearLayoutManager
                            (getActivity(), LinearLayoutManager.HORIZONTAL, false);
                    mRecyclerview.setLayoutManager(secondManager);

                    mRecyclerview.setItemAnimator(new DefaultItemAnimator());
                    mRecyclerview.setAdapter(mAdapter);

                    Toast.makeText(getActivity(), "Good", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProduitList> call, Throwable t) {
                Toast.makeText(getActivity(), "Bad", Toast.LENGTH_SHORT).show();
                ///  pDialog.dismiss();
            }
        });


    /*// See all listener
        tvListAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.getInstance().invokeProducts(getActivity(), getString(R.string.featured_items),
                        AppConstants.TYPE_FEATURED, AppConstants.NO_CATEGORY);
            }
        });*/

       /* tvRecentListAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.getInstance().invokeProducts(mActivity, getString(R.string.recent_items), AppConstants.TYPE_RECENT, AppConstants.NO_CATEGORY);
            }
        });*/



        return v;


    }

    private void showEmptyView() {
        if (loadingView != null) {
            loadingView.setVisibility(View.GONE);
        }
        if (noDataView != null) {
            noDataView.setVisibility(View.VISIBLE);
        }
    }


    public void showLoader() {
        if (loadingView != null) {
            loadingView.setVisibility(View.VISIBLE);
        }

        if (noDataView != null) {
            noDataView.setVisibility(View.GONE);
        }
    }

    public void hideLoader() {
        if (loadingView != null) {
            loadingView.setVisibility(View.GONE);
        }
        if (noDataView != null) {
            noDataView.setVisibility(View.GONE);
        }
    }


}
