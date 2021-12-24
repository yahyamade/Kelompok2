package com.example.aplikasiobat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ViewActivity extends AppCompatActivity {
    TextView txtNamaObat;
    TextView txtDes;
    TextView txtDosis;
    TextView txtJenis;
    TextView txtKategori;
    String namaObat;
    String Deskripsi;
    String dosis;
    String jenis;
    String kategori;

    private DataHelper db;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        db = new DataHelper(this);
        userList = db.selectUserData();


        txtNamaObat = findViewById(R.id.isi_namaObat);
        txtDes = findViewById(R.id.isi_Des);
        txtDosis = findViewById(R.id.isi_dosis);
        txtJenis= findViewById(R.id.isi_jenis);
        txtKategori = findViewById(R.id.isi_kategori);

        namaObat = userList.get(userList.size()-1).getNamaObat();
        Deskripsi = userList.get(userList.size()-1).getDeskripsi();
        dosis = userList.get(userList.size()-1).getDosis();
        jenis = userList.get(userList.size()-1).getJenis();
        kategori = userList.get(userList.size()-1).getKategori();

        txtNamaObat.setText(namaObat);
        txtDes.setText(Deskripsi);
        txtDosis.setText(dosis);
        txtJenis.setText(jenis);
        txtKategori.setText(kategori);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Menampilkan Activity", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this,"Menjeda Activity", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Toast.makeText(this," Memulai Activity Kembali", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"Menghancurkan activity", Toast.LENGTH_SHORT).show();
    }


}