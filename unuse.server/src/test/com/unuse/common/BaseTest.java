package com.unuse.common;

import com.unuse.auth.api.IAuth;
import com.unuse.user.api.UserDataResult;
import com.unuse.util.HttpService;

import java.util.HashMap;

/**
 * Created by unuse on 17/2/23.
 */

public class BaseTest {

    public HttpService httpService = new HttpService();

    private long unitStartTime;

    public void doLogin(String phone, String password) {
        if (null != HttpService.getToken()) {
            return;
        }

        startUnit();

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("phone", phone);
        params.put("password", password);

        UserDataResult result = httpService.runService(params, IAuth.API_PATH_AUTH_ADMINISTRATOR_CONFIRM, UserDataResult.class, false);
        HttpService.setToken(result.getToken());

        endUnit();
    }

    public void startUnit() {
        System.out.println("----------------------------------------");
        unitStartTime = System.currentTimeMillis();
    }

    public void endUnit() {
        System.out.printf("Used Time=%dms\n", System.currentTimeMillis() - unitStartTime );
        System.out.println("----------------------------------------\n");
    }

}
