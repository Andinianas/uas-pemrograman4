package com.example.warungkosan;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.example.warungkosan.R;

import java.util.ArrayList;
import java.util.List;

public class Ubah_hapus extends Activity {

    JSONParser jParser = new JSONParser();
    String url_update_ksn= "http://192.168.43.243/warungkosan/update_ksn.php";
    String url_delete_ksn= "http://192.168.43.243/warungkosan/delete_ksn.php";
    // JSON Node names, ini harus sesuai yang di API
    public static final String TAG_SUCCESS = "success";
    public static final String TAG_KSN = "kosan";
    public static final String TAG_ID = "id_kos";
    public static final String TAG_NAMA = "nama_kos";
    public static final String TAG_ALAMAT = "alamat_kos";
    public static final String TAG_HARGA = "harga";
    public static final String TAG_TELP = "telpon";

    EditText nama, alamat, harga, telpon;
    TextView status,namakosan,telponkosan,hubungi,id;
    Button ubah, hapus;
    String namaStr, alamatStr, hargaStr, telponStr,idStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_hapus);

        nama = (EditText) findViewById(R.id.nama);
        alamat = (EditText) findViewById(R.id.alamat);
        harga = (EditText) findViewById(R.id.harga);
        telpon = (EditText) findViewById(R.id.telpon);
        id = (TextView) findViewById(R.id.id_ksn);

        ubah = (Button) findViewById(R.id.ubah);
        hapus = (Button) findViewById(R.id.hapus);

        //menangkap bundle yang telah di-parsed dari MainActivity
        Bundle b = getIntent().getExtras();
        String id_ksn = b.getString("id_ksn");
        String nama_ksn = b.getString("nama_ksn");
        String alamat_ksn= b.getString("alamat_ksn");
        String harga_ksn= b.getString("harga_ksn");
        String telpon_ksn= b.getString("telpon_ksn");
        //meng-set bundle tersebut
        nama.setText(nama_ksn);
        alamat.setText(alamat_ksn);
        harga.setText(harga_ksn);
        telpon.setText(telpon_ksn);
        id.setText(id_ksn);

        ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	idStr = id.getText().toString();
                namaStr = nama.getText().toString();
                alamatStr = alamat.getText().toString();
                hargaStr = harga.getText().toString();
                telponStr = telpon.getText().toString();
                new UbahKsnTask().execute();
            }
        });

        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idStr = id.getText().toString();
                new HapusKsnTask().execute();
            }
        });
    }

    class UbahKsnTask extends AsyncTask<String, Void, String>
    {
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Ubah_hapus.this);
            pDialog.setMessage("Mohon Tunggu..");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... sText) {

            List<NameValuePair> parameter = new ArrayList<NameValuePair>();
            parameter.add(new BasicNameValuePair(TAG_ID, idStr));
            parameter.add(new BasicNameValuePair(TAG_NAMA, namaStr));
            parameter.add(new BasicNameValuePair(TAG_ALAMAT, alamatStr));
            parameter.add(new BasicNameValuePair(TAG_HARGA, hargaStr));
            parameter.add(new BasicNameValuePair(TAG_TELP, telponStr));
            
            try {
                JSONObject json = jParser.makeHttpRequest(url_update_ksn,"POST", parameter);

                int success = json.getInt(TAG_SUCCESS);
                if (success == 1) {

                    return "OK";
                }
                else {

                    return "FAIL";

                }

            } catch (Exception e) {
                e.printStackTrace();
                return "Exception Caught";
            }

        }

        @Override
        protected void onPostExecute(String result) {

            super.onPostExecute(result);
            pDialog.dismiss();

            if(result.equalsIgnoreCase("Exception Caught"))
            {

                Toast.makeText(Ubah_hapus.this, "Unable to connect to server,please check your internet connection!", Toast.LENGTH_LONG).show();
            }

            if(result.equalsIgnoreCase("FAIL"))
            {
                Toast.makeText(Ubah_hapus.this, "Fail.. Try Again", Toast.LENGTH_LONG).show();
            }

            else {
                Intent i = null;
                i = new Intent(Ubah_hapus.this, Riwayat_iklan.class);
                startActivity(i);
            }

        }
    }


    class HapusKsnTask extends AsyncTask<String, Void, String>
    {
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Ubah_hapus.this);
            pDialog.setMessage("Mohon Tunggu..");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... sText) {

            List<NameValuePair> parameter = new ArrayList<NameValuePair>();
            parameter.add(new BasicNameValuePair(TAG_ID, idStr));

            try {
                JSONObject json = jParser.makeHttpRequest(url_delete_ksn,"POST", parameter);

                int success = json.getInt(TAG_SUCCESS);
                if (success == 1) {

                    return "OK";
                }
                else {

                    return "FAIL";

                }

            } catch (Exception e) {
                e.printStackTrace();
                return "Exception Caught";
            }

        }

        @Override
        protected void onPostExecute(String result) {

            super.onPostExecute(result);
            pDialog.dismiss();

            if(result.equalsIgnoreCase("Exception Caught"))
            {

                Toast.makeText(Ubah_hapus.this, "Unable to connect to server,please check your internet connection!", Toast.LENGTH_LONG).show();
            }

            if(result.equalsIgnoreCase("FAIL"))
            {
                Toast.makeText(Ubah_hapus.this, "Fail.. Try Again", Toast.LENGTH_LONG).show();
            }

            else {
                Intent i = null;
                i = new Intent(Ubah_hapus.this, MainActivity.class);
                startActivity(i);
            }

        }
    }
}


