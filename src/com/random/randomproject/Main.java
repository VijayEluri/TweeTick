package com.random.randomproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main extends TKActivity {
	//Intent intent;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);    
        
        Intent dbIntent = new Intent(this, TKDatabase.class);
        startService(dbIntent);
        
//        TKCommonFlags.activityInProgress = false;
        handleButtons();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.menu_main, menu);
    	
    	return true;
    }
    
    private void handleButtons() {
    	final Button mainAdd      = (Button) findViewById(R.id.main_add);
    	final Button mainReport   = (Button) findViewById(R.id.main_report);
//    	final Button mainEdit     = (Button) findViewById(R.id.main_edit);
    	final Button mainEndPrev  = (Button) findViewById(R.id.main_end_prev);
    	final Button mainSettings = (Button) findViewById(R.id.main_settings);
    	final Button mainQuit     = (Button) findViewById(R.id.main_quit);
    	
//    	if (TKCommonFlags.activityInProgress) 
//    		mainEndPrev.setEnable(false);
//    	else
//    		mainAdd.enable(false);
    	
    	mainAdd.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
    			Log.i("button", "add");
    			if (activityInProgress)
    				handleLastActivity();	
    			startActivityFor(TKAddActivity.class);
//    			activityInProgress = true;
    		}
    	});

    	mainReport.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
    			Log.i("button", "report");
    			startActivityFor(TKReport.class);
    		}
    	});
    	
//    	mainEdit.setOnClickListener(new View.OnClickListener() {
//    		public void onClick(View v) {
//    			Log.i("button", "edit");
//    			startActivityFor(TKEditActivity.class);
//    		}
//    	});
    	
    	mainEndPrev.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
    			Log.i("button", "Ended Previous Activity");
    			if (TKCommonFlags.activityInProgress)
    				Log.i("activity", "In Progress");
    			else
    				Log.i("activity", "Not In Progress");
    			
    			if(handleLastActivity() < 0) {
    				Toast.makeText(Main.this,
    						"No activity in progress at this time",
    						Toast.LENGTH_LONG).show();
    			}
    		}
    	});
    	    	
    	mainSettings.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
    			Log.i("button", "settings");
    			startActivityFor(TKSettings.class);
    		}
    	});

    	mainQuit.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
    			Log.i("button", "quit");
    			db.close();
//    			onDestroy();
//    			finish();
//    			
    			handleLastActivity();
    			
    			int pid = android.os.Process.myPid();
    			android.os.Process.killProcess(pid);
    		}
    	});
    }
    
    public void startActivityFor (Class<?> cls) {
    	intent = new Intent(this, cls);
    	startActivityForResult(intent, 0);
    }
    
    private int handleLastActivity() {
    	if(TKCommonFlags.activityInProgress == false)
    		return -1;
    	
    	String lastActivityString = tkdb.logLastActivity();
		Toast.makeText(Main.this, lastActivityString,
				Toast.LENGTH_LONG).show();
		
		TKCommonFlags.activityInProgress = false;
		
		return 0;
    }
}