package com.unuse.mall.api;

import com.unuse.common.ResponseResult;

import java.util.List;

/**
 * Created by unuse on 17/2/9.
 */

public class MallCategoryListResult extends ResponseResult {

    private List<MallCategory> categoryList;

    public List<MallCategory> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<MallCategory> categoryList) {
        this.categoryList = categoryList;
    }
}
