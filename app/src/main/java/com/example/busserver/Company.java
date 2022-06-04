package com.example.busserver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Company extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        Button button = (Button) findViewById(R.id.Car);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick (View view) {
                Intent intent = new Intent(Company.this, CarList.class);
                startActivity(intent);
            }

        });

        Button button2 = (Button) findViewById(R.id.Driver);
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick (View view) {
                Intent intent = new Intent(Company.this, ListOfDriver.class);
                startActivity(intent);
            }

        });

        Button button3 = (Button) findViewById(R.id.pre);
        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick (View view) {
                finish();
            }

        });
    }
}