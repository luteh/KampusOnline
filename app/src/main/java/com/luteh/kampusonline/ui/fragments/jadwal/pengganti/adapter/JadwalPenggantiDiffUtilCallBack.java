package com.luteh.kampusonline.ui.fragments.jadwal.pengganti.adapter;

import com.luteh.kampusonline.model.jadwal.JadwalPengganti;

import java.util.List;

import androidx.recyclerview.widget.DiffUtil;

/**
 * Created by Luthfan Maftuh on 11/12/2018.
 * Email luthfanmaftuh@gmail.com
 */
public class JadwalPenggantiDiffUtilCallBack extends DiffUtil.Callback {
    private List<JadwalPengganti> oldList;
    private List<JadwalPengganti> newList;

    public JadwalPenggantiDiffUtilCallBack(List<JadwalPengganti> oldList, List<JadwalPengganti> newList) {
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
