package com.unuse.mall;

import com.alibaba.fastjson.JSON;
import com.unuse.common.BaseTest;
import com.unuse.common.ResponseResult;
import com.unuse.mall.api.*;
import com.unuse.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by unuse on 17/2/9.
 */
public class MallTest extends BaseTest{

    @Test
    public void addMallSeller() {

		startUnit();

        MallSeller seller = new MallSeller();
        seller.setUid(1L);
        seller.setName("Unuse");

        ResponseResult result = httpService.runService(seller, IMall.API_PATH_MALL_SELLER_ADD, ResponseResult.class);

        Assert.assertTrue(result.getResult() == ResponseResult.RES_OK);

		endUnit();
    }

    @Test
    public void updateMallSeller() {

		startUnit();

        MallSeller seller = new MallSeller();
        seller.setUid(1L);
        seller.setName("Unuse");
        seller.setCollectNumber(0);
        seller.setGoodsNumber(0);
        seller.setStar(0.0f);
        seller.setTotalSellAmount(0f);
        seller.setTotalSellNumber(0);

        ResponseResult result = httpService.runService(seller, IMall.API_PATH_MALL_SELLER_UPDATE, ResponseResult.class);

        Assert.assertTrue(result.getResult() == ResponseResult.RES_OK);

		endUnit();
    }

    @Test
    public void getMallSellerByUid() {

        startUnit();

        MallSellerResult result = httpService.runService("uid=1", IMall.API_PATH_MALL_SELLER_GET, MallSellerResult.class);

        Assert.assertTrue(result.getResult() == ResponseResult.RES_OK);

        endUnit();
    }

    @Test
    public void updateMallCategory() {

		startUnit();

        MallCategory category = new MallCategory();
        category.setValue(2);
        category.setLevel(1);
        category.setParentCid(0);
        category.setLabel("裤子");
        category.setStatus(0);

        ResponseResult result = httpService.runService(category, IMall.API_PATH_MALL_CATEGORY_UPDATE, ResponseResult.class);

        Assert.assertTrue(result.getResult() == ResponseResult.RES_OK);

		endUnit();
    }

    @Test
    public void getMallCategory() {

		startUnit();

        MallCategoryResult result = httpService.runService("cid=1", IMall.API_PATH_MALL_CATEGORY_GET, MallCategoryResult.class);

        Assert.assertTrue(result.getResult() == ResponseResult.RES_OK);

		endUnit();
    }

    @Test
    public void getMallCategoryListByLevel() {

		startUnit();

        MallCategoryListResult result = httpService.runService("", IMall.API_PATH_MALL_CATEGORY_LIST_GET, MallCategoryListResult.class);

        Assert.assertTrue(result.getResult() == ResponseResult.RES_OK);

		endUnit();
    }

    @Test
    public void updateMallNormalItemDetail() {

		startUnit();

        MallItem mallItem = new MallItem();
//        itemDetail.setItemId(2L);
		mallItem.setDescription("哈哈哈");
		mallItem.setStock(1000);
		mallItem.setCid(5);
		mallItem.setPrice(500.1f);
		mallItem.setTitle("呜呜呜");
		mallItem.setOrder(0);
		mallItem.setStatus(0);
		mallItem.setType(2);

        List<MallSkuInfo> skuInfos = new ArrayList<MallSkuInfo>();
        MallSkuInfo skuInfo = new MallSkuInfo();
        skuInfo.setName("颜色");
        skuInfo.setDetail("白色");
        skuInfos.add(skuInfo);

        skuInfo = new MallSkuInfo();
        skuInfo.setName("大小");
        skuInfo.setDetail("L");
        skuInfos.add(skuInfo);

		mallItem.setSkuList(skuInfos);

        ResponseResult result = httpService.runService(mallItem, IMall.API_PATH_MALL_ITEM_UPDATE, ResponseResult.class);

        Assert.assertTrue(result.getResult() == ResponseResult.RES_OK);

		endUnit();
    }

    @Test
    public void getMallNormalItemList() {

		startUnit();

        MallItemListResult result = httpService.runService("type=1", IMall.API_PATH_MALL_ITEM_LIST_GET, MallItemListResult.class);

        Assert.assertTrue(result.getResult() == ResponseResult.RES_OK);

		endUnit();
    }

    @Test
    public void updateMallComment() {

		startUnit();

        MallComment mallComment = new MallComment();
        mallComment.setCommentId(1L);
        mallComment.setContent("啊啊啊啊啊1");
        mallComment.setItemId(1L);
        mallComment.setUid(1L);
        mallComment.setStar(1);
        mallComment.setStatus(1);

        ResponseResult result = httpService.runService(mallComment, IMall.API_PATH_MALL_ITEM_COMMENT_UPDATE, ResponseResult.class);

        Assert.assertTrue(result.getResult() == ResponseResult.RES_OK);

		endUnit();
    }

    @Test
    public void getMallCommentList() {

		startUnit();

        MallCommentListResult result = httpService.runService("itemId=1", IMall.API_PATH_MALL_ITEM_COMMENT_LIST_GET, MallCommentListResult.class);

        Assert.assertTrue(result.getResult() == ResponseResult.RES_OK);

		endUnit();
    }

    @Test
    public void updateMallReply() {

		startUnit();

        MallReply reply = new MallReply();
        reply.setReplyId(1L);
        reply.setCommentId(1L);
        reply.setContent("1111");
        reply.setFromUid(1L);
        reply.setItemId(1L);
        reply.setToUid(1L);
        reply.setStatus(1);

        ResponseResult result = httpService.runService(reply, IMall.API_PATH_MALL_ITEM_REPLY_UPDATE, ResponseResult.class);

        Assert.assertTrue(result.getResult() == ResponseResult.RES_OK);

		endUnit();
    }

    @Test
    public void getMallReplyList() {

		startUnit();

        MallReplyListResult result = httpService.runService("commentId=1", IMall.API_PATH_MALL_ITEM_REPLY_LIST_GET, MallReplyListResult.class);

        Assert.assertTrue(result.getResult() == ResponseResult.RES_OK);

		endUnit();
    }

    @Test
    public void updateMallCart() {

		startUnit();

        MallCart mallCart = new MallCart();
        mallCart.setCartId(1);
        mallCart.setItemId(1L);
        mallCart.setNumber(2);
        mallCart.setUid(1L);

        List<MallSkuInfo> skuInfos = new ArrayList<MallSkuInfo>();
        MallSkuInfo skuInfo = new MallSkuInfo();
        skuInfo.setName("颜色");
        skuInfo.setDetail("红色");
        skuInfos.add(skuInfo);

        skuInfo = new MallSkuInfo();
        skuInfo.setName("大小");
        skuInfo.setDetail("L");
        skuInfos.add(skuInfo);

        mallCart.setSkuList(skuInfos);

        ResponseResult result = httpService.runService(mallCart, IMall.API_PATH_MALL_CART_UPDATE, ResponseResult.class);

        Assert.assertTrue(result.getResult() == ResponseResult.RES_OK);

		endUnit();
    }

    @Test
    public void getMallCartList() {

		startUnit();

        MallCartListResult result = httpService.runService("uid=1", IMall.API_PATH_MALL_CART_LIST_GET, MallCartListResult.class);

        Assert.assertTrue(result.getResult() == ResponseResult.RES_OK);

		endUnit();
    }

	@Test
	public void updateMallOrder() {

		startUnit();

		MallOrder mallOrder = new MallOrder();
		mallOrder.setOid(1L);
		mallOrder.setUid(1L);
		mallOrder.setStatus(2);

		List<MallSkuInfo> skuInfos = new ArrayList<MallSkuInfo>();
		MallSkuInfo skuInfo = new MallSkuInfo();
		skuInfo.setName("颜色");
		skuInfo.setDetail("红色");
		skuInfos.add(skuInfo);

		skuInfo = new MallSkuInfo();
		skuInfo.setName("大小1");
		skuInfo.setDetail("L");
		skuInfos.add(skuInfo);

		ResponseResult result = httpService.runService(mallOrder, IMall.API_PATH_MALL_ORDER_UPDATE, ResponseResult.class);

		Assert.assertTrue(result.getResult() == ResponseResult.RES_OK);

		endUnit();
	}

	@Test
	public void getMallOrderList() {

		startUnit();

		MallOrderListResult result = httpService.runService("uid=1&type=1&status=4", IMall.API_PATH_MALL_ORDER_LIST_GET, MallOrderListResult.class);

		Assert.assertTrue(result.getResult() == ResponseResult.RES_OK);
	}

	@Test
	public void updateMallReturnGoods() {

		startUnit();

		MallReturnGoods mallReturnGoods = new MallReturnGoods();
		mallReturnGoods.setId(1);
		mallReturnGoods.setOid(1L);
		mallReturnGoods.setUid(1L);
		mallReturnGoods.setReason(2);
		mallReturnGoods.setStatus(1);
		mallReturnGoods.setDes("aaaaa");

		ResponseResult result = httpService.runService(mallReturnGoods, IMall.API_PATH_MALL_RETURN_GOODS_UPDATE, ResponseResult.class);

		Assert.assertTrue(result.getResult() == ResponseResult.RES_OK);

		endUnit();
	}

	@Test
	public void getMallReturnGoodsListByUid() {

		startUnit();

		MallReturnGoodsListResult result = httpService.runService("uid=1&status=0", IMall.API_PATH_MALL_RETURN_GOODS_LIST_GET, MallReturnGoodsListResult.class);

		Assert.assertTrue(result.getResult() == ResponseResult.RES_OK);

		endUnit();
	}

	@Test
	public void updateMallGoods() {

		startUnit();

		MallGoods mallGoods = new MallGoods();
		mallGoods.setItemId(1L);
		mallGoods.setNumber(1);
		mallGoods.setOid(1L);
		mallGoods.setUid(1L);

		List<MallSkuInfo> skuInfos = new ArrayList<MallSkuInfo>();
		MallSkuInfo skuInfo = new MallSkuInfo();
		skuInfo.setName("颜色");
		skuInfo.setDetail("红色");
		skuInfos.add(skuInfo);

		skuInfo = new MallSkuInfo();
		skuInfo.setName("大小1");
		skuInfo.setDetail("L");
		skuInfos.add(skuInfo);

		mallGoods.setSkuList(skuInfos);

		List<MallGoods> mallGoodsList = new ArrayList<MallGoods>();
		mallGoodsList.add(mallGoods);

		mallGoods = new MallGoods();
		mallGoods.setItemId(1L);
		mallGoods.setNumber(1);
		mallGoods.setOid(1L);
		mallGoods.setUid(1L);
		mallGoods.setSkuList(skuInfos);

		mallGoodsList.add(mallGoods);

		ResponseResult result = httpService.runService(mallGoodsList, IMall.API_PATH_MALL_GOODS_ADD, ResponseResult.class);

		Assert.assertTrue(result.getResult() == ResponseResult.RES_OK);

		endUnit();
	}

	@Test
	public void updateMallSet() {

		startUnit();

		MallSet mallSet = new MallSet();
		mallSet.setSid(1);
		mallSet.setContent("哇哈哈哈1");
		mallSet.setStartTime(DateUtil.getYesterdayStartTime());
		mallSet.setEndTime(DateUtil.getYesterdayEndTime());
		mallSet.setTitle("aaaa1");

		List<Long> list = new ArrayList<Long>();
		list.add(1L);
		mallSet.setItemIdListJson(JSON.toJSONString(list));

		ResponseResult result = httpService.runService(mallSet, IMall.API_PATH_MALL_SET_UPDATE, ResponseResult.class);

		Assert.assertTrue(result.getResult() == ResponseResult.RES_OK);

		endUnit();
	}

	@Test
	public void getMallSetList() {

		startUnit();

		MallSetListResult result = httpService.runService("status=0", IMall.API_PATH_MALL_SET_LIST_GET, MallSetListResult.class);

		Assert.assertTrue(result.getResult() == ResponseResult.RES_OK);

		endUnit();
	}

	@Test
	public void getMallHomeItemList() {

		startUnit();

		MallHomeItemListResult result = httpService.runService("", IMall.API_PATH_MALL_ITEM_HOME_GET, MallHomeItemListResult.class);

		Assert.assertTrue(result.getResult() == ResponseResult.RES_OK);

		endUnit();
	}


}
