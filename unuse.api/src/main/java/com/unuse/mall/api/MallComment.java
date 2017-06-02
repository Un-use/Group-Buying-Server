package com.unuse.mall.api;

import com.unuse.file.api.FileData;
import com.unuse.user.api.UserData;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by unuse on 17/2/16.
 */

public class MallComment implements Serializable {

    /** DB data **/

    private Long commentId;

    private Long itemId;

    private Long uid;

    private String content;

    private String pictureListJson;

    private Integer star;

    private Integer status;

    private Integer disabled;

    private Date createTime;

    private Date updateTime;


    /** generate data **/

    private UserData userData;

    private List<FileData> fileDataList;

    private List<MallReply> replyList;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPictureListJson() {
        return pictureListJson;
    }

    public void setPictureListJson(String pictureListJson) {
        this.pictureListJson = pictureListJson;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
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

    public Integer getDisabled() {
        return disabled;
    }

    public void setDisabled(Integer disabled) {
        this.disabled = disabled;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public List<FileData> getFileDataList() {
        return fileDataList;
    }

    public void setFileDataList(List<FileData> fileDataList) {
        this.fileDataList = fileDataList;
    }

    public List<MallReply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<MallReply> replyList) {
        this.replyList = replyList;
    }
}
