package com.nicofrnds02734.crudsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {
    EditText txt_nama, txt_alamat;
    Button btn_simpan;
    DataHelper db;
    String id, nama, alamat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        db = new DataHelper(this);
        txt_nama = findViewById(R.id.txt_nama);
        txt_alamat = findViewById(R.id.txt_alamat);
        btn_simpan = findViewById(R.id.btn_submit);
        id = getIntent().getStringExtra("id");
        nama = getIntent().getStringExtra("nama");
        alamat = getIntent().getStringExtra("alamat");
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
        if (id == null || id == "") {
            setTitle("Add Data");
        } else {
            setTitle("Edit Data");
            txt_nama.setText(nama);
            txt_alamat.setText(alamat);
        }

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit();
            }
        });
    }

    // Update data in SQLite database
    private void edit() {
        db.update(id, txt_nama.getText().toString().trim(),
                txt_alamat.getText().toString().trim());
           txt_nama.setText("");
           txt_alamat.setText("");
           MainActivity.ma.tampilData();
           finish();
           Toast.makeText(this, "Berhasil di Edit !", Toast.LENGTH_SHORT).show();

    }
}