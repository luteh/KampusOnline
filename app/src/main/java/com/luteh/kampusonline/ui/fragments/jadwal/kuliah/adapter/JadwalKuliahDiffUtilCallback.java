package com.luteh.kampusonline.ui.fragments.jadwal.kuliah.adapter;

import com.luteh.kampusonline.model.jadwal.JadwalKuliah;

import java.util.List;

import androidx.recyclerview.widget.DiffUtil;

/**
 * Created by Luthfan Maftuh on 08/12/2018.
 * Email luthfanmaftuh@gmail.com
 */
public class JadwalKuliahDiffUtilCallback extends DiffUtil.Callback {
    private List<JadwalKuliah> oldList;
    private List<JadwalKuliah> newList;

    public JadwalKuliahDiffUtilCallback(List<JadwalKuliah> oldList, List<JadwalKuliah> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).no == newList.get(newItemPosition).no;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }
}
