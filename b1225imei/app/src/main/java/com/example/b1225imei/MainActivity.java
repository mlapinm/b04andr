package com.example.b1225imei;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView1;
    TelephonyManager telephonyManager;
    String sIMEI;
    String sSIMNum;
    String sOperator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = this;

        textView1 = (TextView) findViewById(R.id.textView1);
    }

    @SuppressLint("MissingPermission")
    public void onClickButton1(View view) {

        try {
            telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
//                return;
            }
            sSIMNum = telephonyManager.getSimSerialNumber();
            sIMEI = telephonyManager.getDeviceId();
        }catch (Exception e){
            e.printStackTrace();
        }
        sOperator = String.valueOf(telephonyManager.getSimOperatorName());


        if(sOperator != null){
            textView1.setText("IMEI Number: " + sIMEI);
            textView1.append("\n" + "Sim Serial Number: " + sSIMNum);
            textView1.append("\n" + "Operator name: " + sOperator);
        }
    }
}