package com.luteh.kampusonline.ui.activities;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.luteh.kampusonline.R;
import com.luteh.kampusonline.common.Common;
import com.luteh.kampusonline.common.base.BaseActivity;
import com.luteh.kampusonline.presenter.login.ILoginPresenter;
import com.luteh.kampusonline.presenter.login.LoginPresenterImp;
import com.luteh.kampusonline.view.ILoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

public class LoginActivity extends BaseActivity implements ILoginView {

    private ILoginPresenter iLoginPresenter;
    private FirebaseAuth mAuth;

    @BindView(R.id.etEmailLogin)
    TextInputEditText etEmailLogin;
    @BindView(R.id.etPasswordLogin)
    TextInputEditText etPasswordLogin;
    @BindView(R.id.tilEmailLogin)
    TextInputLayout tilEmailLogin;
    @BindView(R.id.tilPasswordLogin)
    TextInputLayout tilPasswordLogin;

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
//            Common.showSuccessMessage(this, "Transition to the next page");
            finishWithFade();
            startActivityWithFade(DashboardActivity.class);
        } /*else {
            Common.showErrorMessage(this, "Current User is null");
        }*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
//        ButterKnife.bind(this);

        iLoginPresenter = new LoginPresenterImp(this, this);

        mAuth = FirebaseAuth.getInstance();
    }

    @OnClick(R.id.btnLogin)
    protected void submitLogin() {
        Common.showProgressBar(this);
        iLoginPresenter.submitLogin(etEmailLogin.getText().toString(), etPasswordLogin.getText().toString());
    }

    @OnTouch(R.id.rootLoginLayout)
    public boolean onTouchLayout(View view, MotionEvent event) {
        hideSoftKeyboard(view);
        return false;
    }

    @Override
    public void onLoginSuccess(FirebaseUser user) {
        updateUI(user);
        Common.dismissProgressBar();
    }

    @Override
    public void onLoginError(String message) {
        Common.showErrorMessage(this, message);
        Common.dismissProgressBar();
    }

    @Override
    public void onEmailError(String message) {
        tilEmailLogin.setError(message);
        showSoftKeyboard(etEmailLogin);
        Common.dismissProgressBar();
    }

    @Override
    public void onPasswordError(String message) {
        tilPasswordLogin.setError(message);
        showSoftKeyboard(etPasswordLogin);
        Common.dismissProgressBar();
    }

    @Override
    public void clearError() {
        tilEmailLogin.setError(null);
        tilEmailLogin.setErrorEnabled(false);
        tilPasswordLogin.setError(null);
    }
}
