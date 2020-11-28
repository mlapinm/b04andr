package com.example.b1241audio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Context context = this;
    MediaPlayer mediaPlayer;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button);

        mediaPlayer = MediaPlayer.create(context, R.raw.survive);




    }

    public void onClickPlay(View view) {
        try{
            if(mediaPlayer.isPlaying()){
                Toast.makeText(getApplicationContext(),"Toggle Off", Toast.LENGTH_SHORT).show();
                mediaPlayer.stop();
                }else{
                Toast.makeText(getApplicationContext(),"Toggle On", Toast.LENGTH_SHORT).show();
                mediaPlayer.release();
                mediaPlayer = MediaPlayer.create(context, R.raw.survive);
                mediaPlayer.start();
                }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer.isPlaying()) {
            Toast.makeText(getApplicationContext(), "Toggle Off", Toast.LENGTH_SHORT).show();
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}