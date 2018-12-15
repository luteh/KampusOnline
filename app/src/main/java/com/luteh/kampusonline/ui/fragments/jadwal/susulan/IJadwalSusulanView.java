package com.luteh.kampusonline.ui.fragments.jadwal.susulan;

import com.luteh.kampusonline.model.jadwal.JadwalUjian;

import java.util.List;

/**
 * Created by Luthfan Maftuh on 15/12/2018.
 * Email luthfanmaftuh@gmail.com
 */
public interface IJadwalSusulanView {
    void onRetrieveDataSuccess(List<JadwalUjian> jadwalUjianList);
}
