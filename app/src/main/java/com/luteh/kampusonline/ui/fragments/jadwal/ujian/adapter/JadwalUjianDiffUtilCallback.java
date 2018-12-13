package com.luteh.kampusonline.ui.fragments.jadwal.ujian.adapter;

import com.luteh.kampusonline.model.jadwal.JadwalUjian;

import java.util.List;

import androidx.recyclerview.widget.DiffUtil;

/**
 * Created by Luthfan Maftuh on 13/12/2018.
 * Email luthfanmaftuh@gmail.com
 */
public class JadwalUjianDiffUtilCallback extends DiffUtil.Callback {
    private List<JadwalUjian> oldList;
    private List<JadwalUjian> newList;

    public JadwalUjianDiffUtilCallback(List<JadwalUjian> oldList, List<JadwalUjian> newList) {
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
