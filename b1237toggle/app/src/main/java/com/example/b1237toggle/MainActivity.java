package com.example.b1237toggle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleButton = (ToggleButton)findViewById(R.id.toggleButton);
    }

    public void onClickToggle(View view) {
        boolean isOn = toggleButton.isChecked();
        if(isOn){
            Toast.makeText(getApplicationContext(),"Toggle On", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(),"Toggle Off", Toast.LENGTH_SHORT).show();
        }
    }
}