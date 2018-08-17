package com.luteh.kampusonline.common.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.luteh.kampusonline.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.blurry.Blurry;

/**
 * Created by Luthfan Maftuh on 17/08/2018.
 * Email luthfanmaftuh@gmail.com
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView{

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

    public final void finishToRight(){
        finish();
        overridePendingTransition(R.anim.anim_sticky, R.anim.anim_leave_right);
    }

    public void hideSoftKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void bluringBackgroundImage(int imgResourceId){
        Blurry.with(getApplicationContext())
                .radius(10)
                .sampling(1)
                .async()
                .capture(findViewById(imgResourceId))
                .into((ImageView)findViewById(imgResourceId));
    }
}
