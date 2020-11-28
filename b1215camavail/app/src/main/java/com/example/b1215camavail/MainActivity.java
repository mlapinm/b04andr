package com.example.b1215camavail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static PackageManager packageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = this;

        packageManager = context.getPackageManager();
    }

    public void onClickButton1(View view) {
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            Toast.makeText(getApplicationContext(), "Camera Available",
                    Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), "Camera Not Available",
                    Toast.LENGTH_LONG).show();
        }
    }
}