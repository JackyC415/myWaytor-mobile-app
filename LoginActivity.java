package com.example.lap.mywaytor;
package com.example.jchen415.mywaytormobileapplication;

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
    private int counter = 0;
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
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }

        });
       btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v.getId() == R.id.bLogin) {
                    EditText user = (EditText) findViewById(R.id.etInputUsername);
                    String username = user.getText().toString();
                    EditText pass = (EditText) findViewById(R.id.etInputPassword);
                    String password = pass.getText().toString();

                    if (TextUtils.isEmpty(username)) {
                        Toast.makeText(getApplicationContext(), "Enter username!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(password)) {
                        Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String dbAuth = db.LoginAuth(username);
                    if (password.equals(dbAuth)) {
                        startActivity(new Intent(LoginActivity.this, DetailedMenuActivity.class));
                    }
                   else {
                        counter++;
                        if (counter >= 3) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            builder.setTitle("Login Failed");
                            builder.setMessage("Please Check Your Email For Verification!");
                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent payment = new Intent(LoginActivity.this, LoginActivity.class);
                                    startActivity(payment);
                                }
                            });
                            builder.create();
                            builder.show();
                        } else {
                            Toast errorMsg = Toast.makeText(LoginActivity.this, "Username and Password not matched!", Toast.LENGTH_SHORT);
                            errorMsg.show();
                        }
                    }
                }
            }
        });
    }
}
