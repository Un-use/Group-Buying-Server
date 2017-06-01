package com.unuse.mall.controller;

import com.unuse.common.BaseController;
import com.unuse.common.LongResult;
import com.unuse.common.ResponseException;
import com.unuse.common.ResponseResult;
import com.unuse.mall.api.*;
import com.unuse.mall.service.MallServerService;
import com.unuse.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Unuse on 2016/10/29.
 */

@Controller
public class MallServerController extends BaseController {

    @Autowired
    private MallServerService mallServerService;


    /** Mall Seller **/

    @RequestMapping(value = IMall.API_PATH_MALL_SELLER_ADD, method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ResponseResult addMallSeller(@RequestBody MallSeller mallSeller) {

        mallServerService.addMallSeller(mallSeller);

        return ResponseResult.retOK();
    }

    @RequestMapping(value = IMall.API_PATH_MALL_SELLER_UPDATE, method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ResponseResult updateMallSeller(@RequestBody MallSeller mallSeller) {

        if (null == mallSeller.getUid()) {
            throw new ResponseException(ResponseResult.RES_INVALID_PARAM, "请输入UID!");
        }

        mallServerService.updateMallSeller(mallSeller);

        return ResponseResult.retOK();
    }

    @RequestMapping(value = IMall.API_PATH_MALL_SELLER_GET, method = RequestMethod.GET)
    @ResponseBody
    public MallSellerResult getMallSellerByUid(@RequestParam(value = "uid") Long uid) {

        MallSellerResult result = new MallSellerResult();

        MallSeller mallSeller = mallServerService.getMallSellerByUid(uid);
        result.setMallSeller(mallSeller);

        return result;
    }


    /** Mall Category **/

    @RequestMapping(value = IMall.API_PATH_MALL_CATEGORY_UPDATE, method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ResponseResult updateMallCategory(@RequestBody MallCategory mallCategory) {

        if (null == mallCategory.getValue()) {
            mallServerService.addMallCategory(mallCategory);
        } else {
            mallServerService.updateMallCategory(mallCategory);
        }

        return ResponseResult.retOK();
    }

    @RequestMapping(value = IMall.API_PATH_MALL_CATEGORY_GET, method = RequestMethod.GET)
    @ResponseBody
    public MallCategoryResult getMallCategory(@RequestParam(value = "cid") Integer cid) {

        MallCategoryResult result = new MallCategoryResult();

        MallCategory mallCategory = mallServerService.getMallCategoryByCid(cid);
        result.setMallCategory(mallCategory);

        return result;
    }

    @RequestMapping(value = IMall.API_PATH_MALL_CATEGORY_LIST_GET, method = RequestMethod.GET)
    @ResponseBody
    public MallCategoryListResult getMallCategoryListByLevel(
            @RequestParam(value = "level", required = false) Integer level) {

		MallCategoryListResult result = new MallCategoryListResult();

        List<MallCategory> categoryList = mallServerService.getMallCategoryList(level, IMall.CategoryStatus.NORMAL);
        result.setCategoryList(categoryList);

        List<MallCategory> categoryTree = mallServerService.getMallCategoryTree(categoryList);
        result.setCategoryTree(categoryTree);

        return result;
    }


    /** Mall Item **/

    @RequestMapping(value = IMall.API_PATH_MALL_ITEM_UPDATE, method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ResponseResult updateMallItem(@RequestBody MallItem mallItem) {

        if (null == mallItem.getItemId()) {
            mallServerService.addMallItem(mallItem);
        } else {
            mallServerService.updateMallItem(mallItem);
        }

        return ResponseResult.retOK();
    }

    @RequestMapping(value = IMall.API_PATH_MALL_ITEM_LIST_GET, method = RequestMethod.GET)
    @ResponseBody
    public MallItemListResult getMallItemList(
            @RequestParam(value = "type", required = true) Integer type,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "startPrice", required = false) Float startPrice,
            @RequestParam(value = "endPrice", required = false) Float endPrice,
            @RequestParam(value = "cid", required = false) Integer cid,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "start", required = false) Integer start,
            @RequestParam(value = "count", required = false) Integer count) {

		if (null == start) {
			start = 0;
		}
		if (null == count || count < 0 || count > 20) {
			count = 20;
		}

		MallItemListResult result = new MallItemListResult();

        List<MallItem> itemList = mallServerService.getMallItemList(title, startPrice, endPrice, type, cid, null, status, start, count);
        result.setItemList(itemList);

        Integer allCount = mallServerService.getMallItemListCount(title, startPrice, endPrice, type, cid, status);
        result.setAllCount(allCount);

        return result;
    }

    @RequestMapping(value = IMall.API_PATH_MALL_ITEM_HOME_GET, method = RequestMethod.GET)
    @ResponseBody
    public MallHomeItemListResult getMallHomeItemList() {
        MallHomeItemListResult result = new MallHomeItemListResult();

		List<MallSet> todaySetList = mallServerService.getMallSetList(IMall.SetStatus.NORMAL, new Date(), null, null);
		result.setTodaySetList(todaySetList);

		List<MallSet> tomorrowSetList = mallServerService.getMallSetList(IMall.SetStatus.NORMAL, DateUtil.getDateNowTimeAsWant(1), null, null);
		result.setTomorrowSetList(tomorrowSetList);

		List<MallItem> choiceItemList = mallServerService.getMallItemList(null, null, null, null, null, IMall.Choice.CHOICE, IMall.ItemStatus.NORMAL, null, null);
		result.setChoiceItemList(choiceItemList);

		List<MallItem> newItemList = mallServerService.getTodayNewMallItemList(IMall.ItemStatus.NORMAL, null, null);
		result.setNewItemList(newItemList);

        return result;
    }


    /** Mall Comment **/

    @RequestMapping(value = IMall.API_PATH_MALL_ITEM_COMMENT_UPDATE, method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ResponseResult updateMallComment(@RequestBody MallComment mallComment) {

        if (null == mallComment.getCommentId()) {
            mallServerService.addMallComment(mallComment);
        } else {
            mallServerService.updateMallComment(mallComment);
        }

        return ResponseResult.retOK();
    }

    @RequestMapping(value = IMall.API_PATH_MALL_ITEM_COMMENT_LIST_GET, method = RequestMethod.GET)
    @ResponseBody
    public MallCommentListResult getMallCommentList(
            @RequestParam(value = "itemId", required = false) Long itemId,
            @RequestParam(value = "uid", required = false) Long uid,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "disabled", required = false) Integer disabled,
            @RequestParam(value = "start", required = false) Integer start,
            @RequestParam(value = "count", required = false) Integer count) {

		if (null == start) {
			start = 0;
		}
		if (null == count || count < 0 || count > 20) {
			count = 20;
		}

		MallCommentListResult result = new MallCommentListResult();

        List<MallComment> commentList = mallServerService.getMallCommentListByItemId(itemId, uid, status, disabled, start, count);
        result.setCommentList(commentList);

        Integer allCount = mallServerService.getMallCommentListCountByItemId(itemId, uid, status, disabled);
        result.setAllCount(allCount);

        return result;
    }


    /** Mall Reply **/

    @RequestMapping(value = IMall.API_PATH_MALL_ITEM_REPLY_UPDATE, method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ResponseResult updateMallReply(@RequestBody MallReply mallReply) {

        if (null == mallReply.getReplyId()) {
            mallServerService.addMallReply(mallReply);
        } else {
            mallServerService.updateMallReply(mallReply);
        }

        return ResponseResult.retOK();
    }

    @RequestMapping(value = IMall.API_PATH_MALL_ITEM_REPLY_LIST_GET, method = RequestMethod.GET)
    @ResponseBody
    public MallReplyListResult getMallReplyList(
            @RequestParam(value = "commentId", required = false) Long commentId,
            @RequestParam(value = "fromUid", required = false) Long fromUid,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "start", required = false) Integer start,
            @RequestParam(value = "count", required = false) Integer count) {

		if (null == start) {
			start = 0;
		}
		if (null == count || count < 0 || count > 20) {
			count = 20;
		}

		MallReplyListResult result = new MallReplyListResult();

        List<MallReply> replyList = mallServerService.getMallReplyListByCommentId(commentId, fromUid, status, start, count);
        result.setReplyList(replyList);

        Integer allCount = mallServerService.getMallReplyListCountByCommentId(commentId, fromUid, status);
        result.setAllCount(allCount);

        return result;
    }


    /** Mall Cart **/

    @RequestMapping(value = IMall.API_PATH_MALL_CART_UPDATE, method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ResponseResult updateMallCart(@RequestBody MallCart mallCart) {

        if (null == mallCart.getCartId()) {
            mallServerService.addMallCart(mallCart);
        } else {
            mallServerService.updateMallCart(mallCart);
        }

        return ResponseResult.retOK();
    }

    @RequestMapping(value = IMall.API_PATH_MALL_CART_DELETE, method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ResponseResult deleteMallCartByCartId(@RequestParam("cartId") Integer cartId) {

        mallServerService.deleteMallCartByCartId(cartId);

        return ResponseResult.retOK();
    }

    @RequestMapping(value = IMall.API_PATH_MALL_CART_LIST_GET, method = RequestMethod.GET)
    @ResponseBody
    public MallCartListResult getMallCartListByUid(
            @RequestParam(value = "uid", required = true) Long uid,
            @RequestParam(value = "start", required = false) Integer start,
            @RequestParam(value = "count", required = false) Integer count) {

		if (null == start) {
			start = 0;
		}
		if (null == count || count < 0 || count > 20) {
			count = 20;
		}

        MallCartListResult result = new MallCartListResult();

        List<MallCart> cartList = mallServerService.getMallCartListByUid(uid, start, count);
        result.setCartList(cartList);

        Integer allCount = mallServerService.getMallCartListCountByUid(uid);
        result.setAllCount(allCount);

        return result;
    }


    /** Mall Order **/

    @RequestMapping(value = IMall.API_PATH_MALL_ORDER_UPDATE, method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public LongResult updateMallOrder(@RequestBody MallOrder mallOrder) {

        LongResult result = new LongResult();

		Long oid = null;
        if (null == mallOrder.getOid()) {
			oid = mallServerService.addMallOrder(mallOrder);
        } else {
			oid = mallOrder.getOid();
            mallServerService.updateMallOrder(mallOrder);
        }
		result.setValue(oid);

        return result;
    }

    @RequestMapping(value = IMall.API_PATH_MALL_ORDER_LIST_GET, method = RequestMethod.GET)
    @ResponseBody
    public MallOrderListResult getMallOrderListByUid(
            @RequestParam(value = "uid", required = true) Long uid,
            @RequestParam(value = "type", required = true) Integer type,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "start", required = false) Integer start,
            @RequestParam(value = "count", required = false) Integer count) {

		if (null == start) {
			start = 0;
		}
		if (null == count || count < 0 || count > 20) {
			count = 20;
		}

		MallOrderListResult result = new MallOrderListResult();

        List<MallOrder> orderList = mallServerService.getMallOrderListByUid(uid, type, status, start, count);
        result.setOrderList(orderList);

        Integer allCount = mallServerService.getMallOrderListCountByUid(uid, type, status);
        result.setAllCount(allCount);

        return result;
    }


    /** Mall Pay **/

    @RequestMapping(value = IMall.API_PATH_MALL_PAY_MALL_ORDER, method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ResponseResult payForMallOrder(
			@RequestParam(value = "oid", required = true) Long oid,
			@RequestParam(value = "password", required = true) String password) {

        mallServerService.payForMallOrder(getUid(), password, oid);

        return ResponseResult.retOK();
    }


	/** Mall Return Goods **/

	@RequestMapping(value = IMall.API_PATH_MALL_RETURN_GOODS_UPDATE, method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public ResponseResult updateMallReturnGoods(@RequestBody MallReturnGoods mallReturnGoods) {

		if (null == mallReturnGoods.getId()) {
			mallServerService.addMallReturnGoods(mallReturnGoods);
		} else {
			mallServerService.updateMallReturnGoods(mallReturnGoods);
		}

		return ResponseResult.retOK();
	}

	@RequestMapping(value = IMall.API_PATH_MALL_RETURN_GOODS_LIST_GET, method = RequestMethod.GET)
	@ResponseBody
	public MallReturnGoodsListResult getMallReturnGoodsListByUid(
			@RequestParam(value = "uid", required = true) Long uid,
			@RequestParam(value = "status", required = true) Integer status,
			@RequestParam(value = "start", required = false) Integer start,
			@RequestParam(value = "count", required = false) Integer count) {

		if (null == start) {
			start = 0;
		}
		if (null == count || count < 0 || count > 20) {
			count = 20;
		}

		MallReturnGoodsListResult result = new MallReturnGoodsListResult();

		List<MallReturnGoods> returnGoodsList = mallServerService.getMallReturnGoodsListByUid(uid, status, start, count);
		result.setReturnGoodsList(returnGoodsList);

		Integer allCount = mallServerService.getMallReturnGoodsListCountByUid(uid, status);
		result.setAllCount(allCount);

		return result;
	}


	/** Mall Goods **/

	@RequestMapping(value = IMall.API_PATH_MALL_GOODS_ADD, method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public ResponseResult addMallGoods(@RequestBody List<MallGoods> mallGoodsList) {

		mallServerService.addMallGoodsList(mallGoodsList);

		return ResponseResult.retOK();
	}

	@RequestMapping(value = IMall.API_PATH_MALL_GOODS_UPDATE, method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public ResponseResult updateMallGoods(@RequestBody List<MallGoods> mallGoodsList) {

		mallServerService.updateMallGoodsList(mallGoodsList);

		return ResponseResult.retOK();
	}


	/** Mall Set **/

	@RequestMapping(value = IMall.API_PATH_MALL_SET_UPDATE, method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public ResponseResult addMallSet(@RequestBody MallSet mallSet) {

		if (null == mallSet.getSid()) {
			mallServerService.addMallSet(mallSet);
		} else {
			mallServerService.updateMallSet(mallSet);
		}

		return ResponseResult.retOK();
	}

	@RequestMapping(value = IMall.API_PATH_MALL_SET_LIST_GET, method = RequestMethod.GET)
	@ResponseBody
	public MallSetListResult getMallSetList(
			@RequestParam(value = "status", required = false) Integer status,
			@RequestParam(value = "start", required = false) Integer start,
			@RequestParam(value = "count", required = false) Integer count) {

		if (null == start) {
			start = 0;
		}
		if (null == count || count < 0 || count > 20) {
			count = 20;
		}

		MallSetListResult result = new MallSetListResult();

		List<MallSet> setList = mallServerService.getMallSetList(status, null, start, count);
		result.setSetList(setList);

		Integer allCount = mallServerService.getMallSetListCount(status);
		result.setAllCount(allCount);

		return result;
	}

}
