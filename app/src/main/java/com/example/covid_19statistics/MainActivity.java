package com.example.covid_19statistics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {
    private TextView totalcnfr,todaycnfrm,totalact,todayactve,totalrecv,totaltst,totaldeath;
    private TextView todayrecv, todaytst,todaydthe,cntry;
    private TextView date;
    private PieChart pie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        fetchdata();
        cntry=findViewById(R.id.textview1);
        cntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Affected_Countries.class));
            }
        });
    }

    private void setText(String updated) {
        DateFormat format=new SimpleDateFormat("MMM dd yyyy");
        long ms=Long.parseLong(updated);
        Calendar cal= Calendar.getInstance();
        cal.setTimeInMillis(ms);
        date.setText("Updated on "+format.format(cal.getTime()));
    }

    private void init(){
        totalcnfr=findViewById(R.id.totalcnfrm);
        totalact=findViewById(R.id.totalactive);
        totalrecv=findViewById(R.id.totalrcvr);
        totaltst=findViewById(R.id.cases);
        totaldeath=findViewById(R.id.totaldth);
        todayactve=findViewById(R.id.textview24);
        todaycnfrm=findViewById(R.id.todaycnfrm);
        todayrecv=findViewById(R.id.todayrcvr);
        todaytst=findViewById(R.id.todaycases);
        todaydthe=findViewById(R.id.todaydth);
        date=findViewById(R.id.textview2);
        pie=findViewById(R.id.piechart);
    }
    private void fetchdata(){
        String url = "https://corona.lmao.ninja/v2/all";
        StringRequest request
                = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {

                        try {

                            // Creating object of JSONObject
                            JSONObject jsonObject
                                    = new JSONObject(
                                    response.toString());
                            long cnfrm=Long.parseLong(jsonObject.getString("cases"));
                            int active=Integer.parseInt(jsonObject.getString("active"));
                            int rcvr=Integer.parseInt(jsonObject.getString("recovered"));
                            int dth=Integer.parseInt(jsonObject.getString("deaths"));

                                totalcnfr.setText(NumberFormat.getInstance().format(cnfrm));
                                totalrecv.setText(NumberFormat.getInstance().format(rcvr));
                                String updated = jsonObject.getString("updated");
                                setText(updated);
                                totalact.setText(NumberFormat.getInstance().format(active));
                                todaycnfrm.setText("(+"+NumberFormat.getInstance().format(Integer.parseInt(jsonObject.getString("todayCases")))+")");
                                totaldeath.setText(NumberFormat.getInstance().format(dth));
                                todaydthe.setText("(+"+NumberFormat.getInstance().format(Integer.parseInt(jsonObject.getString("todayDeaths")))+")");
                                totaltst.setText(NumberFormat.getInstance().format(Long.parseLong(jsonObject.getString("tests"))));


                                pie.addPieSlice(new PieModel("Confirmed", cnfrm, getResources().getColor(R.color.yellow)));
                                pie.addPieSlice(new PieModel("Recovered", rcvr, getResources().getColor(R.color.greenpie)));
                                pie.addPieSlice(new PieModel("Active", active, getResources().getColor(R.color.bluepie)));
                                pie.addPieSlice(new PieModel("Death", dth, getResources().getColor(R.color.redpie)));
                                pie.startAnimation();
                            }

                        catch(JSONException e){
                                e.printStackTrace();
                            }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(
                                MainActivity.this,
                                error.getMessage(),
                                Toast.LENGTH_SHORT)
                                .show();
                    }
                });

        RequestQueue requestQueue
                = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

}