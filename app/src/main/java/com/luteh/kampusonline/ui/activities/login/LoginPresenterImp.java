package com.luteh.kampusonline.ui.activities.login;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.luteh.kampusonline.R;
import com.luteh.kampusonline.model.login.LoginRequest;

/**
 * Created by Luthfan Maftuh on 12/11/2018.
 * Email luthfanmaftuh@gmail.com
 */
public class LoginPresenterImp implements ILoginPresenter {
    private Context context;
    private ILoginView iLoginView;

    public LoginPresenterImp(Context context, ILoginView iLoginView) {
        this.context = context;
        this.iLoginView = iLoginView;
    }

    @Override
    public void submitLogin(String email, String password) {
        iLoginView.clearError();

        LoginRequest loginRequest = new LoginRequest(email, password);

        switch (loginRequest.isValidData()) {
            case 0:
                iLoginView.onEmailError(context.getResources().getString(R.string.label_message_email_required));
                break;
            case 1:
                iLoginView.onEmailError(context.getResources().getString(R.string.label_message_unvalid_email));
                break;
            case 2:
                iLoginView.onPasswordError(context.getResources().getString(R.string.label_message_password_required));
                break;
            case -1:
                FirebaseAuth.getInstance()
                        .signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    iLoginView.onLoginSuccess(FirebaseAuth.getInstance().getCurrentUser());
                                } else {
                                    iLoginView.onLoginError(context.getResources().getString(R.string.label_message_email_password_not_match));
                                }
                            }
                        });
                break;
        }

    }
}
