package com.luteh.kampusonline.ui.fragments.jadwal.kuliah;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.luteh.kampusonline.common.Common;
import com.luteh.kampusonline.model.JadwalKuliah;

import java.util.ArrayList;
import java.util.List;

import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Luthfan Maftuh on 09/12/2018.
 * Email luthfanmaftuh@gmail.com
 */
public class JadwalKuliahPresenterImp implements IJadwalKuliahPresenter {

    private static final String TAG = JadwalKuliahFragment.class.getSimpleName();

    private IJadwalKuliahView iJadwalKuliahView;

    public JadwalKuliahPresenterImp(IJadwalKuliahView iJadwalKuliahView) {
        this.iJadwalKuliahView = iJadwalKuliahView;
    }

    @Override
    public void retrieveJadwalKuliahData(int spinnerPosition) {
        DatabaseReference databaseReference =
                FirebaseDatabase.getInstance().getReference()
                        .child("jadwal_kuliah")
                        .child(Common.currentUID)
                        .child(Common.semesterListCollectionNames.get(spinnerPosition));

        RxFirebaseDatabase.observeSingleValueEvent(databaseReference,
                dataSnapshot -> {
                    List<JadwalKuliah> jadwalKuliahList = new ArrayList<>();
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        jadwalKuliahList.add(postSnapshot.getValue(JadwalKuliah.class));
                    }
                    return jadwalKuliahList;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(jadwalKuliahList -> {
                    iJadwalKuliahView.showJadwalKuliah(jadwalKuliahList);
                });
    }
}
