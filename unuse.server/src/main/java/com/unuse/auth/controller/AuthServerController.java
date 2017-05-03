package com.unuse.auth.controller;

import com.unuse.auth.api.IAuth;
import com.unuse.auth.api.MenuItem;
import com.unuse.auth.api.MenuItemListResult;
import com.unuse.auth.service.AuthServerService;
import com.unuse.common.BaseController;
import com.unuse.common.ResponseException;
import com.unuse.common.ResponseResult;
import com.unuse.common.StringResult;
import com.unuse.user.api.Administrator;
import com.unuse.user.api.User;
import com.unuse.user.api.UserData;
import com.unuse.user.api.UserDataResult;
import com.unuse.user.service.UserServerService;
import com.unuse.util.MD5Encryption;
import com.unuse.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Unuse on 2016/10/29.
 */

@Controller
public class AuthServerController extends BaseController {

    @Autowired
    private AuthServerService authServerService;

	@Autowired
	private UserServerService userServerService;


	/** Auth **/

	@RequestMapping(value = IAuth.API_PATH_AUTH_USER_CONFIRM, method = RequestMethod.POST)
	@ResponseBody
	public UserDataResult confirmUser(
			@RequestParam("phone") String phone,
			@RequestParam("password") String password) {
		UserDataResult userDataResult = new UserDataResult();

		User user = userServerService.getUserByPhone(phone);
		password = MD5Encryption.toEncryption(password);

		if (null == user || !user.getPassword().equals(password)) {
			throw new ResponseException(ResponseResult.RES_AUTH_FAILED, "账号或者密码错误!");
		}

		userDataResult.setUserData(UserData.makeUserData(user));

		String token = StringUtil.getUUIDString();
		userDataResult.setToken(token);

		authServerService.addSessionData(user.getUid(), token);

		return userDataResult;
	}

	@RequestMapping(value = IAuth.API_PATH_AUTH_ADMINISTRATOR_CONFIRM, method = RequestMethod.POST)
	@ResponseBody
	public UserDataResult confirmAdministrator(
			@RequestParam("phone") String phone,
			@RequestParam("password") String password) {
		UserDataResult userDataResult = new UserDataResult();

		User user = userServerService.getUserByPhone(phone);
		password = MD5Encryption.toEncryption(password);

		if (null == user || !user.getPassword().equals(password)) {
			throw new ResponseException(ResponseResult.RES_AUTH_FAILED, "账号或者密码错误!");
		}

		Administrator administrator = userServerService.getAdministratorByUid(user.getUid());
		if (null == administrator) {
			throw new ResponseException(ResponseResult.RES_AUTH_FAILED, "此账号不是管理员!");
		}

		userDataResult.setUserData(UserData.makeUserData(user));

		String token = StringUtil.getUUIDString();
		userDataResult.setToken(token);

		authServerService.addSessionData(user.getUid(), token);

		return userDataResult;
	}


    /** Menu Item **/

    @RequestMapping(value = IAuth.API_PATH_AUTH_MENU_ITEM_LIST_GET, method = RequestMethod.GET)
    @ResponseBody
    public MenuItemListResult getMenuItemList() {
        MenuItemListResult menuItemListResult = new MenuItemListResult();

        List<MenuItem> menuItemList = authServerService.getMenuItemList();

        menuItemListResult.setMenuItemList(menuItemList);

        return menuItemListResult;
    }


    /** Send SMS **/

    @RequestMapping(value = IAuth.API_PATH_AUTH_SEND_SMS, method = RequestMethod.POST)
    @ResponseBody
    public StringResult sendSMS(@RequestParam("phone") String phone) {
        StringResult res = new StringResult();
        res.setPhone(phone);

        String code = StringUtil.createCode();
        res.setCode(code);

        String resultInfo = authServerService.sendSMSByPhone(phone, code);
        res.setMessageInfo(resultInfo);

        return res;
    }

}
