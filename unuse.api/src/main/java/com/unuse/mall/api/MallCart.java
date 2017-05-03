package com.unuse.mall.api;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by unuse on 17/2/17.
 */

public class MallCart implements Serializable {

    /** DB data **/

    private Integer cartId;

    private Long uid;

    private Long itemId;

    private String skuListJson;

    private Integer number;

    private Date createTime;

    private Date updateTime;


    /** generate data **/

    private List<MallSkuInfo> skuList;

    private MallItem mallItem;

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getSkuListJson() {
        return skuListJson;
    }

    public void setSkuListJson(String skuListJson) {
        this.skuListJson = skuListJson;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<MallSkuInfo> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<MallSkuInfo> skuList) {
        this.skuList = skuList;
    }

    public MallItem getMallItem() {
        return mallItem;
    }

    public void setMallItem(MallItem mallItem) {
        this.mallItem = mallItem;
    }
}
