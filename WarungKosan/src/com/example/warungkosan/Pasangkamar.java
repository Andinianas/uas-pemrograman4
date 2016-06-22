package com.example.warungkosan;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Pasangkamar extends Activity {

	Button Daftar;
	Button masuk;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pasangkamar);
		
		 Daftar = (Button)findViewById(R.id.Daftar);
	     Daftar.setOnClickListener(new View.OnClickListener(){
	        	
	    @Override
	    public void onClick(View v){
	    //TODO Auto-generated method stub
	    	Intent i = new Intent(getApplicationContext(), Daftar.class);
	    	startActivity(i);
	    	}
	        });
	        
	        masuk = (Button)findViewById(R.id.riwayat);
	        masuk.setOnClickListener(new View.OnClickListener(){
	        	
	    @Override
	    public void onClick(View v){
	    //TODO Auto-generated method stub
	    	Intent i = new Intent(getApplicationContext(), Masuk.class);
	    	startActivity(i);
	    	}	
	        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pasangkamar, menu);
		return true;
	}

}
