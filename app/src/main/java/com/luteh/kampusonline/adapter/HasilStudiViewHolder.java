package com.luteh.kampusonline.adapter;

import android.view.View;
import android.widget.TextView;

import com.luteh.kampusonline.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Luthfan Maftuh on 26/11/2018.
 * Email luthfanmaftuh@gmail.com
 */
public class HasilStudiViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvNumber)
    TextView tvNumber;
    @BindView(R.id.tvKode)
    TextView tvKode;
    @BindView(R.id.tvMataKuliah)
    TextView tvMataKuliah;
    @BindView(R.id.tvSks)
    TextView tvSks;
    @BindView(R.id.tvNilai)
    TextView tvNilai;

    public HasilStudiViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
