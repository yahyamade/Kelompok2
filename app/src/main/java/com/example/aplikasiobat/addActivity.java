package com.example.aplikasiobat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Calendar;

public class addActivity extends AppCompatActivity {

    private EditText txtNamaObat;
    private String namaObat;
    private EditText inputDeskripsi;
    private String Deskripsi;
    private SeekBar seekBar;
    private TextView lbl_Dosis;
    private String nilaiDosis;
    private String dosis;
    private Button btnDaftar;
    private RadioGroup radioGroup;
    private String jenis;
    private CheckBox ckat1, ckat2, ckat3, ckat4, ckat5, mLainnya;
    private RadioButton rbpil;
    private RadioButton rbsirup;
    private String kategori;

    private DataHelper db;
    private User user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        txtNamaObat = findViewById(R.id.input_namaObat);
        inputDeskripsi = findViewById(R.id.input_deskripsi);
        seekBar = findViewById(R.id.seekbar);
        lbl_Dosis = findViewById(R.id.lbl_dosis);
        btnDaftar = findViewById(R.id.daftar);
        radioGroup = findViewById(R.id.radioGroupJenis);
        rbpil = findViewById(R.id.pil);
        rbsirup = findViewById(R.id.sirup);
        ckat1 = findViewById(R.id.kat1);
        ckat2 = findViewById(R.id.kat2);
        ckat3 = findViewById(R.id.kat3);
        ckat4 = findViewById(R.id.kat4);
        ckat5 = findViewById(R.id.kat5);
        mLainnya = findViewById(R.id.lainnya);

        db = new DataHelper(this);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                nilaiDosis = String.valueOf(i);
                lbl_Dosis.setText("Dosis : " + nilaiDosis + "Mg");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                namaObat = txtNamaObat.getText().toString();
                Deskripsi = inputDeskripsi.getText().toString();
                dosis = nilaiDosis;
                jenis = getJenSelected();
                kategori = getKategoriSelected();


                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(addActivity.this);
                builder.setIcon(R.drawable.warning);
                builder.setTitle("Daftarkan");
                builder.setMessage(
                        "Apakah anda sudah yakin dengan data anda ?\n\n" +
                                "Nama Obat : \n" + namaObat + "\n\n" +
                                "Deskripsi : \n" + Deskripsi + "\n\n" +
                                "Dosis : \n" + dosis + "\n\n" +
                                "Jenis Obat :\n" + jenis + "\n\n" +
                                "Kategori Yang di Pilih :\n" + kategori + ""
                );

                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Data anda berhasil terdaftarkan !", Toast.LENGTH_SHORT).show();

                        Intent layout2 = new Intent(addActivity.this, MainActivity.class);

                        user = new User();
                        user.setNamaObat(namaObat);
                        user.setDeskripsi(Deskripsi);
                        user.setDosis(dosis);
                        user.setJenis(jenis);
                        user.setKategori(kategori);

                        db.insert(user);

                        startActivity(layout2);
                        finish();

                    }
                });

                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
            }
        });


    }
    private String getKategoriSelected(){
        String Kategori = "";

        if (ckat1.isChecked()) {
            Kategori += ckat1.getText().toString() + "\n";
        }
        if (ckat2.isChecked()) {
            Kategori += ckat2.getText().toString() + "\n";
        }
        if (ckat3.isChecked()) {
            Kategori += ckat3.getText().toString() + "\n";
        }
        if (ckat4.isChecked()) {
            Kategori += ckat4.getText().toString() + "\n";
        }
        if (ckat5.isChecked()) {
            Kategori += ckat5.getText().toString() + "\n";
        }
        if (mLainnya.isChecked()) {
            Kategori += mLainnya.getText().toString() + "\n";
        }

        return Kategori;

    }

    private String getJenSelected(){
        String Jenis = "";

        int selectedId = radioGroup.getCheckedRadioButtonId();

        if (selectedId == rbpil.getId()){
            Jenis = "Pil";
        }
        else if (selectedId == rbsirup.getId()){
            Jenis = "Sirup";
        }

        return Jenis;
    }

}