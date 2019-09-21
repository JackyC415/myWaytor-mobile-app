package com.example.jchen415.mywaytormobileapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class payActivity extends AppCompatActivity {

    Button cashButton, cardButton, cardSummary;
    DBController db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        db = new DBController(payActivity.this);

        cashButton = (Button) findViewById(R.id.cashButton);
        cashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(payActivity.this);
                builder.setTitle("Successful Payment");
                builder.setMessage("Thank You, Your Food Will Be Right Up");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent payment = new Intent(payActivity.this, LoginActivity.class);
                        payment.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        payment.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        payment.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(payment);
                    }
                });
                builder.create();
                builder.show();
            }
        });


        cardButton = (Button) findViewById(R.id.credit_Card);
        cardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!db.CheckCard()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(payActivity.this);
                    builder.setTitle("No Card Exists");
                    builder.setMessage("Please Enter Card Info!");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent payment = new Intent(payActivity.this, CardRegistrationActivity.class);
                            startActivity(payment);
                        }
                    });
                    builder.create();
                    builder.show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(payActivity.this);
                    builder.setTitle("Successful Payment");
                    builder.setMessage("Thank You, Your Food Will Be Right Up");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent payment = new Intent(payActivity.this, LoginActivity.class);
                            payment.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            payment.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            payment.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(payment);
                        }
                    });
                    builder.create();
                    builder.show();
                }
            }
        });


        cardSummary = (Button) findViewById(R.id.Summary);
        cardSummary.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent summary = new Intent(payActivity.this, paymentActivity.class);
                startActivity(summary);
            }
        });

    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder build = new AlertDialog.Builder(this);
        build.setCancelable(true);
        build.setTitle(title);
        build.setMessage(Message);
        build.show();
    }
}