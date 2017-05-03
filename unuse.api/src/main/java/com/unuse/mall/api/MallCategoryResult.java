package com.unuse.mall.api;

import com.unuse.common.ResponseResult;

/**
 * Created by unuse on 17/2/9.
 */

public class MallCategoryResult extends ResponseResult {

    private MallCategory mallCategory;

    public MallCategory getMallCategory() {
        return mallCategory;
    }

    public void setMallCategory(MallCategory mallCategory) {
        this.mallCategory = mallCategory;
    }
}
