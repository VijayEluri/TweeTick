package com.ghnlabs.tweetick;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class TKActivity extends Activity {
	static Intent intent;
	static TKDatabase tkdb;
	static SQLiteDatabase db;
	static boolean activityInProgress;
//	private static String selection;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.tk_show_report);

		activityInProgress = false;
		tkdb = new TKDatabase(this);
		db = tkdb.getWritableDatabase();
	}
}
