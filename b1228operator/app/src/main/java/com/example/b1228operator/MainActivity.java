package com.example.b1228operator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView)findViewById(R.id.textView1);

        TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        textView1.setText("Mobil Operator Code: "
        + tm.getSimOperator()
        + "\n Operator Name: " + tm.getSimOperatorName()
        + "\n Country: " + tm.getSimCountryIso());

        tm.getSimCountryIso();
    }
}