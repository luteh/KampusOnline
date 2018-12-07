package com.luteh.kampusonline.ui.fragments.jadwalkuliah;

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
    TextView tvJadwalKuliahNo;
    @BindView(R.id.tvJadwalKuliahMatkul)
    TextView tvJadwalKuliahMatkul;
    @BindView(R.id.tvJadwalKuliahDosen)
    TextView tvJadwalKuliahDosen;
    @BindView(R.id.tvJadwalKuliahKode)
    TextView tvJadwalKuliahKode;
    @BindView(R.id.tvJadwalKuliahSks)
    TextView tvJadwalKuliahSks;
    @BindView(R.id.tvJadwalKuliahKelas)
    TextView tvJadwalKuliahKelas;
    @BindView(R.id.tvJadwalKuliahRuangan)
    TextView tvJadwalKuliahRuangan;
    @BindView(R.id.tvJadwalKuliahHari)
    TextView tvJadwalKuliahHari;
    @BindView(R.id.tvJadwalKuliahStartClass)
    TextView tvJadwalKuliahStartClass;
    @BindView(R.id.tvJadwalKuliahFinishClass)
    TextView tvJadwalKuliahFinishClass;


    public JadwalKuliahViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
