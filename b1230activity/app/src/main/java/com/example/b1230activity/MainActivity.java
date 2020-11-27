package com.example.b1230activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.editText);

    }

    public void onClickOK(View view) {
        String s = editText.getText().toString();
        Intent intent = new Intent(getApplicationContext(), WebActivity.class);
        intent.putExtra("number", s);
        startActivity(intent);

    }
}