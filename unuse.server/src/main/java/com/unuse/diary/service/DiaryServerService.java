package com.unuse.diary.service;

import com.alibaba.fastjson.JSON;
import com.unuse.diary.api.Diary;
import com.unuse.diary.mapper.DiaryMapper;
import com.unuse.user.api.UserDataResult;
import com.unuse.util.HttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Unuse on 2017/5/3.
 */

@Service
public class DiaryServerService {

    @Autowired
    private DiaryMapper diaryMapper;

    public List<Diary> getDiaryListByStartId(Integer startId, Long uid) {
        List<Diary> diaryList = diaryMapper.getDiaryListByStartId(startId, uid);
        if (null != diaryList && !diaryList.isEmpty()) {
            for (Diary diary : diaryList) {
                diary.setPicList(JSON.parseArray(diary.getPicListJson(), String.class));
            }
        }

        return diaryList;
    }

    public void addDiary(Diary diary) {
        if (null == diary.getPicListJson()) {
            diary.setPicListJson(makeListJson(diary.getPicList()));
        }

        diaryMapper.addDiary(diary);
    }

    private String makeListJson(List<String> stringList) {
        if (null == stringList) {
            return null;
        }

        return JSON.toJSONString(stringList);
    }

    public void updateDiary(Diary diary) {
        if (null == diary.getPicListJson()) {
            diary.setPicListJson(makeListJson(diary.getPicList()));
        }

        diaryMapper.updateDiary(diary);
    }

}
