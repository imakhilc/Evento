package com.evento;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AddPhoto extends Activity {

    int width,height;
    TextView t1,t2,t3,t4,t5,t6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo);

        t1=(TextView) findViewById(R.id.t1);
        t2=(TextView) findViewById(R.id.t2);
        t3=(TextView) findViewById(R.id.t3);
        t4=(TextView) findViewById(R.id.t4);
        t5=(TextView) findViewById(R.id.t5);
        t6=(TextView) findViewById(R.id.t6);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;
        height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), LinearLayout.LayoutParams.WRAP_CONTENT);
        Typeface caviar = Typeface.createFromAsset(getAssets(), "fonts/menufont.ttf");

        t1.setTypeface(caviar);
        t2.setTypeface(caviar);
        t3.setTypeface(caviar);
        t4.setTypeface(caviar);
        t5.setTypeface(caviar);
        t6.setTypeface(caviar);
    }
}
