package com.unuse.mall.api;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by unuse on 17/3/9.
 */

public class MallSet implements Serializable {

	/** DB data **/

	private Integer sid;

	private String title;

	private String content;

	private String itemIdListJson;

	private Integer status;

	private Date startTime;

	private Date endTime;

	private Date createTime;

	private Date updateTime;


	/** generate data **/

	private List<MallItem> itemList;

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getItemIdListJson() {
		return itemIdListJson;
	}

	public void setItemIdListJson(String itemIdListJson) {
		this.itemIdListJson = itemIdListJson;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

	public List<MallItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<MallItem> itemList) {
		this.itemList = itemList;
	}
}
