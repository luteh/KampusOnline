package com.luteh.kampusonline.ui.fragments.jadwal.pengganti.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.luteh.kampusonline.R;
import com.luteh.kampusonline.model.JadwalKuliah;
import com.luteh.kampusonline.model.JadwalPengganti;
import com.luteh.kampusonline.ui.fragments.jadwal.kuliah.adapter.JadwalKuliahDiffUtilCallback;
import com.luteh.kampusonline.ui.fragments.jadwal.kuliah.adapter.JadwalKuliahViewHolder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Luthfan Maftuh on 11/12/2018.
 * Email luthfanmaftuh@gmail.com
 */
public class JadwalPenggantiAdapter extends RecyclerView.Adapter<JadwalKuliahViewHolder> {
    private List<JadwalPengganti> jadwalPenggantiList;

    public JadwalPenggantiAdapter(List<JadwalPengganti> jadwalPenggantiList) {
        this.jadwalPenggantiList = jadwalPenggantiList;
    }

    @NonNull
    @Override
    public JadwalKuliahViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new JadwalKuliahViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_jadwal_kuliah_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull JadwalKuliahViewHolder holder, int position) {
        holder.tvJadwalKuliahNo.setText(jadwalPenggantiList.get(position).no);
        holder.tvJadwalKuliahMatkul.setText(jadwalPenggantiList.get(position).mata_kuliah);
        holder.tvJadwalKuliahDosen.setText(jadwalPenggantiList.get(position).dosen);
        holder.tvJadwalKuliahKode.setText(jadwalPenggantiList.get(position).kode);
        holder.tvJadwalKuliahKelas.setText(String.format("Kelas %s", jadwalPenggantiList.get(position).kelas));
        holder.tvJadwalKuliahRuangan.setText(jadwalPenggantiList.get(position).new_ruangan);
        holder.tvJadwalKuliahSks.setText(String.format("%s SKS", jadwalPenggantiList.get(position).sks));
        holder.tvJadwalKuliahHari.setText(jadwalPenggantiList.get(position).new_hari);
        holder.tvJadwalKuliahStartClass.setText(jadwalPenggantiList.get(position).new_start_class);
        holder.tvJadwalKuliahFinishClass.setText(jadwalPenggantiList.get(position).new_finish_class);
    }

    @Override
    public int getItemCount() {
        return jadwalPenggantiList.size();
    }

    public void updateItem(List<JadwalPengganti> newList) {
        JadwalPenggantiDiffUtilCallBack diffUtilCallback = new JadwalPenggantiDiffUtilCallBack(jadwalPenggantiList, newList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffUtilCallback);

        jadwalPenggantiList.clear();
        jadwalPenggantiList.addAll(newList);
        diffResult.dispatchUpdatesTo(this);
    }
}
