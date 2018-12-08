package com.luteh.kampusonline.ui.fragments.hasilstudi;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.luteh.kampusonline.common.Common;
import com.luteh.kampusonline.model.hasilstudi.HasilStudi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luthfan Maftuh on 25/11/2018.
 * Email luthfanmaftuh@gmail.com
 */
public class HasilStudiPresenterImp implements IHasilStudiPresenter {

    private static final String TAG = "Hasil_Studi";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private Context context;
    private IHasilStudiView iHasilStudiView;

    public HasilStudiPresenterImp(Context context, IHasilStudiView iHasilStudiView) {
        this.context = context;
        this.iHasilStudiView = iHasilStudiView;
    }

    @Override
    public void getHasilStudi(int spinnerPosition) {
        switch (spinnerPosition) {
            case 0:
                queryHasilStudiData("ganjil_2012");
                break;
            case 1:
                queryHasilStudiData("genap_2013");
                break;
        }
    }

    private void queryHasilStudiData(String targetCollection) {
        final List<HasilStudi> hasilStudis = new ArrayList<>();
        db.collection("hasil_studi")
                .document(Common.currentUID)
                .collection(targetCollection)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                hasilStudis.add(document.toObject(HasilStudi.class));
                            }
                        } else {
                            Log.e(TAG, "Error getting documents: ", task.getException());
                        }
                        iHasilStudiView.showHasilStudi(hasilStudis);
                    }
                });
    }
}
