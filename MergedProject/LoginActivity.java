package com.example.lap.mywaytor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;
import android.text.*;

public class LoginActivity extends AppCompatActivity {
    private EditText inputEmail, inputPassword;
    private Button btnRegister, btnLogin;
    DBController db;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DBController(this);



        inputEmail = (EditText) findViewById(R.id.etInputUsername);
        inputPassword = (EditText) findViewById(R.id.etInputPassword);
        btnRegister = (Button) findViewById(R.id.bRegisterLink);
        btnLogin = (Button) findViewById(R.id.bLogin);
        AddData();
    }
    public void AddData() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Login login = new Login();
               //login.setLoginPassword("bye");
                //login.setLoginUsername("hello");
                //db.insertLoginData(login);
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }

        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                startActivity(new Intent(LoginActivity.this, DetailedMenuActivity.class));
            }


        });
    }

}
