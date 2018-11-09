package com.luteh.kampusonline;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.luteh.kampusonline.common.Common;
import com.luteh.kampusonline.common.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

public class LoginActivity extends BaseActivity {

    private Handler bgBlurHandler = null;
    private Runnable bgBlurRunnable = null;
    private final int BG_BLUR_TIME_MILLISECOND = 500;

    @BindView(R.id.etEmailLogin)
    EditText etEmailLogin;
    @BindView(R.id.etPasswordLogin)
    EditText etPasswordLogin;
    @BindView(R.id.tilEmailLogin)
    TextInputLayout tilEmailLogin;
    @BindView(R.id.tilPasswordLogin)
    TextInputLayout tilPasswordLogin;

    private FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            Common.showSuccessMessage(this, "Transition to the next page");
        } else {
            Common.showErrorMessage(this, "Current User is null");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

        initBackgroundBlur();
    }

    public void initBackgroundBlur() {

        if (bgBlurRunnable == null) {
            bgBlurRunnable = new Runnable() {
                @Override
                public void run() {
                    bluringBackgroundImage(R.id.img_bg);
                }
            };
        }

        if (bgBlurHandler == null) {
            bgBlurHandler = new Handler();
        }

        bgBlurHandler.postDelayed(bgBlurRunnable, BG_BLUR_TIME_MILLISECOND);
    }

    @OnClick(R.id.btnLogin)
    public void submitLogIn() {
        if (validateForms(etEmailLogin, etPasswordLogin)) {
//            Common.showToastMessage(this, "Success!");
            String email = etEmailLogin.getText().toString();
            String password = etPasswordLogin.getText().toString();

            Common.showProgressBar(this);
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                            } else {
                                Common.showErrorMessage(getContext(), "Authentication failed!");
                                updateUI(null);
                            }
                            Common.dismissProgressBar();
                        }
                    });
        }
    }

    private boolean validateForms(EditText email, EditText password) {
        tilEmailLogin.setError(null);
        tilPasswordLogin.setError(null);

        if (Common.isEmpty(email)) {
            tilEmailLogin.setError(getResources().getText(R.string.label_message_email_required));
            showSoftKeyboard(email);
            return false;
        }

        if (Common.isEmpty(password)) {
            tilPasswordLogin.setError(getResources().getText(R.string.label_message_password_required));
            showSoftKeyboard(password);
            return false;
        }

        return true;
    }

    @OnTouch(R.id.rootLoginLayout)
    public boolean onTouchLayout(View view, MotionEvent event) {
        hideSoftKeyboard(view);
        return false;
    }
}
