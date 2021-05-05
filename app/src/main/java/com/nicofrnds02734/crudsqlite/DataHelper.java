package com.nicofrnds02734.crudsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "data";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_DATA = "produk";
    public static final String KEY_ID = "id";
    public static final String NAMA = "nama";
    public static final String ALAMAT = "alamat";

    public static final String SQL_TABLE_DATA = " CREATE TABLE " + TABLE_DATA
            + "("
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + NAMA + " TEXT, "
            + ALAMAT + " TEXT "
            + " ) ";

    public DataHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_TABLE_DATA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATA);
    }

    public void TambahData(DataModel dataModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAMA, dataModel.nama);
        values.put(ALAMAT, dataModel.alamat);
        long todo_id = db.insert(TABLE_DATA, null, values);
    }

    public ArrayList<HashMap<String, String>> getData() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM " + TABLE_DATA;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(KEY_ID, cursor.getString(0));
                map.put(NAMA, cursor.getString(1));
                map.put(ALAMAT, cursor.getString(2));
                wordList.add(map);
            } while (cursor.moveToNext());
        }
        Log.e("select sqlite ", "" + wordList);
        database.close();
        return wordList;
    }
    public void delete(int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        String updateQuery = "DELETE FROM " + TABLE_DATA + " WHERE " + KEY_ID + "=" + "'" + id + "'";
        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    public void update(String id, String nama, String alamat) {
        SQLiteDatabase database = this.getWritableDatabase();
        String updateQuery = "UPDATE " + TABLE_DATA + " SET "
                + NAMA + "='" + nama + "', "
                + ALAMAT + "='" + alamat + "'"
                + " WHERE " + KEY_ID + "=" + "'" + id + "'";
        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

}
