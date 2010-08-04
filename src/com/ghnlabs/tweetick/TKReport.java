package com.ghnlabs.tweetick;

import com.ghnlabs.tweetick.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class TKReport extends TKActivity {
	//Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tk_report);    
        
        final Button repButton = (Button) findViewById(R.id.repscr_button);
        
        repButton.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
    			Log.i("button", "add");
    			showReport(TKShowReport.class);
    		}
    	});
        
        Spinner repScrSpinnerResolution = 
        	(Spinner) findViewById(R.id.repscr_spinner_resolution);
        ArrayAdapter<CharSequence> resolutionAdapter = 
        	ArrayAdapter.createFromResource(this, R.array.range_array, 
        			android.R.layout.simple_spinner_item);
        resolutionAdapter.setDropDownViewResource(
        		android.R.layout.simple_spinner_dropdown_item);
        repScrSpinnerResolution.setAdapter(resolutionAdapter);

        Spinner repScrSpinnerChartType = 
        	(Spinner) findViewById(R.id.repscr_spinner_chart_type);
        ArrayAdapter<CharSequence> chartTypeAdapter = 
        	ArrayAdapter.createFromResource(this, R.array.chart_type_array, 
        			android.R.layout.simple_spinner_item);
        chartTypeAdapter.setDropDownViewResource(
        		android.R.layout.simple_spinner_dropdown_item);
        repScrSpinnerChartType.setAdapter(chartTypeAdapter);
    }
    
    public void showReport(Class<?> cls) {
    	intent = new Intent(this, cls);
    	startActivityForResult(intent, 0);
    }
}
