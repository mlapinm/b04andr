package com.example.b1230activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class WebActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        textView = (TextView)findViewById(R.id.textView);

        Intent intent = getIntent();
        String s = intent.getStringExtra("number");
        textView.setText(s);

    }
}