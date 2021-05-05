package com.nicofrnds02734.crudsqlite;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AdapterData extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DataModel> daftar;
    public AdapterData(Activity activity, List<DataModel> items) {
        this.activity = activity;
        this.daftar = items;
    }
    @Override
    public int getCount() {
        return daftar.size();
    }

    @Override
    public Object getItem(int position) {
        return daftar.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.item_data, parent, false);

        TextView nama, alamat;

        nama = view.findViewById(R.id.nama);
        alamat = view.findViewById(R.id.alamat);
        DataModel tampil = daftar.get(position);
        nama.setText(tampil.nama);
        alamat.setText(tampil.alamat);

        return view;
    }
}
