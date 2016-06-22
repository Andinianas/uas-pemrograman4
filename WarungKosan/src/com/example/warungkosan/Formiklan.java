package com.example.warungkosan;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
 
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
 
public class Formiklan extends Activity {
 
    ProgressDialog pDialog;
 
    JSONParser jsonParser = new JSONParser();
    EditText nama,alamat,harga,telpon; 
    Button login;
    private static String url = "http://192.168.43.167/warungkosan/formiklan.php?";
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formiklan);
 
        nama    =(EditText)findViewById(R.id.nama);
        alamat    =(EditText)findViewById(R.id.alamat);
        harga    =(EditText)findViewById(R.id.harga);
        telpon    =(EditText)findViewById(R.id.telpon);
        login = (Button)findViewById(R.id.login);
        
        login.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View v) {
                    new formah().execute();
            }
        });
    }
 
    public class formah extends AsyncTask<String, String, String>
    {
 
        String success;
 
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Formiklan.this);
            pDialog.setMessage("Proses mendaftar...");
            pDialog.setIndeterminate(false);
            pDialog.show();
        }
 
        @Override
        protected String doInBackground(String... params) {
 
            String strnama      = nama.getText().toString();
            String stralamat    = alamat.getText().toString();
            String strharga     = harga.getText().toString();
            String strtelpon    = telpon.getText().toString();
 
            List<NameValuePair> nvp = new ArrayList<NameValuePair>();
            nvp.add(new BasicNameValuePair("nama", strnama));
            nvp.add(new BasicNameValuePair("alamat", stralamat));
            nvp.add(new BasicNameValuePair("harga", strharga));
            nvp.add(new BasicNameValuePair("telpon", strtelpon));

            JSONObject json = jsonParser.makeHttpRequest(url, "POST", nvp);
            try { 
                success = json.getString("success");
 
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
 
            return null;
        }
 
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
 
            if (success.equals("1")) {
                Toast.makeText(getApplicationContext(), "Data disimpan !", Toast.LENGTH_LONG).show();
 
            } else {
                Toast.makeText(getApplicationContext(), "Data Gagal !", Toast.LENGTH_LONG).show();
            }
        }
 
      }
}