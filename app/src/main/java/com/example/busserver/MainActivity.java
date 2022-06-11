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
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {

  String result;
  TextView textView;

  //0603
  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button button = (Button) findViewById(R.id.buttonTosearch);
    textView = (TextView) findViewById(R.id.testTextView);
    button.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick (View view) {

        Intent intentA = new Intent(MainActivity.this, BusSearch.class);
        startActivity(intentA);
        Thread thread = new Thread(mutiThread);
        thread.start();

      }

    });

    Button button2 = (Button) findViewById(R.id.buttonDriverLogin);
    button2.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick (View view) {

        Intent intentB = new Intent(MainActivity.this, BusDriverLogIn.class);
        startActivity(intentB);

      }

    });

    Button button3 = (Button) findViewById(R.id.buttonComLogin);
    button3.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick (View view) {
        Intent intentC = new Intent(MainActivity.this, CompanyLogin.class);
        startActivity(intentC);
      }

    });


  } //end onCreate

  private Runnable mutiThread = new Runnable(){
    public void run()
    {

      try {

        URL url = new URL("http://10.0.2.2/GetData.php");
//        URL url = new URL("http://192.168.0.2/GetData.php");
        // 開始宣告 HTTP 連線需要的物件，這邊通常都是一綑的
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // 建立 Google 比較挺的 HttpURLConnection 物件
        connection.setRequestMethod("POST");
        // 設定連線方式為 POST
        connection.setDoOutput(true); // 允許輸出
        connection.setDoInput(true); // 允許讀入
        connection.setUseCaches(false); // 不使用快取
        connection.connect(); // 開始連線

        int responseCode =
            connection.getResponseCode();
        // 建立取得回應的物件
        if(responseCode ==
            HttpURLConnection.HTTP_OK){
          // 如果 HTTP 回傳狀態是 OK ，而不是 Error
          InputStream inputStream =
              connection.getInputStream();
          // 取得輸入串流
          BufferedReader bufReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 8);
          // 讀取輸入串流的資料
          String box = ""; // 宣告存放用字串
          String line = null; // 宣告讀取用的字串
          while((line = bufReader.readLine()) != null) {
            box += line + "\n";
            // 每當讀取出一列，就加到存放字串後面
          }
          inputStream.close(); // 關閉輸入串流

          result = box; // 把存放用字串放到全域變數
        }
        // 讀取輸入串流並存到字串的部分
        // 取得資料後想用不同的格式
        // 例如 Json 等等，都是在這一段做處理

      } catch(Exception e) {
        result = e.toString(); // 如果出事，回傳錯誤訊息
      }

      // 當這個執行緒完全跑完後執行
      runOnUiThread(new Runnable() {
        public void run() {
          textView.setText(result); // 更改顯示文字
        }
      });
    }
  };

} //end class

