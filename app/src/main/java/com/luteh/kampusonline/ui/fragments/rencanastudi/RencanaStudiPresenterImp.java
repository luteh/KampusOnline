package com.luteh.kampusonline.ui.fragments.rencanastudi;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.luteh.kampusonline.common.Common;
import com.luteh.kampusonline.model.jadwal.JadwalKuliah;
import com.luteh.kampusonline.model.rencanastudi.FRencanaStudi;

import java.util.ArrayList;
import java.util.List;

import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.luteh.kampusonline.common.AppConstant.ARG_FRS;

/**
 * Created by Luthfan Maftuh on 28/12/2018.
 * Email luthfanmaftuh@gmail.com
 */
public class RencanaStudiPresenterImp implements IRencanaStudiPresenter {

    private IRencanaStudiView iRencanaStudiView;

    public RencanaStudiPresenterImp(IRencanaStudiView iRencanaStudiView) {
        this.iRencanaStudiView = iRencanaStudiView;
    }

    @Override
    public void retrieveFrsData() {
        DatabaseReference databaseReference =
                FirebaseDatabase.getInstance().getReference()
                        .child(ARG_FRS)
                        .child(Common.currentUID);

        RxFirebaseDatabase.observeSingleValueEvent(databaseReference,
                dataSnapshot -> dataSnapshot.getValue(FRencanaStudi.class))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(fRencanaStudiList -> {
//                    iJadwalKuliahView.showJadwalKuliah(jadwalKuliahList);
                    Log.d("RencanaStudiPresenter", String.format("FRS: %s", fRencanaStudiList.semester_1.get(2).mata_kuliah));
                });
    }
}
