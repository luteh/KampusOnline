package com.luteh.kampusonline.common;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.luteh.kampusonline.KampusOnlineApplication;
import com.luteh.kampusonline.model.User;

/**
 * Created by Luthfan Maftuh on 20/12/2018.
 * Email luthfanmaftuh@gmail.com
 */
public class AccountHelper {
    public static User getUser() {
        String s = KampusOnlineApplication.getSharedPreferences().getString("user", "");
        if (TextUtils.isEmpty(s)) {
            return null;
        } else {
            return new Gson().fromJson(s, User.class);
        }
    }

    public static String getToken() {
        String s = KampusOnlineApplication.getSharedPreferences().getString("token", "");
        if (TextUtils.isEmpty(s)) {
            return null;
        } else {
            return s;
        }
    }

    public static boolean isLogin() {
        if (TextUtils.isEmpty(getToken())) {
            return false;
        } else {
            return true;
        }
    }


    public static void saveUser(User user) {
        KampusOnlineApplication.getSharedPreferences().edit().putString("user", new Gson().toJson(user)).apply();
    }

    public static void saveToken(String sid) {
        KampusOnlineApplication.getSharedPreferences().edit().putString("token", sid).apply();
    }

    public static void clearUserData(Context context) {
        KampusOnlineApplication.getSharedPreferences().edit().putString("user", "").apply();
        KampusOnlineApplication.getSharedPreferences().edit().putString("token", "").apply();

    }
}
