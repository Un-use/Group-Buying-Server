package com.unuse.auth;

import com.unuse.auth.api.IAuth;
import com.unuse.common.BaseTest;
import com.unuse.common.ResponseResult;
import com.unuse.common.StringResult;
import com.unuse.user.api.IUser;
import com.unuse.user.api.UserAddress;
import com.unuse.user.api.UserDataResult;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created by unuse on 17/2/9.
 */
public class AuthTest extends BaseTest {

    @Test
    public void registerUser() {

        startUnit();
//
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("phone", "18857119716");
        params.put("password", "unuse");

        UserDataResult result = httpService.runService(params, IAuth.API_PATH_AUTH_USER_CONFIRM, UserDataResult.class, false);
        Assert.assertTrue(result.getResult() == ResponseResult.RES_OK);

        endUnit();
    }

    @Test
    public void sendSMS() {

        startUnit();

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("phone", "18857119902");

        StringResult result = httpService.runService(params, IAuth.API_PATH_AUTH_SEND_SMS, StringResult.class, false);
        Assert.assertTrue(result.getResult() == ResponseResult.RES_OK);

        endUnit();
    }

    @Test
    public void updateUserAddress() {

        startUnit();

        UserAddress userAddress = new UserAddress();
        userAddress.setId(2);
        userAddress.setUid(1L);
        userAddress.setAddress("11");
        userAddress.setCity("111");
        userAddress.setDistrict("1111");
        userAddress.setName("111");
        userAddress.setProvince("111");

        ResponseResult result = httpService.runService(userAddress, IUser.API_PATH_USER_ADDRESS_UPDATE, ResponseResult.class);
        Assert.assertTrue(result.getResult() == ResponseResult.RES_OK);

        endUnit();
    }

    @Test
    public void deleteUserAddress() {

        startUnit();

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("id", 1);

        ResponseResult result = httpService.runService(params, IUser.API_PATH_USER_ADDRESS_DELETE, ResponseResult.class, false);
        Assert.assertTrue(result.getResult() == ResponseResult.RES_OK);

        endUnit();
    }

    @Test
    public void getUserAddressListByUid() {

        startUnit();

        ResponseResult result = httpService.runService("uid=1", IUser.API_PATH_USER_ADDRESS_LIST_GET, ResponseResult.class);
        Assert.assertTrue(result.getResult() == ResponseResult.RES_OK);

        endUnit();
    }

}
