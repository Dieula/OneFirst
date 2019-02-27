package oneclick.yonclick.Authentification;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import oneclick.yonclick.InterfaceAPI.ApiEndPointInterface;
import oneclick.yonclick.InterfaceAPI.RestApi;
import oneclick.yonclick.ModelAuth.RequestLogin;
import oneclick.yonclick.ModelAuth.ResponseLogin;
import oneclick.yonclick.R;
import oneclick.yonclick.activity.MainActivity;
import retrofit2.Call;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    Button ivCon;
    TextView ivInsc;

    private Call<ResponseLogin> call;
    private ApiEndPointInterface apiRegister;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ProgressDialog progressDialog;

    private int status;
    private Response<ResponseLogin> reponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        //calling the editext
        email = (EditText) findViewById(R.id.etUser);
        password = (EditText) findViewById(R.id.etPassword);
        //calling the imageview
        ivCon = (Button) findViewById(R.id.Iv_login);
        //
        ivInsc = (TextView) findViewById(R.id.tvCompte);
        ivInsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),InscriptionActivity.class));
            }
        });



        progressDialog = new ProgressDialog(LoginActivity.this);
        sharedPreferences = getSharedPreferences("PreferencesTAG", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        ivCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
          /* if (email.getText().toString() != null )
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
            }*/
        });
    }


    public void register(){
        LoginAsyncTask registerAsyncTask = new LoginAsyncTask();
        registerAsyncTask.execute();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));

    }

    private boolean validate()
    {
        boolean valid = true;



        return valid;

    }

    public class LoginAsyncTask extends AsyncTask {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Object o) {
            if (status == 200) {
                Toast.makeText(getApplicationContext(), "Bienvenue sur YONCLICK", Toast.LENGTH_SHORT).show();
                StaticUser.setRegister(reponse.body().getLogin());

            } else {
                Toast.makeText(getApplicationContext(), "Not good", Toast.LENGTH_SHORT).show();

            }
            super.onPostExecute(o);
            super.onPostExecute(o);
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                apiRegister = RestApi.getApi();
                RequestLogin requestLogin = new RequestLogin();
                requestLogin.setEmail(email.getText().toString());
                requestLogin.setPassword(password.getText().toString());
                call = apiRegister.user(requestLogin);
                reponse = call.execute();
                status = reponse.code();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }


    public void onLogButton(View v ){
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }

}
