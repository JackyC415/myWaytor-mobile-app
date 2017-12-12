package com.example.jchen415.mywaytormobileapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class orderSummary2 extends AppCompatActivity {

    //create db object
    DBController db;

    //create objects
    TextView orders;
    Button go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        //point to the database
        db = new DBController(this);

        //point to the object
        orders = (TextView) findViewById(R.id.name_O);
        go = (Button) findViewById(R.id.Pay);

        if(db.checkSummary2() == true) {
            //load data into buffer
            Cursor res = db.getAllData2();
            StringBuffer buff = new StringBuffer();
            StringBuffer buff1 = new StringBuffer();
            StringBuffer buff2 = new StringBuffer();

            do {

                buff.append("Name: " + res.getString(0) + " X " + res.getString(2) + "     Price: " + res.getString(1) + "\n");
            } while (res.moveToNext());

            orders.setText(buff);

            goTo();
        }
        else {
            Toast.makeText(getApplicationContext(), "Cart Empty!", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    //go to next activity
    public void goTo() {
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startActivity(new Intent(orderSummary2.this, Payment.class));
                }
        });
    }
}
