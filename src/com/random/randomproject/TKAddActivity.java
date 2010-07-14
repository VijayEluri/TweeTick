package com.random.randomproject;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class TKAddActivity extends TKActivity {  
	final int ADD    = 1;
	final int UPDATE = 2;
	
	//Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tk_add_activity);  
        
        final Button addButton = (Button) findViewById(R.id.addscr_button);
      
        addButton.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
    			Log.i("button", "add");
    			updateActivity(Main.class, ADD);
    		}
    	});
        
        ArrayList<String> allTypes = tkdb.getActivityTypes();
        
        if (allTypes.size() < 1) {
        	return;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
        		android.R.layout.simple_spinner_item, allTypes);        
        adapter.setDropDownViewResource(
        		android.R.layout.simple_spinner_dropdown_item);
        Spinner activityTypeSpinner = (Spinner) 
        		findViewById(R.id.addscr_spinner); 
        activityTypeSpinner.setAdapter(adapter);
    }
    
    public void updateActivity(Class<?> cls, int action) {    	
    	Spinner activityTypeSpinner = 
    		(Spinner) findViewById(R.id.addscr_spinner);
		EditText activityEditText = (EditText) findViewById(R.id.addscr_edit);
		String activityString = 
			activityEditText.getText().toString();
	
		ArrayAdapter<String> adapter = 
			(ArrayAdapter<String>)activityTypeSpinner.getAdapter();
		String activityTypeString = (String) adapter.getItem(0);
	
		Long ret = tkdb.insertActivity(activityString, activityTypeString); 
    	if(ret > -1) {        	
    		Toast.makeText(this,
					activityString + " of type " + activityTypeString + 
					" added at position " + ret.toString(), 
					Toast.LENGTH_LONG).show();
    		
        	intent = new Intent(this, cls);
        	startActivityForResult(intent, 0);
    	}
    	else {
    		Log.i("insert", "failed!");
    	}
    }
}
