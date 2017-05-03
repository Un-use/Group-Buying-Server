package com.unuse.diary.api;

/**
 * Created by Unuse on 2017/5/3.
 */

public interface IDiary {


    /**
     * Call this API to get diary list
     *
     * @type: GET
     *
     * @RequestParam:
     * startId      : [Integer][optional]  id of Diary
     *
     * @return: object of DiaryListResult
     *
     */
    String API_PATH_DIARY_LIST_GET = "/diary/list/get";

    /**
     * Call this API to update diary
     *
     * @type: POST
     *
     * @RequestBody:
     * diary      : [Diary][must]  object of Diary
     *
     * @return: object of ResponseResult
     *
     */
    String API_PATH_DIARY_UPDATE = "/diary/update";

}
