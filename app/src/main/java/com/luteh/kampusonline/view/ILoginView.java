package com.luteh.kampusonline.view;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Luthfan Maftuh on 12/11/2018.
 * Email luthfanmaftuh@gmail.com
 */
public interface ILoginView {
    /**
     * Blur background image
     **/
    void onBlurBackground();

    /**
     * Login success
     **/
    void onLoginSuccess(FirebaseUser user);

    /**
     * Show the login error toast message
     */
    void onLoginError(String message);

    /**
     * Show the email error message
     *
     * @param message the error message
     */
    void onEmailError(String message);

    /**
     * Show the password error message
     *
     * @param message the error message
     */
    void onPasswordError(String message);

    /**
     * Clear the error effect on all fields
     **/
    void clearError();
}
