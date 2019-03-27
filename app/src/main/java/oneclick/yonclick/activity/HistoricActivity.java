package oneclick.yonclick.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;

import oneclick.yonclick.Adapter.HistoricArrayAdapter;
import oneclick.yonclick.Model.Historic;
import oneclick.yonclick.R;

public class HistoricActivity extends AppCompatActivity {


    ProgressBar progress;
    private SwipeRefreshLayout swiperefresh;
    TextView tvError;



    HistoricArrayAdapter serviceAdapter;
    ArrayList<Historic> aServices;
    ListView lvServices;


    JSONArray serviceJsonResults;
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic);




        progress = (ProgressBar) findViewById(R.id.progress);
        swiperefresh = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        tvError = findViewById(R.id.tvError);
        tvError.setVisibility(View.GONE);

        // call the listview
        lvServices = (ListView) findViewById(R.id.lvServices);



        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                progress.setVisibility(View.VISIBLE);
                getListService();
                swiperefresh.setRefreshing(false);
            }
        });
        // Configure the refreshing colors
        swiperefresh.setColorSchemeResources(android.R.color.holo_red_light,
                android.R.color.holo_orange_dark,
                android.R.color.holo_red_dark);

        progress.setVisibility(View.VISIBLE);

        sharedPreferences = getSharedPreferences("PreferencesTAG", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();



      /*  if(sharedPreferences.getString("nom_client", null).length()>5){
            getSupportActionBar().setTitle("Historique : "+sharedPreferences.getString("nom_client", null).substring(0,6)+"...");
        }else{
            getSupportActionBar().setTitle("Historique : "+sharedPreferences.getString("nom_client", null));
        }*/

        getListService();
    }

    private void getListService() {


    }


}
