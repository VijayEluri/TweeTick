package com.random.randomproject;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class TKShowReport extends TKActivity {
	Intent intent;
	TKChart chart;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.tk_show_report);
        drawPieChart();
    }
    
    private void drawPieChart() {    	
    	ArrayList<String[]> timeTypePairList = 
    		amortizedList(tkdb.getActivitiesForToday());
    	
        chart = new TKChart(this, timeTypePairList);        
        setContentView(chart);
    }
    
    private ArrayList<String[]> amortizedList(ArrayList<String[]> oList) {
    	Log.i("listSize", "n: " + oList.size());
    	
    	return oList;
    }
}
