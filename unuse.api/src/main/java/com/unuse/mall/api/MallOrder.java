package com.unuse.mall.api;

import com.unuse.user.api.UserData;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by unuse on 17/2/17.
 */

public class MallOrder implements Serializable {

    /** DB data **/

    private Long oid;

    private Long uid;

    private String gidListJson;

    private Integer type; // 订单类型

    private Integer status;

    private Date createTime;

    private Date updateTime;


    /** generate data **/

    private List<MallGoods> mallGoodsList;

    private UserData userData;

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getGidListJson() {
        return gidListJson;
    }

    public void setGidListJson(String gidListJson) {
        this.gidListJson = gidListJson;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public List<MallGoods> getMallGoodsList() {
        return mallGoodsList;
    }

    public void setMallGoodsList(List<MallGoods> mallGoodsList) {
        this.mallGoodsList = mallGoodsList;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }
}
