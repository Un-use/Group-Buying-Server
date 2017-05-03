package com.unuse.mall.api;

import com.unuse.common.ResponseResult;

import java.util.List;

/**
 * Created by unuse on 17/2/17.
 */
public class MallCartListResult extends ResponseResult {

    private List<MallCart> cartList;

    private Integer allCount;

    public List<MallCart> getCartList() {
        return cartList;
    }

    public void setCartList(List<MallCart> cartList) {
        this.cartList = cartList;
    }

    public Integer getAllCount() {
        return allCount;
    }

    public void setAllCount(Integer allCount) {
        this.allCount = allCount;
    }
}
