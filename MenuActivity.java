package com.example.jchen415.mywaytormobileapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.widget.ImageButton;
import android.widget.*;

public class MenuActivity extends AppCompatActivity {

    private ImageButton friedChicken;
    private Button payment_Button, addCard_Button, deleteCard_Button;
    DBController db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        db = new DBController(this);

        friedChicken = (ImageButton) findViewById(R.id.fried_Chicken);
        payment_Button = (Button) findViewById(R.id.payment_Button);
        addCard_Button = (Button) findViewById(R.id.addCard_Button);
        deleteCard_Button = (Button) findViewById(R.id.deleteCard_Button);

        friedChicken.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View v)
            {
                Intent fChicken = new Intent(MenuActivity.this, DetailedMenuActivity.class);
                startActivity(fChicken);
            }
         });

        payment_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                if (db.CheckCard() == true) {
                    Intent payment = new Intent(MenuActivity.this, payActivity.class);
                    startActivity(payment);
                }
                else {
                    Toast errorMsg = Toast.makeText(MenuActivity.this, "Please Add Card!", Toast.LENGTH_SHORT);
                    errorMsg.show();
                }
            }
        });

        addCard_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent card = new Intent(MenuActivity.this, CardRegistrationActivity.class);
                startActivity(card);
            }
        });

        deleteCard_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String pID = db.getCardpID();
                if (db.CheckCard()) {
                    db.deleteCard(pID);
                    Toast.makeText(MenuActivity.this, "Card Deleted!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MenuActivity.this, "No Card On File!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
