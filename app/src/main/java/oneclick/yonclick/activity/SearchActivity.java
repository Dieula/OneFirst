package oneclick.yonclick.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

import oneclick.yonclick.Adapter.ProductListAdapter;
import oneclick.yonclick.Model.Categorie;
import oneclick.yonclick.Model.Product;
import oneclick.yonclick.R;
import oneclick.yonclick.Uils.ActivityUtils;
import oneclick.yonclick.Uils.AppUtility;
import oneclick.yonclick.Uils.ListTypeShow;
import oneclick.yonclick.dataa.constant.AppConstants;
import oneclick.yonclick.listener.EndlessRecyclerViewScrollListener;
import oneclick.yonclick.listener.OnItemClickListener;

public class SearchActivity extends BaseActivity {

    // initialize variables
    private Context mContext;
    private Activity mActivity;

    private RecyclerView rvProductList;
    private ArrayList<Product> productList;
    private ArrayList<Categorie> categoryList;
    private String[] categoryArray;
    private ProductListAdapter mProductListAdapter;

    protected LayoutManagerType mCurrentLayoutManagerType;
    protected RecyclerView.LayoutManager mLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private LinearLayoutManager linearLayoutManager;
    private static final int COLUMN_SPAN_COUNT = 2;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    // initialize View
    private Toolbar mToolbar;
    private SearchView mSearchView;
    private ImageView ivListTypeIcon;
    private ToggleButton btnToggleExpand;
    private RelativeLayout lytSearchAttribute;
    private LinearLayout lytSearchHeader, lytSortingData, noDataView, categoryChooser;
    private EditText edtMinPrice, edtMaxPrice;
    private TextView tvSortingName, selectedCat;
    private ProgressBar loadMoreView;

    // listener
    private EndlessRecyclerViewScrollListener scrollListener;

    // initialize search key
    private int pageNumber = AppConstants.INITIAL_PAGE_NUMBER;
    private String searchKey = AppConstants.EMPTY_STRING;
    private String order = AppConstants.KEY_ASC;
    private String orderBy = AppConstants.KEY_TITLE;
    private String categoryId = AppConstants.EMPTY_STRING;

    private boolean loading = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        initVariable();
        initView();

        loadData();

        initListener();
    }

    private void initVariable() {
        mContext = getApplicationContext();
        mActivity = SearchActivity.this;

        productList = new ArrayList<>();
        categoryList = new ArrayList<>();
    }

    private void initView() {
        setContentView(R.layout.activity_search);

    // from base class
    initLoader();

    rvProductList = (RecyclerView) findViewById(R.id.rvProductList);
    lytSearchAttribute = (RelativeLayout) findViewById(R.id.lytSearchAttribute);
    lytSearchHeader = (LinearLayout) findViewById(R.id.lytSearchHeader);
    lytSortingData = (LinearLayout) findViewById(R.id.lytSortingData);
    categoryChooser = (LinearLayout) findViewById(R.id.categoryChooser);
    noDataView = (LinearLayout) findViewById(R.id.noDataView);
    mSearchView = (SearchView) findViewById(R.id.searchView);
        mSearchView.setIconified(false);

    edtMinPrice = (EditText) findViewById(R.id.edtMinPrice);
    edtMaxPrice = (EditText) findViewById(R.id.edtMaxPrice);
    tvSortingName = (TextView) findViewById(R.id.tvSortingName);
    selectedCat = (TextView) findViewById(R.id.selectedCat);
    loadMoreView = (ProgressBar) findViewById(R.id.loadMore);



}

    private void loadData()

    {

        Intent intent = this.getIntent();
        searchKey = intent.getStringExtra(AppConstants.SEARCH_KEY);

        mSearchView.setQuery(searchKey, false);

        loadSearchProducts(false, false);
    }


    private void initListener() {
        mProductListAdapter.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemListener(View view, int position) {
                // invoke product details activity by product id
                ActivityUtils.getInstance().invokeProductDetails(mActivity, productList.get(position).getId());
            }
        });

    }

    private void loadSearchProducts(final boolean loadMore, boolean withCategory) {

        if (!loadMore) {
            showLoader();
        } else {
            loadMoreView.setVisibility(View.VISIBLE);
        }

        //set value
        searchKey = mSearchView.getQuery().toString();
        String minPrice = edtMinPrice.getText().toString();
        String maxPrice = edtMaxPrice.getText().toString();

        RequestSearchProducts searchProduct;
        if (withCategory) {
            searchProduct = new RequestSearchProducts(mActivity, pageNumber, searchKey, categoryId, minPrice, maxPrice, order, orderBy);
        } else {
            searchProduct = new RequestSearchProducts(mActivity, pageNumber, searchKey, minPrice, maxPrice, order, orderBy);
        }

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
                if (categoryId.isEmpty()) {
                    loadSearchProducts(true, false);
                } else {
                    loadSearchProducts(true, true);
                }
            }
        }
    }
    }
