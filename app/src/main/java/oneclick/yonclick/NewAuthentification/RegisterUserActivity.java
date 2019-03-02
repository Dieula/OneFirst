package oneclick.yonclick.NewAuthentification;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import oneclick.yonclick.Authentification.LoginActivity;
import oneclick.yonclick.R;
import oneclick.yonclick.Uils.AppConfig;
import oneclick.yonclick.Uils.AppController;
import oneclick.yonclick.activity.MainActivity;
import oneclick.yonclick.dataa.sessionManager.SessionManager;
import oneclick.yonclick.dataa.sqlite.SQLiteHandler;

public class RegisterUserActivity extends AppCompatActivity {

    private static final String TAG = RegisterUserActivity.class.getSimpleName();

    private Button buttonS;
    String deviceType,deviceId;

    private EditText etNomUser,password,confirmPass,etEmailUser;

    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        buttonS = findViewById(R.id.btnInscription);

        etNomUser = (EditText) findViewById(R.id.etNomUser);
        password = (EditText) findViewById(R.id.etPass);
        confirmPass = (EditText) findViewById(R.id.etConfirmPass);
        etEmailUser = (EditText) findViewById(R.id.etEmailUser);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // Session manager
        session = new SessionManager(getApplicationContext());

        // SQLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(RegisterUserActivity.this,
                    MainActivity.class);
            startActivity(intent);
            finish();
        }

        // Register Button Click event
        buttonS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String name = etEmailUser.getText().toString().trim();
                String email = etEmailUser.getText().toString().trim();
                String mPassword = password.getText().toString().trim();
                  deviceId = ("androidId");
                 deviceType = ("2");

                if (!name.isEmpty() && !email.isEmpty() && !mPassword.isEmpty()) {
                    registerUser(name, email, mPassword,deviceId,deviceType);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please enter your details!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
/*
        // Link to Login Screen
        btnLinkToLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        LoginActivity.class);
                startActivity(i);
                finish();
            }
        });*/

    }

    /**
     * Function to store user in MySQL database will post params(tag, name,
     * email, password) to register url
     * */
    private void registerUser(final String name, final String email, final String deviceType
            , final String password, String type) {
        // Tag used to cancel the request
        String tag_string_req = "req_register";

             TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                // get IMEI
                @SuppressLint("MissingPermission")
                final String androidId = tm.getDeviceId();

        final String deviceT = "Sisi";
       /* final String androidId = Settings.Secure.getString(
                getContentResolver(), Settings.Secure.ANDROID_ID);*/

        pDialog.setMessage("Registering ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_REGISTER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    int status = jObj.getInt("1");
                    if (status == 1) {
                        // User successfully stored in MySQL
                        // Now store the user in sqlite
                        String uid = jObj.getString("accessToken");

                        JSONObject user = jObj.getJSONObject("data");
                        String name = user.getString("name");
                        String email = user.getString("email");
                        String deviceId = user.getString("androidId");
                        String deviceType = user.getString("2");
                        String created_at = user
                                .getString("created_at");

                        // Inserting row in users table
                        db.addUser(name, email, uid, created_at,deviceId,deviceType);

                        Toast.makeText(getApplicationContext(), "User successfully registered. Try login now!", Toast.LENGTH_LONG).show();

                        // Launch login activity
                        Intent intent = new Intent(
                                RegisterUserActivity.this,
                                LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(), "Baddd"+errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), "Errrorrr"+error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                params.put("apiKey", "8484884774837498");
                params.put("name", name);
                params.put("email", email);
                params.put("password", password);
                params.put("deviceType", deviceT);
                params.put("deviceId", androidId);



                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();


      /*  buttonS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    startActivity(new Intent(getApplicationContext(),CompletezInscriptionActivity.class));

                if(etNomUser.getText().toString().equals("") && etEmailUser.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "un ou plusieurs sont vides", Toast.LENGTH_SHORT).show();
                }else{
                    *//*progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Connexion en cours...");
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.setCancelable(false);
                    progressDialog.show();*//*
                }


               *//* if (etUserNif.getText().toString() != null )
                {
                    if(validate())
                    {
                        register();
                    }

                }
                else
                {
                    Toast.makeText(InscriptionActivity.this, "Verifier vos champs!", Toast.LENGTH_SHORT).show();
                }*//*
            }
        });*/

    }
}
