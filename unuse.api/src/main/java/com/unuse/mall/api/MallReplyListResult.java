package com.unuse.mall.api;

import com.unuse.common.ResponseResult;

import java.util.List;

/**
 * Created by unuse on 17/2/16.
 */
public class MallReplyListResult extends ResponseResult {

    private List<MallReply> replyList;

    private Integer allCount;

    public List<MallReply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<MallReply> replyList) {
        this.replyList = replyList;
    }

    public Integer getAllCount() {
        return allCount;
    }

    public void setAllCount(Integer allCount) {
        this.allCount = allCount;
    }
}
