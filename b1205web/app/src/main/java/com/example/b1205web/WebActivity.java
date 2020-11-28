package com.example.b1205web;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class WebActivity extends AppCompatActivity {

    private WebView webView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        webView1 = (WebView)findViewById(R.id.webview1);

        webView1.getSettings().setJavaScriptEnabled(true);
        webView1.loadUrl("https://en.wikipedia.org/");

    }
}