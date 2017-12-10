package com.example.jchen415.mywaytormobileapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class DeleteCardActivity extends AppCompatActivity {
    DBController db;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_card);
        db = new DBController(this);

        final Button bDeleteCard = findViewById(R.id.deleteCard);

        bDeleteCard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (db.checkCard() == false) {
                    Toast.makeText(getApplicationContext(), "No Card Exists!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    String PID = db.getCardpID();
                    db.deleteCard(PID);
                    startActivity(new Intent(DeleteCardActivity.this, ManageCardActivity.class));
                }
            }
        });
    }
}