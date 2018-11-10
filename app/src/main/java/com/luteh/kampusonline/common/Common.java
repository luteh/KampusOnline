package com.luteh.kampusonline.common;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

import com.luteh.kampusonline.R;
import com.shashank.sony.fancytoastlib.FancyToast;

/**
 * Created by Luthfan Maftuh on 08/11/2018.
 * Email luthfanmaftuh@gmail.com
 */
public class Common {

    private static AlertDialog.Builder builder;
    private static AlertDialog dialog;

    public static void showProgressBar(Context context) {
        builder = new AlertDialog.Builder(context);
        builder.setCancelable(false); // if you want user to wait for some process to finish,
        builder.setView(R.layout.layout_loading_dialog);

        dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        if (!dialog.isShowing()) dialog.show();
    }

    public static void dismissProgressBar() {
        if (dialog.isShowing()) dialog.dismiss();
    }


    public static void showSuccessMessage(Context context, String message) {
        FancyToast.makeText(context, message, 0, FancyToast.SUCCESS, false).show();
    }

    public static void showInfoMessage(Context context, String message) {
        FancyToast.makeText(context, message, 1, FancyToast.INFO, false).show();
    }

    public static void showErrorMessage(Context context, String message) {
        FancyToast.makeText(context, message, 1, FancyToast.ERROR, false).show();
    }

    public static boolean isValidEmail(EditText editText) {
        return (!TextUtils.isEmpty(editText.getText()) && Patterns.EMAIL_ADDRESS.matcher(editText.getText()).matches());
    }

    public static boolean isEmpty(EditText editText) {
        return TextUtils.isEmpty(editText.getText());
    }

    public static void showToastMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
