package com.luteh.kampusonline.ui.fragments.jadwal.kuliah.adapter;

import android.view.View;
import android.widget.TextView;

import com.luteh.kampusonline.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Luthfan Maftuh on 07/12/2018.
 * Email luthfanmaftuh@gmail.com
 */
public class JadwalKuliahViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvJadwalKuliahNo)
    public TextView tvJadwalKuliahNo;
    @BindView(R.id.tvJadwalKuliahMatkul)
    public TextView tvJadwalKuliahMatkul;
    @BindView(R.id.tvJadwalKuliahDosen)
    public TextView tvJadwalKuliahDosen;
    @BindView(R.id.tvJadwalKuliahKode)
    public TextView tvJadwalKuliahKode;
    @BindView(R.id.tvJadwalKuliahSks)
    public TextView tvJadwalKuliahSks;
    @BindView(R.id.tvJadwalKuliahKelas)
    public TextView tvJadwalKuliahKelas;
    @BindView(R.id.tvJadwalKuliahRuangan)
    public TextView tvJadwalKuliahRuangan;
    @BindView(R.id.tvJadwalKuliahHari)
    public TextView tvJadwalKuliahHari;
    @BindView(R.id.tvJadwalKuliahStartClass)
    public TextView tvJadwalKuliahStartClass;
    @BindView(R.id.tvJadwalKuliahFinishClass)
    public TextView tvJadwalKuliahFinishClass;

    public JadwalKuliahViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
