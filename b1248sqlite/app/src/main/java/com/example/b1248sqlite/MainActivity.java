package com.example.b1248sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView)findViewById(R.id.textView1);

        String sName;

        SQLiteDatabase db = openOrCreateDatabase("MyDB", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS myTable(Name VARCHAR, Age INT(3));");
        db.execSQL("INSERT INTO myTable VALUES('Ari', 27)");
        db.execSQL("INSERT INTO myTable VALUES('Ami', 37)");
        Cursor cursor = db.rawQuery("SELECT * FROM myTable", null);
        cursor.moveToFirst();
        Log.d("Ari", cursor.getString(cursor.getColumnIndex("Name")));
        Log.d("Ari", cursor.getString(cursor.getColumnIndex("Age")));
        cursor.move(1);
        Log.d("Ari", cursor.getString(cursor.getColumnIndex("Name")));
        Log.d("Ari", cursor.getString(cursor.getColumnIndex("Age")));
        cursor.move(-1);
        Log.d("Ari", cursor.getString(cursor.getColumnIndex("Name")));
        Log.d("Ari", cursor.getString(cursor.getColumnIndex("Age")));
        sName = cursor.getString(cursor.getColumnIndex("Name"));
        db.close();
        textView1.setText(sName);

    }
}