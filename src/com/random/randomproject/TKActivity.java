package com.random.randomproject;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class TKActivity extends Activity {
	static Intent intent;
	static TKDatabase tkdb;
	static SQLiteDatabase db;
//	private static String selection;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.tk_show_report);

		tkdb = new TKDatabase(this);
		db = tkdb.getWritableDatabase();
		
//		selection = new String();
	}

//	public String getSpinnerSelectedItem(Spinner spinner) {
//		String selection = new String();
//
//		final class MyOnItemSelectedListener implements OnItemSelectedListener {
//
//			public void onItemSelected(AdapterView<?> parent,
//					View view, int pos, long id) {
//				Toast.makeText(parent.getContext(), "Activity Type is " +
//						parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
//				selection = parent.getItemAtPosition(pos).toString();
//			}
//
//			public void onNothingSelected(AdapterView<?> parent) {
//				// Do nothing.
//			}
//		}
//		
//		spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
//		
//		return selection;
//	}
}
