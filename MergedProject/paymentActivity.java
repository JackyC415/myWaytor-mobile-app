package com.example.lap.mywaytor;

import android.support.v7.app.AppCompatActivity;
import java.util.*;
import android.os.Bundle;
import android.view.*;
import android.text.*;
import android.widget.*;
import android.content.Intent;


public class paymentActivity extends AppCompatActivity {

    TextView name;
    String FoodName;
    String number;
    Button paymentConfirm,cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        paymentConfirm = (Button) findViewById(R.id.confirmPayButton);
        cancel = (Button) findViewById(R.id.cancelButton);
        TextView subTotalAmount = (TextView) findViewById(R.id.subTotalAmount);
        TextView summaryText = (TextView) findViewById(R.id.summaryText);
        TextView totalCostText = (TextView) findViewById(R.id.totalCostText);
        TextView subTotalText = (TextView) findViewById(R.id.subTotalText);
        TextView tipText = (TextView) findViewById(R.id.tipText);
        TextView totalText= (TextView) findViewById(R.id.totalText);

        paymentConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent payment= new Intent(paymentActivity.this, payActivity.class);
                startActivity(payment);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent payment= new Intent(paymentActivity.this, MenuActivity.class);
                startActivity(payment);
            }
        });


        Intent intent = this.getIntent();
        if(intent != null)
        {
            FoodName = intent.getStringExtra("FOODNAME");
            number = intent.getStringExtra("NUMBEROFORDER");
        }




        name = (TextView) findViewById(R.id.summaryText);
        name.setText("Food Name" +" " +FoodName + '\n'+  "Number of Order: " + " " + number + '\n');

    }


}