package com.luteh.kampusonline;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.common.eventbus.EventBus;

/**
 * Created by Luthfan Maftuh on 20/12/2018.
 * Email luthfanmaftuh@gmail.com
 */
public class KampusOnlineApplication extends Application {
    private static KampusOnlineApplication singleton;

    public static KampusOnlineApplication getInstance() {
        return singleton;
    }

    public static void saveConfig(String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(key, value);
        editor.apply();
        editor.clear();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
    }

    public static SharedPreferences getSharedPreferences() {
        return KampusOnlineApplication.getInstance().getSharedPreferences("mypref", Context.MODE_PRIVATE);
    }
}
