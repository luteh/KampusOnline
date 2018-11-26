package com.luteh.kampusonline.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luteh.kampusonline.R;
import com.luteh.kampusonline.model.hasilstudi.HasilStudi;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Luthfan Maftuh on 26/11/2018.
 * Email luthfanmaftuh@gmail.com
 */
public class HasilStudiAdapter extends RecyclerView.Adapter<HasilStudiViewHolder> {

    private List<HasilStudi> hasilStudiList;

    public HasilStudiAdapter(List<HasilStudi> hasilStudiList) {
        this.hasilStudiList = hasilStudiList;
    }

    @NonNull
    @Override
    public HasilStudiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_hasil_studi_item, parent, false);
        return new HasilStudiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HasilStudiViewHolder holder, int position) {
        holder.tvNumber.setText((position + 1) + "");
        holder.tvKode.setText(hasilStudiList.get(position).kode);
        holder.tvMataKuliah.setText(hasilStudiList.get(position).mata_kuliah);
        holder.tvSks.setText(hasilStudiList.get(position).sks);
        holder.tvNilai.setText(hasilStudiList.get(position).nilai);
    }

    @Override
    public int getItemCount() {
        return hasilStudiList.size();
    }
}
