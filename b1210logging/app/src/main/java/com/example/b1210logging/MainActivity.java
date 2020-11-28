package com.example.b1210logging;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClickButton1(View view) {

        Log.e("error", "This is error");
        Log.v("warning", "This is warning");
        Log.i("info", "This is info");
        Log.d("debug", "This is debug");
        Log.v("verbose", "This is verbose");

    }
}