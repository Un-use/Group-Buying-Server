package com.unuse.mall.api;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by unuse on 17/2/9.
 */

public class MallCategory implements Serializable {

    /** DB data **/

    private Integer value;

    private Integer parentCid;

    private String label;

    private Integer level;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private List<MallCategory> children;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getParentCid() {
        return parentCid;
    }

    public void setParentCid(Integer parentCid) {
        this.parentCid = parentCid;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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

    public List<MallCategory> getChildren() {
        return children;
    }

    public void setChildren(List<MallCategory> children) {
        this.children = children;
    }
}
