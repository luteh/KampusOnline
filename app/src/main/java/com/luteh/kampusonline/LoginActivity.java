package com.luteh.kampusonline;

import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.luteh.kampusonline.common.Common;
import com.luteh.kampusonline.common.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;
import butterknife.internal.Utils;
import jp.wasabeef.blurry.Blurry;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

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
    public void submitLogIn(){
        if (validate(etEmailLogin, etPasswordLogin)) {
            Common.showToastMessage(this, "Success!");
        }
    }

    private boolean validate(EditText email, EditText password) {
        tilEmailLogin.setError(null);
        tilPasswordLogin.setError(null);

        if (!Common.isValidEmail(email)) {
            tilEmailLogin.setError(getResources().getText(R.string.label_message_unvalid_email));
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
    public boolean onTouchLayout(View view, MotionEvent event){
        hideSoftKeyboard(view);
        return false;
    }
}
