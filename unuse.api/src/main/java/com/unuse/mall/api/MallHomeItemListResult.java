package com.unuse.mall.api;

import com.unuse.common.ResponseResult;

import java.util.List;

/**
 * Created by unuse on 17/3/9.
 */

public class MallHomeItemListResult extends ResponseResult {

	private List<MallSet> todaySetList;

	private List<MallSet> tomorrowSetList;

	private List<MallItem> choiceItemList;

	private List<MallItem> newItemList;

	public List<MallSet> getTodaySetList() {
		return todaySetList;
	}

	public void setTodaySetList(List<MallSet> todaySetList) {
		this.todaySetList = todaySetList;
	}

	public List<MallSet> getTomorrowSetList() {
		return tomorrowSetList;
	}

	public void setTomorrowSetList(List<MallSet> tomorrowSetList) {
		this.tomorrowSetList = tomorrowSetList;
	}

	public List<MallItem> getChoiceItemList() {
		return choiceItemList;
	}

	public void setChoiceItemList(List<MallItem> choiceItemList) {
		this.choiceItemList = choiceItemList;
	}

	public List<MallItem> getNewItemList() {
		return newItemList;
	}

	public void setNewItemList(List<MallItem> newItemList) {
		this.newItemList = newItemList;
	}
}
