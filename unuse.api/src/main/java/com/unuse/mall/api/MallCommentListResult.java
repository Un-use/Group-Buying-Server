package com.unuse.mall.api;

import com.unuse.common.ResponseResult;

import java.util.List;

/**
 * Created by unuse on 17/2/16.
 */
public class MallCommentListResult extends ResponseResult {

    private List<MallComment> commentList;

    private Integer allCount;

    public List<MallComment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<MallComment> commentList) {
        this.commentList = commentList;
    }

    public Integer getAllCount() {
        return allCount;
    }

    public void setAllCount(Integer allCount) {
        this.allCount = allCount;
    }
}
