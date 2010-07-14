package com.random.randomproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
    }
    
    public void showReport(Class<?> cls) {
    	intent = new Intent(this, cls);
    	startActivityForResult(intent, 0);
    }
}
