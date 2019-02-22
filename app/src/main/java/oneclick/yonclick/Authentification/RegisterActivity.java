package oneclick.yonclick.Authentification;

import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import oneclick.yonclick.InterfaceAPI.ApiEndPointInterface;
import oneclick.yonclick.ModelAuth.UserResponse;
import oneclick.yonclick.R;
import oneclick.yonclick.activity.MainActivity;
import retrofit2.Call;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private Button buttonS;

    private EditText etNomUser,password,confirmPass,etEmailUser;


    private Call<UserResponse> call;
    private ApiEndPointInterface apiRegister;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ProgressDialog progressDialog;

    private int codeResponse;
    private Response<UserResponse> reponse;

    JSONArray articleJsonResults;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        buttonS = findViewById(R.id.btnInscription);

        etNomUser = (EditText) findViewById(R.id.etNomUser);
        password = (EditText) findViewById(R.id.etPass);
        confirmPass = (EditText) findViewById(R.id.etConfirmPass);
        etEmailUser = (EditText) findViewById(R.id.etEmailUser);

        progressDialog = new ProgressDialog(RegisterActivity.this);

        buttonS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    startActivity(new Intent(getApplicationContext(),CompletezInscriptionActivity.class));

                if(etNomUser.getText().toString().equals("") && etEmailUser.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "un ou plusieurs sont vides", Toast.LENGTH_SHORT).show();
                }else{
                    /*progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Connexion en cours...");
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.setCancelable(false);
                    progressDialog.show();*/
                    register();
                }


               /* if (etUserNif.getText().toString() != null )
                {
                    if(validate())
                    {
                        register();
                    }

                }
                else
                {
                    Toast.makeText(InscriptionActivity.this, "Verifier vos champs!", Toast.LENGTH_SHORT).show();
                }*/
            }
        });




    }
    public String getDeviceId2() {


        String androidId = Settings.Secure.getString(
                getContentResolver(), Settings.Secure.ANDROID_ID);

        return androidId;
    }

    public void register() {

        String url ="http://45.76.247.112/api/user/create";
        String androidId = Settings.Secure.getString(
                getContentResolver(), Settings.Secure.ANDROID_ID);

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("Content-Type", "application/json");
        params.put("apiKey", "8484884774837498");
        params.put("name", etNomUser.getText().toString());
        params.put("password", password.getText().toString());
        params.put("confPass_client", confirmPass.getText().toString());
        params.put("email", etEmailUser.getText().toString());
        params.put("deviceId",androidId);
        params.put("deviceType","Sisi");


        client.post(url,params, new JsonHttpResponseHandler(){
            //ingbenoit@gmail.com
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
               // progressDialog.dismiss();
                //JSONArray articleJsonResults = null;
                articleJsonResults = null;
                try {

                    Object objectlogin = response.get("data");
                    if (objectlogin instanceof JSONArray) {

                        articleJsonResults = response.getJSONArray("data");
                        editor.putString("nom_client", etNomUser.getText().toString());
                        editor.putString("email_client", etEmailUser.getText().toString());
                        editor.apply();
                        Toast.makeText(RegisterActivity.this, etNomUser.getText().toString()+", Bienvenue sur YonClick...", Toast.LENGTH_SHORT).show();
                      //  progressDialog.dismiss();
                        notifNewUser(etNomUser.getText().toString(),etEmailUser.getText().toString());
                    }else{
                      //  progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "Verifier nom utilisateur et mot de pass", Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    Toast.makeText(RegisterActivity.this, "Badddd", Toast.LENGTH_SHORT).show();

            //    progressDialog.dismiss();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
               // progressDialog.dismiss();
                Toast.makeText(RegisterActivity.this, "Verifier nom utilisateur et mot de pass", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void notifNewUser(String nom, String email) {
        //Get an instance of NotificationManager//

        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.iconlogo)
                        .setContentTitle("Bienvenue sur YonClick: "+nom)
                        .setContentText("Vos Informations :+"+email);

        // Gets an instance of the NotificationManager service//

        NotificationManager mNotificationManager =

                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //When you issue multiple notifications about the same type of event, it’s best practice for your app to try to update an existing notification with this new information, rather than immediately creating a new notification. If you want to update this notification at a later date, you need to assign it an ID. You can then use this ID whenever you issue a subsequent notification. If the previous notification is still visible, the system will update this existing notification, rather than create a new one. In this example, the notification’s ID is 001//

        //NotificationManager.notify().

        mNotificationManager.notify(001, mBuilder.build());

        etEmailUser.getText().clear();
        etNomUser.getText().clear();
        password.getText().clear();
        confirmPass.getText().clear();

        Intent i = new Intent(RegisterActivity.this, MainActivity.class);
        //i.putExtra("services",serv);
        startActivity(i);
        finishAffinity();

    }

}
