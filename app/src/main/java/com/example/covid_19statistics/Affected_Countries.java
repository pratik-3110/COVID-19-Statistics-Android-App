package com.example.covid_19statistics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Affected_Countries extends AppCompatActivity {

    private EditText edt;
    private ListView liv;
    public static List<CountryModel> list=new ArrayList<>();
    CountryModel cm;
    CustomAdapter ca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affected__countries);
        edt=findViewById(R.id.search);
        liv=findViewById(R.id.lv);
        getSupportActionBar().setTitle("Affected_Countries");;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        fetchdata();
        liv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getApplicationContext(),DetailAct.class).putExtra("position",i));
            }
        });
        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ca.getFilter().filter(charSequence);
                ca.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void fetchdata(){
        String url = "https://corona.lmao.ninja/v2/countries";
        StringRequest request
                = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {
                        try {
                            JSONArray jsonArray=new JSONArray(response);
                            for(int i=0;i<jsonArray.length();i++) {
                                JSONObject jsonObject=jsonArray.getJSONObject(i);
                                String countrynme=jsonObject.getString("country");
                                String date=jsonObject.getString("updated");
                                String confirm=jsonObject.getString("cases");
                                String recvr=jsonObject.getString("recovered");
                                String active=jsonObject.getString("active");
                                String deth=jsonObject.getString("deaths");
                                String totaltest=jsonObject.getString("tests");
                                String todaycses=jsonObject.getString("todayCases");
                                String todaydths=jsonObject.getString("todayDeaths");
                                JSONObject obj=jsonObject.getJSONObject("countryInfo");
                                String flagUrl=obj.getString("flag");
                                cm=new CountryModel(flagUrl,countrynme,confirm,todaycses,deth,todaydths,recvr,active,totaltest,date);
                                list.add(cm);

                            }
                            ca=new CustomAdapter(Affected_Countries.this,list);
                            liv.setAdapter(ca);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(
                                Affected_Countries.this,
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