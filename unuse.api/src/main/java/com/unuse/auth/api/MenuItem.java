package com.unuse.auth.api;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Unuse on 2016/10/29.
 */

public class MenuItem implements Serializable {

    private Integer id;

    private Integer parentId;

    private String text;

    private String href;

    private List<MenuItem> menus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<MenuItem> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuItem> menus) {
        this.menus = menus;
    }
}
