package com.example.busserver;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {

//  Context context = this;
//  EditText et1, et2, et3, et4;
//  WebView webView;
//  String url = "http://localhost/phpmyadmin/index.php?route=/sql&pos=0&db=bussystem&table=drive";
//  CookieManager cookieManager;
//  String cookieStr, idText;
//  ListView listView1;

  //0603
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

//    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
//    StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedClosableObjects().detectLeakedSqlLiteObjects().penaltyDeath().penaltyLog().build());
//
//    Wcookie(context);
//    Handler myHandler = new Handler();
//    myHandler.postDelayed(runTimerStop, 15000);
//    if (cookieStr != null) {
//      myHandler.removeCallbacks(runTimerStop);
//    }

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


  } //end onCreate

  private Runnable mutiThread = new Runnable() {
    @Override
    public void run() {
      String result1;
      String result2;
      BreakIterator textView = null;
      BreakIterator textView1 = null;
      try {
        URL url = new URL("http://10.0.2.2/GetData.php");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        connection.connect();

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
          InputStream inputStream = connection.getInputStream();
          BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 8);
          String line = null;
          while ((line = bufferedReader.readLine()) != null) {
            JSONArray dataJson = new JSONArray(line);
            int i = dataJson.length() - 1;
            JSONObject info = dataJson.getJSONObject(i);
            String time = info.getString("time");
            String dB = info.getString("dB");
            result1 = time.toString();
            result2 = dB.toString();
          }
          inputStream.close();
        }
      } catch (Exception e) {
        result1 = e.toString();
        result2 = e.toString();
      }

      runOnUiThread(new Runnable() {
        @Override
        public void run() {

          textView.setText(result1);
          textView1.setText(result2);
        }
      });
    }
  };



//  private void Wcookie(Context context) {
//
//    CookieSyncManager.createInstance(context);
//    cookieManager = CookieManager.getInstance();
//    webView = new WebView(context);
//    webView.getSettings().setJavaScriptEnabled(true);
//    webView.setWebViewClient(new WebViewClient() {
//
//      @Override
//      public void onPageFinished(WebView view, String url) {
//
//        super.onPageFinished(view, url);
//        cookieManager.setAcceptCookie(true);
//        cookieStr = cookieManager.getCookie(url);
//
//      }
//
//    });
//
//    webView.loadUrl(url);
//    webView.clearHistory();
//    webView.clearCache(true);
//
//    cookieManager.removeAllCookie();
//    cookieManager.removeSessionCookie();
//
//  } //end Wcookie
//
//  private Runnable runTimerStop = new Runnable() {
//    @Override
//    public void run() {
//
//      select(null);
//
//    }
//  };
//
//  public void select(String id) {
//
//    try {
//
//    } catch (Exception e) {
//      Log.e("log_tag=", e.toString());
//    }
//
//  } //end select

} //end class

