package com.unuse.mall.mapper;

import com.unuse.mall.api.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by unuse on 17/2/6.
 */

@Repository
public interface MallMapper {

    /** Mall Seller **/

    void addMallSeller(MallSeller mallSeller);

    void updateMallSeller(MallSeller mallSeller);

    MallSeller getMallSellerByUid(@Param("uid") Long uid);


    /** Mall Category **/

    void addMallCategory(MallCategory category);

    void updateMallCategory(MallCategory category);

    MallCategory getMallCategoryByCid(@Param("cid") Integer cid);

    List<MallCategory> getMallCategoryList(@Param("level") Integer level,
                                           @Param("status") Integer status);


    /** Mall Item **/

    void addMallItem(MallItem mallItem);

    void updateMallItem(MallItem mallItem);

    void updateMallItemStock(@Param("itemId") Long itemId,
                             @Param("number") Integer number,
                             @Param("status") Integer status);

    MallItem getMallItemByItemId(@Param("itemId") Long itemId);

    List<Long> getMallItemItemIdList(@Param("title") String title,
									 @Param("startPrice") Float startPrice,
									 @Param("endPrice") Float endPrice,
									 @Param("type") Integer type,
									 @Param("cidStr") String cidStr,
									 @Param("choice") Integer choice,
									 @Param("status") Integer status,
                                  	 @Param("start") Integer start,
                                  	 @Param("count") Integer count);

    Integer getMallItemListCount(@Param("title") String title,
                                 @Param("startPrice") Float startPrice,
                                 @Param("endPrice") Float endPrice,
                                 @Param("type") Integer type,
                                 @Param("cidStr") String cidStr,
                                 @Param("status") Integer status);


    /** Mall Comment **/

    void addMallComment(MallComment mallComment);

    void updateMallComment(MallComment mallComment);

    MallComment getMalCommentByCommentId(@Param("commentId") Long commentId);

    List<MallComment> getMalCommentListByItemId(@Param("itemId") Long itemId,
                                                @Param("uid") Long uid,
                                                @Param("status") Integer status,
                                                @Param("disabled") Integer disabled,
                                                @Param("start") Integer start,
                                                @Param("count") Integer count);

    Integer getMallCommentListCountByItemId(@Param("itemId") Long itemId,
                                            @Param("uid") Long uid,
                                            @Param("status") Integer status,
                                            @Param("disabled") Integer disabled);


    /** Mall Reply **/

    void addMallReply(MallReply mallReply);

    void updateMallReply(MallReply mallReply);

    MallReply getMallReplyByReplyId(@Param("replyId") Long replyId);

    List<MallReply> getMallReplyListByCommentId(@Param("commentId") Long commentId,
                                                @Param("fromUid") Long fromUid,
                                                @Param("status") Integer status,
                                                @Param("start") Integer start,
                                                @Param("count") Integer count);

    Integer getMallReplyListCountByCommentId(@Param("commentId") Long commentId,
                                             @Param("fromUid") Long fromUid,
                                             @Param("status") Integer status);


    /** Mall Cart **/

    void addMallCart(MallCart mallCart);

    void updateMallCart(MallCart mallCart);

    void deleteMallCartByCartId(@Param("cartId") Integer cartId);

    MallCart getMallCartByCartId(@Param("cartId") Integer cartId);

    List<MallCart> getMallCartListByUid(@Param("uid") Long uid,
                                        @Param("start") Integer start,
                                        @Param("count") Integer count);

    Integer getMallCartListCountByUid(@Param("uid") Long uid);


    /** Mall Order **/

    void addMallOrder(MallOrder mallOrder);

    void updateMallOrder(MallOrder mallOrder);

    MallOrder getMallOrderByOid(@Param("oid") Long oid);

    List<MallOrder> getMallOrderListByUid(@Param("uid") Long uid,
                                          @Param("type") Integer type,
                                          @Param("status") Integer status,
                                          @Param("start") Integer start,
                                          @Param("count") Integer count);

    Integer getMallOrderListCountByUid(@Param("uid") Long uid,
                                       @Param("type") Integer type,
                                       @Param("status") Integer status);


    /** Mall Return Goods **/

    void addMallReturnGoods(MallReturnGoods mallReturnGoods);

    void updateMallReturnGoods(MallReturnGoods mallReturnGoods);

    MallReturnGoods getMallReturnGoodsById(@Param("id") Integer id);

    List<MallReturnGoods> getMallReturnGoodsListByUid(@Param("uid") Long uid,
                                                      @Param("status") Integer status,
                                                      @Param("start") Integer start,
                                                      @Param("count") Integer count);

    Integer getMallReturnGoodsListCountByUid(@Param("uid") Long uid,
                                             @Param("status") Integer status);


    /** Mall Goods **/

    void addMallGoods(MallGoods mallGoods);

    void updateMallGoods(MallGoods mallGoods);

    List<MallReturnGoods> getMallGoodsListByOid(@Param("oid") Long oid,
                                                @Param("status") Integer status,
                                                @Param("start") Integer start,
                                                @Param("count") Integer count);

    Integer getMallGoodsListCountByOid(@Param("oid") Long oid,
                                       @Param("status") Integer status);

    MallGoods getMallGoodsByGid(@Param("gid") Long gid);


    /** Mall Set **/

    void addMallSet(MallSet mallSet);

    void updateMallSet(MallSet mallSet);

    List<MallSet> getMallSetList(@Param("status") Integer status,
                                 @Param("nowTime") Date nowTime,
                                 @Param("start") Integer start,
                                 @Param("count") Integer count);

    MallSet getMallSetBySid(@Param("gid") Integer gid);

    Integer getMallSetListCount(@Param("status") Integer status);

    List<Long> getTodayNewMallItemIdList(@Param("startTime") Date startTime,
                                         @Param("endTime") Date endTime,
                                         @Param("status") Integer status,
                                         @Param("start") Integer start,
                                         @Param("count") Integer count);
}
