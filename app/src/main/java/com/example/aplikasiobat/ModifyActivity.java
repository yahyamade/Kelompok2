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

public class ModifyActivity extends AppCompatActivity {
    private EditText edtNamaObat;
    private String namaObat;
    private EditText inputdeskripsi;
    private String Deskripsi;
    private SeekBar seekBar;
    private TextView lbl_dosis;
    private String dosis;
    private Button btnUbah;
    private RadioGroup radioGroup;
    private String jenis;
    private RadioButton rbpil;
    private RadioButton rbsirup;
    private CheckBox ckat1, ckat2, ckat3, ckat4, ckat5, mLainnya;
    private String mResult = "";
    private String kategori;
    private int id;

    private DataHelper db;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        edtNamaObat = findViewById(R.id.input_namaObat);
        inputdeskripsi = findViewById(R.id.input_deskripsi);
        seekBar = findViewById(R.id.seekbar);
        lbl_dosis = findViewById(R.id.lbl_dosis);
        btnUbah = findViewById(R.id.ubah);
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

        Intent intent = getIntent();
        id = intent.getExtras().getInt("id");
        namaObat = intent.getExtras().getString("namaObat");
        Deskripsi = intent.getExtras().getString("Deskripsi");
        dosis = intent.getExtras().getString("dosis");
        jenis = intent.getExtras().getString("jenis");
        kategori = intent.getExtras().getString("kategori");

        edtNamaObat.setText(namaObat);
        inputdeskripsi.setText(Deskripsi);
        lbl_dosis.setText("Dosis : " + dosis + "Mg");
        setJenisSelected();
        setkategoriSelected();
        seekBar.setProgress(Integer.parseInt(dosis));



        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                dosis = String.valueOf(i);
                lbl_dosis.setText("Dosis : " + dosis + "Mg");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                namaObat = edtNamaObat.getText().toString().trim();
                Deskripsi = inputdeskripsi.getText().toString().trim();
                jenis = getJenisSelected();
                kategori = getKategoriSelected();

                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(ModifyActivity.this);
                builder.setIcon(R.drawable.warning);
                builder.setTitle("Daftarkan");
                builder.setMessage(
                        "Apakah anda sudah yakin dengan data anda ?\n\n" +
                                "Nama Obat : \n" + namaObat + "\n\n" +
                                "Deskripsi : \n" + Deskripsi + "\n\n" +
                                "Dosis : \n" + dosis + "\n\n" +
                                "Jenis :\n" + jenis + "\n\n" +
                                "Kategori Yang di Pilih :\n" + kategori + ""
                );

                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Data anda berhasil terdaftarkan !", Toast.LENGTH_SHORT).show();

                        Intent layout2 = new Intent(ModifyActivity.this, MainActivity.class);

                        user = new User();
                        user.setId(id);
                        user.setNamaObat(namaObat);
                        user.setDeskripsi(Deskripsi);
                        user.setDosis(dosis);
                        user.setJenis(jenis);
                        user.setKategori(kategori);

                        db.update(user);

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

    private void setkategoriSelected() {
        if (kategori.contains("Batuk")) {
            ckat1.setChecked(true);
        }
        if (kategori.contains("Flu")) {
            ckat2.setChecked(true);
        }
        if (kategori.contains("Demam")) {
            ckat3.setChecked(true);
        }
        if (kategori.contains("Sakit Gigi")) {
            ckat4.setChecked(true);
        }
        if (kategori.contains("Radang tenggorokan")) {
            ckat5.setChecked(true);
        }
        if (kategori.contains("Lainnya")) {
            mLainnya.setChecked(true);
        }
    }

    private String getKategoriSelected(){
        String kategori ="";

        if (ckat1.isChecked()) {
            kategori += ckat1.getText().toString() + "\n";
        }
        if (ckat2.isChecked()) {
            kategori += ckat2.getText().toString() + "\n";
        }
        if (ckat3.isChecked()) {
            kategori += ckat3.getText().toString() + "\n";
        }
        if (ckat4.isChecked()) {
            kategori += ckat4.getText().toString() + "\n";
        }
        if (ckat5.isChecked()) {
            kategori += ckat5.getText().toString() + "\n";
        }
        if (mLainnya.isChecked()) {
            kategori += mLainnya.getText().toString() + "\n";
        }

        return kategori;

    }


    private void setJenisSelected(){
        if (jenis.equals("Laki-Laki")) {
            rbpil.setChecked(true);
        } else if (jenis.equals("Perempuan")){
            rbsirup.setChecked(true);
        }
    }

    private String getJenisSelected(){
        String gender = "";

        int selectedId = radioGroup.getCheckedRadioButtonId();

        if (selectedId == rbpil.getId()){
            gender = "Pil";
        }
        else if (selectedId == rbsirup.getId()){
            gender = "Sirup";
        }
        return gender;
    }

}