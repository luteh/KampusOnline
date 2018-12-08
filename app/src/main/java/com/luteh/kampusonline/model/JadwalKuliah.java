package com.luteh.kampusonline.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Luthfan Maftuh on 08/12/2018.
 * Email luthfanmaftuh@gmail.com
 */
public class JadwalKuliah {
    public String no;
    public String kode;
    @SerializedName("mata_kuliah")
    public String mataKuliah;
    public String sks;
    public String dosen;
    public String kelas;
    public String ruangan;
    public String hari;
    @SerializedName("start_class")
    public String startClass;
    @SerializedName("finish_class")
    public String finishClass;
}
