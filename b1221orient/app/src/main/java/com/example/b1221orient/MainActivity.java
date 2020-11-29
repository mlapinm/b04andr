package com.example.b1221orient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Surface;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int rotation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rotation = getWindowManager().getDefaultDisplay().getRotation();
    }

    public void onClickButton1(View view) {
        String text;
        switch (rotation){
            case Surface.ROTATION_0:
                text = "SCREEN_ORIENTATION_PORTRAIT";
                break;
            case Surface.ROTATION_90:
                text = "SCREEN_ORIENTATION_LANDSCAPE";
                break;
            case Surface.ROTATION_180:
                text = "SCREEN_ORIENTATION_REVERSE_PORTRIT";
                break;
            case Surface.ROTATION_270:
                text = "SCREEN_ORIENTATION_REVERSE_LANDSCAPE";
                break;
            default:
                text = "SCREEN_ORIENTATION_PORTRAIT";
        }
        Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG).show();
    }
}
