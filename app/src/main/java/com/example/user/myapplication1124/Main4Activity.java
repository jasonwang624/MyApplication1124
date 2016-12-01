package com.example.user.myapplication1124;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Main4Activity extends AppCompatActivity {
    ImageView img3;
    TextView tv4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        tv4 = (TextView) findViewById(R.id.textView4);
        img3 = (ImageView) findViewById(R.id.imageView3);

        DLImageTask task = new DLImageTask();
        task.execute("http://www.drodd.com/images14/flower26.jpg");
    }
    class DLImageTask extends AsyncTask<String , Integer ,Bitmap>
    {
        private Bitmap bitmap = null;
        private InputStream inputStream = null;
        private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        @Override
        protected Bitmap doInBackground(String... params) {
            try
            {
                URL url = new URL(params[0]);
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
                    int sum = (int) ((readAllSize / fullSize) * 100); // 累計讀取進度\
                    publishProgress(sum);
                }
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {

            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] result = outputStream.toByteArray();
            bitmap = BitmapFactory.decodeByteArray(result, 0, result.length);
            return bitmap;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            tv4.setText(String.valueOf(values[0]));
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            img3.setImageBitmap(bitmap);

        }
    }
}
