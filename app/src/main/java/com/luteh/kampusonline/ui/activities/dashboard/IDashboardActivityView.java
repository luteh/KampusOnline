package com.luteh.kampusonline.ui.activities.dashboard;

import com.luteh.kampusonline.model.JatuhTempoDate;
import com.luteh.kampusonline.model.User;

import java.util.List;

/**
 * Created by Luthfan Maftuh on 14/11/2018.
 * Email luthfanmaftuh@gmail.com
 */
public interface IDashboardActivityView {
    /**
     * Set user info to nav bar profile
     * @param user The user object that stored the result of user info from firestore
     */
    void onRetrieveUserInfoSuccess(User user);

    void showJatuhTempoDialog(List<JatuhTempoDate> jatuhTempoDateList);
}
