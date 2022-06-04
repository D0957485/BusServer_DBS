package com.example.busserver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.Button;

public class MainActivity extends AppCompatActivity {

  //0603
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button button = (Button) findViewById(R.id.buttonTosearch);
    button.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick (View view) {
        Intent intent = new Intent(MainActivity.this, DriverList.class);
        startActivity(intent);
      }

    });

    Button button2 = (Button) findViewById(R.id.buttonDriverLogin);
    button2.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick (View view) {
        Intent intent = new Intent(MainActivity.this, StartCar.class);
        startActivity(intent);
      }

    });

    Button button3 = (Button) findViewById(R.id.buttonComLogin);
    button3.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick (View view) {
        Intent intent = new Intent(MainActivity.this, CarList.class);
        startActivity(intent);
      }

    });


  }
}

