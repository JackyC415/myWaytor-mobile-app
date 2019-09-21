package com.example.jchen415.mywaytormobileapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DeleteCardActivity extends AppCompatActivity {
    DBController db;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_card);
        db = new DBController(this);

        final TextView helloTextView = findViewById(R.id.Dgreeting);
        helloTextView.setText(R.string.user_greeting);

        final TextView CNTextView = findViewById(R.id.CNum);
        final String CardNumber = db.getCardNumber();
        CNTextView.setText(CardNumber);

        final Button bDeleteCard = findViewById(R.id.deleteCard);

        bDeleteCard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (db.checkCard() == false) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(DeleteCardActivity.this);
                    builder.setTitle("No Card Exists");
                    builder.setMessage("Please Register a Card!");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent payment = new Intent(DeleteCardActivity.this, CardRegistrationActivity.class);
                            startActivity(payment);
                        }
                    });
                    builder.create();
                    builder.show();
                } else {
                    String PID = db.getCardpID();
                    db.deleteCard(PID);
                    AlertDialog.Builder builder = new AlertDialog.Builder(DeleteCardActivity.this);
                    builder.setTitle("Card Deleted!");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(DeleteCardActivity.this, Menu1.class));
                        }
                    });
                    builder.create();
                    builder.show();

                }
            }
        });
    }
}
