package com.example.lap.mywaytor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class CardRegistrationActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_registration);
        final EditText etCardName = (EditText) findViewById(R.id.etCardName);
        final EditText etExpirationDate = (EditText) findViewById(R.id.etExpirationDate);
        final EditText etCVV = (EditText) findViewById(R.id.etCVV);
        final EditText etZipcode = (EditText) findViewById(R.id.etZipcode);
        final EditText etBillingAddress = (EditText) findViewById(R.id.etBillingAddress);
        final EditText etCity = (EditText) findViewById(R.id.etCity);
        final EditText etState = (EditText) findViewById(R.id.etState);
        final EditText etCardNumber = (EditText) findViewById(R.id.etCardNumber);

        final Button bAddCard = (Button) findViewById(R.id.bAddCard);
        bAddCard.setText("Add Card");

        bAddCard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /*final String CardName = etCardName.getText().toString();
                final String ExpirationDate = etExpirationDate.getText().toString();
                final String zipcode = etZipcode.getText().toString();
                int Zipcode = Integer.valueOf(zipcode);
                final String cvv = etCVV.getText().toString();
                int CVV = Integer.parseInt(cvv);
                final String BillingAddress = etBillingAddress.getText().toString();
                final String cardnumber = etCardNumber.getText().toString();
                int CardNumber = Integer.parseInt(cardnumber);
                final String City = etCity.getText().toString();
                final String State = etState.getText().toString();
                if (TextUtils.isEmpty(CardName)) {
                    Toast.makeText(getApplicationContext(), "Enter First Name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(cardnumber)) {
                    Toast.makeText(getApplicationContext(), "Enter Last Name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(ExpirationDate)) {
                    Toast.makeText(getApplicationContext(), "Enter Username!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(zipcode)) {
                    Toast.makeText(getApplicationContext(), "Enter Password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(cvv)) {
                    Toast.makeText(getApplicationContext(), "Enter Email!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(BillingAddress)) {
                    Toast.makeText(getApplicationContext(), "Enter Age!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(City)) {
                    Toast.makeText(getApplicationContext(), "Enter Age!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(State)) {
                    Toast.makeText(getApplicationContext(), "Enter Age!", Toast.LENGTH_SHORT).show();
                    return;
                }*/
                startActivity(new Intent(CardRegistrationActivity.this, MenuActivity.class));

            }
        });
    }
}