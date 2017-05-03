package com.unuse.user.api;

/**
 * Created by unuse on 17/2/24.
 */

public interface IUser {

	/**
	 * Call this API to register a user
	 *
	 * @type: POST
	 *
	 * @RequestParam:
	 * phone      : [String][must]  phone of user
	 * password   : [String][must]  password of user
	 *
	 * @return: object of UserDataResult
	 *
	 */
	String API_PATH_USER_REGISTER = "/user/register";

	/**
	 * Call this API to update user detail
	 *
	 * @type: POST
	 *
	 * @RequestBody:
	 * user       : [User][must]  object of user
	 *
	 * @return: object of ResponseResult
	 *
	 */
	String API_PATH_USER_UPDATE = "/user/update";

	/**
	 * Call this API to get UserData
	 *
	 * @type: GET
	 *
	 * @RequestParam:
	 * uid      : [Long][must]  uid of user
	 *
	 * @return: object of UserDataResult
	 *
	 */
	String API_PATH_USER_GET = "/user/get";

	/**
	 * Call this API to add a administrator
	 *
	 * @type: POST
	 *
	 * @RequestParam:
	 * uid   : [Long][must]  uid of user
	 *
	 * @return: object of ResponseResult
	 *
	 */
	String API_PATH_ADMINISTRATOR_ADD = "/administrator/add";

	/**
	 * Call this API to update administrator
	 *
	 * @type: POST
	 *
	 * @RequestBody:
	 * administrator   : [Administrator][must]  object of administrator
	 *
	 * @return: object of ResponseResult
	 *
	 */
	String API_PATH_ADMINISTRATOR_UPDATE = "/administrator/update";

	/**
	 * Call this API to add or update UserAddress
	 *
	 * @type: POST
	 *
	 * @RequestBody:
	 * userAddress   : [UserAddress][must]  Object of UserAddress
	 *
	 * @return: object of ResponseResult
	 *
	 */
	String API_PATH_USER_ADDRESS_UPDATE = "/user/address/update";

	/**
	 * Call this API to delete a UserAddress
	 *
	 * @type: POST
	 *
	 * @RequestParam:
	 * id   : [Integer][must]  id of UserAddress
	 *
	 * @return: object of ResponseResult
	 *
	 */
	String API_PATH_USER_ADDRESS_DELETE = "/user/address/delete";

	/**
	 * Call this API to get UserAddress list
	 *
	 * @type: GET
	 *
	 * @RequestParam:
	 * uid     : [Long][must]  uid of user
	 *
	 * @return: object of UserAddressListResult
	 *
	 */
	String API_PATH_USER_ADDRESS_LIST_GET = "/user/address/list/get";
}
