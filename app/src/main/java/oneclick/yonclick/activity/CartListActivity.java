package oneclick.yonclick.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import oneclick.yonclick.Adapter.CartListAdapter;
import oneclick.yonclick.Model.CartItem;
import oneclick.yonclick.Model.LineItem;
import oneclick.yonclick.R;
import oneclick.yonclick.Uils.ActivityUtils;
import oneclick.yonclick.Uils.DialogUtils;
import oneclick.yonclick.dataa.constant.AppConstants;
import oneclick.yonclick.dataa.preference.AppPreference;
import oneclick.yonclick.dataa.preference.PrefKey;
import oneclick.yonclick.dataa.sqlite.CartDBController;
import oneclick.yonclick.listener.OnItemCheckedListener;
import oneclick.yonclick.listener.OnItemClickListener;

public class CartListActivity extends BaseActivity {

    // initialize variables
    private Context mContext;
    private Activity mActivity;
    private RecyclerView rvCartList;
    private ArrayList<CartItem> cartList;
    private CartListAdapter cartListAdapter;
    private int selectedCounter = 0;
    private float totalPrice = 0;

    // initialize View
    private TextView tvTotalPrice, tvSelectionCount, emptyView;
    private CheckBox checkBoxAll;
    private Button btnBuy;
    private LinearLayout lytSelectionAll, footerView;

    private TextView info_text,tvProductQuantity;
    private ImageButton btnQuantityPlus, btnQuantityMinus, searchButton;

    private int quantityCounter = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_cart_list);

        initVariables();
        initView();
        loadCartData();
        loadUiData();
        initLister();


    }
    private void initVariables() {
        mContext = getApplicationContext();
        mActivity = CartListActivity.this;
        cartList = new ArrayList<>();
    }
    private void initView() {
        setContentView(R.layout.activity_cart_list);

        initToolbar();
        enableBackButton();
        setToolbarTitle(getString(R.string.cart_list));
        initLoader();

        rvCartList = (RecyclerView) findViewById(R.id.rvCartList);
        tvTotalPrice = (TextView) findViewById(R.id.tvTotalPrice);
        tvSelectionCount = (TextView) findViewById(R.id.tvSelectionCount);
        //emptyView = (TextView) findViewById(R.id.emptyView);
        checkBoxAll = (CheckBox) findViewById(R.id.checkBoxAll);
        lytSelectionAll = (LinearLayout) findViewById(R.id.lytSelectionAll);
        footerView = (LinearLayout) findViewById(R.id.footerView);
       // btnBuy = (Button) findViewById(R.id.btnBuy);
        info_text = (TextView) findViewById(R.id.info_text);

        // init RecyclerView
        rvCartList.setHasFixedSize(true);

        rvCartList.setLayoutManager(new LinearLayoutManager(mActivity));
        cartListAdapter = new CartListAdapter(mContext, cartList);

        rvCartList.setAdapter(cartListAdapter);

//        quantityCounter = Integer.valueOf(tvProductQuantity.getText().toString());

    }
    private void initLister() {

        cartListAdapter.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemListener(View viewItem, int position) {

                switch (viewItem.getId()) {
                    case R.id.remove:
                        // Remove from cart list
                        deleteCartItemDialog(cartList.get(position).productId);
                        break;
                    default:
                        ActivityUtils.getInstance().invokeProductDetails(mActivity, cartList.get(position).productId);
                        break;
                }

            }
        });
        cartListAdapter.setItemCheckedListener(new OnItemCheckedListener() {
            @Override
            public void onItemCheckListener(View view, int position, boolean isChecked) {
                if (isChecked) {
                    try {
                        CartDBController cartController = new CartDBController(mContext);
                        //cartController.createDB();
                        cartController.updateCartItem(cartList.get(position).productId, AppConstants.VALUE_SELECTED);
                        cartController.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        CartDBController cartController = new CartDBController(mContext);
                       // cartController.createDB();
                        cartController.updateCartItem(cartList.get(position).productId, AppConstants.VALUE_NOT_SELECTED);
                        cartController.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                loadUiData();


            }
        });



        checkBoxAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    try {
                        CartDBController cartController = new CartDBController(mContext);
                       // cartController.createDB();
                        cartController.updateAllCartItemSelection(AppConstants.VALUE_SELECTED);
                        cartController.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        CartDBController cartController = new CartDBController(mContext);
                      //  cartController.createDB();
                        cartController.updateAllCartItemSelection(AppConstants.VALUE_NOT_SELECTED);
                        cartController.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                loadUiData();
                cartListAdapter.notifyDataSetChanged();
            }
        });
      /*  btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

            } *//*{
                if (selectedCounter > 0) {

                    // store price into preference
                    AppPreference.getInstance(mContext).setString(PrefKey.PAYMENT_TOTAL_PRICE, String.valueOf(totalPrice));

                    boolean isRegistered = AppPreference.getInstance(mContext).getBoolean(PrefKey.REGISTERED);
                    if(isRegistered) {
                        ActivityUtils.getInstance().invokeAddressActivity(mActivity, buildLineItems(), false, true);
                    } else {
                        ActivityUtils.getInstance().invokeLoginAndOrder(mActivity, buildLineItems());
                    }

                } else {
                    Toast.makeText(mContext, "Select product", Toast.LENGTH_SHORT).show();
                }
            }*//*
        });*/
    }

    private void loadCartData() {
        if (!cartList.isEmpty()) {
            cartList.clear();
        }
        try {
            CartDBController cartController = new CartDBController(mContext);
        //    cartController.createDB();
            cartList.addAll(cartController.getAllCartData());
            cartController.close();

            cartListAdapter.notifyDataSetChanged();
            if (cartList.isEmpty()) {
                lytSelectionAll.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(cartList.isEmpty()) {
            showEmptyView();
         //  Toast.makeText(getApplicationContext(), "Empty", Toast.LENGTH_SHORT).show();
          info_text.setText(getString(R.string.empty_cart));
        } else {
            hideLoader();
        }
    }

    private void loadUiData() {
        if (!cartList.isEmpty()) {
            cartList.clear();
        }
        totalPrice = 0;
        selectedCounter = 0;
        try {
            CartDBController cartController = new CartDBController(mContext);
           // cartController.createDB();
            cartList.addAll(cartController.getAllCartData());
            for (int i = 0; i < cartList.size(); i++) {
                if (cartList.get(i).isSelected == AppConstants.VALUE_SELECTED) {
                    totalPrice += cartList.get(i).price * cartList.get(i).quantity;
                    selectedCounter++;
                }
            }
            cartController.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // refresh footer view
        if (selectedCounter > 0) {
            tvTotalPrice.setText("Total: " + AppConstants.CURRENCY + totalPrice);
        } else {
            footerView.setVisibility(View.GONE);
        }

        // refresh checkbox
        if (selectedCounter > 0) {
            tvSelectionCount.setText(selectedCounter + " selected");
            footerView.setVisibility(View.VISIBLE);
            if (cartList.size() == selectedCounter) {
                checkBoxAll.setChecked(true);
            }
        } else {
            tvSelectionCount.setText(AppConstants.EMPTY_STRING);
            checkBoxAll.setChecked(false);
            footerView.setVisibility(View.GONE);
        }
    }


    private void deleteCartItemDialog(final int productId) {

        DialogUtils.showDialogPrompt(mActivity, null, getString(R.string.delete_cart_item),
                getString(R.string.dialog_btn_yes), getString(R.string.dialog_btn_no),
                true, new DialogUtils.DialogActionListener() {
            @Override
            public void onPositiveClick() {
                try {
                    CartDBController cartController = new CartDBController(mContext);
                  //  cartController.createDB();
                    cartController.deleteCartItemById(productId);
                    cartController.close();


                    cartListAdapter.notifyDataSetChanged();
                    if (cartList.isEmpty()) {
                        footerView.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                loadCartData();
            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
