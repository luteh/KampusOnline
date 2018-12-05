package com.luteh.kampusonline.ui.activities.login;

import com.luteh.kampusonline.model.User;

/**
 * Created by Luthfan Maftuh on 12/11/2018.
 * Email luthfanmaftuh@gmail.com
 */
public interface ILoginPresenter {
    /**
     * Login
     *
     * @param email    the email
     * @param password the password
     */
    void submitLogin(String email, String password);

}
