package com.nicofrnds02734.crudsqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DataHelper db;
    AlertDialog.Builder dialog;
    ListView tv_list;
    AdapterData adapter;
    FloatingActionButton fab;
    List<DataModel> itemList = new ArrayList<>();
    public static final String KEY_ID = "id";
    public static final String NAMA = "nama";
    public static final String ALAMAT = "alamat";
    public static MainActivity ma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DataHelper(this);
        fab = findViewById(R.id.tambah);
        tv_list = findViewById(R.id.tv_list);

        adapter = new AdapterData(MainActivity.this, itemList);
        tv_list.setAdapter(adapter);
        ma = this;

        tv_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view,
                                           final int position, long id) {
                // TODO Auto-generated method stub
                final String idx = itemList.get(position).getId();
                final String name = itemList.get(position).getNama();
                final String address = itemList.get(position).getAlamat();
                final CharSequence[] dialogitem = {"Edit", "Delete"};
                dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setCancelable(true);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        switch (which) {
                            case 0:
                                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                                intent.putExtra(KEY_ID, idx);
                                intent.putExtra(NAMA, name);
                                intent.putExtra(ALAMAT, address);
                                startActivity(intent);
                                itemList.clear();
                                break;
                            case 1:
                                db.delete(Integer.parseInt(idx));
                                itemList.clear();
                                tampilData();
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });
        tampilData();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TambahActivity.class));
                itemList.clear();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu, menu);
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.peta){
            startActivity(new Intent(this, PetaActivity.class));
        }
        return true;
    }

    public void tampilData() {
        ArrayList<HashMap<String, String>> row = db.getData();
        for (int i = 0; i < row.size(); i++) {
            String id = row.get(i).get(KEY_ID);
            String nama = row.get(i).get(NAMA);
            String alamat = row.get(i).get(ALAMAT);
            DataModel data = new DataModel();
            data.setId(id);
            data.setNama(nama);
            data.setAlamat(alamat);
            itemList.add(data);
        }
        adapter.notifyDataSetChanged();
    }
}