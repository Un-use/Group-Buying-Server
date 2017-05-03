package com.unuse.user.controller;

import com.unuse.auth.service.AuthServerService;
import com.unuse.common.BaseController;
import com.unuse.common.ResponseException;
import com.unuse.common.ResponseResult;
import com.unuse.user.api.*;
import com.unuse.user.service.UserServerService;
import com.unuse.util.MD5Encryption;
import com.unuse.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by unuse on 17/2/24.
 */

@Controller
public class UserServerController extends BaseController {

	@Autowired
	private UserServerService userServerService;

	@Autowired
	private AuthServerService authServerService;


	/** User **/

	@RequestMapping(value = IUser.API_PATH_USER_REGISTER, method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public UserDataResult registerUser(
			@RequestParam("phone") String phone,
			@RequestParam("password") String password) {
		UserDataResult userDataResult = new UserDataResult();

		User user = userServerService.getUserByPhone(phone);
		if (null != user) {
			throw new ResponseException(ResponseResult.RES_AUTH_FAILED, "用户已存在!");
		}

		Long uid = userServerService.addUser(phone, MD5Encryption.toEncryption(password));

		user = userServerService.getUserByUid(uid);

		userDataResult.setUserData(UserData.makeUserData(user));

		String token = StringUtil.getUUIDString();
		userDataResult.setToken(token);

		authServerService.addSessionData(uid, token);

		return userDataResult;
	}

	@RequestMapping(value = IUser.API_PATH_USER_UPDATE, method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public UserDataResult updateUser(@RequestBody User user) {

		if (null == user.getUid()) {
			throw new ResponseException(ResponseResult.RES_INVALID_PARAM, "uid不能为空!");
		}

		UserDataResult result = new UserDataResult();

		userServerService.updateUser(user);

		result.setUserData(userServerService.getUserDataByUid(user.getUid()));

		return result;
	}

	@RequestMapping(value = IUser.API_PATH_USER_GET, method = RequestMethod.GET)
	@ResponseBody
	public UserDataResult getUserData(@RequestParam("uid") Long uid) {
		UserDataResult result = new UserDataResult();

		User user = userServerService.getUserByUid(uid);
		result.setUserData(UserData.makeUserData(user));

		return result;
	}


	/** Administrator **/

	@RequestMapping(value = IUser.API_PATH_ADMINISTRATOR_ADD, method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public ResponseResult addAdministrator(@RequestParam("uid") Long uid) {

		Administrator administrator = userServerService.getAdministratorByUid(uid);
		if (null != administrator) {
			throw new ResponseException(ResponseResult.RES_AUTH_FAILED, "管理员已存在!");
		}

		userServerService.addAdministrator(uid);

		return ResponseResult.retOK();
	}

	@RequestMapping(value = IUser.API_PATH_ADMINISTRATOR_UPDATE, method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public ResponseResult updateAdministrator(@RequestBody Administrator administrator) {

		userServerService.updateAdministrator(administrator);

		return ResponseResult.retOK();
	}


	/** User Address **/

	@RequestMapping(value = IUser.API_PATH_USER_ADDRESS_UPDATE, method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public ResponseResult updateUserAddress(@RequestBody UserAddress userAddress) {

		if (null == userAddress.getId()) {
			userServerService.addUserAddress(userAddress);
		} else {
			userServerService.updateUserAddress(userAddress);
		}

		return ResponseResult.retOK();
	}

	@RequestMapping(value = IUser.API_PATH_USER_ADDRESS_DELETE, method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public ResponseResult deleteUserAddressById(@RequestParam(value = "id", required = true) Integer id) {

		userServerService.deleteUserAddressById(id);

		return ResponseResult.retOK();
	}

	@RequestMapping(value = IUser.API_PATH_USER_ADDRESS_LIST_GET, method = RequestMethod.GET)
	@ResponseBody
	public UserAddressListResult getUserAddressListByUid(@RequestParam(value = "uid", required = true) Long uid) {
		UserAddressListResult result = new UserAddressListResult();

		List<UserAddress> addressList = userServerService.getUserAddressListByUid(uid);
		result.setAddressList(addressList);

		return result;
	}

}
