package com.unuse.mall.api;

import com.unuse.file.api.FileData;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by unuse on 17/2/24.
 */

public class MallReturnGoods implements Serializable {

	/** DB data **/

	private Integer id;

	private Long uid;

	private Long oid;

	private Long itemId;

	private Integer reason;

	private String des;

	private String pictureListJson;

	private Integer status;

	private Date createTime;

	private Date updateTime;


	/** generate data **/

	private List<FileData> fileDataList;

	private MallOrder mallOrder;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getReason() {
		return reason;
	}

	public void setReason(Integer reason) {
		this.reason = reason;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getPictureListJson() {
		return pictureListJson;
	}

	public void setPictureListJson(String pictureListJson) {
		this.pictureListJson = pictureListJson;
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

	public List<FileData> getFileDataList() {
		return fileDataList;
	}

	public void setFileDataList(List<FileData> fileDataList) {
		this.fileDataList = fileDataList;
	}

	public MallOrder getMallOrder() {
		return mallOrder;
	}

	public void setMallOrder(MallOrder mallOrder) {
		this.mallOrder = mallOrder;
	}
}
