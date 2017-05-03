package com.unuse.mall.api;

import java.io.Serializable;

/**
 * Created by unuse on 17/2/15.
 */

public class MallSkuInfo implements Serializable {

    private String name;

    private String detail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
