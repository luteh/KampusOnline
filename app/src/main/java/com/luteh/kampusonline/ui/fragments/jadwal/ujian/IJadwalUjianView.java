package com.luteh.kampusonline.ui.fragments.jadwal.ujian;

import com.luteh.kampusonline.model.jadwal.JadwalUjian;

import java.util.List;

/**
 * Created by Luthfan Maftuh on 13/12/2018.
 * Email luthfanmaftuh@gmail.com
 */
public interface IJadwalUjianView {
    void onRetrieveDataSuccess(List<JadwalUjian> jadwalUjianList);
}
