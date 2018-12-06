package com.luteh.kampusonline.ui.activities.dashboard;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.core.FirestoreClient;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.luteh.kampusonline.model.JatuhTempoDate;
import com.luteh.kampusonline.model.User;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Luthfan Maftuh on 14/11/2018.
 * Email luthfanmaftuh@gmail.com
 */
public class DashboardActivityPresenterImp implements IDashboardActivityPresenter {

    private static final String TAG = DashboardActivity.class.getSimpleName();
    private CompositeDisposable disposable = new CompositeDisposable();

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
                        User user1 = new User(document.get("name").toString(),
                                document.get("npm").toString(),
                                document.get("fakultas").toString(),
                                document.get("jurusan").toString());
                        iDashboardActivityView.onRetrieveUserInfoSuccess(user1);
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

        StorageReference storageReference = FirebaseStorage.getInstance().getReference()
                .child("users/image_profile/" + uid + ".jpg");
        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                User userl = new User(uri);
                iDashboardActivityView.onRetrieveUserInfoSuccess(userl);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });


    }

    @Override
    public void getJatuhTempoDate() {
        disposable.add(
                getJatuhTempoLastDate()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<JatuhTempoDate>>() {
                            @Override
                            public void onSuccess(List<JatuhTempoDate> jatuhTempoDates) {
                                iDashboardActivityView.showJatuhTempoDialog(jatuhTempoDates);
                                Log.d(TAG, "onSuccess: " + jatuhTempoDates.get(0).startDate);
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(TAG, "onError: " + e.getMessage());
                            }
                        })
        );
    }

    @Override
    public void onDestroy() {
        disposable.dispose();
    }

    private Single<List<JatuhTempoDate>> getJatuhTempoLastDate() {
        return Single.create(new SingleOnSubscribe<List<JatuhTempoDate>>() {

            @Override
            public void subscribe(final SingleEmitter<List<JatuhTempoDate>> emitter) throws Exception {
                final List<JatuhTempoDate> jatuhTempoDateList = new ArrayList<>();
                final DocumentReference docRef = FirebaseFirestore.getInstance()
                        .collection("jatuh_tempo_frs")
                        .document("2012");
                docRef.get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        jatuhTempoDateList.add(document.toObject(JatuhTempoDate.class));
                                        Log.d(TAG, jatuhTempoDateList.size() + "");
                                        emitter.onSuccess(jatuhTempoDateList);
                                    } else {
                                        Log.d(TAG, "No such document");
                                    }
                                } else {
                                    Log.d(TAG, "get failed with ", task.getException());
                                }
                            }
                        });
            }
        });
    }
}
