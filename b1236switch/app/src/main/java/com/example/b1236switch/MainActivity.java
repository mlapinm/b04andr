package com.example.b1236switch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Switch switch1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switch1 = (Switch)findViewById(R.id.switch1);

    }

    public void onClickChange(View view) {
        boolean isChecked = switch1.isChecked();
        if(isChecked == true){
            Toast.makeText(getBaseContext(), "On", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getBaseContext(), "Off", Toast.LENGTH_SHORT).show();
        }
    }
}