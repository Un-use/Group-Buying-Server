package com.unuse.mall.api;

import com.unuse.file.api.FileData;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by unuse on 17/2/6.
 */

public class MallItem implements Serializable {

    /** DB data **/

    private Long itemId; // 商品ID

    private Integer joinNumber; // 参团人数

    private String title; // 商品名称

    private Float price; // 商品价格

	private Integer salesVolume; // 商品销量

    private Integer cid; // 商品标签ID

	private String mainPictureListJson; // JSON of 商品主图

	private String detailPictureListJson; // JSON of 商品详情图

    private Integer order; // 商品排序

    private Integer type; // 商品类型 1:普通商品 2:团购商品

	private Integer stock; // 商品库存

	private String skuListJson; // JSON of 商品规格

	private String description; // 商品描述

	private String usage; // 商品使用说明

	private Integer choice;

    private String secret;

    private Integer status; // 商品状态

    private Date createTime; // 创建时间

    private Date updateTime; // 更新时间


    /** generate data **/

	private List<FileData> mainFileDataList; // 商品图片

	private List<FileData> detailFileDataList; // 商品图片

	private List<MallSkuInfo> skuList; // 商品规格


	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Integer getJoinNumber() {
		return joinNumber;
	}

	public void setJoinNumber(Integer joinNumber) {
		this.joinNumber = joinNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(Integer salesVolume) {
		this.salesVolume = salesVolume;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getMainPictureListJson() {
		return mainPictureListJson;
	}

	public void setMainPictureListJson(String mainPictureListJson) {
		this.mainPictureListJson = mainPictureListJson;
	}

	public String getDetailPictureListJson() {
		return detailPictureListJson;
	}

	public void setDetailPictureListJson(String detailPictureListJson) {
		this.detailPictureListJson = detailPictureListJson;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getSkuListJson() {
		return skuListJson;
	}

	public void setSkuListJson(String skuListJson) {
		this.skuListJson = skuListJson;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public Integer getChoice() {
		return choice;
	}

	public void setChoice(Integer choice) {
		this.choice = choice;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
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

	public List<FileData> getMainFileDataList() {
		return mainFileDataList;
	}

	public void setMainFileDataList(List<FileData> mainFileDataList) {
		this.mainFileDataList = mainFileDataList;
	}

	public List<FileData> getDetailFileDataList() {
		return detailFileDataList;
	}

	public void setDetailFileDataList(List<FileData> detailFileDataList) {
		this.detailFileDataList = detailFileDataList;
	}

	public List<MallSkuInfo> getSkuList() {
		return skuList;
	}

	public void setSkuList(List<MallSkuInfo> skuList) {
		this.skuList = skuList;
	}
}
