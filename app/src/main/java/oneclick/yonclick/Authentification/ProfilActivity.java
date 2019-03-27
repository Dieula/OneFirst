package oneclick.yonclick.Authentification;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import oneclick.yonclick.Adapter.AdapterProfil;
import oneclick.yonclick.Model.Profil;
import oneclick.yonclick.R;
import oneclick.yonclick.activity.BaseActivity;
import oneclick.yonclick.activity.HistoricActivity;
import oneclick.yonclick.activity.MainActivity;

public class ProfilActivity extends BaseActivity {

    AdapterProfil profilAdapter;
    ArrayList<Profil> aProfil;
    ListView lvProfil;
    SharedPreferences sharedPreferences;

     TextView tvName;
     ImageView imageView;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        tvName = (TextView) findViewById(R.id.tvName);
        imageView = (ImageView) findViewById(R.id.vpImageSlider);



        initToolbar();
        enableBackButton();
        setToolbarTitle(getString(R.string.profil));


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
                Profil profil = (Profil) lvProfil.getItemAtPosition(position);

                if (position == 0)
                {
                    Toast.makeText(ProfilActivity.this, "Modifier", Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(getApplicationContext(),));
                }
                else if (position == 1)
                {
                    startActivity(new Intent(getApplicationContext(),HistoricActivity.class));
                }
                else if (position == 2)
                {
                    shareInfo();
                }
/*


                Intent i = new Intent(ProfilActivity.this, MainActivity.class);
                i.putExtra("ProfilUser", profil);
                   startActivity(i);
*/

            }
        });

        aProfil = new ArrayList<>();
        profilAdapter = new AdapterProfil(ProfilActivity.this, aProfil);
        lvProfil.setAdapter(profilAdapter);

        profilAdapter.addAll(Profil.fromFakeData());
        profilAdapter.notifyDataSetChanged();
    }

    private void shareInfo() {
        //add the message for sharing
        String messageToShare="Pour plus de detail et de contenu, visitez notre siteWeb- http://oneclick.ht/";

        messageToShare = "visitez notre site Web:- http://oneclick.ht/";

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "YonClick");
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Pour plus de detail et de contenu vous pouvez télécharger l'application");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);

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
