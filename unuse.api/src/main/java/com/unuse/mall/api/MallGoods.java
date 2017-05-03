package com.unuse.mall.api;

import com.unuse.user.api.UserAddress;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by unuse on 17/3/7.
 */

public class MallGoods implements Serializable {

	/** DB data **/

	private Long gid;

	private Long uid;

	private Long oid;

	private Long itemId;

    private Integer userAddressId;

	private Integer number;

	private String skuListJson;

	private Integer status;

	private Date createTime;

	private Date updateTime;


	/** generate data **/

	private Float price;

	private List<MallSkuInfo> skuList;

	private MallItem mallItem;

    private UserAddress userAddress;

	public Long getGid() {
		return gid;
	}

	public void setGid(Long gid) {
		this.gid = gid;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

    public Integer getUserAddressId() {
        return userAddressId;
    }

    public void setUserAddressId(Integer userAddressId) {
        this.userAddressId = userAddressId;
    }

    public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getSkuListJson() {
		return skuListJson;
	}

	public void setSkuListJson(String skuListJson) {
		this.skuListJson = skuListJson;
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

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
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

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }
}
