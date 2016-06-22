package com.example.warungkosan;

import java.util.ArrayList;
import java.util.HashMap;
import com.example.warungkosan.Kosan;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Kosanadapter extends BaseAdapter {
    private Activity activity;
    //private ArrayList<HashMap<String, String>> data;
    private ArrayList<Kosan> data_ksn=new ArrayList<Kosan>();

    private static LayoutInflater inflater = null;

    public Kosanadapter(Activity a, ArrayList<Kosan> d) {
        activity = a; data_ksn = d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public int getCount() {
        return data_ksn.size();
    }
    public Object getItem(int position) {
        return data_ksn.get(position);
    }
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.activity_list_item_ksn, null);
        TextView id_ksn = (TextView) vi.findViewById(R.id.id_ksn);
        TextView nama_ksn = (TextView) vi.findViewById(R.id.nama_ksn);
        TextView alamat_ksn = (TextView) vi.findViewById(R.id.alamat_ksn);
        TextView harga_ksn = (TextView) vi.findViewById(R.id.harga_ksn);
        TextView telpon_ksn = (TextView) vi.findViewById(R.id.telpon_ksn);
        
        Kosan daftar_ksn = data_ksn.get(position);
        id_ksn.setText(daftar_ksn.getKsnId());
        nama_ksn.setText(daftar_ksn.getKsnName());
        alamat_ksn.setText(daftar_ksn.getKsnAlamat());
        harga_ksn.setText(daftar_ksn.getKsnHarga());
        telpon_ksn.setText(daftar_ksn.getKsnTelpon());

        return vi;
    }
}


