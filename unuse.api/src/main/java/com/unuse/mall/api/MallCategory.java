package com.unuse.mall.api;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by unuse on 17/2/9.
 */

public class MallCategory implements Serializable {

    /** DB data **/

    private Integer cid;

    private Integer parentCid;

    private String title;

    private Integer level;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getParentCid() {
        return parentCid;
    }

    public void setParentCid(Integer parentCid) {
        this.parentCid = parentCid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
}
