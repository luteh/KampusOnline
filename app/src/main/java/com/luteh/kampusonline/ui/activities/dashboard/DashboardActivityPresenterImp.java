package com.luteh.kampusonline.ui.activities.dashboard;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.luteh.kampusonline.common.Common;
import com.luteh.kampusonline.model.JatuhTempoDate;
import com.luteh.kampusonline.model.User;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import androidx.annotation.NonNull;
import durdinapps.rxfirebase2.RxFirebaseDatabase;
import durdinapps.rxfirebase2.RxFirestore;
import io.reactivex.MaybeObserver;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
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
    public void onInit() {
        retrieveSemesterListData();
    }

    private void retrieveSemesterListData() {
        DatabaseReference databaseReference =
                FirebaseDatabase.getInstance().getReference()
                        .child("semester_list")
                        .child(Common.currentUID)
                        .child("semester_list");

        RxFirebaseDatabase.observeSingleValueEvent(databaseReference,
                dataSnapshot -> {
                    List<String> list = (List<String>) dataSnapshot.getValue();
                    return list;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(list -> {
                    setSemesterLists(list);
                });
    }

    private void setSemesterLists(List<String> list) {
        Common.semesterLists.addAll(list);
        for (String stringLists : list) {
            Common.semesterListCollectionNames.add(
                    stringLists.replace(" ", "_")
                            .replace("/", "-")
                            .toLowerCase());
        }
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
                        User user1 = document.toObject(User.class);
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
                        .subscribeWith(new DisposableSingleObserver<JatuhTempoDate>() {
                            @Override
                            public void onSuccess(JatuhTempoDate jatuhTempoDates) {
                                iDashboardActivityView.showJatuhTempoDialog(jatuhTempoDates);
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(TAG, "onError: " + e.getMessage());
                            }
                        })
        );
    }

    private Single<JatuhTempoDate> getJatuhTempoLastDate() {
        return Single.create(new SingleOnSubscribe<JatuhTempoDate>() {

            @Override
            public void subscribe(final SingleEmitter<JatuhTempoDate> emitter) throws Exception {
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
                                        JatuhTempoDate jatuhTempoDate = document.toObject(JatuhTempoDate.class);
                                        emitter.onSuccess(jatuhTempoDate);
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

    @Override
    public void onDestroy() {
        disposable.dispose();
    }
}
