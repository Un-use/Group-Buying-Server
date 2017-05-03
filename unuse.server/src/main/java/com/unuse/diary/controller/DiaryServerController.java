package com.unuse.diary.controller;

import com.unuse.common.BaseController;
import com.unuse.common.ResponseResult;
import com.unuse.diary.api.Diary;
import com.unuse.diary.api.DiaryListResult;
import com.unuse.diary.api.IDiary;
import com.unuse.diary.service.DiaryServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Unuse on 2017/5/3.
 */

@Controller
public class DiaryServerController extends BaseController {

    @Autowired
    private DiaryServerService diaryServerService;


    @RequestMapping(value = IDiary.API_PATH_DIARY_LIST_GET, method = RequestMethod.GET)
    @ResponseBody
    public DiaryListResult getDiaryListByStartId(@RequestParam(value = "startId", required = false) Integer startId) {
        DiaryListResult result = new DiaryListResult();

        result.setDiaryList(diaryServerService.getDiaryListByStartId(startId, getUid()));

        return result;
    }

    @RequestMapping(value = IDiary.API_PATH_DIARY_UPDATE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult updateDiary(@RequestBody Diary diary) {

        diary.setUid(getUid());

        if (null == diary.getId()) {
            diaryServerService.addDiary(diary);
        } else {
            diaryServerService.updateDiary(diary);
        }

        return ResponseResult.retOK();
    }


}
