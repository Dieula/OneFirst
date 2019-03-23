package oneclick.yonclick.NewAuthentification;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import oneclick.yonclick.R;
import oneclick.yonclick.Uils.AppConfig;
import oneclick.yonclick.Uils.AppController;
import oneclick.yonclick.activity.MainActivity;
import oneclick.yonclick.dataa.sessionManager.SessionManager;
import oneclick.yonclick.dataa.sqlite.SQLiteHandler;

public class    LoginUserActivity extends AppCompatActivity {


    private static final String TAG = RegisterUserActivity.class.getSimpleName();

    EditText email, password;
    Button ivCon;
    TextView ivInsc;

    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);


        //calling the editext
        email = (EditText) findViewById(R.id.etUser);
        password = (EditText) findViewById(R.id.etPassword);
        //calling the imageview
        ivCon = (Button) findViewById(R.id.Iv_login);
        ivInsc = (TextView) findViewById(R.id.tvCompte);
        //
       /* ivInsc = (TextView) findViewById(R.id.tvCompte);
       *//* ivInsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),InscriptionActivity.class));
            }
        });*//*
*/
        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // SQLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // Session manager
        session = new SessionManager(getApplicationContext());

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(LoginUserActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        // Login button Click Event
        ivCon.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String mEmail = email.getText().toString().trim();
                String mPassword = password.getText().toString().trim();

                // Check for empty data in the form
                if (!mEmail.isEmpty() && !mPassword.isEmpty()) {
                    // login user
                    checkLogin(mEmail, mPassword);
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(),
                            "Please enter the credentials!", Toast.LENGTH_LONG)
                            .show();
                }
            }

        });

        // Link to Register Screen
        ivInsc.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        RegisterUserActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    /**
     * function to verify login details in mysql db
     */
    private void checkLogin(final String email, final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";

        pDialog.setMessage("Logging in ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    int status = jObj.getInt("1");

                    // Check for error node in json
                    if (status == 1) {
                        // user successfully logged in
                        // Create login session
                        session.setLogin(true);

                        // Now store the user in SQLite


                        JSONObject user = jObj.getJSONObject("data");
                        String uid = jObj.getString("accessToken");
                        String name = user.getString("name");
                        String email = user.getString("email");
                        String created_at = user.getString("created_at");

                        // Inserting row in users table
                        db.addUser(name, email, name, email, uid, created_at);

                        // Launch main activity
                        Intent intent = new Intent(LoginUserActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                params.put("apiKey", "8484884774837498");
                params.put("email", email);
                params.put("password", password);

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
    }

}


/*


        ivCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
          *//* if (email.getText().toString() != null )
                {
                    if(validate())
                    {
                        register();
                    }

                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Verifier vos champs!", Toast.LENGTH_SHORT).show();
                }
                startActivity(new Intent(getApplicationContext(),InscriptionActivity.class));
            }*//*
        });*/

