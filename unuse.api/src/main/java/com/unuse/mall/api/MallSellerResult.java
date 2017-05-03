package com.unuse.mall.api;

import com.unuse.common.ResponseResult;

/**
 * Created by unuse on 17/2/6.
 */
public class MallSellerResult extends ResponseResult {

    private MallSeller mallSeller;

    public MallSeller getMallSeller() {
        return mallSeller;
    }

    public void setMallSeller(MallSeller mallSeller) {
        this.mallSeller = mallSeller;
    }
}
