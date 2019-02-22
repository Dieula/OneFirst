package oneclick.yonclick.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import java.util.ArrayList;

import oneclick.yonclick.Adapter.PlatAdapter;
import oneclick.yonclick.ApiService.ApiService;
import oneclick.yonclick.BaseUrl.RetroClient;
import oneclick.yonclick.Model.Plat;
import oneclick.yonclick.ModelList.PlatList;
import oneclick.yonclick.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlatActivity extends AppCompatActivity {
    private ArrayList<Plat> restaurants;
    private ProgressDialog pDialog;
    private RecyclerView recyclerView;
    private PlatAdapter eAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plat);

/*


        //Display the Up button home
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar actionBar = getSupportActionBar(); // or getActionBar();
        getSupportActionBar().setTitle("5 Coins"); // set the top title
        String title = actionBar.getTitle().toString(); // get the title
        actionBar.hide(); // or even hide the actionbar

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.arrowleft);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
*/

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading Data.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        //Creating an object of our api interface
        ApiService api = RetroClient.getApiService();

        /**
         * Calling JSON
         */
        Call<PlatList> call = api.getPlatJSON();

        /**
         * Enqueue Callback will be call when get response...
         */
        call.enqueue(new Callback<PlatList>() {
            @Override
            public void onResponse(Call<PlatList> call, Response<PlatList> response) {
                //Dismiss Dialog
                pDialog.dismiss();

                if (response.isSuccessful()) {
                    /**
                     * Got Successfully
                     */
                    restaurants = response.body().getPlats();
                    recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                    eAdapter = new PlatAdapter(getApplicationContext(), restaurants);

                    GridLayoutManager mGridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                    recyclerView.setLayoutManager(mGridLayoutManager);

/*
                    RecyclerView.LayoutManager eLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(eLayoutManager);*/
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(eAdapter);
                }
            }

            @Override
            public void onFailure(Call<PlatList> call, Throwable t) {
                pDialog.dismiss();
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_actionbar, menu);


        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.miCompose:
               // composeMessage();
                return true;
            case R.id.miProfile:
               // showProfileView();
                return true;
            case R.id.miSearch:
               // showProfileView();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}