package com.riglabs.tweetick;

import java.util.Date;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TKAddActivityType extends TKActivity {
	//TKDatabase db;
	//Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tk_add_activity_type);    
        
        performUpdate();
    }
    
    public void performUpdate() {
        final Button setAddButton = (Button) findViewById(
        		R.id.setaddscr_button);
        final EditText eText = (EditText) findViewById(R.id.setaddscr_edit);
        
        setAddButton.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {		
    			addActivityType(eText.getText().toString());
    		}
    	});
    }
    
    private void addActivityType(String aType) {
//		Log.i("button", "setaddscr_button");
//		Log.i("button", aType);
		
		Date d = new Date();
		
		Long ret = tkdb.insertType(aType);
		if(ret > -1) {
			intent = new Intent(this, Main.class);    	
			startActivityForResult(intent, 0);
			Toast.makeText(TKAddActivityType.this,
					aType + " added",
//					aType + " added at position " + ret.toString() + 
//					" at " + d.toString(), 
					Toast.LENGTH_SHORT).show();
		}
    }
}
