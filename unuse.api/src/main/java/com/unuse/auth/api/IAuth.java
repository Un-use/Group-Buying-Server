package com.unuse.auth.api;

/**
 * Created by Unuse on 2016/10/29.
 */

public interface IAuth {

    /**
     * Call this API to confirm user
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
    String API_PATH_AUTH_USER_CONFIRM = "/auth/user/confirm";

    /**
     * Call this API to confirm administrator
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
    String API_PATH_AUTH_ADMINISTRATOR_CONFIRM = "/auth/administrator/confirm";

    /**
     * Call this API to get menu item list
     *
     * @type: GET
     *
     * @RequestParam:
     *
     * @return: object of MenuItemListResult
     *
     */
    String API_PATH_AUTH_MENU_ITEM_LIST_GET = "/auth/menu/item/list/get";

    /**
     * Call this API to send a SMS
     *
     * @type: GET
     *
     * @RequestParam:
     * phone   : [String][must]  phone
     *
     * @return: object of ResponseResult
     *
     */
    String API_PATH_AUTH_SEND_SMS = "/auth/send/sms";

}
