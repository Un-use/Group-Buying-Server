package com.unuse.auth.api;

import com.unuse.common.ResponseResult;

import java.util.List;

/**
 * Created by Unuse on 2016/10/29.
 */

public class MenuItemListResult extends ResponseResult {

    private List<MenuItem> menuItemList;

    public List<MenuItem> getMenuItemList() {
        return menuItemList;
    }

    public void setMenuItemList(List<MenuItem> menuItemList) {
        this.menuItemList = menuItemList;
    }
}
