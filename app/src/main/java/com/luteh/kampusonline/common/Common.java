package com.luteh.kampusonline.common;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Luthfan Maftuh on 08/11/2018.
 * Email luthfanmaftuh@gmail.com
 */
public class Common {
    public static boolean isValidEmail(EditText editText) {
        return (!TextUtils.isEmpty(editText.getText()) && Patterns.EMAIL_ADDRESS.matcher(editText.getText()).matches());
    }

    public static boolean isEmpty(EditText editText){
        return TextUtils.isEmpty(editText.getText());
    }

    public static void showToastMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
