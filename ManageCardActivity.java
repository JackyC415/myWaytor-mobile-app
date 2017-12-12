package com.example.jchen415.mywaytormobileapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ManageCardActivity extends AppCompatActivity {
    DBController db;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_card);
        db = new DBController(this);

        final TextView helloTextView = findViewById(R.id.MCgreeting);
        helloTextView.setText(R.string.user_greeting);
        final TextView MCNum = findViewById(R.id.MCNum);
        final String CardNum = db.getCardNumber();
        MCNum.setText(CardNum);
        final Button bDeleteCard = findViewById(R.id.deleteCard);
        final Button bAddCard = findViewById(R.id.addCard);

        bAddCard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ManageCardActivity.this, CardRegistrationActivity.class));
            }
        });
        bDeleteCard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (db.checkCard() == false) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ManageCardActivity.this);
                    builder.setTitle("Card Empty!");
                    builder.setMessage("Please Register a Card!");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(ManageCardActivity.this, CardRegistrationActivity.class));
                        }
                    });
                    builder.create();
                    builder.show();
                } else {
                    startActivity(new Intent(ManageCardActivity.this, DeleteCardActivity.class));
                }
            }
        });
    }
}
