package com.evento;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Notifications extends AppCompatActivity {

    Activity context;
    ProgressDialog pd;
    String content;
    Button content_b;
    String date;
    Button date_b;
    JSONArray jArray;
    int start=1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getSupportActionBar().setElevation(0);
        }
        context=this;
        content="";
    }

    public void onStart(){
        super.onStart();
        BackTask bt=new BackTask();
        if(start==1) {
            bt.execute("http://db-event2k16.rhcloud.com/scripts/notifications.php");
            start=0;
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
                LinearLayout layout = (LinearLayout) findViewById(R.id.notif_content);
                LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dpToPx(25));
                lp2.setMargins(0, 0, 0, dpToPx(10));
                Typeface font = Typeface.createFromAsset(getAssets(), "fonts/normalone.ttf");

                for(int i=jArray.length()-1;i>=0; i--) {
                    JSONObject json = jArray.getJSONObject(i);
                    content = json.getString("content");
                    date = json.getString("time")+" | "+json.getString("date");

                    content_b = new Button(Notifications.this);
                    date_b = new Button(Notifications.this);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        content_b.setStateListAnimator(null);
                        date_b.setStateListAnimator(null);
                    }
                    content_b.setBackgroundResource(R.drawable.box);
                    content_b.setText(content);
                    content_b.setTextColor(Color.parseColor("#666666"));
                    content_b.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                    content_b.setGravity(Gravity.LEFT);
                    content_b.setTransformationMethod(null);
                    content_b.setPadding(dpToPx(15), dpToPx(15), dpToPx(15), dpToPx(15));
                    content_b.setTypeface(font);

                    date_b.setBackgroundResource(R.drawable.box);
                    date_b.setText(date.toUpperCase());
                    date_b.setTextColor(Color.parseColor("#AFAFAF"));
                    date_b.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                    date_b.setGravity(Gravity.RIGHT | Gravity.TOP);
                    date_b.setPadding(dpToPx(0), dpToPx(0), dpToPx(15), dpToPx(0));

                    layout.addView(content_b, lp1);
                    layout.addView(date_b, lp2);
                }

            } catch (Exception e) {
                // TODO: handle exception
                Log.e("log_tag", "Error Parsing Data " + e.toString());
                if(content=="") {
                    Toast.makeText(Notifications.this, "No Internet Access", Toast.LENGTH_SHORT).show();
                }
            }finally {

            }
        }
    }
    /*@Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(intent);
        finish();
    }*/
}