package com.example.b1217pdf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        final WebView webView1;
        webView1 = (WebView)findViewById(R.id.webview1);
        webView1.getSettings().setJavaScriptEnabled(true);
        webView1.loadUrl("http://www.adobe.com/content/dam/acom/en/devnet/acrobat/pdfs/PDFOpenParameters.pdf");
    }
}