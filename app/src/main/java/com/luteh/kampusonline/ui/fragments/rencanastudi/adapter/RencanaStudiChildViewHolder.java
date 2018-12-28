package com.luteh.kampusonline.ui.fragments.rencanastudi.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.TextView;

import com.luteh.kampusonline.R;
import com.thoughtbot.expandablecheckrecyclerview.viewholders.CheckableChildViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Luthfan Maftuh on 28/12/2018.
 * Email luthfanmaftuh@gmail.com
 */
public class RencanaStudiChildViewHolder extends CheckableChildViewHolder {

    @BindView(R.id.tvNumber)
    TextView tvNumber;
    @BindView(R.id.tvMataKuliah)
    TextView tvMataKuliah;
    @BindView(R.id.tvKode)
    TextView tvKode;
    @BindView(R.id.tvSks)
    TextView tvSks;
    @BindView(R.id.tvNilai)
    TextView tvNilai;
    @BindView(R.id.cbFrs)
    CheckBox cbFrs;

    public RencanaStudiChildViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public Checkable getCheckable() {
        return cbFrs;
    }
}
