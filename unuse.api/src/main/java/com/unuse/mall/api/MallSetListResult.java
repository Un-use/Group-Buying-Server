package com.unuse.mall.api;

import com.unuse.common.ResponseResult;

import java.util.List;

/**
 * Created by unuse on 17/3/9.
 */

public class MallSetListResult extends ResponseResult {

	private List<MallSet> setList;

	private Integer allCount;

	public List<MallSet> getSetList() {
		return setList;
	}

	public void setSetList(List<MallSet> setList) {
		this.setList = setList;
	}

	public Integer getAllCount() {
		return allCount;
	}

	public void setAllCount(Integer allCount) {
		this.allCount = allCount;
	}
}
