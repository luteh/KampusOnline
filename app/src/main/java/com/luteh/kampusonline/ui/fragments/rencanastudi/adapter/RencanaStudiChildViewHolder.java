package com.luteh.kampusonline.ui.fragments.rencanastudi.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.luteh.kampusonline.R;
import com.luteh.kampusonline.model.rencanastudi.Semester;
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
    CheckedTextView cbFrs;

    public RencanaStudiChildViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public Checkable getCheckable() {
        return cbFrs;
    }

    public void setChildItemValue(Semester semester) {
        tvNumber.setText(String.format("%s", semester.no.toString()));
        tvMataKuliah.setText(semester.mata_kuliah);
        tvKode.setText(semester.kode);
        tvSks.setText(String.format("%s SKS", semester.sks));
        tvNilai.setText(semester.nilai);
    }

    public void setCheckBoxTint(Context context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (cbFrs.isChecked())
                cbFrs.setCheckMarkTintList(
                        ColorStateList.valueOf(context.getResources().getColor(R.color.colorGreen, null)));
            else
                cbFrs.setCheckMarkTintList(
                        ColorStateList.valueOf(context.getResources().getColor(R.color.colorPrimary, null)));
        }
    }
}
