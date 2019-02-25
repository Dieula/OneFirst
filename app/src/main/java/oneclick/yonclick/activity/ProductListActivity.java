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
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import org.apache.http.params.HttpParams;

import java.util.ArrayList;

import oneclick.yonclick.Adapter.ProductListAdapter;
import oneclick.yonclick.Adapter.ProduitAdapter;
import oneclick.yonclick.Model.Product;
import oneclick.yonclick.R;
import oneclick.yonclick.Uils.ActivityUtils;
import oneclick.yonclick.Uils.AppUtility;
import oneclick.yonclick.Uils.ListTypeShow;
import oneclick.yonclick.dataa.constant.AppConstants;
import oneclick.yonclick.listener.OnItemClickListener;

public class ProductListActivity extends AppCompatActivity {

    // initialize variables
    private Activity mActivity;
    private Context mContext;

    private RecyclerView rvProductList;
    private ArrayList<Product> productList;
    private ProductListAdapter mProductListAdapter;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initVariable();
        initView();
        loadProductList();
        initListener();

    }


    private void initVariable() {
        mActivity = ProductListActivity.this;
        mContext = mActivity.getApplicationContext();

        productList = new ArrayList<>();

        Intent intent = getIntent();
        title = intent.getStringExtra(AppConstants.PAGE_TITLE);
        type = intent.getIntExtra(AppConstants.PAGE_TYPE, AppConstants.VALUE_ZERO);
        categoryId = intent.getIntExtra(AppConstants.CATEGORY_ID, AppConstants.VALUE_ZERO);

    }

    private void initView() {
        setContentView(R.layout.activity_product_list);

        initToolbar();
        enableBackButton();
        setToolbarTitle(title);
        //initLoader();

        rvProductList = (RecyclerView) findViewById(R.id.rvProductList);
        viewToggle = (ImageView) findViewById(R.id.viewToggle);
        loadMoreView = (ProgressBar) findViewById(R.id.loadMore);

        // init RecyclerView
        rvProductList.setHasFixedSize(true);
        setRecyclerViewLayoutManager(rvProductList, LayoutManagerType.LINEAR_LAYOUT_MANAGER);
        mProductListAdapter = new ProductListAdapter(mContext, productList, ListTypeShow.LINEAR);
        rvProductList.setAdapter(mProductListAdapter);



    }



    public void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    public void enableBackButton() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public Toolbar getToolbar() {
        if (mToolbar == null) {
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(mToolbar);
        }
        return mToolbar;
    }

    public void setToolbarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
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
                        productList.get(position).getID_Produits());
            }
        });


        viewToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleView();
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
            viewToggle.setImageResource(R.drawable.ic_list);
            setRecyclerViewLayoutManager(rvProductList, LayoutManagerType.GRID_LAYOUT_MANAGER);

            mProductListAdapter = new ProductListAdapter(mContext, productList, ListTypeShow.GRID);
            rvProductList.setAdapter(mProductListAdapter);

        } else {
            viewToggle.setImageResource(R.drawable.ic_grid);
            setRecyclerViewLayoutManager(rvProductList, LayoutManagerType.LINEAR_LAYOUT_MANAGER);

            mProductListAdapter = new ProductListAdapter(mContext, productList, ListTypeShow.LINEAR);
            rvProductList.setAdapter(mProductListAdapter);
        }
    }

}