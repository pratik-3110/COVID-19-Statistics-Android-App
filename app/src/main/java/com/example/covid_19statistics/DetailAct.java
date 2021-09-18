package com.example.covid_19statistics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DetailAct extends AppCompatActivity {
    private int poscntry;
    TextView cntrytv,cnfrmtv,recvtv,deathtv,activetv,testtv,todaycnrm,todaydeth,datetv;
    String update;
    PieChart pie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Intent intent=getIntent();
        poscntry=intent.getIntExtra("position",0);
        cntrytv=findViewById(R.id.textview1);
        cnfrmtv=findViewById(R.id.totalcnfrm);
        recvtv=findViewById(R.id.totalrcvr);
        deathtv=findViewById(R.id.totaldth);
        testtv=findViewById(R.id.cases);
        activetv=findViewById(R.id.totalactive);
        todaycnrm=findViewById(R.id.todaycnfrm);
        todaydeth=findViewById(R.id.todaydth);
        datetv=findViewById(R.id.textview2);
        pie=findViewById(R.id.piechart);
        update=Affected_Countries.list.get(poscntry).getUpdate();
        setText(update);
        cntrytv.setText(Affected_Countries.list.get(poscntry).getCntry());
        long cnfrm=Long.parseLong(Affected_Countries.list.get(poscntry).getCnfrm());
        int rcvr=Integer.parseInt(Affected_Countries.list.get(poscntry).getRecvr());
        int active=Integer.parseInt(Affected_Countries.list.get(poscntry).getActive());
        int dth=Integer.parseInt(Affected_Countries.list.get(poscntry).getTotaldth());
        cnfrmtv.setText(NumberFormat.getInstance().format(cnfrm));
        recvtv.setText(NumberFormat.getInstance().format(rcvr));
        activetv.setText(NumberFormat.getInstance().format(active));
        deathtv.setText(NumberFormat.getInstance().format(dth));
        testtv.setText(NumberFormat.getInstance().format(Long.parseLong(Affected_Countries.list.get(poscntry).getTotaltst())));
        todaycnrm.setText("(+"+NumberFormat.getInstance().format(Integer.parseInt(Affected_Countries.list.get(poscntry).getTodaycnfrm()))+")");
        todaydeth.setText("(+"+NumberFormat.getInstance().format(Integer.parseInt(Affected_Countries.list.get(poscntry).getTodaydth()))+")");

        pie.addPieSlice(new PieModel("Confirmed",cnfrm, getResources().getColor(R.color.yellow)));
        pie.addPieSlice(new PieModel("Recovered", rcvr, getResources().getColor(R.color.greenpie)));
        pie.addPieSlice(new PieModel("Active", active, getResources().getColor(R.color.bluepie)));
        pie.addPieSlice(new PieModel("Death", dth, getResources().getColor(R.color.redpie)));
        pie.startAnimation();
    }
    
    private void setText(String updated) {
        DateFormat format=new SimpleDateFormat("MMM dd yyyy");
        long ms=Long.parseLong(updated);
        Calendar cal= Calendar.getInstance();
        cal.setTimeInMillis(ms);
        datetv.setText("Updated on "+format.format(cal.getTime()));
    }
}