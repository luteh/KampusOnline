package com.luteh.kampusonline.ui.fragments.jadwal.ujian;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.luteh.kampusonline.common.Common;
import com.luteh.kampusonline.model.jadwal.JadwalUjian;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Luthfan Maftuh on 13/12/2018.
 * Email luthfanmaftuh@gmail.com
 */
public class JadwalUjianPresenterImp implements IJadwalUjianPresenter {
    private static final String TAG = JadwalUjianFragment.class.getSimpleName();

    private IJadwalUjianView iJadwalUjianView;

    public JadwalUjianPresenterImp(IJadwalUjianView iJadwalUjianView) {
        this.iJadwalUjianView = iJadwalUjianView;
    }

    @Override
    public void retrieveJadwalUjianData(int spinnerPosition) {
        DatabaseReference databaseReference =
                FirebaseDatabase.getInstance().getReference()
                        .child("jadwal_ujian")
                        .child(Common.currentUID)
                        .child(Common.ujianSemesterListChildNames.get(spinnerPosition));

        RxFirebaseDatabase.observeSingleValueEvent(databaseReference,
                dataSnapshot -> {
                    List<JadwalUjian> jadwalUjianList = new ArrayList<>();
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        jadwalUjianList.add(postSnapshot.getValue(JadwalUjian.class));
                    }
                    return jadwalUjianList;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(jadwalUjianList -> {
                    iJadwalUjianView.onRetrieveDataSuccess(jadwalUjianList);
                });
    }
}
