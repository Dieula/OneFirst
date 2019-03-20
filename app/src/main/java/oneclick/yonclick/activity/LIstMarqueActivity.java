package oneclick.yonclick.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import oneclick.yonclick.Adapter.AdapterListCategorie;
import oneclick.yonclick.Adapter.BrandListCategorieAdapter;
import oneclick.yonclick.ApiService.ApiService;
import oneclick.yonclick.BaseUrl.RetroClient;
import oneclick.yonclick.Model.Brand;
import oneclick.yonclick.Model.Categorie;
import oneclick.yonclick.Model.GetCategoryWithProduit;
import oneclick.yonclick.Model.GetMarqueWithProduit;
import oneclick.yonclick.ModelList.BrandList;
import oneclick.yonclick.ModelList.CategorieList;
import oneclick.yonclick.R;
import oneclick.yonclick.Uils.ActivityUtils;
import oneclick.yonclick.Uils.ListTypeShow;
import oneclick.yonclick.dataa.constant.AppConstants;
import oneclick.yonclick.dataa.preference.AppPreference;
import oneclick.yonclick.listener.OnItemClickListener;
import retrofit2.Call;
import retrofit2.Callback;

public class LIstMarqueActivity extends BaseActivity {

    // initialize variables
    private Activity mActivity;
    private Context mContext;
    ListTypeShow listTypeShow;
  //  List<GetCategoryWithProduit> productsList;

    List<GetMarqueWithProduit> getMarqueWithProduits;

    private RecyclerView rvProductList;
    private ArrayList<GetMarqueWithProduit> productList;
    private BrandListCategorieAdapter mProductListAdapter;


    private Toolbar mToolbar;
    protected LayoutManagerType mCurrentLayoutManagerType;
    protected RecyclerView.LayoutManager mLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private LinearLayoutManager linearLayoutManager;
    private static final int COLUMN_SPAN_COUNT = 2;
    private ProgressBar loadMoreView;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    // initialize View
    private ImageView viewToggle;

    private String title;
    private int type, categoryId;

    private int pageNumber = AppConstants.INITIAL_PAGE_NUMBER;
    private boolean loading = true;


   int brand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        initVariable();
        initView();
        loadProductList();
        //initListener();


        AppPreference appPreference = AppPreference.getInstance(getApplicationContext());
        brand = appPreference.getInteger("BrandID");

    }

    private void initVariable() {
        mActivity = LIstMarqueActivity.this;
        mContext = mActivity.getApplicationContext();
        productList = new ArrayList<>();

        Intent intent = getIntent();
        title = intent.getStringExtra(AppConstants.PAGE_TITLE);
        type = intent.getIntExtra(AppConstants.PAGE_TYPE, AppConstants.VALUE_ZERO);
        categoryId = intent.getIntExtra(AppConstants.CATEGORY_ID, AppConstants.VALUE_ZERO);

    }

    private void initView() {
        setContentView(R.layout.activity_list_marque);

        initToolbar();
        enableBackButton();
        setToolbarTitle(title);
        initLoader();


        rvProductList = (RecyclerView) findViewById(R.id.rvProductList);
        viewToggle = (ImageView) findViewById(R.id.viewToggle);
        loadMoreView = (ProgressBar) findViewById(R.id.loadMore);


    }

    private void loadProductList() {
        loadProductByCategory(AppConstants.INITIAL_PAGE_NUMBER);
    }

    private void initListener() {
        mProductListAdapter.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemListener(View view, int position) {
                // invoke product details activity by product id
                ActivityUtils.getInstance().invokeProductDetails(mActivity,
                        productList.get(position).getId());
            }
        });


        viewToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //toggleView();
            }
        });


        rvProductList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    onScrolledMore();
                }
            }
        });
    }

    // Generate list and grid layout manager
    public void setRecyclerViewLayoutManager(RecyclerView mRecyclerView, LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        switch (layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                gridLayoutManager = new GridLayoutManager(mActivity, COLUMN_SPAN_COUNT);
                mLayoutManager = gridLayoutManager;
                mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
                linearLayoutManager = new LinearLayoutManager(mActivity);
                mLayoutManager = linearLayoutManager;
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

                break;
            default:
                linearLayoutManager = new LinearLayoutManager(mActivity);
                mLayoutManager = linearLayoutManager;
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
    }

    private void loadProductByCategory(int pageNumber) {
        //Creating an object of our api interface
        final ApiService CategorieBY = RetroClient.getApiService();

        /**
         * Calling JSON
         */
        final Call<BrandList> prodMyJSON = CategorieBY.getMyBrandJSON();

        /**
         * Enqueue Callback will be call when get response...
         */

        prodMyJSON.enqueue(new Callback<BrandList>() {
            @Override
            public void onResponse(Call<BrandList> call, retrofit2.Response<BrandList> response) {

                //Dismiss Dialog
                //  pDialog.dismiss();

                if (response.isSuccessful()) {


                    BrandList brandList = response.body();

                    Brand Cat = new Brand();

                    for (Brand categorie : brandList.getData())
                    {

                        if (categorie.getId() == brand) {
                            Cat = categorie;
                            break;
                        }

                    }



                    getMarqueWithProduits = new ArrayList<>();

                    getMarqueWithProduits = Cat.getGetMarqueWithProduits();


                    rvProductList = (RecyclerView) findViewById(R.id.rvProductList);


                    mProductListAdapter = new BrandListCategorieAdapter(getApplicationContext(), getMarqueWithProduits,listTypeShow);

                    LinearLayoutManager secondManager = new LinearLayoutManager
                            (getApplication(), LinearLayoutManager.VERTICAL, false);
                    rvProductList.setLayoutManager(secondManager);

                    rvProductList.setItemAnimator(new DefaultItemAnimator());
                    rvProductList.setAdapter(mProductListAdapter);



                    Toast.makeText(getApplication(), "Good", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BrandList> call, Throwable t) {
                Toast.makeText(getApplication(), "Bad", Toast.LENGTH_SHORT).show();
                ///  pDialog.dismiss();
            }
        });

    }

    private void onScrolledMore() {
        int visibleItemCount, totalItemCount, pastVisiblesItems;
        if (mCurrentLayoutManagerType == LayoutManagerType.GRID_LAYOUT_MANAGER) {
            visibleItemCount = gridLayoutManager.getChildCount();
            totalItemCount = gridLayoutManager.getItemCount();
            pastVisiblesItems = gridLayoutManager.findFirstVisibleItemPosition();
        } else {
            visibleItemCount = linearLayoutManager.getChildCount();
            totalItemCount = linearLayoutManager.getItemCount();
            pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();
        }
        if (loading) {
            if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                loading = false;
                loadMoreView.setVisibility(View.VISIBLE);
                pageNumber = pageNumber + 1;
                loadProductByCategory(pageNumber);
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void toggleView() {
        if (mCurrentLayoutManagerType == LayoutManagerType.LINEAR_LAYOUT_MANAGER) {
            viewToggle.setImageResource(R.mipmap.ic_list);
            setRecyclerViewLayoutManager(rvProductList, LayoutManagerType.GRID_LAYOUT_MANAGER);

            mProductListAdapter = new BrandListCategorieAdapter(getApplicationContext(), getMarqueWithProduits,ListTypeShow.GRID);

            rvProductList.setAdapter(mProductListAdapter);

        } else {
            viewToggle.setImageResource(R.mipmap.ic_grid);
            setRecyclerViewLayoutManager(rvProductList,LayoutManagerType.LINEAR_LAYOUT_MANAGER);
            mProductListAdapter = new BrandListCategorieAdapter(getApplicationContext(), getMarqueWithProduits,ListTypeShow.LINEAR);


            rvProductList.setAdapter(mProductListAdapter);
        }
    }


}
