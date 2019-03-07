package oneclick.yonclick.Authentification;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import oneclick.yonclick.InterfaceAPI.ApiEndPointInterface;
import oneclick.yonclick.InterfaceAPI.RestApi;
import oneclick.yonclick.ModelAuth.UserRequest;
import oneclick.yonclick.ModelAuth.UserResponse;
import oneclick.yonclick.R;
import oneclick.yonclick.activity.MainActivity;
import retrofit2.Call;
import retrofit2.Response;

public class InscriptionActivity extends AppCompatActivity {


    private Button buttonS;

    private EditText etNomUser,password,confirmPass,etEmailUser;


    private Call<UserResponse> call;
    private ApiEndPointInterface apiRegister;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ProgressDialog progressDialog;

    private int status;
    private Response<UserResponse> reponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        //preferences
        progressDialog = new ProgressDialog(InscriptionActivity.this);
        sharedPreferences = getSharedPreferences("Register", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();


        //widget
        buttonS = findViewById(R.id.btnInscription);

        etNomUser = (EditText) findViewById(R.id.etNomUser);
        password = (EditText) findViewById(R.id.etPass);
        confirmPass = (EditText) findViewById(R.id.etConfirmPass);
        etEmailUser = (EditText) findViewById(R.id.etEmailUser);

        buttonS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(getApplicationContext(),CompletezInscriptionActivity.class));

                register();

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



    private void register() {
        RegisterAsyncTask registerAsyncTask = new RegisterAsyncTask();
        registerAsyncTask.execute();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));

    }

    private boolean validate() {

        boolean valid = true;
        String mName = etNomUser.getText().toString();
        String mPhone = password.getText().toString();
        String mNif = etEmailUser.getText().toString();

        //Verify the name
        if (mName.isEmpty() || mName.length() < 3){

            etNomUser.setError("at least 6 characters");
            valid = false;
        }
        else
        {
            etNomUser.setError(null);
        }

        //Verify the email
        if (mPhone.isEmpty() || mPhone.isEmpty())
            etEmailUser.setError("at least 8 characters ");
        return valid;
    }

    public class RegisterAsyncTask extends AsyncTask {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Object o) {
            if (status == 200) {
                StaticUser.setRegister(reponse.body().getRegister());
                Toast.makeText(InscriptionActivity.this, "Good"+reponse.message(), Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(InscriptionActivity.this, ""+reponse.message(), Toast.LENGTH_SHORT).show();

            }
            super.onPostExecute(o);
        }



        @Override
        protected Object doInBackground(Object[] objects) {

            try {
                apiRegister = RestApi.getApi();

                TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                // get IMEI
                @SuppressLint("MissingPermission")
                String imei = tm.getDeviceId();

                UserRequest userRequest = new UserRequest();
                userRequest.setName(etNomUser.getText().toString());
                userRequest.setEmail(etEmailUser.getText().toString());
                userRequest.setPassword(password.getText().toString());
                userRequest.setDeviceId(imei);
                userRequest.setDeviceType("2");

                //save the userinfo
                editor.putString("nom_client", etNomUser.getText().toString());
                editor.putString("email_client", etEmailUser.getText().toString());
                editor.putString("imei", imei);
                editor.putString("imei", imei);
                editor.apply();

                call = apiRegister.utilisateur(userRequest);
                reponse = call.execute();
                status = reponse.code();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    public void LogButton(View v){
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}
