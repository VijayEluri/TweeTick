package com.ghnlabs.tweetick;

import java.util.ArrayList;

import com.ghnlabs.tweetick.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class TKAddActivity extends TKActivity {  
	final int ADD    = 1;
	final int UPDATE = 2;
	private static String activityTypeFromSpinner;
	
	//Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tk_add_activity);  
        activityTypeFromSpinner = new String();
        
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

        final class MyOnItemSelectedListener implements OnItemSelectedListener {

            public void onItemSelected(AdapterView<?> parent,
                View view, int pos, long id) {
              activityTypeFromSpinner = 
            	  parent.getItemAtPosition(pos).toString();
            }

            public void onNothingSelected(AdapterView<?> parent) {
              // Do nothing.
            }
        }
        
        activityTypeSpinner.setOnItemSelectedListener(
        		new MyOnItemSelectedListener());
        activityTypeSpinner.setAdapter(adapter);
    }
    
    public void updateActivity(Class<?> cls, int action) {  
		EditText activityEditText = (EditText) findViewById(R.id.addscr_edit);
		String activityString = 
			activityEditText.getText().toString();
	
		Long ret = tkdb.insertActivity(activityString, activityTypeFromSpinner); 
    	if(ret > -1) {        	
    		Toast.makeText(this,
//					activityString + " of type " + 
					activityTypeFromSpinner + " added.",  
					Toast.LENGTH_LONG).show();
    		
    		TKCommonFlags.activityInProgress = true;
    		
        	intent = new Intent(this, cls);
        	startActivityForResult(intent, 0);
    	}
    	else {
    		Log.i("insert", "failed!");
    	}
    }
}
