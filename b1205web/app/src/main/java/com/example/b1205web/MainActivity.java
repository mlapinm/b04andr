package com.example.b1205web;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button1;
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        button1 = (Button)findViewById(R.id.button1);
    }

    public void onClickButton1(View view) {
        Intent intent = new Intent(context, WebActivity.class);
        startActivity(intent);
    }
}