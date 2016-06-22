package com.example.warungkosan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Show extends Activity {

    JSONParser jParser = new JSONParser();
    
    // JSON Node names, ini harus sesuai yang di Eclipse
    public static final String TAG_SUCCESS = "success";
    public static final String TAG_NAMA = "nama_kos";
    public static final String TAG_ALAMAT = "alamat_kos";
    public static final String TAG_HARGA = "harga";
    public static final String TAG_TELP = "telpon";

    TextView namakosan, alamatkosan, hargakosan, telponkosan;
    Button kembali;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        kembali = (Button)findViewById(R.id.back);
        kembali.setOnClickListener(new View.OnClickListener(){
        	
    @Override
    public void onClick(View v){
    //TODO Auto-generated method stub
    	Intent i = new Intent(getApplicationContext(), Carikosan.class);
    	startActivity(i);
    	}
        });
        
        
        namakosan = (TextView) findViewById(R.id.namakosan);
        alamatkosan = (TextView) findViewById(R.id.alamatkosan);
        hargakosan = (TextView) findViewById(R.id.hargakosan);
        telponkosan = (TextView) findViewById(R.id.telponkosan);

        //menangkap bundle yang telah di-parsed dari MainActivity
        Bundle b = getIntent().getExtras();
        String isi_namakosan = b.getString("nama_ksn");
        String isi_alamatkosan= b.getString("alamat_ksn");
        String isi_hargakosan= b.getString("harga_ksn");
        String isi_telponkosan= b.getString("telpon_ksn");
        
        //meng-set bundle tersebut
        namakosan.setText(isi_namakosan);
        alamatkosan.setText(isi_alamatkosan);
        hargakosan.setText(isi_hargakosan);
        telponkosan.setText(isi_telponkosan);

    }
}


