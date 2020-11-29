package com.example.b1226model;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView) findViewById(R.id.textView1);

        String sModelNumber = Build.MODEL;
        String sBoard = Build.BOARD;
        String sBrand = Build.BRAND;

        textView1.setText("Model: " + sModelNumber +
         "\n" + "Board: " + sBoard +
         "\n" + "Brand: " + sBrand);

    }
}