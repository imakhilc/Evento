package com.evento;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Results extends AppCompatActivity {

    Activity context;
    ProgressDialog pd;
    String title,first,second,third;
    Button title_b,reslt_b,line;
    TextView s21,s41,s61,s81,s22,s42,s62,s82;
    int s2,s4,s6,s8;
    Typeface dig,font,bfont;
    JSONArray jArray;
    int start=1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        dig = Typeface.createFromAsset(getAssets(), "fonts/digital.ttf");
        font = Typeface.createFromAsset(getAssets(), "fonts/normalone.ttf");
        bfont = Typeface.createFromAsset(getAssets(), "fonts/boldone.ttf");

        s21 = (TextView)findViewById(R.id.s2);
        s41 = (TextView)findViewById(R.id.s4);
        s61 = (TextView)findViewById(R.id.s6);
        s81 = (TextView)findViewById(R.id.s8);
        s22 = (TextView)findViewById(R.id.s2score);
        s42 = (TextView)findViewById(R.id.s4score);
        s62 = (TextView)findViewById(R.id.s6score);
        s82 = (TextView)findViewById(R.id.s8score);

        s22.setTypeface(dig);
        s42.setTypeface(dig);
        s62.setTypeface(dig);
        s82.setTypeface(dig);

        s21.setTypeface(font);
        s41.setTypeface(font);
        s61.setTypeface(font);
        s81.setTypeface(font);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getSupportActionBar().setElevation(0);
        }
        context=this;
        title="";
    }

    public void onStart(){
        super.onStart();
        BackTask bt=new BackTask();
        if(start==1) {
            bt.execute("http://db-event2k16.rhcloud.com/scripts/results.php");
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
                LinearLayout layout1 = (LinearLayout) findViewById(R.id.reslt_content);
                LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dpToPx(2));
                lp2.setMargins(0, 0, 0, dpToPx(10));

                int i;

                for(i=0;i<jArray.length(); i++) {
                    JSONObject json = jArray.getJSONObject(i);
                    title = json.getString("event");
                    first = json.getString("1st");
                    second = json.getString("2nd");
                    third = json.getString("3rd");

                    if(title.toUpperCase().contains("G_P")){
                        title = title.replace("G_P", "(Group)");

                        if(first.toUpperCase().contains("S2")){
                            s2+=10;
                        }else if(first.toUpperCase().contains("S4")){
                            s4+=10;
                        }else if(first.toUpperCase().contains("S6")){
                            s6+=10;
                        }else if(first.toUpperCase().contains("S8")){
                            s8+=10;
                        }

                        if(second.toUpperCase().contains("S2")){
                            s2+=6;
                        }else if(second.toUpperCase().contains("S4")){
                            s4+=6;
                        }else if(second.toUpperCase().contains("S6")){
                            s6+=6;
                        }else if(second.toUpperCase().contains("S8")){
                            s8+=6;
                        }

                        if(third.toUpperCase().contains("S2")){
                            s2+=3;
                        }else if(third.toUpperCase().contains("S4")){
                            s4+=3;
                        }else if(third.toUpperCase().contains("S6")){
                            s6+=3;
                        }else if(third.toUpperCase().contains("S8")){
                            s8+=3;
                        }

                    }else{
                        if(first.toUpperCase().contains("S2")){
                            s2+=5;
                        }else if(first.toUpperCase().contains("S4")){
                            s4+=5;
                        }else if(first.toUpperCase().contains("S6")){
                            s6+=5;
                        }else if(first.toUpperCase().contains("S8")){
                            s8+=5;
                        }

                        if(second.toUpperCase().contains("S2")){
                            s2+=3;
                        }else if(second.toUpperCase().contains("S4")){
                            s4+=3;
                        }else if(second.toUpperCase().contains("S6")){
                            s6+=3;
                        }else if(second.toUpperCase().contains("S8")){
                            s8+=3;
                        }

                        if(third.toUpperCase().contains("S2")){
                            s2+=1;
                        }else if(third.toUpperCase().contains("S4")){
                            s4+=1;
                        }else if(third.toUpperCase().contains("S6")){
                            s6+=1;
                        }else if(third.toUpperCase().contains("S8")){
                            s8+=1;
                        }
                    }

                    title_b = new Button(Results.this);
                    reslt_b = new Button(Results.this);
                    line = new Button(Results.this);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        title_b.setStateListAnimator(null);
                        reslt_b.setStateListAnimator(null);
                        line.setStateListAnimator(null);
                    }

                    title_b.setBackgroundResource(R.drawable.box);
                    title_b.setText(title.toUpperCase());
                    // + " \uD83C\uDFC6"+s2+","+s4+","+s6+","+s8
                    title_b.setTextColor(Color.parseColor("#333333"));
                    title_b.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                    title_b.setGravity(Gravity.CENTER);
                    title_b.setPadding(dpToPx(10), dpToPx(10), dpToPx(10), dpToPx(10));
                    title_b.setTypeface(bfont);

                    line.setBackgroundColor(Color.parseColor("#eeeeee"));

                    reslt_b.setBackgroundResource(R.drawable.box);
                    reslt_b.setText("1 ⭐\t\t" + first.toUpperCase() + "\n\n2 ⭐\t\t" + second.toUpperCase() + "\n\n3 ⭐\t\t" + third.toUpperCase());
                    reslt_b.setTextColor(Color.parseColor("#666666"));
                    reslt_b.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
                    reslt_b.setGravity(Gravity.LEFT);
                    reslt_b.setPadding(dpToPx(15), dpToPx(15), dpToPx(15), dpToPx(15));
                    reslt_b.setTypeface(font);

                    layout1.addView(title_b, lp1);
                    layout1.addView(line, lp3);
                    layout1.addView(reslt_b, lp2);
                }

                s22.setText(""+s2);
                s42.setText(""+s4);
                s62.setText(""+s6);
                s82.setText(""+s8);

            } catch (Exception e) {
                // TODO: handle exception
                Log.e("log_tag", "Error Parsing Data " + e.toString());
                if(title=="") {
                    Toast.makeText(Results.this, "No Internet Access", Toast.LENGTH_SHORT).show();
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