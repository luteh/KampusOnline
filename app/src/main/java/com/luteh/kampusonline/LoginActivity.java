package com.luteh.kampusonline;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.luteh.kampusonline.common.base.BaseActivity;

import jp.wasabeef.blurry.Blurry;

public class LoginActivity extends BaseActivity {

    private Handler bgBlurHandler = null;
    private Runnable bgBlurRunnable = null;
    private final int BG_BLUR_TIME_MILLISECOND = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

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
}
