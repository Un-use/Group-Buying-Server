package com.unuse.mall.api;

import com.unuse.file.api.FileData;
import com.unuse.user.api.UserData;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by unuse on 17/2/16.
 */
public class MallReply implements Serializable {

    /** DB data **/

    private Long replyId;

    private Long commentId;

    private Long itemId;

    private Long toUid;

    private Long fromUid;

    private String content;

    private String pictureListJson;

    private Integer status;

    private Date createTime;

    private Date updateTime;


    /** generate data **/

    private List<FileData> fileDataList;

    private UserData toUserData;

    private UserData fromUserData;

    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

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

    public Long getToUid() {
        return toUid;
    }

    public void setToUid(Long toUid) {
        this.toUid = toUid;
    }

    public Long getFromUid() {
        return fromUid;
    }

    public void setFromUid(Long fromUid) {
        this.fromUid = fromUid;
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

    public UserData getToUserData() {
        return toUserData;
    }

    public void setToUserData(UserData toUserData) {
        this.toUserData = toUserData;
    }

    public UserData getFromUserData() {
        return fromUserData;
    }

    public void setFromUserData(UserData fromUserData) {
        this.fromUserData = fromUserData;
    }

    public List<FileData> getFileDataList() {
        return fileDataList;
    }

    public void setFileDataList(List<FileData> fileDataList) {
        this.fileDataList = fileDataList;
    }
}
