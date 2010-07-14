package com.random.randomproject;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class TKActivity extends Activity {
	static Intent intent;
	static TKDatabase tkdb;
	static SQLiteDatabase db;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.tk_show_report);
        
        tkdb = new TKDatabase(this);
        db = tkdb.getWritableDatabase();
    }
}
