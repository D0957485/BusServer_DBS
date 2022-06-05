package com.example.busserver;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.webkit.CookieManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

  Context context = this;
  EditText et1, et2, et3, et4;
  WebView webView;
  String url = "http://localhost/phpmyadmin/index.php?route=/sql&pos=0&db=bussystem&table=drive";
  CookieManager cookieManager;
  String cookieStr, idText;
  ListView listView1;

  //0603
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button button = (Button) findViewById(R.id.buttonTosearch);
    button.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick (View view) {
        Intent intent = new Intent(MainActivity.this, BusSearch.class);
        startActivity(intent);
      }

    });

    Button button2 = (Button) findViewById(R.id.buttonDriverLogin);
    button2.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick (View view) {
        Intent intent = new Intent(MainActivity.this, BusDriverLogIn.class);
        startActivity(intent);
      }

    });

    Button button3 = (Button) findViewById(R.id.buttonComLogin);
    button3.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick (View view) {
        Intent intent = new Intent(MainActivity.this, CompanyLogin.class);
        startActivity(intent);
      }

    });


  }
}

