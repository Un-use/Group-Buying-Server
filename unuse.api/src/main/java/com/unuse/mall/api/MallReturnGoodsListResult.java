package com.unuse.mall.api;

import com.unuse.common.ResponseResult;

import java.util.List;

/**
 * Created by unuse on 17/2/24.
 */

public class MallReturnGoodsListResult extends ResponseResult {

	private List<MallReturnGoods> returnGoodsList;

	private Integer allCount;

	public List<MallReturnGoods> getReturnGoodsList() {
		return returnGoodsList;
	}

	public void setReturnGoodsList(List<MallReturnGoods> returnGoodsList) {
		this.returnGoodsList = returnGoodsList;
	}

	public Integer getAllCount() {
		return allCount;
	}

	public void setAllCount(Integer allCount) {
		this.allCount = allCount;
	}
}
