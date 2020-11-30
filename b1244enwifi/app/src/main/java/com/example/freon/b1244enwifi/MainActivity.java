package com.example.freon.b1244enwifi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.net.wifi.aware.WifiAwareManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    WifiManager wifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
    }

    public void onClickBuOn(View view) {
        Toast.makeText(getApplicationContext(),"Wifi On", Toast.LENGTH_LONG).show();
        wifiManager.setWifiEnabled(true);

    }

    public void onClickBuOff(View view) {
        Toast.makeText(getApplicationContext(),"Wifi Off", Toast.LENGTH_LONG).show();
//        wifiManager.setWifiEnabled(false);
        Intent panelIntent = new Intent(Settings.Panel.ACTION_WIFI);
        startActivityForResult(panelIntent, 1);

    }
}