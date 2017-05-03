package com.unuse.mall.api;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by unuse on 17/2/6.
 */

public class MallSeller implements Serializable {

    /** DB data **/

    private Long uid;

    private String name;

    private Integer collectNumber;

    private Float star;

    private Integer totalSellNumber;

    private Float totalSellAmount;

    private Integer goodsNumber;

    private Date createTime;

    private Date updateTime;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCollectNumber() {
        return collectNumber;
    }

    public void setCollectNumber(Integer collectNumber) {
        this.collectNumber = collectNumber;
    }

    public Float getStar() {
        return star;
    }

    public void setStar(Float star) {
        this.star = star;
    }

    public Integer getTotalSellNumber() {
        return totalSellNumber;
    }

    public void setTotalSellNumber(Integer totalSellNumber) {
        this.totalSellNumber = totalSellNumber;
    }

    public Float getTotalSellAmount() {
        return totalSellAmount;
    }

    public void setTotalSellAmount(Float totalSellAmount) {
        this.totalSellAmount = totalSellAmount;
    }

    public Integer getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(Integer goodsNumber) {
        this.goodsNumber = goodsNumber;
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
}
