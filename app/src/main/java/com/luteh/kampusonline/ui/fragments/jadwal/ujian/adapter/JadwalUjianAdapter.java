package com.luteh.kampusonline.ui.fragments.jadwal.ujian.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.luteh.kampusonline.R;
import com.luteh.kampusonline.model.jadwal.JadwalUjian;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Luthfan Maftuh on 13/12/2018.
 * Email luthfanmaftuh@gmail.com
 */
public class JadwalUjianAdapter extends RecyclerView.Adapter<JadwalUjianViewHolder> {
    private List<JadwalUjian> jadwalUjianList;

    public JadwalUjianAdapter(List<JadwalUjian> jadwalUjianList) {
        this.jadwalUjianList = jadwalUjianList;
    }

    @NonNull
    @Override
    public JadwalUjianViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new JadwalUjianViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_jadwal_ujian_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull JadwalUjianViewHolder holder, int position) {
        holder.tvJadwalUjianNo.setText(jadwalUjianList.get(position).no);
        holder.tvJadwalUjianMatkul.setText(jadwalUjianList.get(position).mata_kuliah);
        holder.tvJadwalUjianKelas.setText(String.format("Kelas %s", jadwalUjianList.get(position).kelas));
        holder.tvJadwalUjianRuangan.setText(jadwalUjianList.get(position).ruangan);
        holder.tvJadwalUjianStartClass.setText(jadwalUjianList.get(position).start_class);
        holder.tvJadwalUjianFinishClass.setText(jadwalUjianList.get(position).finish_class);
        holder.tvJadwalUjianKelompok.setText(String.format("Kelompok %s", jadwalUjianList.get(position).kelompok));
        holder.tvJadwalUjianKursi.setText(String.format("Kursi %s", jadwalUjianList.get(position).kursi));
        holder.tvJadwalUjianHari.setText(jadwalUjianList.get(position).hari);
        holder.tvJadwalUjianKode.setText(jadwalUjianList.get(position).kode);
        holder.tvJadwalUjianTanggal.setText(
                String.format("%s", jadwalUjianList.get(position).tanggal)
                        .substring(0, 6)
        );
    }

    @Override
    public int getItemCount() {
        return jadwalUjianList.size();
    }

    public void updateItem(List<JadwalUjian> newList) {
        JadwalUjianDiffUtilCallback diffUtilCallback = new JadwalUjianDiffUtilCallback(jadwalUjianList, newList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffUtilCallback);

        jadwalUjianList.clear();
        jadwalUjianList.addAll(newList);
        diffResult.dispatchUpdatesTo(this);
    }
}
