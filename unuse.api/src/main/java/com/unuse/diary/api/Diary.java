package com.unuse.diary.api;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Unuse on 2017/5/3.
 */

public class Diary implements Serializable {

    private Integer id;

    private Long uid;

    private String title;

    private String content;

    private String picListJson;

    private Date createTime;

    private Date updateTime;

    private List<String> picList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicListJson() {
        return picListJson;
    }

    public void setPicListJson(String picListJson) {
        this.picListJson = picListJson;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<String> getPicList() {
        return picList;
    }

    public void setPicList(List<String> picList) {
        this.picList = picList;
    }
}
