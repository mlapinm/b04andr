package com.example.freon.b1243rating;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickButton1(View view) {
        TextView textView1;
        RatingBar ratingBar1;

        textView1 = (TextView)findViewById(R.id.textView1);
        ratingBar1 = (RatingBar)findViewById(R.id.ratingBar1);

        float value1 = ratingBar1.getRating();
        String sValue1 = String.valueOf(value1);
        textView1.setText(sValue1);
    }
}