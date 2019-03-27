package oneclick.yonclick.Authentification;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import oneclick.yonclick.Adapter.AdapterProfil;
import oneclick.yonclick.Model.Profil;
import oneclick.yonclick.R;
import oneclick.yonclick.activity.BaseActivity;
import oneclick.yonclick.activity.MainActivity;

public class ProfilActivity extends BaseActivity {

    AdapterProfil profilAdapter;
    ArrayList<Profil> aProfil;
    ListView lvProfil;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);


        initToolbar();
        enableBackButton();
        setToolbarTitle(getString(R.string.cart_list));


         sharedPreferences = getSharedPreferences("Register", Context.MODE_PRIVATE);

        sharedPreferences.getString("SESSION_ID", "SESSION_ID");
        sharedPreferences.getString("nom_client", "");
        sharedPreferences.getString("email_client", "");
        sharedPreferences.getString("imei", "");


        // call the listview
        lvProfil = (ListView) findViewById(R.id.rvProfilList);
        // onclick in the listview for seeing the details
        lvProfil.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Profil equipe = (Profil) lvProfil.getItemAtPosition(position);

                Intent i = new Intent(ProfilActivity.this, MainActivity.class);
                i.putExtra("Equipe", equipe);
                   startActivity(i);

            }
        });

        aProfil = new ArrayList<>();
        profilAdapter = new AdapterProfil(ProfilActivity.this, aProfil);
        lvProfil.setAdapter(profilAdapter);

        profilAdapter.addAll(Profil.fromFakeData());
        profilAdapter.notifyDataSetChanged();
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Respond to the action bar's Up/Home button to the home page with (finishAffinity)
                Intent i = new Intent(ProfilActivity.this, MainActivity.class);
                startActivity(i);
                finishAffinity();
                return true;
        }
        return super.onOptionsItemSelected(item);
       // name.setText(sharedPreferences.getString("name_user", ""));
    }
}
