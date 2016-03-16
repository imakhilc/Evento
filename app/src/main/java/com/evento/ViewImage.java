package com.evento;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ViewImage extends Activity {

    String i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        Intent myIntent = getIntent();
        i = myIntent.getStringExtra("number");
        String imageUrl = "http://db-event2k16.rhcloud.com/pics/gallery/full_res/gallery_pic_" + i + ".jpg";
        WebView wv = (WebView) findViewById(R.id.fullimage);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setLoadWithOverviewMode(true);
        wv.getSettings().setUseWideViewPort(true);
        wv.getSettings().setBuiltInZoomControls(true);
        wv.loadUrl(imageUrl);
    }
}