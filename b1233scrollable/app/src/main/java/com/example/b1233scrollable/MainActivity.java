package com.example.b1233scrollable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        textView.setMovementMethod((new ScrollingMovementMethod()));

        String data = "";

        StringBuffer stringBuffer = new StringBuffer();

        InputStream inputStream = this.getResources().openRawResource(R.raw.sample);

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        if(inputStream != null){
            try{
                while ((data = reader.readLine()) != null){
                    stringBuffer.append(data + "\n");
                }
                textView.setText(stringBuffer);
                inputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}