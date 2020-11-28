package com.example.b1209url;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button)findViewById(R.id.button1);

    }

    public void onClickButton1(View view) {

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://ru.wikipedia.org"));
        startActivity(intent);

    }
}