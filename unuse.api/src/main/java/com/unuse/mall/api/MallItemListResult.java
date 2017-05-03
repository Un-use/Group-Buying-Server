package com.unuse.mall.api;

import com.unuse.common.ResponseResult;

import java.util.List;

/**
 * Created by unuse on 17/2/15.
 */

public class MallItemListResult extends ResponseResult {

    private List<MallItem> itemList;

    private Integer allCount;

    public List<MallItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<MallItem> itemList) {
        this.itemList = itemList;
    }

    public Integer getAllCount() {
        return allCount;
    }

    public void setAllCount(Integer allCount) {
        this.allCount = allCount;
    }
}
