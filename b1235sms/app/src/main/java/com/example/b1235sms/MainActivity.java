package com.example.b1235sms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextPhone;
    EditText editTextContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextPhone = (EditText)findViewById(R.id.editTextPhone);
        editTextContent = (EditText)findViewById(R.id.editTextContent);
    }

    public void onClickButton1(View view) {
        String sPhone = editTextPhone.getText().toString();
        String sContent = editTextContent.getText().toString();

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(sPhone, null, sContent, null, null);
            Toast.makeText(getApplicationContext(), "SMS Sent", Toast.LENGTH_LONG).show();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "SMS Fail", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }
}