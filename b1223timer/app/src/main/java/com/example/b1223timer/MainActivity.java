package com.example.b1223timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView)findViewById(R.id.textView1);

    }

    public void onClickButton1(View view) {

        CountDownTimer countDownTimer = new CountDownTimer(60*1000, 10) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView1.setText("Countdown: " + millisUntilFinished);
            }

            @Override
            public void onFinish() {
                textView1.setText("Countdown End");
            }
        }.start();
    }
}