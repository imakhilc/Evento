package com.evento;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class About extends AppCompatActivity implements View.OnClickListener {

    TextView thanks,title2, fb1, tw1, wh1, fb2, tw2, wh2, fb3, tw3, wh3, fb4, tw4, wh4, tfb1, ttw1, twh1, tfb2, ttw2, twh2, tfb3, ttw3, twh3, tfb4, ttw4, twh4, tme1, tme2, tme3, tme4;
    Button feed;
    Activity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Typeface icon = Typeface.createFromAsset(getAssets(), "fonts/fontawesome.ttf");
        Typeface caviar = Typeface.createFromAsset(getAssets(), "fonts/menufont.ttf");
        context=this;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getSupportActionBar().setElevation(0);
        }
        title2 = (TextView) findViewById(R.id.title2);
        feed = (Button) findViewById(R.id.feed);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            feed.setStateListAnimator(null);
        }

        thanks = (TextView) findViewById(R.id.thanks);
        tme1 = (TextView) findViewById(R.id.tme1);
        tme2 = (TextView) findViewById(R.id.tme2);
        tme3 = (TextView) findViewById(R.id.tme3);
        tme4 = (TextView) findViewById(R.id.tme4);

        fb1 = (TextView) findViewById(R.id.fb1);
        tw1 = (TextView) findViewById(R.id.tw1);
        wh1 = (TextView) findViewById(R.id.wh1);
        fb2 = (TextView) findViewById(R.id.fb2);
        tw2 = (TextView) findViewById(R.id.tw2);
        wh2 = (TextView) findViewById(R.id.wh2);
        fb3 = (TextView) findViewById(R.id.fb3);
        tw3 = (TextView) findViewById(R.id.tw3);
        wh3 = (TextView) findViewById(R.id.wh3);
        fb4 = (TextView) findViewById(R.id.fb4);
        tw4 = (TextView) findViewById(R.id.tw4);
        wh4 = (TextView) findViewById(R.id.wh4);
        tfb1 = (TextView) findViewById(R.id.tfb1);
        ttw1 = (TextView) findViewById(R.id.ttw1);
        twh1 = (TextView) findViewById(R.id.twh1);
        tfb2 = (TextView) findViewById(R.id.tfb2);
        ttw2 = (TextView) findViewById(R.id.ttw2);
        twh2 = (TextView) findViewById(R.id.twh2);
        tfb3 = (TextView) findViewById(R.id.tfb3);
        ttw3 = (TextView) findViewById(R.id.ttw3);
        twh3 = (TextView) findViewById(R.id.twh3);
        tfb4 = (TextView) findViewById(R.id.tfb4);
        ttw4 = (TextView) findViewById(R.id.ttw4);
        twh4 = (TextView) findViewById(R.id.twh4);

        thanks.setTypeface(caviar);
        title2.setTypeface(caviar);
        tme1.setTypeface(caviar);
        tme2.setTypeface(caviar);
        tme3.setTypeface(caviar);
        tme4.setTypeface(caviar);
        fb1.setTypeface(icon);
        tw1.setTypeface(icon);
        wh1.setTypeface(icon);
        fb2.setTypeface(icon);
        tw2.setTypeface(icon);
        wh2.setTypeface(icon);
        fb3.setTypeface(icon);
        tw3.setTypeface(icon);
        wh3.setTypeface(icon);
        fb4.setTypeface(icon);
        tw4.setTypeface(icon);
        wh4.setTypeface(icon);

        feed.setTypeface(icon);

        fb1.setText("\uf230");
        tw1.setText("\uf003");
        wh1.setText("\uf232");
        fb2.setText("\uf230");
        tw2.setText("\uf003");
        wh2.setText("\uf232");
        fb3.setText("\uf230");
        tw3.setText("\uf003");
        wh3.setText("\uf232");
        fb4.setText("\uf230");
        tw4.setText("\uf003");
        wh4.setText("\uf232");

        feed.setText("\uf0e0");

        try {
            feed.setOnClickListener(this);
            fb1.setOnClickListener(this);
            tw1.setOnClickListener(this);
            wh1.setOnClickListener(this);
            fb2.setOnClickListener(this);
            tw2.setOnClickListener(this);
            wh2.setOnClickListener(this);
            fb3.setOnClickListener(this);
            tw3.setOnClickListener(this);
            wh3.setOnClickListener(this);
            fb4.setOnClickListener(this);
            tw4.setOnClickListener(this);
            wh4.setOnClickListener(this);
            tfb1.setOnClickListener(this);
            ttw1.setOnClickListener(this);
            twh1.setOnClickListener(this);
            tfb2.setOnClickListener(this);
            ttw2.setOnClickListener(this);
            twh2.setOnClickListener(this);
            tfb3.setOnClickListener(this);
            ttw3.setOnClickListener(this);
            twh3.setOnClickListener(this);
            tfb4.setOnClickListener(this);
            ttw4.setOnClickListener(this);
            twh4.setOnClickListener(this);
        } catch (Exception e) {

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fb1:
            case R.id.tfb1:
                Uri uri1 = Uri.parse("http://www.facebook.com/imAKHILc");
                Intent intent1 = new Intent(Intent.ACTION_VIEW, uri1);
                startActivity(intent1);
                break;
            case R.id.tw1:
            case R.id.ttw1:
                Intent emailIntent1 = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent1.setType("plain/text");
                emailIntent1.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"imakhilc@gmail.com"});
                emailIntent1.putExtra(android.content.Intent.EXTRA_SUBJECT, "to App Admin");
                emailIntent1.putExtra(android.content.Intent.EXTRA_TEXT, "");
                startActivity(Intent.createChooser(emailIntent1, "Send Mail"));
                break;
            case R.id.wh1:
            case R.id.twh1:
                Intent callIntent1 = new Intent(Intent.ACTION_CALL);
                callIntent1.setData(Uri.parse("tel:+918281719809"));
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent1);
                break;
            case R.id.fb2:
            case R.id.tfb2:
                Uri uri2 = Uri.parse("http://www.facebook.com/akshajkiran");
                Intent intent4 = new Intent(Intent.ACTION_VIEW, uri2);
                startActivity(intent4);
                break;
            case R.id.tw2:
            case R.id.ttw2:
                Intent emailIntent2 = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent2.setType("plain/text");
                emailIntent2.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"imaxian7@gmail.com"});
                emailIntent2.putExtra(android.content.Intent.EXTRA_SUBJECT, "to App Admin");
                emailIntent2.putExtra(android.content.Intent.EXTRA_TEXT, "");
                startActivity(Intent.createChooser(emailIntent2, "Send Mail"));
                break;
            case R.id.wh2:
            case R.id.twh2:
                Intent callIntent2 = new Intent(Intent.ACTION_CALL);
                callIntent2.setData(Uri.parse("tel:+918089128035"));
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent2);
                break;
            case R.id.fb3:
            case R.id.tfb3:
                Uri uri3 = Uri.parse("http://www.facebook.com/amrith.babu");
                Intent intent7 = new Intent(Intent.ACTION_VIEW, uri3);
                startActivity(intent7);
                break;
            case R.id.tw3:
            case R.id.ttw3:
                Intent emailIntent3 = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent3.setType("plain/text");
                emailIntent3.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"amrithbabunv@gmail.com"});
                emailIntent3.putExtra(android.content.Intent.EXTRA_SUBJECT, "to App Admin");
                emailIntent3.putExtra(android.content.Intent.EXTRA_TEXT, "");
                startActivity(Intent.createChooser(emailIntent3, "Send Mail"));
                break;
            case R.id.wh3:
            case R.id.twh3:
                Intent callIntent3 = new Intent(Intent.ACTION_CALL);
                callIntent3.setData(Uri.parse("tel:+919497696252"));
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent3);
                break;
            case R.id.fb4:
            case R.id.tfb4:
                Uri uri4 = Uri.parse("http://www.facebook.com/arunvvr");
                Intent intent10 = new Intent(Intent.ACTION_VIEW, uri4);
                startActivity(intent10);
                break;
            case R.id.tw4:
            case R.id.ttw4:
                Intent emailIntent4 = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent4.setType("plain/text");
                emailIntent4.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"av03445@gmail.com"});
                emailIntent4.putExtra(android.content.Intent.EXTRA_SUBJECT, "to App Admin");
                emailIntent4.putExtra(android.content.Intent.EXTRA_TEXT, "");
                startActivity(Intent.createChooser(emailIntent4, "Send Mail"));
                break;
            case R.id.wh4:
            case R.id.twh4:
                Intent callIntent4 = new Intent(Intent.ACTION_CALL);
                callIntent4.setData(Uri.parse("tel:+918129929415"));
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent4);
                break;
            case R.id.feed:
                startActivity(new Intent(About.this,Feedback.class));
                break;
        }
    }

    /*@Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(intent);
        finish();
    }*/
}