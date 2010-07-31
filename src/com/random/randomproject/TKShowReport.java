package com.random.randomproject;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

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
//        ArrayList<TextView> labelList = chart.getLabels();
        setContentView(chart);
        
//        for (int i = 0; i < labelList.size(); i++) {
//        	TextView label = (TextView) labelList.get(i);
//        	addContentView(label, layout);
//        	addContentView(label, 
//        			new ViewGroup.LayoutParams(chart.getLabelWidth(), 
//        					chart.getLabelHeight()));
//        }
    }
    
    private ArrayList<String[]> amortizedList(ArrayList<String[]> oList) {
    	Log.i("listSize", "n: " + oList.size());
    	ArrayList<String[]> aList = new ArrayList<String[]>();
    	
    	for (int i = 0; i < oList.size(); i++) {
    		String [] pair = oList.get(i);
    		int index = getIndex(aList, pair[1]);
    		    		
    		if (index != i && index >= 0) {
    			Long updatedTime = 
    				Long.parseLong(pair[0]) +
    				Long.parseLong(aList.get(index)[0]);
    			
    			Log.i("adding", 
    					pair[1] + ": " + pair[0] + 
    					" to " + oList.get(index)[0]);
    			String [] newPair =	new String[2]; 
    			newPair[0] = updatedTime.toString();
        		newPair[1] = pair[1];
        		
        		try {
        			aList.set(index, newPair);
        		}
        		catch(Exception e) {
        			Log.e("set", e.toString());
        		}
    		}
    		else
    			aList.add(pair);
    	}
    	
//    	for (int i = 0; i < oList.size(); i++) {
//    		Log.i(i + ":",
//    				oList.get(i)[0] + "/" + oList.get(i)[1]);
//    	}
//    	
//    	for (int i = 0; i < aList.size(); i++) {
//    		Log.i(i + ":",
//    				aList.get(i)[0] + "/" + aList.get(i)[1]);    		
//    	}
    	
    	return aList;
    }
    
    private int getIndex(ArrayList<String[]> list, String findString) {
    	int ret = -1;
    	
    	if (list.size() < 1) 
    		return ret;
    	
    	for (int i = 0; i < list.size(); i++) {
    		int compareRet = findString.compareTo((String)list.get(i)[1]);
//    		Log.i("comparing", i + "th element " + 
//    				(String)list.get(i)[1] + " to " + findString);
    		if (compareRet == 0) {
//    			Log.i("found", findString + "=" + (String)list.get(i)[1]);
//    			Log.i("returning", " " + i);
    			return i;
    		}
    	}
    	
    	return ret;
    }
}
