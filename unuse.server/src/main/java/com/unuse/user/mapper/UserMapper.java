package com.unuse.user.mapper;

import com.unuse.user.api.Administrator;
import com.unuse.user.api.User;
import com.unuse.user.api.UserAddress;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by unuse on 17/2/24.
 */

@Repository
public interface UserMapper {

	/** User **/

	void addUser(User user);

	void updateUser(User user);

	void deleteUserByUid(@Param("uid") Long uid);

	User getUserByUid(@Param("uid") Long uid);

	User getUserByPhone(@Param("phone") String phone);


	/** Administrator **/

	Administrator getAdministratorByUid(@Param("uid") Long uid);

	void addAdministrator(Administrator administrator);

	void updateAdministrator(Administrator administrator);


	/** User Address **/

	void addUserAddress(UserAddress userAddress);

	void updateUserAddress(UserAddress userAddress);

	void deleteUserAddressById(@Param("id") Integer id);

	List<UserAddress> getUserAddressListByUid(@Param("uid") Long uid);

    UserAddress getUserAddressById(@Param("id") Integer id);
}
