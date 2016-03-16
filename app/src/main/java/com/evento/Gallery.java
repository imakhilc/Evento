package com.evento;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Gallery extends AppCompatActivity implements View.OnClickListener {

    Activity context;
    ImageView galpic[]= new ImageView[100];
    Button picby;
    String picby_t;
    ProgressDialog pd;
    JSONArray jArray;
    Button more,add;
    int start=1;
    int j=0,k=5,total,time=0,no=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getSupportActionBar().setElevation(0);
        }
        context=this;
        picby_t="";
        more = (Button) findViewById(R.id.more);
        add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddPhoto.class);
                startActivity(intent);
            }
        });
        try{
            more.setOnClickListener(this);
        }catch (Exception e){

        }
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total-=6;
                start=1;
                onStart();
            }
        });
    }

    public void onStart(){
        super.onStart();
        BackTask bt=new BackTask();
        if(start==1) {
            bt.execute("http://db-event2k16.rhcloud.com/scripts/gallery.php");
            start=0;
        }
    }

    @Override
    public void onClick(View v) {

    }

    private class BackTask extends AsyncTask<String,Integer,Void> {
        String text="";
        protected void onPreExecute(){
            super.onPreExecute();
            pd = new ProgressDialog(context);
            pd.setMessage("Please wait...");
            pd.setCancelable(false);
            pd.setIndeterminate(false);
            pd.show();
        }

        protected Void doInBackground(String...params){
            URL url;
            try {
                url = new URL(params[0]);
                HttpURLConnection con=(HttpURLConnection)url.openConnection();
                InputStream is=con.getInputStream();
                BufferedReader br=new BufferedReader(new InputStreamReader(is));
                String line;
                while((line=br.readLine())!=null){
                    text+=line;
                }

                br.close();

            }catch (Exception e) {
                e.printStackTrace();
                if(pd!=null) pd.dismiss();
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            if (pd != null)
                pd.dismiss();

            try {
                jArray = new JSONArray(text);

                Display display = getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int width = size.x;
                LinearLayout layout1 = (LinearLayout) findViewById(R.id.pics1);
                LinearLayout layout2 = (LinearLayout) findViewById(R.id.pics2);
                LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams((width/2)-dpToPx(15),(width/2)-dpToPx(15));
                LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams((width/2)-dpToPx(15), LinearLayout.LayoutParams.WRAP_CONTENT);
                lp2.setMargins(0, 0, 0, dpToPx(10));
                Typeface font = Typeface.createFromAsset(getAssets(), "fonts/normalone.ttf");

                if(time==0) {
                    total = jArray.length();
                    time++;
                }

                k=total;
                if(total<=6) {
                    more.setVisibility(View.GONE);
                    j = 0;
                }
                else {
                    more.setVisibility(View.VISIBLE);
                    j = total - 6;
                }

                for(int i=k-1;i>=j;i--){
                    JSONObject json = jArray.getJSONObject(i);
                    picby_t = json.getString("about");

                    picby = new Button(Gallery.this);
                    picby.setBackgroundResource(R.drawable.box);
                    picby.setTextColor(Color.parseColor("#666666"));
                    picby.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                    picby.setGravity(Gravity.CENTER);
                    picby.setTransformationMethod(null);
                    picby.setText(picby_t);
                    picby.setTypeface(font);

                    galpic[i] = new ImageView(Gallery.this);
                    galpic[i].setPadding(dpToPx(10), dpToPx(10), dpToPx(10), dpToPx(10));
                    galpic[i].setImageResource(R.drawable.placeholder);
                    galpic[i].setBackgroundColor(Color.parseColor("#ffffff"));
                    galpic[i].setTag("" + i);
                    final int finalI = i;
                    galpic[i].setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            Intent myIntent = new Intent(getApplicationContext(), ViewImage.class);
                            myIntent.putExtra("number", galpic[finalI].getTag().toString());
                            startActivity(myIntent);
                        }
                    });

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        picby.setStateListAnimator(null);
                    }

                    if(no%2!=0) {
                        layout1.addView(galpic[i], lp1);
                        layout1.addView(picby, lp2);
                    }
                    else if(no%2==0) {
                        layout2.addView(galpic[i], lp1);
                        layout2.addView(picby, lp2);
                    }
                    no++;

                    new DownloadImageTask(galpic[i])
                            .execute("http://db-event2k16.rhcloud.com/pics/gallery/gallery_pic_" + i + ".jpg");
                    add.setVisibility(View.VISIBLE);
                }

            } catch (Exception e) {
                // TODO: handle exception
                Log.e("log_tag", "Error Parsing Data " + e.toString());
                if(picby_t=="") {
                    Toast.makeText(Gallery.this, "No Internet Access", Toast.LENGTH_SHORT).show();
                }
            }finally {

            }
        }
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    public Activity getContext() {
        return context;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
    /*@Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(intent);
        finish();
    }*/
}
