package com.example.warungkosan;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


public class Carikosan extends Activity {

    ListView list;

    JSONParser jParser = new JSONParser();
    ArrayList<Kosan> daftar_ksn = new ArrayList<Kosan>();
    JSONArray daftarksn = null;
    String url_read_ksn = "http://192.168.43.167/warungkosan/read_ksn.php";
    // JSON Node names, ini harus sesuai yang di API
    public static final String TAG_SUCCESS = "success";
    public static final String TAG_KSN = "kosan";
    public static final String TAG_ID = "id_kos";
    public static final String TAG_NAMA = "nama_kos";
    public static final String TAG_ALAMAT = "alamat_kos";
    public static final String TAG_HARGA = "harga";
    public static final String TAG_TELP = "telpon";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carikosan);
        
        list = (ListView) findViewById(R.id.listview_ksn);

        //jalankan ReadKsnTask
        ReadKsnTask m= (ReadKsnTask) new ReadKsnTask().execute();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int urutan, long id) {
                //dapatkan data nama alamat kosan dan simpan dalam variable string
                String idksn = ((TextView) view.findViewById(R.id.id_ksn)).getText().toString();
                String namaksn = ((TextView) view.findViewById(R.id.nama_ksn)).getText().toString();
                String alamatksn = ((TextView) view.findViewById(R.id.alamat_ksn)).getText().toString();
                String hargaksn = ((TextView) view.findViewById(R.id.harga_ksn)).getText().toString();
                String telponksn = ((TextView) view.findViewById(R.id.telpon_ksn)).getText().toString();

                //varible string tadi kita simpan dalam suatu Bundle, dan kita parse bundle tersebut bersama intent menuju kelas UpdateDeleteActivity
                Intent i = null;
                i = new Intent(Carikosan.this, Show.class);
                Bundle b = new Bundle();
                b.putString("id_ksn", idksn);
                b.putString("nama_ksn", namaksn);
                b.putString("alamat_ksn", alamatksn);
                b.putString("harga_ksn", hargaksn);
                b.putString("telpon_ksn", telponksn);
                i.putExtras(b);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.action_settings) {
            Intent i = new Intent(Carikosan.this, Show.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    class ReadKsnTask extends AsyncTask<String, Void, String>
    {
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Carikosan.this);
            pDialog.setMessage("Mohon Tunggu..");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... sText) {
            String returnResult = getKsnList(); //memanggil method getKosList()
            return returnResult;

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            pDialog.dismiss();
            if(result.equalsIgnoreCase("Exception Caught"))
            {
                Toast.makeText(Carikosan.this, "Unable to connect to server,please check your internet connection!", Toast.LENGTH_LONG).show();
            }

            if(result.equalsIgnoreCase("no results"))
            {
                Toast.makeText(Carikosan.this, "Data empty", Toast.LENGTH_LONG).show();
            }
            else
            {
                list.setAdapter(new Kosanadapter(Carikosan.this,daftar_ksn)); //Adapter menampilkan data mahasiswa ke dalam listView
            }
        }


        //method untuk memperoleh daftar mahasiswa dari JSON
        public String getKsnList()
        {
            Kosan tempKsn = new Kosan();
            List<NameValuePair> parameter = new ArrayList<NameValuePair>();
            try {
                JSONObject json = jParser.makeHttpRequest(url_read_ksn,"POST", parameter);

                int success = json.getInt(TAG_SUCCESS);
                if (success == 1) { //Ada record Data (SUCCESS = 1)
                    //Getting Array of daftar_ksn
                    daftarksn = json.getJSONArray(TAG_KSN);
                    // looping through All daftar_ksn
                    for (int i = 0; i < daftarksn.length() ; i++){
                        JSONObject c = daftarksn.getJSONObject(i);
                        tempKsn = new Kosan();
                        tempKsn.setKsnId(c.getString(TAG_ID));
                        tempKsn.setKsnName(c.getString(TAG_NAMA));
                        tempKsn.setKsnAlamat(c.getString(TAG_ALAMAT));
                        tempKsn.setKsnHarga(c.getString(TAG_HARGA));
                        tempKsn.setKsnTelpon(c.getString(TAG_TELP));
                        daftar_ksn.add(tempKsn);
                    }
                    return "OK";
                }
                else {
                    //Tidak Ada Record Data (SUCCESS = 0)
                    return "no results";
                }

            } catch (Exception e) {
                e.printStackTrace();
                return "Exception Caught";
            }
        }

    }
}
