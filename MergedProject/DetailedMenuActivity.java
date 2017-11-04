package com.example.lap.mywaytor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;


public class DetailedMenuActivity extends AppCompatActivity {


    private Button confirm;
    TextView count;
    int counts = 0;

    TextView name;
    EditText numberCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_menu);

        count = (TextView) findViewById(R.id.countOrder);

        confirm = (Button) findViewById(R.id.confirmButton);
        numberCount = (EditText) findViewById(R.id.countOrder);
        name = (TextView) findViewById(R.id.friendChickens);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                String x = name.getText().toString();
                String a = numberCount.getText().toString();

                Intent confirmB= new Intent(DetailedMenuActivity.this, MenuActivity.class);
                confirmB.putExtra("FOODNAME", x);
                confirmB.putExtra("NUMBEROFORDER", a);
                startActivity(confirmB);
            }
        });

    }

    public void countOrders(View v){
        counts++;
        count.setText(Integer.toString(counts));

    }


    public void dCountOrders(View v){
        if(counts > 0)
        {
            counts--;
        }
        count.setText(Integer.toString(counts));

    }
}