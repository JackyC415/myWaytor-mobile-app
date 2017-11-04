package com.example.lap.mywaytor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;


public class LocationActivity extends AppCompatActivity{
    private Button bRetry, bYes;
   // private EditText message;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        //message = (EditText) findViewById(R.id.etMessage);
        bRetry = (Button) findViewById(R.id.bRegisterLink);
        bYes = (Button) findViewById(R.id.bLogin);

        bRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Retry Pressed!", Toast.LENGTH_SHORT).show();
            }
        });
        bYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LocationActivity.this, MenuActivity.class));
            }
        });
    }

}

