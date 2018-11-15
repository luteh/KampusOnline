package com.luteh.kampusonline.presenter.dashboardactivity;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.luteh.kampusonline.common.Common;
import com.luteh.kampusonline.model.User;
import com.luteh.kampusonline.view.IDashboardActivityView;

import androidx.annotation.NonNull;

/**
 * Created by Luthfan Maftuh on 14/11/2018.
 * Email luthfanmaftuh@gmail.com
 */
public class DashboardActivityPresenterImp implements IDashboardActivityPresenter {

    private static final String TAG = "Dashboard_Activity";

    private Context context;
    private IDashboardActivityView iDashboardActivityView;

    public DashboardActivityPresenterImp(Context context, IDashboardActivityView iDashboardActivityView) {
        this.context = context;
        this.iDashboardActivityView = iDashboardActivityView;
    }

    @Override
    public void getUserInfo(String uid) {
        DocumentReference docRef = FirebaseFirestore.getInstance().collection("users").document(uid);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        User user = new User(
                                document.get("name").toString(),
                                document.get("npm").toString(),
                                document.get("fakultas").toString(),
                                document.get("jurusan").toString()
                        );
//                        Log.d(this.getClass().getSimpleName(), "DocumentSnapshot data: " + document.getData());
                        iDashboardActivityView.onRetrieveUserInfoSuccess(user);
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }
}
