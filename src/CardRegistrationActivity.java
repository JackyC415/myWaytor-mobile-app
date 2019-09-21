package com.example.jchen415.mywaytormobileapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class CardRegistrationActivity extends AppCompatActivity {

    DBController db;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_registration);
        db = new DBController(CardRegistrationActivity.this);

        final EditText etCardName = (EditText) findViewById(R.id.etCardName);
        final EditText etCardNumber = (EditText) findViewById(R.id.etCardNumber);
        final EditText etExpirationDate = (EditText) findViewById(R.id.etExpirationDate);
        final EditText etCVV = (EditText) findViewById(R.id.etCVV);
        final EditText etBillingAddress = (EditText) findViewById(R.id.etBillingAddress);
        final EditText etZipcode = (EditText) findViewById(R.id.etZipcode);
        final EditText etCity = (EditText) findViewById(R.id.etCity);
        final EditText etState = (EditText) findViewById(R.id.etState);
        final EditText etCountry = (EditText) findViewById(R.id.etCountry);
        final Button bAddCard = (Button) findViewById(R.id.bAddCard);
        bAddCard.setText("Add Card");

        bAddCard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String CardName = etCardName.getText().toString();
                final String CardNumber = etCardNumber.getText().toString();
                final String ExpirationDate = etExpirationDate.getText().toString();
                final String ZipCode = etZipcode.getText().toString();
                final String CVV = etCVV.getText().toString();
                final String BillingAddress = etBillingAddress.getText().toString();
                final String City = etCity.getText().toString();
                final String State = etState.getText().toString();
                final String Country = etCountry.getText().toString();

                if (TextUtils.isEmpty(CardName)) {
                    Toast.makeText(getApplicationContext(), "Enter Card Name!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(CardNumber)) {
                    Toast.makeText(getApplicationContext(), "Enter Card Number!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (CardNumber.length() > 16) {
                    Toast.makeText(getApplicationContext(), "Card Number is 16 digits!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(ExpirationDate)) {
                    Toast.makeText(getApplicationContext(), "Enter Expiration Date!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (ExpirationDate.length() > 4) {
                    Toast.makeText(getApplicationContext(), "Exp Date Too Long!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(CVV)) {
                    Toast.makeText(getApplicationContext(), "Enter CVV!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (CVV.length() > 3) {
                    Toast.makeText(getApplicationContext(), "CVV is only 3 digits long!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(BillingAddress)) {
                    Toast.makeText(getApplicationContext(), "Enter Billing Address!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(ZipCode)) {
                    Toast.makeText(getApplicationContext(), "Enter Zip Code!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(City)) {
                    Toast.makeText(getApplicationContext(), "Enter City!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(State)) {
                    Toast.makeText(getApplicationContext(), "Enter State!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(Country)) {
                    Toast.makeText(getApplicationContext(), "Enter Country!", Toast.LENGTH_LONG).show();
                    return;
                }

                //Check if card number already exists
                if (db.checkCardExists(CardNumber) == true) {
                    Toast.makeText(getApplicationContext(), "Card Already Exists!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Converting String to Integer for object argument purposes
                final long cardnumber = Long.parseLong(CardNumber);
                final int zipcode = Integer.parseInt(ZipCode);
                final int cvv = Integer.parseInt(CVV);

                //Calling get foreign key function from database
                String r_id = db.getFK();
                //Inserting card registration information...
                db.insertCardData(new CardHolder(1, r_id, CardName, cardnumber, ExpirationDate, cvv, BillingAddress, zipcode, City, State, Country));

                AlertDialog.Builder builder = new AlertDialog.Builder(CardRegistrationActivity.this);
                builder.setTitle("Card Added!");
                builder.setMessage("You May Check Out Now!");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(CardRegistrationActivity.this, Menu1.class));
                    }
                });
                builder.create();
                builder.show();
            }
        });
    }
}
