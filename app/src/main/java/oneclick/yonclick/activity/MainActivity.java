package oneclick.yonclick.activity;
;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import oneclick.yonclick.Fragment.AbonnementFragment;
import oneclick.yonclick.Fragment.EcolageFragment;
import oneclick.yonclick.Fragment.MagasinsFragment;
import oneclick.yonclick.Fragment.RestaurantFragment;
import oneclick.yonclick.Model.CartItem;
import oneclick.yonclick.R;
import oneclick.yonclick.Uils.AppUtility;
import oneclick.yonclick.activity.BaseActivity;
import oneclick.yonclick.dataa.sqlite.CartDBController;


public class MainActivity extends AppCompatActivity {

    ImageView imgToolbarCart;
    TextView tvCartCounter;

    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // cart counter
        imgToolbarCart = (ImageView) findViewById(R.id.imgToolbarCart);
        tvCartCounter = (TextView) findViewById(R.id.tvCartCounter);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);


        final FragmentManager fragmentManager = getSupportFragmentManager();

        // define your fragments here
        final Fragment fragment1 = new MagasinsFragment();
        final Fragment fragment2 = new RestaurantFragment();
        final Fragment fragment3 = new EcolageFragment();
        final Fragment fragment4 = new AbonnementFragment();

        // handle navigation selection
        navigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment fragment = null;
                        switch (item.getItemId()) {
                            case R.id.nav_slideshow:

                                fragment = fragment1;
                                break;

                            case R.id.nav_restaurant:
                                fragment = fragment2;
                                break;

                            case R.id.nav_gallery:
                                fragment = fragment3;
                                break;

                            case R.id.nav_cadeaux:
                            default:
                                fragment = fragment4;
                                break;
                        }
                        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
                        return true;
                    }
                });

        // Set default selection
        navigation.setSelectedItemId(R.id.nav_slideshow);



    }


    @Override
    public void onResume() {
        super.onResume();

        loadCartCounter();

    }


    private void loadCartCounter() {
        try {
            CartDBController cartController = new CartDBController(mContext);
            cartController.open();
            ArrayList<CartItem> cartList = cartController.getAllCartData();
            cartController.close();

            if (cartList.isEmpty()) {
                tvCartCounter.setVisibility(View.GONE);
            } else {
                tvCartCounter.setVisibility(View.VISIBLE);
                tvCartCounter.setText(String.valueOf(cartList.size()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


