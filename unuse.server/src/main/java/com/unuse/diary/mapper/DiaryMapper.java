package com.unuse.diary.mapper;

import com.unuse.diary.api.Diary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Unuse on 2017/5/3.
 */

@Repository
public interface DiaryMapper {

    List<Diary> getDiaryListByStartId(@Param("startId") Integer startId, @Param("uid") Long uid);

    void addDiary(Diary diary);

    void updateDiary(Diary diary);

}
