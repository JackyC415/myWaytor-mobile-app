package com.example.lap.mywaytor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.content.*;



public class RegisterActivity extends AppCompatActivity {

  public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText etFirstName = (EditText) findViewById(R.id.etFirstName);
        final EditText etLastName = (EditText) findViewById(R.id.etLastName);
        final EditText etUsername = (EditText) findViewById(R.id.etInputUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etInputPassword);
        final EditText etAge = (EditText) findViewById(R.id.etAge);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etGender = (EditText) findViewById(R.id.etGender);

        final Button bRegister = (Button) findViewById(R.id.bRegisterLink);
        bRegister.setText("Register Here");

        bRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String FirstName = etFirstName.getText().toString();
                final String LastName = etLastName.getText().toString();
                final String Username = etUsername.getText().toString();
                final String Password = etPassword.getText().toString();
                final String Age = etAge.getText().toString();
                final String Email = etEmail.getText().toString();
                final String Gender = etGender.getText().toString();

                //Registration r = new Registration();
                //DBController db = new DBController(this, null, null, 1);
                //SQLiteDatabase db
                if (TextUtils.isEmpty(FirstName)) {
                    Toast.makeText(getApplicationContext(), "Enter First Name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(LastName)) {
                    Toast.makeText(getApplicationContext(), "Enter Last Name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Age)) {
                    Toast.makeText(getApplicationContext(), "Enter Age!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Gender)) {
                    Toast.makeText(getApplicationContext(), "Enter Gender!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Email)) {
                    Toast.makeText(getApplicationContext(), "Enter Email!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Username)) {
                    Toast.makeText(getApplicationContext(), "Enter Username!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Password)) {
                    Toast.makeText(getApplicationContext(), "Enter Password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                /*r.setUserName(Username);
                r.setUserPassword(Password);
                String user = r.getUserName();
                String pass = r.getUserPassword();*/

                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }
}

