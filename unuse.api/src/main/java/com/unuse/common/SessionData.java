package com.unuse.common;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by unuse on 17/2/9.
 */

public class SessionData implements Serializable {

    private Long id;

    private Long uid;

    private String token;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
