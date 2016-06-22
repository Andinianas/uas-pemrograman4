package com.example.warungkosan;

import java.util.HashMap;

import org.json.JSONArray;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Setelahlogin extends Activity {

	Button pasangiklan,riwayatiklan;
	Sessionmanager session;
	String email;
	TextView status;
    JSONArray contacts = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setelahlogin);
		
	      pasangiklan = (Button)findViewById(R.id.pasang);
	      pasangiklan.setOnClickListener(new View.OnClickListener(){

	    @Override
	    public void onClick(View v){
	    //TODO Auto-generated method stub
	    	Intent i = new Intent(Setelahlogin.this, Formiklan.class);
	    	startActivity(i);
	    	}
	        });
	        
	        riwayatiklan = (Button)findViewById(R.id.riwayat);
	        riwayatiklan.setOnClickListener(new View.OnClickListener(){
	        	
	    @Override
	    public void onClick(View v){
	    //TODO Auto-generated method stub
	    	Intent i = new Intent(getApplicationContext(), Riwayat_iklan.class);
	    	startActivity(i);
	    	}	
	        });
	    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setelahlogin, menu);
		return true;
	}

}
