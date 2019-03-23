package oneclick.yonclick.Fragment;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.ParseException;
import oneclick.yonclick.Adapter.BrandAdapter;
import oneclick.yonclick.Adapter.CategorieAdapter;
import oneclick.yonclick.Adapter.MagasinsAdapter;
import oneclick.yonclick.Adapter.ProduitAdapter;
import oneclick.yonclick.ApiService.ApiService;
import oneclick.yonclick.Detail.DetailsProduitActivity;
import oneclick.yonclick.Helper.HttpParams;
import oneclick.yonclick.Model.Category;
import oneclick.yonclick.Model.Plat;
import oneclick.yonclick.ModelList.BrandList;
import oneclick.yonclick.Model.Categorie;
import oneclick.yonclick.Model.Brand;
import oneclick.yonclick.Model.Magasin;
import oneclick.yonclick.Model.Product;
import oneclick.yonclick.ModelList.CategorieList;
import oneclick.yonclick.ModelList.MagasinsList;
import oneclick.yonclick.ModelList.ProduitList;
import oneclick.yonclick.R;
import oneclick.yonclick.BaseUrl.RetroClient;
import oneclick.yonclick.Uils.ActivityUtils;
import oneclick.yonclick.Uils.AppUtility;
import oneclick.yonclick.activity.LIstMarqueActivity;
import oneclick.yonclick.activity.ListCategorieActivity;
import oneclick.yonclick.dataa.constant.AppConstants;
import oneclick.yonclick.dataa.preference.AppPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static oneclick.yonclick.InterfaceAPI.RestApi.BASE_URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class MagasinsFragment extends Fragment {

    String mProductName;
    String mProductID;
    String mProductPrice;
    String mProductImageUrl;

    private ArrayList<Category> categories;
    private ProgressDialog pDialog;
    private RecyclerView recyclerView;
    private CategorieAdapter eAdapter;



    JSONArray serviceJsonResults;
    SharedPreferences sharedPreferences ;
   /*
    private ArrayList<Categorie> categories;
    private ProgressDialog pDialog;
    private RecyclerView recyclerView;
    private CategorieAdapter eAdapter;
   */

    //Store
    private ArrayList<Magasin> magasins;
    private RecyclerView mMagasinRecyclerView;
    private MagasinsAdapter eMagasinAdapter;

    //Brand
    private ArrayList<Brand> brands;
    private RecyclerView BrandRecyclerView;
    private BrandAdapter eBrandAdapter;


    private TextView tvCateorie, tvNouveau, tvRecentListAll, tvListAllNouveau,tvProduitListAll, tvStoreAllNouveau,
            tvCartCounter, tvBrand, textView,tvProduit;
    private ImageView imgToolbarCart, imgNotification, ivSearchIcon;

    private EditText edtSearchProduct;
    //2

    RelativeLayout popularParent,lytCategoryList,lytBrandList,lytProduitList,lytProduitRecent,lytNMagasinList;

    private ArrayList<Product> productsList;
    private ProgressDialog dialog;
    private RecyclerView mRecyclerview;
    private ProduitAdapter mAdapter;

    SharedPreferences.Editor editor;

    LinearLayout loadingView, noDataView;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_magasins, container, false);


        sharedPreferences = getActivity().getSharedPreferences("Produit", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();




     //Method inside
        GetAllCategorie();
        GetAllProduct();
        GetAllStore();
        GetAllBrand();
        GetAllNewProduct();
        initListener();
        initView();


        // See all listener
        tvListAllNouveau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.getInstance().invokeProducts(getActivity(), getString(R.string.nouveau),
                        AppConstants.TYPE_FEATURED, AppConstants.NO_CATEGORY);
            }
        });


        tvRecentListAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.getInstance().invokeProducts(getActivity(), getString(R.string.meilleure), AppConstants.TYPE_RECENT, AppConstants.NO_CATEGORY);
            }
        });

        // See all listener
        tvProduitListAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.getInstance().invokeProducts(getActivity(), getString(R.string.produit),
                        AppConstants.TYPE_FEATURED, AppConstants.NO_CATEGORY);
            }
        });






        // search icon at home action listener
        ivSearchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtSearchProduct.getText().toString().isEmpty()) {
                    AppUtility.showToast(getActivity(), getString(R.string.type_something));
                } else {
                    ActivityUtils.getInstance().invokeSearchActivity(getActivity(), edtSearchProduct.getText().toString());
                }
            }
        });


        edtSearchProduct.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    if (edtSearchProduct.getText().toString().isEmpty()) {
                        AppUtility.showToast(getActivity(), getString(R.string.type_something));
                    } else {
                        ActivityUtils.getInstance().invokeSearchActivity(getActivity(), edtSearchProduct.getText().toString());
                    }
                    return true;
                }
                return false;
            }
        });


        return v;


    }

    private void initView() {

        AppUtility.noInternetWarning(v.findViewById(R.id.loadingView), getActivity());
        if (!AppUtility.isNetworkAvailable(getActivity())) {
            showEmptyView();
        }



        // cart counter
        imgToolbarCart = (ImageView) v.findViewById(R.id.imgToolbarCart);
        imgNotification = (ImageView) v.findViewById(R.id.imgNotification);
        ivSearchIcon = (ImageView) v.findViewById(R.id.ivSearchIcon);
        tvCartCounter = (TextView) v.findViewById(R.id.tvCartCounter);
        edtSearchProduct = (EditText) v.findViewById(R.id.edtSearchProduct);

        //Categorie
        lytCategoryList = (RelativeLayout) v.findViewById(R.id.lytCategoryList);
        tvCateorie = (TextView) lytCategoryList.findViewById(R.id.tvListTitle);
        popularParent = (RelativeLayout) lytCategoryList.findViewById(R.id.parentPanel);

        //Brand
        lytBrandList = (RelativeLayout) v.findViewById(R.id.lytBrandList);
        tvBrand = (TextView) lytBrandList.findViewById(R.id.tvListTitle);
        popularParent = (RelativeLayout) lytBrandList.findViewById(R.id.parentPanel);



        //Nouveaute
        lytProduitList = (RelativeLayout) v.findViewById(R.id.lytProduitList);
        tvNouveau = (TextView) lytProduitList.findViewById(R.id.tvListTitle);
        tvListAllNouveau = (TextView) lytProduitList.findViewById(R.id.tvSeeALL);
        popularParent = (RelativeLayout) lytProduitList.findViewById(R.id.parentPanel);


        //Product
        lytProduitRecent = (RelativeLayout) v.findViewById(R.id.lytNouveauList);
        tvProduit = (TextView) lytProduitRecent.findViewById(R.id.tvListTitle);
        tvProduitListAll = (TextView) lytProduitRecent.findViewById(R.id.tvSeeALL);
        popularParent = (RelativeLayout) lytProduitRecent.findViewById(R.id.parentPanel);

        //Magasins
        lytNMagasinList = (RelativeLayout) v.findViewById(R.id.lytNMagasinList);
        textView = (TextView) lytNMagasinList.findViewById(R.id.tvListTitle);
        tvRecentListAll = (TextView) lytNMagasinList.findViewById(R.id.tvSeeALL);
        popularParent = (RelativeLayout) lytNMagasinList.findViewById(R.id.parentPanel);


        // search icon at home action listener
        ivSearchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtSearchProduct.getText().toString().isEmpty()) {
                    AppUtility.showToast(getActivity(), getString(R.string.type_something));
                } else {
                    ActivityUtils.getInstance().invokeSearchActivity(getActivity(), edtSearchProduct.getText().toString());
                }
            }
        });


        edtSearchProduct.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    if (edtSearchProduct.getText().toString().isEmpty()) {
                        AppUtility.showToast(getActivity(), getString(R.string.type_something));
                    } else {
                        ActivityUtils.getInstance().invokeSearchActivity(getActivity(), edtSearchProduct.getText().toString());
                    }
                    return true;
                }
                return false;
            }
        });


        //Tcheck the internet
        loadingView = (LinearLayout) v.findViewById(R.id.loadingView);
        noDataView = (LinearLayout) v.findViewById(R.id.noDataView);

    }

    private void initListener() {

    }

    private void GetAllNewProduct() {

        //Nouveaute View
        final ApiService prod = RetroClient.getApiService();

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
                    final List<Product> productsList = response.body().getEmployee();

                    mRecyclerview = (RecyclerView) lytProduitList.findViewById(R.id.homeRecyclerView);
                    tvNouveau.setText("Nouveautés (" + productsList.size() + ")");
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

    }

    private void GetAllBrand() {

        //Brand View
        //Creating an object of our api interface
        ApiService brand = RetroClient.getApiService();

        /**
         * Calling JSON
         */
        Call<BrandList> brandListCall = brand.getMyBrandJSON();

        /**
         * Enqueue Callback will be call when get response...
         */

        brandListCall.enqueue(new Callback<BrandList>() {
            @Override
            public void onResponse(Call<BrandList> call, Response<BrandList> response) {

                if (response.isSuccessful()) {
                    /**
                     * Got Successfully
                     */
                    final List<Brand> categories = response.body().getData();
                    BrandRecyclerView = (RecyclerView) lytBrandList.findViewById(R.id.homeRecyclerView);
                    eBrandAdapter = new BrandAdapter(getActivity(), categories);

                    tvBrand.setText("Marque");

                    LinearLayoutManager secondManager = new LinearLayoutManager
                            (getActivity(), LinearLayoutManager.HORIZONTAL, false);
                    BrandRecyclerView.setLayoutManager(secondManager);

                    BrandRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    BrandRecyclerView.setAdapter(eBrandAdapter);

                    Toast.makeText(getActivity(), "Good", Toast.LENGTH_SHORT).show();


                    eBrandAdapter.setOnItemClickListener(new BrandAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View itemView, int position) {

                            Brand product = categories.get(position);
                            Intent i = new Intent(getActivity(), LIstMarqueActivity.class);

                            AppPreference appPreference =
                                    AppPreference.getInstance(getActivity());

                            appPreference.setInteger("BrandID",product.getId());

                            startActivity(i);

                        }
                    });


                }
            }

            @Override
            public void onFailure(Call<BrandList> call, Throwable t) {
            }
        });


    }

    private void GetAllStore() {
        //Get all store
        ApiService store = RetroClient.getApiService();

        /**
         * Calling JSON
         */
        Call<MagasinsList> magasin = store.getMyStoreJSON();

        /**
         * Enqueue Callback will be call when get response...
         */

        magasin.enqueue(new Callback<MagasinsList>() {
            @Override
            public void onResponse(Call<MagasinsList> call, retrofit2.Response<MagasinsList> response) {

                //Dismiss Dialog
                //  pDialog.dismiss();

                if (response.isSuccessful()) {
                    /**
                     * Got Successfully
                     */
                    List<Magasin> MagasinsList = response.body().getData();
                    mMagasinRecyclerView = (RecyclerView) lytNMagasinList.findViewById(R.id.homeRecyclerView);
                    textView.setText("Magasins (" + MagasinsList.size() + ")");


                    eMagasinAdapter = new MagasinsAdapter(getActivity(), MagasinsList);


                    LinearLayoutManager secondManager = new LinearLayoutManager
                            (getActivity(), LinearLayoutManager.HORIZONTAL, false);
                    mMagasinRecyclerView.setLayoutManager(secondManager);

                    mMagasinRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    mMagasinRecyclerView.setAdapter(eMagasinAdapter);

                    Toast.makeText(getActivity(), "Good"+MagasinsList.size(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MagasinsList> call, Throwable t) {
                Toast.makeText(getActivity(), "Bad", Toast.LENGTH_SHORT).show();
                ///  pDialog.dismiss();
            }
        });

    }

    private void GetAllProduct() {

        //Product View
        ApiService product = RetroClient.getApiService();

        /**
         * Calling JSON
         */
        Call<ProduitList> dMyJSON = product.getProduit();

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
                    mRecyclerview = (RecyclerView) lytProduitRecent.findViewById(R.id.homeRecyclerView);
                    tvProduit.setText("Produits (" + productsList.size() + ")");
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



    }

    private void GetAllCategorie() {



        //Categorie View
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading Data.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        //Creating an object of our api interface
        ApiService api = RetroClient.getApiService();



        Call<CategorieList> call = api.getMyJSON();




        call.enqueue(new Callback<CategorieList>() {
            @Override
            public void onResponse(Call<CategorieList> call, retrofit2.Response<CategorieList> response)
            {

                //Dismiss Dialog
                pDialog.dismiss();
                // hideLoader();

                if (response.isSuccessful()) {



                    final List<Categorie> categories = response.body().getData();
                    recyclerView = (RecyclerView) lytCategoryList.findViewById(R.id.homeRecyclerView);
                    eAdapter = new CategorieAdapter(getActivity(), categories);



                    tvCateorie.setText("Categorie");

                    LinearLayoutManager secondManager = new LinearLayoutManager
                            (getActivity(), LinearLayoutManager.HORIZONTAL, false);
                    recyclerView.setLayoutManager(secondManager);

                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(eAdapter);

                    eAdapter.setOnItemClickListener(new CategorieAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View itemView, int position) {
                            Categorie product = categories.get(position);
                            Intent intent = new Intent(getActivity(), ListCategorieActivity.class);

                            AppPreference appPreference =
                            AppPreference.getInstance(getActivity());
                            appPreference.setInteger("categorieID",product.getId());

                            getActivity().startActivity(intent);
                        }
                    });


                }
            }

            @Override
            public void onFailure(Call<CategorieList> call, Throwable t) {
                pDialog.dismiss();
                //  hideLoader();
            }
        });

       /* String url = BASE_URL+"categoryproduct";

        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Content-Type", "application/json");
        client.addHeader("apiKey", "8484884774837498");

        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("DEBUG", response.toString());

                {

                    serviceJsonResults = null;
                    try {
                        serviceJsonResults = response.getJSONArray("data");
                        categories = Category.fromJSONArray(serviceJsonResults);

                        recyclerView = (RecyclerView) lytCategoryList.findViewById(R.id.homeRecyclerView);
                        eAdapter = new CategorieAdapter(getActivity(), categories);
                        // Attach the adapter to the recyclerview to populate items
                        recyclerView.setAdapter(eAdapter);
                        // swipeContainer.setRefreshing(false);

                        LinearLayoutManager secondManager = new LinearLayoutManager
                                (getActivity(), LinearLayoutManager.HORIZONTAL, false);
                        recyclerView.setLayoutManager(secondManager);

                        // Attach the layout manager to the recycler view
                        recyclerView.setLayoutManager(secondManager);
                        //recyclerView.setItemAnimator(new SlideInUpAnimator());

                        Toast.makeText(getActivity(), response + "Goodddrrrrrrr!", Toast.LENGTH_SHORT).show();
                        // idProgress.setVisibility(View.GONE);
                        eAdapter.setOnItemClickListener(new CategorieAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Category plat = categories.get(position);
                                Intent go = new Intent(getActivity(), DetailsProduitActivity.class);
                                go.putExtra("plat", plat);
                                startActivity(go);
                                Toast.makeText(getActivity(), categories + " was clicked!", Toast.LENGTH_SHORT).show();
                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
           //Toast.makeText(getContext(), "Baddddddd"+errorResponse.toString(), Toast.LENGTH_SHORT).show();
            }

        });
*/




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
