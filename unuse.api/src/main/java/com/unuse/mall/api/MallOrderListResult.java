package com.unuse.mall.api;

import com.unuse.common.ResponseResult;

import java.util.List;

/**
 * Created by unuse on 17/2/21.
 */

public class MallOrderListResult extends ResponseResult {

    private List<MallOrder> orderList;

    private Integer allCount;

    public List<MallOrder> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<MallOrder> orderList) {
        this.orderList = orderList;
    }

    public Integer getAllCount() {
        return allCount;
    }

    public void setAllCount(Integer allCount) {
        this.allCount = allCount;
    }
}
