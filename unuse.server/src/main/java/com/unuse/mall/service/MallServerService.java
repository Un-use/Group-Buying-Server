package com.unuse.mall.service;

import com.alibaba.fastjson.JSON;
import com.unuse.common.ResponseException;
import com.unuse.common.ResponseResult;
import com.unuse.config.service.ConfigServerService;
import com.unuse.mall.api.*;
import com.unuse.mall.mapper.MallMapper;
import com.unuse.user.api.User;
import com.unuse.user.service.UserServerService;
import com.unuse.util.DateUtil;
import com.unuse.util.MD5Encryption;
import com.unuse.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Unuse on 2016/10/29.
 */

@Service
public class MallServerService {

    @Autowired
    private MallMapper mallMapper;

    @Autowired
    private ConfigServerService configServerService;

    @Autowired
    private UserServerService userServerService;


    /** Mall Seller **/

    public void addMallSeller(MallSeller mallSeller) {
        mallMapper.addMallSeller(mallSeller);
    }

    public void updateMallSeller(MallSeller mallSeller) {
        mallMapper.updateMallSeller(mallSeller);
    }

    public MallSeller getMallSellerByUid(Long uid) {
        return mallMapper.getMallSellerByUid(uid);
    }


    /** Mall Category **/

    public void addMallCategory(MallCategory category) {
        mallMapper.addMallCategory(category);

		configServerService.setCidStringMapCache(null);
    }

    public void updateMallCategory(MallCategory category) {
        mallMapper.updateMallCategory(category);

		configServerService.setCidStringMapCache(null);
    }

    public MallCategory getMallCategoryByCid(Integer cid) {
        return mallMapper.getMallCategoryByCid(cid);
    }

    public List<MallCategory> getMallCategoryList(Integer level, Integer status) {
        return mallMapper.getMallCategoryList(level, status);
    }


    /** Mall Normal Item **/

    public void addMallItem(MallItem mallItem) {

    	if (null == mallItem.getMainPictureListJson()) {
			mallItem.setMainPictureListJson(makeListJson(mallItem.getMainPictureList()));
		}

		if (null == mallItem.getDetailPictureListJson()) {
			mallItem.setDetailPictureListJson(makeListJson(mallItem.getDetailPictureList()));
		}

		if (null == mallItem.getSkuListJson()) {
			mallItem.setSkuListJson(makeListJson(mallItem.getSkuList()));
		}

		mallItem.setSecret(StringUtil.createSecretKey6());

        if (null != mallItem.getType() && mallItem.getType() == IMall.Type.GROUP) {
			mallItem.setJoinNumber(0);
        }

        mallMapper.addMallItem(mallItem);
    }

	private String makeListJson(Object object) {

		if (null == object) {
			return null;
		}

		return JSON.toJSONString(object);
	}

    public void updateMallItem(MallItem mallItem) {

    	if (null == mallItem.getMainPictureListJson()) {
			mallItem.setMainPictureListJson(makeListJson(mallItem.getMainPictureList()));
		}

		if (null == mallItem.getDetailPictureListJson()) {
			mallItem.setDetailPictureListJson(makeListJson(mallItem.getDetailPictureList()));
		}

		if (null == mallItem.getSkuListJson()) {
			mallItem.setSkuListJson(makeListJson(mallItem.getSkuList()));
		}

        mallMapper.updateMallItem(mallItem);

		configServerService.setMallItemCache(mallItem.getItemId(), null);
    }

    public MallItem getMallItemByItemId(Long itemId) {

		MallItem mallItem = configServerService.getMallItemCache(itemId);

        if (null == mallItem) {
			mallItem = mallMapper.getMallItemByItemId(itemId);
			if (null != mallItem) {
				String preUrl = StringUtil.makePicturePreUrl(configServerService.getItemURL(), mallItem.getSecret(), itemId.toString(), null);

				mallItem.setMainPictureList(makePictureList(mallItem.getMainPictureListJson(), preUrl));

				mallItem.setDetailPictureList(makePictureList(mallItem.getDetailPictureListJson(), preUrl));

				mallItem.setSkuList(makeMallItemSkuList(mallItem.getSkuListJson()));

				configServerService.setMallItemCache(mallItem.getItemId(), mallItem);
			}
        }

        return mallItem;
    }

	public List<MallItem> getMallItemList(String title, Float startPrice, Float endPrice, Integer type, Integer cid, Integer choice, Integer status, Integer start, Integer count) {

		String cidString = getCategoryCidStringByCid(cid);

		List<Long> itemIdList = mallMapper.getMallItemItemIdList(title, startPrice, endPrice, type, cidString, choice, status, start, count);

		return getMallItemListByItemId(itemIdList);
	}

	public Integer getMallItemListCount(String title, Float startPrice, Float endPrice, Integer type, Integer cid, Integer status) {

		String cidString = getCategoryCidStringByCid(cid);

		return mallMapper.getMallItemListCount(title, startPrice, endPrice, type, cidString, status);
	}

	private String getCategoryCidStringByCid(Integer cid) {

		if (null == cid) {
			return null;
		}

		Map<Integer, String> cidStringMap = configServerService.getCidStringMapCache();

		if (null == cidStringMap) {
			cidStringMap = new HashMap<Integer, String>();

			List<MallCategory> categoryList = getMallCategoryList(null, IMall.CategoryStatus.NORMAL);

			String cidString = "";
			for (MallCategory mallCategory : categoryList) {
				if (null == mallCategory) {
					continue;
				}

				if (IMall.CategoryLevel.FIRST == mallCategory.getLevel()) {
					cidStringMap.put(mallCategory.getCid(), mallCategory.getCid().toString());
				} else {
					cidString = cidStringMap.get(mallCategory.getParentCid());
					cidString += "," + mallCategory.getCid();
					cidStringMap.put(mallCategory.getParentCid(), cidString);
					cidStringMap.put(mallCategory.getCid(), mallCategory.getCid().toString());
				}

			}

			configServerService.setCidStringMapCache(cidStringMap);
		}

		return cidStringMap.get(cid);
	}

	public List<MallItem> getTodayNewMallItemList(Integer status, Integer start, Integer count) {

		List<Long> itemIdList = mallMapper.getTodayNewMallItemIdList(DateUtil.getCurrentDayStartTime(), DateUtil.getCurrentDayEndTime(), status, start, count);

		return getMallItemListByItemId(itemIdList);
	}

	private List<MallItem> getMallItemListByItemId(List<Long> itemIdList) {

		List<MallItem> mallItemList = null;

		if (null != itemIdList && !itemIdList.isEmpty()) {
			mallItemList = new ArrayList<MallItem>();

			for (Long itemId : itemIdList) {
				if (null != itemId) {
					mallItemList.add(getMallItemByItemId(itemId));
				}

			}

		}

		return mallItemList;
	}

    private List<MallSkuInfo> makeMallItemSkuList(String skuListJson) {

        List<MallSkuInfo> result = null;

        if (StringUtil.isNotBlank(skuListJson)) {
            result = JSON.parseArray(skuListJson, MallSkuInfo.class);
        }

        return result;
    }


    /** Mall Comment **/

    public void addMallComment(MallComment mallComment) {

    	if (null == mallComment.getPictureListJson()) {
			mallComment.setPictureListJson(makeListJson(mallComment.getPictureList()));
		}

        mallMapper.addMallComment(mallComment);
    }

    public void updateMallComment(MallComment mallComment) {

		if (null == mallComment.getPictureListJson()) {
			mallComment.setPictureListJson(makeListJson(mallComment.getPictureList()));
		}

        mallMapper.updateMallComment(mallComment);
    }

    public List<MallComment> getMallCommentListByItemId(Long itemId, Integer status, Integer disabled, Integer start, Integer count) {

        List<MallComment> commentList = mallMapper.getMalCommentListByItemId(itemId, status, disabled, start, count);
        if (null != commentList && !commentList.isEmpty()) {
            MallItem normalItem = null;

            for (MallComment mallComment : commentList) {
                if (null != mallComment) {
                    if (null == normalItem) {
                        normalItem = mallMapper.getMallItemByItemId(itemId);
                    }

                    mallComment.setUserData(userServerService.getUserDataByUid(mallComment.getUid()));

                    String preUrl = StringUtil.makePicturePreUrl(configServerService.getItemURL(), normalItem.getSecret(), normalItem.getItemId().toString(), "comment");
                    mallComment.setPictureList(makePictureList(mallComment.getPictureListJson(), preUrl));

                    mallComment.setReplyList(getMallReplyListByCommentId(mallComment.getCommentId(), IMall.CommentStatus.PASS, null, null));
                }

            }

        }
        return commentList;
    }

    public Integer getMallCommentListCountByItemId(Long itemId, Integer status, Integer disabled) {
        return mallMapper.getMallCommentListCountByItemId(itemId, status, disabled);
    }

    public MallComment getMallCommentByCommentId(Long commentId) {

        MallComment mallComment = mallMapper.getMalCommentByCommentId(commentId);

        if (null != mallComment) {
            MallItem normalItem = mallMapper.getMallItemByItemId(mallComment.getItemId());

            String preUrl = StringUtil.makePicturePreUrl(configServerService.getItemURL(), normalItem.getSecret(), normalItem.getItemId().toString(), "comment");
            mallComment.setPictureList(makePictureList(mallComment.getPictureListJson(), preUrl));

            mallComment.setUserData(userServerService.getUserDataByUid(mallComment.getUid()));
        }

        return mallComment;
    }

    private List<String> makePictureList(String pictureListJson, String preUrl) {

        List<String> result = null;

        if (StringUtil.isNotBlank(pictureListJson)) {
            result = new ArrayList<String>();

            List<String> pictureList = JSON.parseArray(pictureListJson, String.class);
            for (String picture : pictureList) {
                result.add(preUrl + picture);
            }

        }

        return result;
    }


    /** Mall Reply **/

    public void addMallReply(MallReply mallReply) {

    	if (null == mallReply.getPictureListJson()) {
			mallReply.setPictureListJson(makeListJson(mallReply.getPictureList()));
		}

        mallMapper.addMallReply(mallReply);
    }

    public void updateMallReply(MallReply mallReply) {

		if (null == mallReply.getPictureListJson()) {
        	mallReply.setPictureListJson(makeListJson(mallReply.getPictureList()));
		}

        mallMapper.updateMallReply(mallReply);
    }

    public List<MallReply> getMallReplyListByCommentId(Long commentId, Integer status, Integer start, Integer count) {

        List<MallReply> replyList = mallMapper.getMallReplyListByCommentId(commentId, status, start, count);

        if (null != replyList && !replyList.isEmpty()) {
            MallItem mallItem = null;

            for (MallReply mallReply : replyList) {

                if (null != mallReply) {

                    if (null == mallItem) {
						mallItem = getMallItemByItemId(mallReply.getItemId());
                    }

                    mallReply.setToUserData(userServerService.getUserDataByUid(mallReply.getToUid()));

                    mallReply.setFromUserData(userServerService.getUserDataByUid(mallReply.getFromUid()));

                    String preUrl = StringUtil.makePicturePreUrl(configServerService.getItemURL(), mallItem.getSecret(), mallItem.getItemId().toString(), "reply");
                    mallReply.setPictureList(makePictureList(mallReply.getPictureListJson(), preUrl));
                }

            }

        }

        return replyList;
    }

    public Integer getMallReplyListCountByCommentId(Long commentId, Integer status) {
        return mallMapper.getMallReplyListCountByCommentId(commentId, status);
    }


    /** Mall Cart **/

    public void addMallCart(MallCart mallCart) {

		if (null == mallCart.getSkuListJson()) {
			mallCart.setSkuListJson(makeListJson(mallCart.getSkuList()));
		}

        mallMapper.addMallCart(mallCart);
    }

    public void updateMallCart(MallCart mallCart) {

    	if (null == mallCart.getSkuListJson()) {
			mallCart.setSkuListJson(makeListJson(mallCart.getSkuList()));
		}

        mallMapper.updateMallCart(mallCart);
    }

    public void deleteMallCartByCartId(Integer cartId) {
        mallMapper.deleteMallCartByCartId(cartId);
    }

    public List<MallCart> getMallCartListByUid(Long uid, Integer start, Integer count) {

        List<MallCart> cartList = mallMapper.getMallCartListByUid(uid, start, count);
        if (null != cartList && !cartList.isEmpty()) {

            for (MallCart mallCart : cartList) {

                if (null != mallCart) {
                    mallCart.setSkuList(makeMallItemSkuList(mallCart.getSkuListJson()));

                    mallCart.setMallItem(getMallItemByItemId(mallCart.getItemId()));
                }

            }

        }

        return cartList;
    }

    public Integer getMallCartListCountByUid(Long uid) {
        return mallMapper.getMallCartListCountByUid(uid);
    }


    /** Mall Order **/

    public Long addMallOrder(MallOrder mallOrder) {

        mallMapper.addMallOrder(mallOrder);

		return mallOrder.getOid();
    }

    public void updateMallOrder(MallOrder mallOrder) {
        mallMapper.updateMallOrder(mallOrder);
    }

	private MallOrder getMallOrderByOid(Long oid) {

		MallOrder mallOrder = mallMapper.getMallOrderByOid(oid);

		if (null != mallOrder) {
			mallOrder.setMallGoodsList(makeMallGoodsListByGidListJson(mallOrder.getGidListJson()));
		}

		return mallOrder;
	}

	private List<MallGoods> makeMallGoodsListByGidListJson(String gidListJson) {

		List<MallGoods> result = null;

		if (StringUtil.isNotBlank(gidListJson)) {

			List<Long> gidList = JSON.parseArray(gidListJson, Long.class);

			if (null != gidList && !gidList.isEmpty()) {
				result = new ArrayList<MallGoods>();

				for (Long gid : gidList) {
					result.add(getMallGoodsByGid(gid));
				}

			}

		}

		return result;
	}

    public List<MallOrder> getMallOrderListByUid(Long uid, Integer type, Integer status, Integer start, Integer count) {

        List<MallOrder> orderList = mallMapper.getMallOrderListByUid(uid, type, status, start, count);
        if (null != orderList && !orderList.isEmpty()) {

            for (MallOrder mallOrder : orderList) {

                if (null != mallOrder) {
					mallOrder.setMallGoodsList(makeMallGoodsListByGidListJson(mallOrder.getGidListJson()));
					mallOrder.setUserData(userServerService.getUserDataByUid(mallOrder.getUid()));
                }

            }

        }

        return orderList;
    }

    public Integer getMallOrderListCountByUid(Long uid, Integer type, Integer status) {
        return mallMapper.getMallOrderListCountByUid(uid, type, status);
    }

    public void payForMallOrder(Long uid, String password, Long oid) {

        MallOrder mallOrder = getMallOrderByOid(oid);

        User user = userServerService.getUserByUid(uid);

		password = MD5Encryption.toEncryption(password);
		if (!password.equals(user.getPassword())) {
			throw new ResponseException(ResponseResult.RES_UNSUPPORT, "支付密码错误!");
		}

        Float funds = user.getFunds();

		Float allPrice = 0f;
		List<MallGoods> mallGoodsList = mallOrder.getMallGoodsList();
		if (null == mallGoodsList || mallGoodsList.isEmpty()) {
			throw new ResponseException(ResponseResult.RES_NOT_ALLOW, "请选择商品!");
		}

		for (MallGoods mallGoods : mallGoodsList) {
			allPrice += mallGoods.getPrice();

			MallItem mallItem = getMallItemByItemId(mallGoods.getItemId());
			if (null != mallItem) {
				if (IMall.ItemStatus.DELETE == mallItem.getStatus()) {
					throw  new ResponseException(ResponseResult.RES_NOT_ALLOW, "该商品已删除!");
				}
				if (mallItem.getStock() - mallGoods.getNumber() < 0) {
					throw new ResponseException(ResponseResult.RES_NOT_ALLOW, "商品库存不足!");
				}

			}

		}

        if (funds < allPrice) {
            throw new ResponseException(ResponseResult.RES_NOT_ALLOW, "账户余额不足!");
        }
        user.setFunds(funds - allPrice);

		userServerService.updateUser(user);

        // 修改订单状态
		if (IMall.Type.NORMAL == mallOrder.getType()) {
			mallOrder.setStatus(IMall.NormalOrderStatus.WAIT_DELIVERY);
		} else if (IMall.Type.GROUP == mallOrder.getType()) {
			mallOrder.setStatus(IMall.GroupOrderStatus.JOIN);
		}
        mallMapper.updateMallOrder(mallOrder);

		for (MallGoods mallGoods : mallGoodsList) {
			mallGoods.setStatus(IMall.GoodsStatus.FINISHED);
			mallMapper.updateMallGoods(mallGoods);

			MallItem mallItem = getMallItemByItemId(mallGoods.getItemId());
			if (null != mallItem) {
				Integer status = null;
				if (mallItem.getStock() - mallGoods.getNumber() == 0) {
					status = IMall.ItemStatus.DELETE;
				}
				mallMapper.updateMallItemStock(mallGoods.getItemId(), mallGoods.getNumber(), status);
			}
		}

    }


	/** Mall Return Goods **/

	public void addMallReturnGoods(MallReturnGoods mallReturnGoods) {

		if (null == mallReturnGoods.getPictureListJson()) {
			mallReturnGoods.setPictureListJson(makeListJson(mallReturnGoods.getPictureList()));
		}

		mallMapper.addMallReturnGoods(mallReturnGoods);
	}

	public void updateMallReturnGoods(MallReturnGoods mallReturnGoods) {

		if (null == mallReturnGoods.getPictureListJson()) {
			mallReturnGoods.setPictureListJson(makeListJson(mallReturnGoods.getPictureList()));
		}

		mallMapper.updateMallReturnGoods(mallReturnGoods);
	}

	public List<MallReturnGoods> getMallReturnGoodsListByUid(Long uid, Integer status, Integer start, Integer count) {

		List<MallReturnGoods> returnGoodsList = mallMapper.getMallReturnGoodsListByUid(uid, status, start, count);
		if (null != returnGoodsList && !returnGoodsList.isEmpty()) {

			for (MallReturnGoods mallReturnGoods : returnGoodsList) {

				if (null != mallReturnGoods) {
					mallReturnGoods.setMallOrder(getMallOrderByOid(mallReturnGoods.getOid()));

					MallItem mallItem = getMallItemByItemId(mallReturnGoods.getItemId());
					if (null != mallItem) {
						String preUrl = StringUtil.makePicturePreUrl(configServerService.getItemURL(), mallItem.getSecret(), mallItem.getItemId().toString(), "returnGoods");
						mallReturnGoods.setPictureList(makePictureList(mallReturnGoods.getPictureListJson(), preUrl));
					}

				}

			}

		}

		return returnGoodsList;
	}

	public Integer getMallReturnGoodsListCountByUid(Long uid, Integer status) {
		return mallMapper.getMallReturnGoodsListCountByUid(uid, status);
	}


	/** Mall Goods **/

	public void addMallGoodsList(List<MallGoods> mallGoodsList) {

		if (null == mallGoodsList || mallGoodsList.isEmpty()) {
			return;
		}

		Long oid = null;
		List<Long> gidList = new ArrayList<Long>();
		for (MallGoods mallGoods : mallGoodsList) {
			if (null != mallGoods) {
				oid = mallGoods.getOid();
				mallGoods.setSkuListJson(makeListJson(mallGoods.getSkuList()));
				mallMapper.addMallGoods(mallGoods);
				gidList.add(mallGoods.getGid());
			}
		}

		if (null != oid) {
			MallOrder mallOrder = new MallOrder();
			mallOrder.setOid(oid);
			mallOrder.setGidListJson(JSON.toJSONString(gidList));
			updateMallOrder(mallOrder);
		}
	}

	public void updateMallGoodsList(List<MallGoods> mallGoodsList) {

		if (null == mallGoodsList || mallGoodsList.isEmpty()) {
			return;
		}

		for (MallGoods mallGoods : mallGoodsList) {
			if (null != mallGoods) {
				mallGoods.setSkuListJson(makeListJson(mallGoods.getSkuList()));
				mallMapper.updateMallGoods(mallGoods);
			}
		}

	}

	public MallGoods getMallGoodsByGid(Long gid) {

		MallGoods mallGoods = mallMapper.getMallGoodsByGid(gid);

		if (null != mallGoods) {
			mallGoods.setSkuList(makeMallItemSkuList(mallGoods.getSkuListJson()));

			MallItem mallItem = getMallItemByItemId(mallGoods.getItemId());
			mallGoods.setMallItem(mallItem);

			if (null != mallItem) {
				mallGoods.setPrice(mallItem.getPrice() * mallGoods.getNumber());
			}

            mallGoods.setUserAddress(userServerService.getUserAddressById(mallGoods.getUserAddressId()));
		}

		return mallGoods;
	}


	/** Mall Set **/

	public void addMallSet(MallSet mallSet) {
		mallMapper.addMallSet(mallSet);
	}

	public void updateMallSet(MallSet mallSet) {
		mallMapper.updateMallSet(mallSet);
	}

	public MallSet getMallSetBySid(Integer sid) {

		MallSet mallSet = mallMapper.getMallSetBySid(sid);
		fillMallSet(mallSet);

		return mallSet;
	}

	public List<MallSet> getMallSetList(Integer status, Date nowTime, Integer start, Integer count) {

		List<MallSet> setList = mallMapper.getMallSetList(status, nowTime, start, count);

		if (null != setList && !setList.isEmpty()) {
			for (MallSet mallSet : setList) {
				fillMallSet(mallSet);
			}

		}

		return setList;
	}

	private void fillMallSet(MallSet mallSet) {

		if (null != mallSet) {
			List<MallItem> itemList = new ArrayList<MallItem>();

			List<Long> itemIdList = JSON.parseArray(mallSet.getItemIdListJson(), Long.class);
			for (Long itemId : itemIdList) {
				itemList.add(getMallItemByItemId(itemId));
			}

			mallSet.setItemList(itemList);
		}

	}

	public Integer getMallSetListCount(Integer status) {
		return mallMapper.getMallSetListCount(status);
	}


}
