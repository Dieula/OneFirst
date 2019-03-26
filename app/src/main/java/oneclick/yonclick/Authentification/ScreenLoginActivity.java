package oneclick.yonclick.Authentification;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import oneclick.yonclick.R;

public class ScreenLoginActivity extends AppCompatActivity {

    Button btnConnexion,btnInscription;
    RelativeLayout RelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_login);


        RelativeLayout = findViewById(R.id.RelativeLayout);
        btnConnexion = findViewById(R.id.btnConnexion);
        btnInscription = findViewById(R.id.btnInscription);

        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });


        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),InscriptionActivity.class));
            }
        });

    }

}
