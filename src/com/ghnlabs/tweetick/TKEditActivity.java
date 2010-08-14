package com.ghnlabs.tweetick;

import com.ghnlabs.tweetick.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class TKEditActivity extends TKActivity {
	final int ADD    = 1;
	final int UPDATE = 2;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tk_edit_activity);  
       
        final Button editButton = (Button) findViewById(R.id.editscr_button);
               
        editButton.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
//    			Log.i("button", "update");
    			updateActivity(TKUpdateActivity.class, UPDATE);
    		}
    	});
    }
    
    public void updateActivity(Class<?> cls, int action) {
    	intent = new Intent(this, cls);
    	startActivityForResult(intent, 0);
    }
}
