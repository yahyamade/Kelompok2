package com.example.aplikasiobat;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class UserViewHolder extends RecyclerView.ViewHolder{
    TextView txtNamaObat;
    TextView txtKategori;
    TextView txtJenis;
    private CardView listUser;
    private Context context;
    private List<User> userList;
    private int id;
    private String namaObat;
    private String Deskripsi;
    private String dosis;
    private String jenis;
    private String kategori;


    public UserViewHolder(@NonNull View itemView){
        super(itemView);
        context = itemView.getContext();

        txtNamaObat = itemView.findViewById(R.id.txtNamaObatList);
        txtKategori = itemView.findViewById(R.id.txtKategoriList);
        txtJenis = itemView.findViewById(R.id.txtJenisList);
        listUser = itemView.findViewById(R.id.row_item);

        listUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {alertAction(context, getAdapterPosition());}
        });
    }

    private void alertAction(Context context,int position) {
        String[] option ={"Detail", "Edit", "Delete"};
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
        DataHelper db =new DataHelper(context);
        userList = db.selectUserData();

        id = userList.get(position).getId();
        namaObat = userList.get(position).getNamaObat();
        Deskripsi = userList.get(position).getDeskripsi();
        dosis = userList.get(position).getDosis();
        jenis = userList.get(position).getJenis();
        kategori = userList.get(position).getKategori();


        builder.setTitle("Pilih Opsi");
        builder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                switch (i){
                    case 0:
                        Intent viewActivity = new Intent(context, ViewActivity.class);
                        viewActivity.putExtra("id",id);
                        viewActivity.putExtra("namaObat",namaObat);
                        viewActivity.putExtra("Deskripsi", Deskripsi);
                        viewActivity.putExtra("dosis",dosis);
                        viewActivity.putExtra("jenis",jenis);
                        viewActivity.putExtra("kategori", kategori);

                        context.startActivity(viewActivity);
                        break;

                    case 1:
                        Intent  modifyActivity = new Intent(context,  ModifyActivity.class);
                        modifyActivity.putExtra("id",id);
                        modifyActivity.putExtra("namaObat",namaObat);
                        modifyActivity.putExtra("Deskripsi", Deskripsi);
                        modifyActivity.putExtra("dosis",dosis);
                        modifyActivity.putExtra("jenis",jenis);
                        modifyActivity.putExtra("kategori", kategori);

                        context.startActivity( modifyActivity);
                        break;

                    case 2:
                        DataHelper db = new DataHelper(context);
                        db.delete(userList.get(position).getId());
                        userList = db.selectUserData();
                        MainActivity.setupRecyclerView(context, userList, MainActivity.recyclerView);
                        Toast.makeText(context, "Data Berhasil Dihapus", Toast.LENGTH_SHORT);
                        break;

                }

            }
        });
        builder.show();

    }

}
