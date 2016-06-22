package com.example.warungkosan;

import java.util.ArrayList;
import java.util.HashMap;
 
import org.json.JSONArray;
import org.json.JSONObject;
 
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
 
public class Masuk extends Activity  {
 
    Button login;
    Intent a;
    EditText nama,password;
    String url, success;
    Sessionmanager session;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masuk);
 
        session = new Sessionmanager(getApplicationContext());
 
        login = (Button) findViewById(R.id.login);
        nama = (EditText) findViewById(R.id.nama);
        password = (EditText) findViewById(R.id.email);
 
        login.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View v) {
                url = "http://192.168.43.167/warungkosan/masuk.php?" + "nama="
                        + nama.getText().toString() + "&password="
                        + password.getText().toString();
 
                if (nama.getText().toString().trim().length() > 0
                        && password.getText().toString().trim().length() > 0) 
                {
                    new masuk().execute();
                } 
                else
                {
                    Toast.makeText(getApplicationContext(), "Username/password masih kosong gan.!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
 
    public class masuk extends AsyncTask<String, String, String> 
    {
        ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();
        ProgressDialog pDialog;
 
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
 
            pDialog = new ProgressDialog(Masuk.this);
            pDialog.setMessage("Tunggu Bentar ya...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected String doInBackground(String... arg0) {
            JSONParser jParser = new JSONParser();
 
            JSONObject json = jParser.getJSONFromUrl(url);
 
            try {
                success = json.getString("success");
 
                Log.e("error", "nilai sukses=" + success);
 
                JSONArray hasil = json.getJSONArray("login");
 
                if (success.equals("1")) {
 
                    for (int i = 0; i < hasil.length(); i++) {
 
                        JSONObject c = hasil.getJSONObject(i);
 
                        String nama = c.getString("username").trim();
                        String email = c.getString("email").trim();
                        session.createLoginSession(nama, email);
                        Log.e("ok", " ambil data");
 
                    }
                } else {
                    Log.e("erro", "tidak bisa ambil data 0");
                }
 
            } catch (Exception e) {
                // TODO: handle exception
                Log.e("erro", "tidak bisa ambil data 1");
            }
 
            return null;
 
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            pDialog.dismiss();
            if (success.equals("1")) {
                a = new Intent(Masuk.this, Setelahlogin.class);
                startActivity(a);
                finish();
            } else {
 
                Toast.makeText(getApplicationContext(), "Username/password salah gan.!!", Toast.LENGTH_LONG).show();
            }
 
        }
 
    }
 
}