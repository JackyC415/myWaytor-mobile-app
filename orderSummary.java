package com.example.jchen415.mywaytormobileapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import android.app.AlertDialog.Builder;
import android.widget.Toast;

import org.w3c.dom.Text;

public class orderSummary extends AppCompatActivity {

    //create objects
    DBController db;
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

        if(db.checkSummary1() == true) {
            Cursor res = db.getAllData();
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

    public void goTo() {
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(orderSummary.this, Payment.class));
            }
        });
    }
}