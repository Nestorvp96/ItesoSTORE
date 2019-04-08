package com.iteso.sesion9;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.iteso.sesion9.beans.User;

public class LogIn extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        etUsername = findViewById(R.id.Username);
        etPassword = findViewById(R.id.Password);
        btnLogin = findViewById(R.id.btnLogin);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user = new User();
                user.setName(etUsername.getText().toString());
                user.setPassword(etPassword.getText().toString());
                user.setLogged(true);

                SharedPreferences sharedPreferences = getSharedPreferences("com.example.preferences.PREFERENCES", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("USERNAME", user.getName());
                editor.putString("PASSWORD", user.getPassword());
                editor.putBoolean("LOGGED", user.isLogged());

                editor.commit();

                Intent intent = new Intent(LogIn.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
