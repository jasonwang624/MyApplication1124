package com.example.user.myapplication1124;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Main2Activity extends AppCompatActivity {

    private Bitmap bitmap = null;
    private InputStream inputStream = null;
    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    ImageView img;
    TextView tv;
    double sum = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        img = (ImageView)findViewById(R.id.imageView2);
        tv = (TextView) findViewById(R.id.textView2);

        new Thread() {
            @Override
            public void run() {
                super.run();
                try
                {
                    URL url = new URL("http://www.letu.life/wp-content/uploads/2015/09/14420348702333.jpg");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.connect();
                    inputStream = conn.getInputStream();

                    double fullSize = conn.getContentLength(); // 總長度
                    byte[] buffer = new byte[64]; // buffer ( 每次讀取長度 )
                    int readSize = 0; // 當下讀取長度
                    int readAllSize = 0;


                    while ((readSize = inputStream.read(buffer)) != -1)
                    {
                        outputStream.write(buffer, 0, readSize);
                        readAllSize += readSize;
                        sum = (readAllSize / fullSize) * 100; // 累計讀取進度
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tv.setText(String.valueOf(sum));
                            }
                        });
                    }
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {

                } catch (IOException e) {
                    e.printStackTrace();
                }
                byte[] result = outputStream.toByteArray();
                bitmap = BitmapFactory.decodeByteArray(result, 0, result.length);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        img.setImageBitmap(bitmap);
                    }
                });
            }
        }.start();
    }
    public void click1 (View v)
    {
        Intent it = new Intent(Main2Activity.this,Main3Activity.class);
        startActivity(it);
    }

}
