package com.unuse.user.service;

import com.unuse.config.service.ConfigServerService;
import com.unuse.user.api.Administrator;
import com.unuse.user.api.User;
import com.unuse.user.api.UserAddress;
import com.unuse.user.api.UserData;
import com.unuse.user.mapper.UserMapper;
import com.unuse.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by unuse on 17/2/24.
 */

@Service
public class UserServerService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private ConfigServerService configServerService;


	/** User **/

	public Long addUser(String phone, String password) {
		User user = new User();
		user.setPhone(phone);
		user.setPassword(password);
		userMapper.addUser(user);

		return user.getUid();
	}

	public void updateUser(User user) {
		userMapper.updateUser(user);

		user = getUserByUid(user.getUid());
		configServerService.setUserDataCache(UserData.makeUserData(user));
	}

	public User getUserByUid(Long uid) {
		User user = userMapper.getUserByUid(uid);

		if (null != user) {
			String avatar = makeUserAvatar(user.getAvatar());
			user.setAvatar(avatar);
		}

		return user;
	}

	public UserData getUserDataByUid(Long uid) {
		UserData userData = configServerService.getUserDataCache(uid);

		if (null == userData) {
			userData = UserData.makeUserData(getUserByUid(uid));
			configServerService.setUserDataCache(userData);
		}

		return userData;
	}

	public User getUserByPhone(String phone) {
		User user = userMapper.getUserByPhone(phone);

		if (null != user) {
			String avatar = makeUserAvatar(user.getAvatar());
			user.setAvatar(avatar);
		}

		return user;
	}

	private String makeUserAvatar(String avatar) {
		String result = null;

		if (StringUtil.isNotBlank(avatar)) {
			result = StringUtil.makePicturePreUrl(configServerService.getUserURL(), null, null, null) + avatar;
		}

		return result;
	}


	/** Administrator **/

	public Long addAdministrator(Long uid) {
		Administrator administrator = new Administrator();
		administrator.setUid(uid);
		userMapper.addAdministrator(administrator);

		return administrator.getUid();
	}

	public void updateAdministrator(Administrator administrator) {
		userMapper.updateAdministrator(administrator);
	}

	public Administrator getAdministratorByUid(Long uid) {
		return  userMapper.getAdministratorByUid(uid);
	}


	/** User Address **/

	public void addUserAddress(UserAddress userAddress) {
		userMapper.addUserAddress(userAddress);
	}

	public void updateUserAddress(UserAddress userAddress) {
		userMapper.updateUserAddress(userAddress);
	}

	public void deleteUserAddressById(Integer id) {
		userMapper.deleteUserAddressById(id);
	}

	public List<UserAddress> getUserAddressListByUid(Long uid) {
		return userMapper.getUserAddressListByUid(uid);
	}

    public UserAddress getUserAddressById(Integer id) {
        return userMapper.getUserAddressById(id);
    }
}
