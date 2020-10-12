package id.dicoding.mirel.logindengansqllite;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginSucess extends AppCompatActivity {

    private Button btnlogout;
    private Button btnmaps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sucess);
        btnlogout = findViewById(R.id.btnlogout);
        btnmaps = findViewById(R.id.btnmaps);


        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginSucess.this,MainActivity.class));
                finish();
            }
        });

        btnmaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginSucess.this,MapsActivity.class));
               
            }
        });
    }


}
