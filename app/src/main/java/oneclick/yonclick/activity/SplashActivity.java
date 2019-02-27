package oneclick.yonclick.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import oneclick.yonclick.Authentification.LoginActivity;
import oneclick.yonclick.NewAuthentification.RegisterUserActivity;
import oneclick.yonclick.R;

public class  SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        //create the splash
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, RegisterUserActivity.class);
                startActivity(i);
                finish();
            }
        },1000);
    }

}
