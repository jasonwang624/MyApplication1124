package com.example.user.myapplication1124;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        tv = (TextView) findViewById(R.id.textView3);
        MyTask task = new MyTask();
        task.execute(5);
    }
    public void click1(View v)
    {
        Intent it = new Intent(Main3Activity.this,Main4Activity.class);
        startActivity(it);
    }

    class MyTask extends AsyncTask<Integer,Integer,Integer>{

        @Override
        protected Integer doInBackground(Integer... params) {
            int n = params[0];
            for(int i=0; i<n; i++)
            {
                Log.d("TASK",String.valueOf(i));
                publishProgress(i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return 100;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            tv.setText(String.valueOf(values[0]));
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            tv.setText(String.valueOf(integer));
        }
    }
}
