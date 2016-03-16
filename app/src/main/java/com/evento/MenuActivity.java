package com.evento;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    Button b1,b2,b3,b4,b5,b6,b7,b8,i1,i2,i3,i4,i5,i6,i7,i8;
    TextView title;
    int x=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        title = (TextView) findViewById(R.id.title);

        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        b5 = (Button) findViewById(R.id.b5);
        b6 = (Button) findViewById(R.id.b6);
        b7 = (Button) findViewById(R.id.b7);
        b8 = (Button) findViewById(R.id.b8);

        i1 = (Button) findViewById(R.id.i1);
        i2 = (Button) findViewById(R.id.i2);
        i3 = (Button) findViewById(R.id.i3);
        i4 = (Button) findViewById(R.id.i4);
        i5 = (Button) findViewById(R.id.i5);
        i6 = (Button) findViewById(R.id.i6);
        i7 = (Button) findViewById(R.id.i7);
        i8 = (Button) findViewById(R.id.i8);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            b1.setStateListAnimator(null);
            b2.setStateListAnimator(null);
            b3.setStateListAnimator(null);
            b4.setStateListAnimator(null);
            b5.setStateListAnimator(null);
            b6.setStateListAnimator(null);
            b7.setStateListAnimator(null);
            b8.setStateListAnimator(null);
        }

        Typeface menutext = Typeface.createFromAsset(getAssets(), "fonts/menufont.ttf");
        Typeface icon = Typeface.createFromAsset(getAssets(), "fonts/fontawesome.ttf");
        title.setTypeface(menutext);
        b1.setTypeface(menutext);
        b2.setTypeface(menutext);
        b3.setTypeface(menutext);
        b4.setTypeface(menutext);
        b5.setTypeface(menutext);
        b6.setTypeface(menutext);
        b7.setTypeface(menutext);
        b8.setTypeface(menutext);
        i1.setTypeface(icon);
        i2.setTypeface(icon);
        i3.setTypeface(icon);
        i4.setTypeface(icon);
        i5.setTypeface(icon);
        i6.setTypeface(icon);
        i7.setTypeface(icon);
        i8.setTypeface(icon);

        i1.setText("\uf005");
        i2.setText("\uf073");
        i3.setText("\uf091");
        i4.setText("\uf15c");
        i5.setText("\uf1c5");
        i6.setText("\uf0a1");
        i7.setText("\uf164");
        i8.setText("\uf0c0");

        try {
            b1.setOnClickListener(this);
            b2.setOnClickListener(this);
            b3.setOnClickListener(this);
            b4.setOnClickListener(this);
            b5.setOnClickListener(this);
            b6.setOnClickListener(this);
            b7.setOnClickListener(this);
            b8.setOnClickListener(this);

            i1.setOnClickListener(this);
            i2.setOnClickListener(this);
            i3.setOnClickListener(this);
            i4.setOnClickListener(this);
            i5.setOnClickListener(this);
            i6.setOnClickListener(this);
            i7.setOnClickListener(this);
            i8.setOnClickListener(this);
        } catch (Exception e) {

        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b1:
            case R.id.i1:
                Intent intent1 = new Intent(getApplicationContext(), Event.class);
                startActivity(intent1);
                //finish();
                break;
            case R.id.b2:
            case R.id.i2:
                Intent intent2 = new Intent(getApplicationContext(), Schedule.class);
                startActivity(intent2);
                //finish();
                break;
            case R.id.b3:
            case R.id.i3:
                Intent intent3 = new Intent(getApplicationContext(), Results.class);
                startActivity(intent3);
                ////finish();
                break;
            case R.id.b4:
            case R.id.i4:
                Intent intent4 = new Intent(getApplicationContext(), Rules.class);
                startActivity(intent4);
                //finish();
                break;
            case R.id.b5:
            case R.id.i5:
                Intent intent5 = new Intent(getApplicationContext(), Gallery.class);
                startActivity(intent5);
                //finish();
                break;
            case R.id.b6:
            case R.id.i6:
                Intent intent6 = new Intent(getApplicationContext(), Notifications.class);
                startActivity(intent6);
                //finish();
                break;
            case R.id.b7:
            case R.id.i7:
                Intent intent7 = new Intent(getApplicationContext(), Dedications.class);
                startActivity(intent7);
                //finish();
                break;
            case R.id.b8:
            case R.id.i8:
                Intent intent8 = new Intent(getApplicationContext(), About.class);
                startActivity(intent8);
                //finish();
                break;
        }
    }
    @Override
    public void onBackPressed() {
        if(x==0) {
            Toast.makeText(MenuActivity.this, "Press again to exit", Toast.LENGTH_SHORT).show();
            x++;
        }else
            finish();
    }
}