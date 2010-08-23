package com.riglabs.tweetick;

import java.util.ArrayList;

import com.riglabs.tweetick.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class TKSettings extends TKActivity {
	Intent intent;
	static Spinner activitySpinner;
	private static String activityTypeFromSpinner;

	
	final int ADD  = 1;
	final int EDIT = 2;
	final int DEL  = 3;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tk_settings);  
        activityTypeFromSpinner = new String();
        
        final Button addButton = (Button) findViewById(R.id.setscr_add);
        final Button editButton = (Button) findViewById(R.id.setscr_edit);
        final Button delButton = (Button) findViewById(R.id.setscr_remove);
        
        addButton.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
//    			Log.i("button", "add");
    			updateActivity(TKAddActivityType.class, ADD);
    		}
    	});
        
        editButton.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
//    			Log.i("button", "edit");
    			updateActivity(TKUpdateActivityType.class, EDIT);
    		}
    	});
        
        delButton.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
//    			Log.i("button", "delete");
    			updateActivity(TKUpdateActivityType.class, DEL);
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
        		findViewById(R.id.setscr_spinner); 
        
        final class MyOnItemSelectedListener implements OnItemSelectedListener {

            public void onItemSelected(AdapterView<?> parent,
                View view, int pos, long id) {
//              Toast.makeText(parent.getContext(), "Activity Type is " +
//                  parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
              activityTypeFromSpinner = parent.getItemAtPosition(pos).toString();
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
    	intent = new Intent(this, cls);  
    	int n = 0;    	
    	
    	if (action == DEL) {
//    		Spinner activityTypeSpinner = (Spinner) 
//    		findViewById(R.id.setscr_spinner);
    		
    		try {
//    			ArrayAdapter<String> adapter = 
//    				(ArrayAdapter<String>)activityTypeSpinner.getAdapter();
//    			String delString = (String) adapter.getItem(0);
//    			Log.i("delType", delString);
    			tkdb.deleteActivityType(activityTypeFromSpinner);
    			Toast.makeText(this, "Deleted activity type " +
    					activityTypeFromSpinner, 
    					Toast.LENGTH_LONG).show();
    		}
    		catch (Exception e) {
    			Log.e("adapter", e.toString());
    		}
    		
    		return;
    	}
    	
    	if (n > 0) {
    		Toast.makeText(this, "delete successful", 
					Toast.LENGTH_LONG).show();
    	}
    	
		startActivityForResult(intent, 0);		
    }
}
