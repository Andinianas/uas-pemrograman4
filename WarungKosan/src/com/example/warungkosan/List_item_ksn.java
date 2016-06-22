package com.example.warungkosan;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class List_item_ksn extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_item_ksn);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_item_ksn, menu);
		return true;
	}

}
