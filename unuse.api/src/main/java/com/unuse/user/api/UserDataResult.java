package com.unuse.user.api;

import com.unuse.common.ResponseResult;

/**
 * Created by unuse on 17/2/16.
 */
public class UserDataResult extends ResponseResult {

    private UserData userData;

    private String token;

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
