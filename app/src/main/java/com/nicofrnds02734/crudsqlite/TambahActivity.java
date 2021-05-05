package com.nicofrnds02734.crudsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TambahActivity extends AppCompatActivity {
    EditText txt_nama, txt_alamat;
    Button btn_simpan, btn_cancle;
    DataHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        db = new DataHelper(this);
        txt_nama = findViewById(R.id.txt_nama);
        txt_alamat = findViewById(R.id.txt_alamat);
        btn_simpan = findViewById(R.id.btn_submit);
        btn_cancle = findViewById(R.id.btn_cancel);
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_nama.setText("");
                txt_alamat.setText("");
            }
        });
        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanData();
            }
        });
    }

    private void simpanData() {
        String nama = txt_nama.getText().toString();
        String alamat = txt_alamat.getText().toString();
        if(nama.isEmpty()){
         txt_nama.setError("Nama harus diisi");
        }else if(alamat.isEmpty()){
            txt_alamat.setError("Alamat harus diisi");
        } else {
            db.TambahData(new DataModel(null, nama, alamat));
            Toast.makeText(this, "Data Ditambahkan", Toast.LENGTH_SHORT).show();
            MainActivity.ma.tampilData();
            finish();
        }
    }
}