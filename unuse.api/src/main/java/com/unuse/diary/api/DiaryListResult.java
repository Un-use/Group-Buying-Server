package com.unuse.diary.api;

import com.unuse.common.ResponseResult;

import java.util.List;

/**
 * Created by Unuse on 2017/5/3.
 */

public class DiaryListResult extends ResponseResult {

    private List<Diary> diaryList;

    public List<Diary> getDiaryList() {
        return diaryList;
    }

    public void setDiaryList(List<Diary> diaryList) {
        this.diaryList = diaryList;
    }
}
