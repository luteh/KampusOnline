package com.luteh.kampusonline.ui.fragments.jadwal.ujian.adapter;

import android.view.View;
import android.widget.TextView;

import com.luteh.kampusonline.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Luthfan Maftuh on 13/12/2018.
 * Email luthfanmaftuh@gmail.com
 */
public class JadwalUjianViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tvJadwalUjianNo)
    public TextView tvJadwalUjianNo;
    @BindView(R.id.tvJadwalUjianMatkul)
    public TextView tvJadwalUjianMatkul;
    @BindView(R.id.tvJadwalUjianKode)
    public TextView tvJadwalUjianKode;
    @BindView(R.id.tvJadwalUjianKelas)
    public TextView tvJadwalUjianKelas;
    @BindView(R.id.tvJadwalUjianRuangan)
    public TextView tvJadwalUjianRuangan;
    @BindView(R.id.tvJadwalUjianHari)
    public TextView tvJadwalUjianHari;
    @BindView(R.id.tvJadwalUjianStartClass)
    public TextView tvJadwalUjianStartClass;
    @BindView(R.id.tvJadwalUjianFinishClass)
    public TextView tvJadwalUjianFinishClass;
    @BindView(R.id.tvJadwalUjianTanggal)
    public TextView tvJadwalUjianTanggal;
    @BindView(R.id.tvJadwalUjianKelompok)
    public TextView tvJadwalUjianKelompok;
    @BindView(R.id.tvJadwalUjianKursi)
    public TextView tvJadwalUjianKursi;

    public JadwalUjianViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
