package com.unuse.diary;

import com.unuse.auth.api.IAuth;
import com.unuse.common.BaseTest;
import com.unuse.common.ResponseResult;
import com.unuse.common.StringResult;
import com.unuse.diary.api.Diary;
import com.unuse.diary.api.DiaryListResult;
import com.unuse.diary.api.IDiary;
import com.unuse.user.api.IUser;
import com.unuse.user.api.UserAddress;
import com.unuse.user.api.UserDataResult;
import com.unuse.util.HttpService;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by unuse on 17/5/3.
 */
public class DiaryTest extends BaseTest {

    @Test
    public void addDiary() {

        startUnit();

        DiaryListResult result = httpService.runService(null, IDiary.API_PATH_DIARY_LIST_GET, DiaryListResult.class, true);
        Assert.assertTrue(result.getResult() == ResponseResult.RES_OK);

        endUnit();
    }

    @Test
    public void updateDiary() {

        startUnit();

        Diary diary = new Diary();
        diary.setId(1);
        diary.setTitle("1111");
        diary.setContent("2221");
        diary.setPicListJson("[\"1111.jpg\",\"2222.jpg\"]");

        ResponseResult result = httpService.runService(diary, IDiary.API_PATH_DIARY_UPDATE, ResponseResult.class);
        Assert.assertTrue(result.getResult() == ResponseResult.RES_OK);

        endUnit();
    }

}
