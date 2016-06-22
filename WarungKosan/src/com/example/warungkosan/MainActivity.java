package com.example.warungkosan;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	Button cari;
	Button pasangkamar;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        cari = (Button)findViewById(R.id.pasang);
        cari.setOnClickListener(new View.OnClickListener(){
        	
    @Override
    public void onClick(View v){
    //TODO Auto-generated method stub
    	Intent i = new Intent(getApplicationContext(), Carikosan.class);
    	startActivity(i);
    	}
        });
        
        pasangkamar = (Button)findViewById(R.id.riwayat);
        pasangkamar.setOnClickListener(new View.OnClickListener(){
        	
    @Override
    public void onClick(View v){
    //TODO Auto-generated method stub
    	Intent i = new Intent(getApplicationContext(), Pasangkamar.class);
    	startActivity(i);
    	}	
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
