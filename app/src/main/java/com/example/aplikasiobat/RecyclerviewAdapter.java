package com.example.aplikasiobat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerviewAdapter extends RecyclerView.Adapter<UserViewHolder> {
    List<User> userList;

    public RecyclerviewAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item, parent, false);
        UserViewHolder userViewHolder = new UserViewHolder(view);

        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.txtNamaObat.setText(userList.get(position).getNamaObat());
        holder.txtJenis.setText(userList.get(position).getJenis());
        holder.txtKategori.setText(userList.get(position).getKategori());
    }

    // Ini untuk menghitung jumlah datanya
    @Override
    public int getItemCount() {
        return userList.size();
    }
}
