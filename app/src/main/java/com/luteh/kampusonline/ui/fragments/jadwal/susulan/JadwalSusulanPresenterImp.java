package com.luteh.kampusonline.ui.fragments.jadwal.susulan;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.luteh.kampusonline.common.Common;
import com.luteh.kampusonline.model.jadwal.JadwalUjian;
import com.luteh.kampusonline.ui.fragments.jadwal.ujian.JadwalUjianFragment;

import java.util.ArrayList;
import java.util.List;

import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Luthfan Maftuh on 15/12/2018.
 * Email luthfanmaftuh@gmail.com
 */
public class JadwalSusulanPresenterImp implements IJadwalSusulanPresenter {
    private static final String TAG = JadwalUjianFragment.class.getSimpleName();

    private IJadwalSusulanView IJadwalSusulanView;

    public JadwalSusulanPresenterImp(IJadwalSusulanView IJadwalSusulanView) {
        this.IJadwalSusulanView = IJadwalSusulanView;
    }

    @Override
    public void retrieveJadwalSusulanData(int spinnerPosition) {
        DatabaseReference databaseReference =
                FirebaseDatabase.getInstance().getReference()
                        .child("jadwal_susulan")
                        .child(Common.currentUID)
                        .child(Common.susulanSemesterListChildNames.get(spinnerPosition));

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
                    IJadwalSusulanView.onRetrieveDataSuccess(jadwalUjianList);
                });
    }
}
