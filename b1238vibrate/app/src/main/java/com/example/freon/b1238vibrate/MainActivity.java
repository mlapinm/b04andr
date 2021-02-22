package com.example.freon.b1238vibrate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vibrator = (Vibrator)this.getSystemService(Context.VIBRATOR_SERVICE);
    }

    public void onClickButton(View view) {
        vibrator.vibrate(180);
    }
}