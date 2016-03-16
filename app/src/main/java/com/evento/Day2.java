package com.evento;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Day2 extends Fragment {
    ProgressDialog pd;
    String item;
    int stage;
    int day;
    Button item_b;
    TextView stage1,stage2,stage3;
    JSONArray jArray;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        item="";
        return  inflater.inflate(R.layout.day2,container,false);
    }
    public void onStart(){
        super.onStart();
        BackTask bt=new BackTask();
        bt.execute("http://db-event2k16.rhcloud.com/scripts/schedule.php");
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    private class BackTask extends AsyncTask<String,Integer,Void> {
        String text="";
        protected void onPreExecute(){
            super.onPreExecute();
            pd = new ProgressDialog(getActivity());
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
                LinearLayout layout1 = (LinearLayout) getView().findViewById(R.id.two1);
                LinearLayout layout2 = (LinearLayout) getView().findViewById(R.id.two2);
                LinearLayout layout3 = (LinearLayout) getView().findViewById(R.id.two3);
                LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp1.setMargins(0, 0, 0, dpToPx(10));
                Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/normalone.ttf");

                for(int i=0;i<jArray.length(); i++) {
                    JSONObject json = jArray.getJSONObject(i);
                    item = json.getString("item")+"\n\nTime: "+
                            json.getString("time");
                    stage = json.getInt("stage");
                    day = json.getInt("day");
                    item_b = new Button(getActivity());
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        item_b.setStateListAnimator(null);
                    }
                    item_b.setBackgroundResource(R.drawable.box);
                    item_b.setText(item.toUpperCase());
                    item_b.setTextColor(Color.parseColor("#666666"));
                    item_b.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
                    item_b.setGravity(Gravity.LEFT);
                    item_b.setTransformationMethod(null);
                    item_b.setPadding(dpToPx(15), dpToPx(15), dpToPx(15), dpToPx(15));
                    item_b.setTypeface(font);

                    if(day==2) {
                        if (stage == 1) {
                            stage1 = (TextView) getView().findViewById(R.id.stage12);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                stage1.setStateListAnimator(null);
                            }
                            stage1.setTypeface(font);
                            stage1.setVisibility(View.VISIBLE);
                            layout1.addView(item_b, lp1);
                        } else if (stage == 2) {
                            stage2 = (TextView) getView().findViewById(R.id.stage22);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                stage2.setStateListAnimator(null);
                            }
                            stage2.setTypeface(font);
                            stage2.setVisibility(View.VISIBLE);
                            layout2.addView(item_b, lp1);
                        } else if (stage == 3) {
                            stage3 = (TextView) getView().findViewById(R.id.stage32);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                stage3.setStateListAnimator(null);
                            }
                            stage3.setTypeface(font);
                            stage3.setVisibility(View.VISIBLE);
                            layout3.addView(item_b, lp1);
                        }
                    }
                }

            } catch (Exception e) {
                // TODO: handle exception
                Log.e("log_tag", "Error Parsing Data " + e.toString());
                if(item=="") {
                    Toast.makeText(getActivity(), "No Internet Access", Toast.LENGTH_SHORT).show();
                }
            }finally {

            }
        }
    }
}