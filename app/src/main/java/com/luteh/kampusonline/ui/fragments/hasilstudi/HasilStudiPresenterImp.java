package com.luteh.kampusonline.ui.fragments.hasilstudi;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.luteh.kampusonline.common.Common;
import com.luteh.kampusonline.model.hasilstudi.HasilStudi;

import java.util.ArrayList;
import java.util.List;

import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Luthfan Maftuh on 25/11/2018.
 * Email luthfanmaftuh@gmail.com
 */
public class HasilStudiPresenterImp implements IHasilStudiPresenter {

    private static final String TAG = "Hasil_Studi";

    private Context context;
    private IHasilStudiView iHasilStudiView;

    public HasilStudiPresenterImp(Context context, IHasilStudiView iHasilStudiView) {
        this.context = context;
        this.iHasilStudiView = iHasilStudiView;
    }

    @Override
    public void getHasilStudi(int spinnerPosition) {
        retrieveHasilStudiData(Common.semesterListCollectionNames.get(spinnerPosition));
    }

    private void retrieveHasilStudiData(String childSemester) {
        DatabaseReference databaseReference =
                FirebaseDatabase.getInstance().getReference()
                        .child("hasil_studi")
                        .child(Common.currentUID)
                        .child(childSemester);

        RxFirebaseDatabase.observeSingleValueEvent(databaseReference,
                dataSnapshot -> {
                    List<HasilStudi> hasilStudiList = new ArrayList<>();
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        hasilStudiList.add(postSnapshot.getValue(HasilStudi.class));
                    }
                    return hasilStudiList;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(hasilStudiList -> {
                    iHasilStudiView.showHasilStudi(hasilStudiList);
                });
    }
}
