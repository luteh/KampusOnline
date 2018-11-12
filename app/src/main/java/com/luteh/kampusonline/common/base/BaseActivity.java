package com.luteh.kampusonline.common.base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.transition.Fade;
import android.transition.Slide;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Visibility;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.luteh.kampusonline.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.blurry.Blurry;

/**
 * Created by Luthfan Maftuh on 17/08/2018.
 * Email luthfanmaftuh@gmail.com
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    private Fragment mCurrentFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Context getContext() {
        return this;
    }

    public final void startActivityWithFade(Class clazz) {
        startActivity(clazz);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public final void startActivityFromRight(Class clazz, Bundle bundle) {
        startActivity(clazz, bundle);
        overridePendingTransition(R.anim.anim_enter_right, R.anim.anim_sticky);
    }

    public final void startActivityFromRight(Class clazz) {
        startActivity(clazz);
        overridePendingTransition(R.anim.anim_enter_right, R.anim.anim_sticky);
    }

    public final void startActivityFromBottom(Class clazz) {
        startActivity(clazz);
        overridePendingTransition(R.anim.anim_enter_bottom, R.anim.anim_sticky);
    }

    public final void startActivity(Class clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public final void startActivity(Class clazz) {
        startActivity(clazz, null);
    }

    public final void finishToRight() {
        finish();
        overridePendingTransition(R.anim.anim_sticky, R.anim.anim_leave_right);
    }

    public final void finishWithFade() {
        finish();
        overridePendingTransition(android.R.anim.fade_out, R.anim.anim_leave_right);
    }

    public void onRootLayoutClicked(View view) {
        hideSoftKeyboard(view);

        /*view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideSoftKeyboard(v);
                return false;
            }
        });*/
    }

    public void hideSoftKeyboard(View view) {
        try {
            view = getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//                view.setFocusableInTouchMode(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showSoftKeyboard(EditText editText) {
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public void bluringBackgroundImage(int imgResourceId) {
        Blurry.with(getApplicationContext())
                .radius(10)
                .sampling(1)
                .async()
                .capture(findViewById(imgResourceId))
                .into((ImageView) findViewById(imgResourceId));
    }

    /*public void setupUI(final View view){
        view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                hideSoftKeyboard(view);
                return false;
            }
        });
    }*/

    // This method is used to set the default fragment that will be shown.
    public void setDefaultFragment(Fragment defaultFragment) {
        this.replaceFragment(defaultFragment, R.string.title_dashboard_fragment);
    }

    // Replace current Fragment with the destination Fragment.
    public void replaceFragment(Fragment destFragment, int titleResId) {

        if (mCurrentFragment != destFragment) {
            // First get FragmentManager object.
            FragmentManager fragmentManager = this.getSupportFragmentManager();

            // Begin Fragment transaction.
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            destFragment.setEnterTransition(transitionWithFadeIn());
            destFragment.setExitTransition(transitionWithFadeOut());


            // Replace the layout holder with the required Fragment object.
            fragmentTransaction.replace(R.id.fragmentFrameLayout, destFragment);

            // Commit the Fragment replace action.
            fragmentTransaction.commit();
            setTitle(titleResId);
            mCurrentFragment = destFragment;
        }
    }

    private Fade transitionWithFadeIn() {
        if (Build.VERSION.SDK_INT > 20) {
            Fade fadeIn = new Fade();
            fadeIn.setDuration(500);
            fadeIn.setMode(Visibility.MODE_IN);
            return fadeIn;
        } else return null;
    }

    private Fade transitionWithFadeOut() {
        if (Build.VERSION.SDK_INT > 20) {
            Fade fadeOut = new Fade();
            fadeOut.setDuration(300);
            fadeOut.setMode(Visibility.MODE_OUT);
            return fadeOut;
        } else return null;
    }
}
