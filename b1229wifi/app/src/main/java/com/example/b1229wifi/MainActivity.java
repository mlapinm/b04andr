package com.example.b1229wifi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView1;
    WifiManager wifiManager;
    WifiInfo wifiInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView)findViewById(R.id.textView1);
        wifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
        wifiInfo = wifiManager.getConnectionInfo();
        String sDisplay;
        sDisplay = " SSID: " + wifiInfo.getSSID() + "\n"
                + " RSSi: " + wifiInfo.getRssi() + "\n"
                + " Mac Address: " + wifiInfo.getMacAddress();
        textView1.setText(sDisplay);

    }
}