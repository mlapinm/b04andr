package com.example.freon.b1245timepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    TextView textView1;
    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView)findViewById(R.id.textView1);
        timePicker = (TimePicker)findViewById(R.id.timePicker);

        textView1.setText(getTime());

    }

    public void onClickButton1(View view) {
        textView1.setText(getTime());
    }

    public String getTime(){
        String sTime = "Time: " + timePicker.getHour()
                + " : " + timePicker.getMinute();
        return sTime;
    }
}