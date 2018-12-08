package com.luteh.kampusonline.ui.fragments.jadwalkuliah.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.luteh.kampusonline.R;
import com.luteh.kampusonline.model.JadwalKuliah;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Luthfan Maftuh on 08/12/2018.
 * Email luthfanmaftuh@gmail.com
 */
public class JadwalKuliahAdapter extends RecyclerView.Adapter<JadwalKuliahViewHolder> {
    private List<JadwalKuliah> jadwalKuliahList;

    public JadwalKuliahAdapter(List<JadwalKuliah> jadwalKuliahList) {
        this.jadwalKuliahList = jadwalKuliahList;
    }

    @NonNull
    @Override
    public JadwalKuliahViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new JadwalKuliahViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_hasil_studi_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull JadwalKuliahViewHolder holder, int position) {
        holder.tvJadwalKuliahNo.setText(jadwalKuliahList.get(position).no);
        holder.tvJadwalKuliahMatkul.setText(jadwalKuliahList.get(position).mataKuliah);
        holder.tvJadwalKuliahDosen.setText(jadwalKuliahList.get(position).dosen);
        holder.tvJadwalKuliahKode.setText(jadwalKuliahList.get(position).kode);
        holder.tvJadwalKuliahKelas.setText(jadwalKuliahList.get(position).kelas);
        holder.tvJadwalKuliahRuangan.setText(jadwalKuliahList.get(position).ruangan);
        holder.tvJadwalKuliahSks.setText(jadwalKuliahList.get(position).sks);
        holder.tvJadwalKuliahHari.setText(jadwalKuliahList.get(position).hari);
        holder.tvJadwalKuliahStartClass.setText(jadwalKuliahList.get(position).startClass);
        holder.tvJadwalKuliahFinishClass.setText(jadwalKuliahList.get(position).finishClass);
    }

    @Override
    public int getItemCount() {
        return jadwalKuliahList.size();
    }

    public void updateItem(List<JadwalKuliah> newList) {
        JadwalKuliahDiffUtilCallback diffUtilCallback = new JadwalKuliahDiffUtilCallback(jadwalKuliahList, newList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffUtilCallback);

        jadwalKuliahList.clear();
        jadwalKuliahList.addAll(newList);
        diffResult.dispatchUpdatesTo(this);
    }
}
