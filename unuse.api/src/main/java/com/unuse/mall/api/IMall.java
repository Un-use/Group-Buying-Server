package com.unuse.mall.api;

/**
 * Created by Unuse on 2016/10/29.
 */

public interface IMall {

    class ItemStatus {
        public final static int NORMAL   = 0;
        public final static int DELETE   = 1;
    }

    class Type {
        public final static int NORMAL   = 1;
        public final static int GROUP    = 2;
    }

	class Choice {
		public final static int NORMAL   = 0;
		public final static int CHOICE   = 1;
	}

	class CategoryLevel {
		public final static int FIRST    = 1;
		public final static int SECOND   = 2;
	}

	class CategoryStatus {
		public final static int NORMAL   = 0;
		public final static int DELETE   = 1;
	}

    class NormalOrderStatus {
        public final static int NORMAL          = 0;
		public final static int DELETE          = 1;
		public final static int FINISHED        = 2;
        public final static int WAIT_PAYMENT    = 3;
        public final static int WAIT_DELIVERY   = 4;
        public final static int WAIT_RECEIVE    = 5;
        public final static int WAIT_COMMENT    = 6;
    }

    class GroupOrderStatus {
        public final static int NORMAL          = 0;
        public final static int DELETE          = 1;
		public final static int FINISHED        = 2;
        public final static int JOIN            = 3;
        public final static int START           = 4;
        public final static int FAIL            = 5;
        public final static int WAIT_COMMENT    = 6;
    }

	class GoodsStatus {
		public final static int NORMAL     = 0;
		public final static int DELETE     = 1;
		public final static int FINISHED   = 2;
	}

	class CommentStatus {
		public final static int PASS    = 0;
		public final static int REJECT  = 1;
		public final static int WAIT   	= 2;
	}

	class ReplyStatus {
		public final static int PASS    = 0;
		public final static int REJECT  = 1;
		public final static int WAIT   	= 2;
	}

	class SetStatus {
		public final static int NORMAL   = 0;
		public final static int DELETE   = 1;
	}

	class CommentDisabled {
		public final static int SHOW    = 0;
		public final static int HIDDEN  = 1;
	}

	class ReturnGoodsStatus {
		public final static int NORMAL     = 0;
		public final static int DELETE     = 1;
		public final static int FINISHED   = 2;
	}

    /** Mall Seller **/

    /**
     * Call this API to add a MallSeller
     *
     * @type: POST
     *
     * @RequestBody:
     * mallSeller : [MallSeller][must] object of MallSeller
     *
     * @return: object of ResponseResult
     *
     */
    String API_PATH_MALL_SELLER_ADD = "/mall/seller/add";

    /**
     * Call this API to update MallSeller
     *
     * @type: POST
     *
     * @RequestBody:
     * mallSeller : [MallSeller][must] object of MallSeller
     *
     * @return: object of ResponseResult
     *
     */
    String API_PATH_MALL_SELLER_UPDATE = "/mall/seller/update";

    /**
     * Call this API to get a MallSeller
     *
     * @type: GET
     *
     * @RequestParam:
     * uid   : [Long][must]  id of user
     *
     * @return: object of MallSellerResult
     *
     */
    String API_PATH_MALL_SELLER_GET = "/mall/seller/get";


    /** Mall Category **/

    /**
     * Call this API to add or update MallCategory
     *
     * @type: POST
     *
     * @RequestBody:
     * mallCategory : [MallCategory][must] object of MallCategory
     *
     * @return: object of ResponseResult
     *
     */
    String API_PATH_MALL_CATEGORY_UPDATE = "/mall/category/update";

    /**
     * Call this API to get a MallCategory
     *
     * @type: GET
     *
     * @RequestParam:
     * cid   : [Integer][must]  cid of MallCategory
     *
     * @return: object of MallCategoryResult
     *
     */
    String API_PATH_MALL_CATEGORY_GET = "/mall/category/get";

    /**
     * Call this API to get MallCategory list
     *
     * @type: GET
     *
     * @RequestParam:
     * level   : [Integer][optional]  level of MallCategory
     *
     * @return: object of MallCategoryListResult
     *
     */
    String API_PATH_MALL_CATEGORY_LIST_GET = "/mall/category/list/get";


    /** Mall Item **/

    /**
     * Call this API to add or update MallItem
     *
     * @type: POST
     *
     * @RequestBody:
     * mallItem      : [Long][must]  object of MallItem
     *
     * @return: object of ResponseResult
     *
     */
    String API_PATH_MALL_ITEM_UPDATE = "/mall/item/update";

	/**
	 * Call this API to get MallItem list
	 *
	 * @type: GET
	 *
	 * @RequestParam:
	 * type       : [Integer][must] type of MallItem
	 * title      : [String][optional]  title of MallItem
	 * startPrice : [Float][optional]
	 * endPrice   : [Float][optional]
	 * cid        : [Integer][optional] cid of MallCategory
	 * status     : [Integer][optional] status of MallItem
	 * start      : [Integer][optional]
	 * count      : [Integer][optional]
	 *
	 * @return: object of MallItemListResult
	 *
	 */
	String API_PATH_MALL_ITEM_LIST_GET = "/mall/item/list/get";

	/**
	 * Call this API to get home MallItem
	 *
	 * @type: GET
	 *
	 * @RequestParam:
	 *
	 * @return: object of MallHomeItemListResult
	 *
	 */
	String API_PATH_MALL_ITEM_HOME_GET = "/mall/item/home/get";


    /** Mall Comment **/

    /**
     * Call this API to add or update MallComment
     *
     * @type: POST
     *
     * @RequestBody:
     * mallComment      : [MallComment][must]  object of MallComment
     *
     * @return: object of ResponseResult
     *
     */
    String API_PATH_MALL_ITEM_COMMENT_UPDATE = "/mall/item/comment/update";

    /**
     * Call this API to get MallComment list
     *
     * @type: GET
     *
     * @RequestParam:
     * itemId     : [Long][optional]  itemId of MallItem
	 * status     : [Integer][optional]
	 * disabled   : [Integer][optional]
	 * start      : [Integer][optional]
     * count      : [Integer][optional]
     *
     * @return: object of MallCommentListResult
     *
     */
    String API_PATH_MALL_ITEM_COMMENT_LIST_GET = "/mall/item/comment/list/get";


    /** Mall Reply **/

    /**
     * Call this API to add or update MallReply
     *
     * @type: POST
     *
     * @RequestBody:
     * mallReply      : [MallReply][must]  object of MallReply
     *
     * @return: object of ResponseResult
     *
     */
    String API_PATH_MALL_ITEM_REPLY_UPDATE = "/mall/item/reply/update";

    /**
     * Call this API to get MallReply list
     *
     * @type: GET
     *
     * @RequestParam:
     * commentId  : [Long][must]  commentId of MallComment
	 * status     : [Integer][optional] status of MallComment
	 * start      : [Integer][optional]
     * count      : [Integer][optional]
     *
     * @return: object of MallReplyListResult
     *
     */
    String API_PATH_MALL_ITEM_REPLY_LIST_GET = "/mall/item/reply/list/get";


    /** Mall Cart **/

    /**
     * Call this API to add or update MallCart
     *
     * @type: POST
     *
     * @RequestBody:
     * mallCart      : [MallCart][must]  object of MallCart
     *
     * @return: object of ResponseResult
     *
     */
    String API_PATH_MALL_CART_UPDATE = "/mall/cart/update";

    /**
     * Call this API to delete a MallCart
     *
     * @type: POST
     *
     * @RequestParam:
     * cartId      : [Integer][must]  id of MallCart
     *
     * @return: object of ResponseResult
     *
     */
    String API_PATH_MALL_CART_DELETE = "/mall/cart/delete";

    /**
     * Call this API to get MallCart list
     *
     * @type: GET
     *
     * @RequestParam:
     * uid        : [Long][must]  uid of User
     * start      : [Integer][optional]
     * count      : [Integer][optional]
     *
     * @return: object of MallCartListResult
     *
     */
    String API_PATH_MALL_CART_LIST_GET = "/mall/cart/list/get";


    /** Mall Order **/

    /**
     * Call this API to add or update MallOrder
     *
     * @type: POST
     *
     * @RequestBody:
     * mallOrder     : [MallOrder][must]  object of MallOrder
     *
     * @return: object of ResponseResult
     *
     */
    String API_PATH_MALL_ORDER_UPDATE = "/mall/order/update";

    /**
     * Call this API to get MallOrder list
     *
     * @type: GET
     *
     * @RequestParam:
     * uid        : [Long][must]  uid of User
     * type       : [Integer][must]  type of MallOrder
     * status     : [Integer][false]  status of MallOrder
     * start      : [Integer][optional]
     * count      : [Integer][optional]
     *
     * @return: object of MallOrderListResult
     *
     */
    String API_PATH_MALL_ORDER_LIST_GET = "/mall/order/list/get";


    /** Mall pay **/

    /**
     * Call this API to pay for MallOrder
     *
     * @type: POST
     *
     * @RequestBody:
	 * oid        : [Long][must] id of MallOrder
	 * password   : [String][must] password of User
     *
     * @return: object of ResponseResult
     *
     */
    String API_PATH_MALL_PAY_MALL_ORDER = "/mall/order/pay";


	/** Mall Return Goods **/

	/**
	 * Call this API to add or update MallReturnGoods
	 *
	 * @type: POST
	 *
	 * @RequestBody:
	 * mallReturnGoods     : [MallReturnGoods][must]  object of MallReturnGoods
	 *
	 * @return: object of ResponseResult
	 *
	 */
	String API_PATH_MALL_RETURN_GOODS_UPDATE = "/mall/return/goods/update";

	/**
	 * Call this API to get MallReturnGoods list
	 *
	 * @type: GET
	 *
	 * @RequestParam:
	 * uid        : [Long][must]  uid of User
	 * status     : [Integer][must]  status of MallReturnGoods
	 * start      : [Integer][optional]
	 * count      : [Integer][optional]
	 *
	 * @return: object of MallOrderListResult
	 *
	 */
	String API_PATH_MALL_RETURN_GOODS_LIST_GET = "/mall/return/goods/list/get";


	/** Mall Goods **/

	/**
	 * Call this API to add MallGoods
	 *
	 * @type: POST
	 *
	 * @RequestBody:
	 * mallGoodsList     : [List<MallGoods>][must]  object of MallGoods
	 *
	 * @return: object of ResponseResult
	 *
	 */
	String API_PATH_MALL_GOODS_ADD = "/mall/goods/add";

	/**
	 * Call this API to add or update MallGoods
	 *
	 * @type: POST
	 *
	 * @RequestBody:
	 * mallGoodsList     : [List<MallGoods>][must]  object of MallGoods
	 *
	 * @return: object of ResponseResult
	 *
	 */
	String API_PATH_MALL_GOODS_UPDATE = "/mall/goods/update";


	/** Mall Set **/

	/**
	 * Call this API to add or update MallSet
	 *
	 * @type: POST
	 *
	 * @RequestBody:
	 * mallSet     : [MallSet][must]  object of MallSet
	 *
	 * @return: object of ResponseResult
	 *
	 */
	String API_PATH_MALL_SET_UPDATE = "/mall/set/update";

	/**
	 * Call this API to get MallSet list
	 *
	 * @type: GET
	 *
	 * @RequestParam:
	 * status     : [Integer][optional]  status of MallSet
	 * start      : [Integer][optional]
	 * count      : [Integer][optional]
	 *
	 * @return: object of MallSetListResult
	 *
	 */
	String API_PATH_MALL_SET_LIST_GET = "/mall/set/list/get";
}
