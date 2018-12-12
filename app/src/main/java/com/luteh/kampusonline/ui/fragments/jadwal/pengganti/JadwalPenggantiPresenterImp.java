package com.luteh.kampusonline.ui.fragments.jadwal.pengganti;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.luteh.kampusonline.common.Common;
import com.luteh.kampusonline.model.jadwal.JadwalPengganti;

import java.util.ArrayList;
import java.util.List;

import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Luthfan Maftuh on 11/12/2018.
 * Email luthfanmaftuh@gmail.com
 */
public class JadwalPenggantiPresenterImp implements IJadwalPenggantiPresenter {

    private IJadwalPenggantiView iJadwalPenggantiView;

    public JadwalPenggantiPresenterImp(IJadwalPenggantiView iJadwalPenggantiView) {
        this.iJadwalPenggantiView = iJadwalPenggantiView;
    }

    @Override
    public void retrieveJadwalPenggantiData(int spinnerPosition) {
        DatabaseReference databaseReference =
                FirebaseDatabase.getInstance().getReference()
                        .child("jadwal_pengganti")
                        .child(Common.currentUID)
                        .child(Common.semesterListCollectionNames.get(spinnerPosition));

        RxFirebaseDatabase.observeSingleValueEvent(databaseReference,
                dataSnapshot -> {
                    List<JadwalPengganti> jadwalPenggantiList = new ArrayList<>();
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        jadwalPenggantiList.add(postSnapshot.getValue(JadwalPengganti.class));
                    }
                    return jadwalPenggantiList;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(jadwalPenggantiList -> {
                    iJadwalPenggantiView.showJadwalPengganti(jadwalPenggantiList);
                });
    }
}
