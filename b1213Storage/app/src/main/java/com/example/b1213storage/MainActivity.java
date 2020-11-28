package com.example.b1213storage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView)findViewById(R.id.textView1);
        long megaBytesAvailable = 0;

        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        megaBytesAvailable = (long) statFs.getBlockSizeLong()
                * (long) statFs.getBlockCountLong()
                / 1048576  ;

        textView1.setText(" " + megaBytesAvailable + " MB");
    }
}