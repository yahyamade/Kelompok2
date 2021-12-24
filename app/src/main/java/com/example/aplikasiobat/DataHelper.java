package com.example.aplikasiobat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class DataHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "FormDataObat";
    private static final String TABLE_NAME = "tbl_obat";
    private static final String KEY_ID = "_id";
    private static final String KEY_NAMAOBAT = "nama_obat";
    private static final String KEY_DESKRIPSI = "deskripsi";
    private static final String KEY_DOSIS = "dosis";
    private static final String KEY_JENIS = "jenis";
    private static final String KEY_KATEGORI = "kategori";



    public DataHelper(@Nullable Context context) { super(context, DB_NAME, null, DB_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createUserTable = "CREATE TABLE " + TABLE_NAME +
                " (" + KEY_ID + " INTEGER PRIMARY KEY, " +
                KEY_NAMAOBAT + " TEXT, " +
                KEY_DESKRIPSI + " TEXT, " +
                KEY_DOSIS + " TEXT, " +
                KEY_JENIS + " TEXT, " +
                KEY_KATEGORI + " TEXT " + ")";
        sqLiteDatabase.execSQL(createUserTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;

        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public void insert (User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NAMAOBAT,user.getNamaObat());
        values.put(KEY_DESKRIPSI,user.getDeskripsi());
        values.put(KEY_DOSIS,user.getDosis());
        values.put(KEY_JENIS,user.getJenis());
        values.put(KEY_KATEGORI,user.getKategori());

        db.insert(TABLE_NAME, null, values);
    }

    public List<User> selectUserData() {
        ArrayList<User> users = new ArrayList<User>();

        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {KEY_ID, KEY_NAMAOBAT, KEY_DESKRIPSI, KEY_DOSIS, KEY_JENIS, KEY_KATEGORI};

        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);

        while (cursor.moveToNext()) {

            int id =cursor.getInt(0);
            String namaObat = cursor.getString(1);
            String Deskripsi = cursor.getString(2);
            String dosis = cursor.getString(3);
            String jenis = cursor.getString(4);
            String kategori = cursor.getString(5);

            User user = new User();
            user.setId(id);
            user.setNamaObat(namaObat);
            user.setDeskripsi(Deskripsi);
            user.setDosis(dosis);
            user.setJenis(jenis);
            user.setKategori(kategori);

            users.add(user);
        }

        return users;
    }

    public void update(User user){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NAMAOBAT,user.getNamaObat());
        values.put(KEY_DESKRIPSI,user.getDeskripsi());
        values.put(KEY_DOSIS,user.getDosis());
        values.put(KEY_JENIS,user.getJenis());
        values.put(KEY_KATEGORI,user.getKategori());

        String whereClause = KEY_ID + " = '" + user.getId() +"'";

        db.update(TABLE_NAME, values,whereClause,null);
    }


    public void delete(int id) {
        SQLiteDatabase db = getWritableDatabase();

        String whereClause = KEY_ID + " = '" + id + "'";

        db.delete(TABLE_NAME, whereClause, null);
    }
}

