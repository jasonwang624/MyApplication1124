package com.example.user.myapplication1124;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = (ImageView) findViewById(R.id.imageView);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        ImageRequest request = new ImageRequest("http://www.drodd.com/images14/flower26.jpg",
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        img.setImageBitmap(response);
                    }
                }, 0, 0, ImageView.ScaleType.FIT_CENTER, Bitmap.Config.RGB_565,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("NET", error.getMessage());
                    }
                });
        queue.add(request);
        queue.start();
    }
    public void click1 (View v)
    {
        Intent it = new Intent(MainActivity.this,Main2Activity.class);
        startActivity(it);
    }
}

