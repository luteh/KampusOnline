package com.luteh.kampusonline;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.luteh.kampusonline.common.base.BaseActivity;
import com.luteh.kampusonline.common.util.PermissionHelper;

public class SplashActivity extends BaseActivity {

    private PermissionHelper permissionHelper;

    private Handler splashScreenHandler = null;
    private Runnable splashScreenRunnable = null;
    private final int SPLASH_TIME_MILLISECOND = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        initPermission();
        //initSplash();
    }

    public void initPermission() {
        permissionHelper = new PermissionHelper.Builder(this)
                .withPermissions(
                        Manifest.permission.INTERNET,
                        Manifest.permission.GET_ACCOUNTS)
                .withListener(new PermissionHelper.OnPermissionCheckedListener() {
                    @Override
                    public void onPermissionGranted(boolean isPermissionAlreadyGranted) {
                        //splashPresenter.initSplash();
                        initSplash();
                    }

                    @Override
                    public void onPermissionDenied() {
                        showPermissionDialog();
                    }
                })
                .build();

        permissionHelper.requestPermission();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0) {
            initSplash();
        }
    }

    public void initSplash() {

        if (splashScreenRunnable == null) {
            splashScreenRunnable = new Runnable() {
                @Override
                public void run() {
                    //splashView.openHome();
                    startActivityWithFade(LoginActivity.class);
                }
            };
        }

        if (splashScreenHandler == null) {
            splashScreenHandler = new Handler();
        }

        splashScreenHandler.postDelayed(splashScreenRunnable, SPLASH_TIME_MILLISECOND);
    }

    private void showPermissionDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppTheme);

        builder.setTitle(getResources().getString(R.string.label_warning));
        builder.setCancelable(false);
        builder.setMessage(getResources().getString(R.string.label_message_apps_requirement));
        builder.setPositiveButton(getResources().getString(R.string.label_show_permission), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                initPermission();
            }
        });
        builder.setNegativeButton(getResources().getString(R.string.label_close_app), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                finish();
            }
        });
        builder.show();
    }
}
