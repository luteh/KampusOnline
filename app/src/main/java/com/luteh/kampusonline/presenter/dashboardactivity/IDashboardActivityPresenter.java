package com.luteh.kampusonline.presenter.dashboardactivity;

import com.luteh.kampusonline.model.User;

/**
 * Created by Luthfan Maftuh on 14/11/2018.
 * Email luthfanmaftuh@gmail.com
 */
public interface IDashboardActivityPresenter {
    /**
     * Get user profile info
     * @param uid User UID that generated by Firebase Authentication
     */
    void getUserInfo(String uid);

}
