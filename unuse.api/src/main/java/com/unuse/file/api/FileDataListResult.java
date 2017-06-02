package com.unuse.file.api;

import com.unuse.common.ResponseResult;

import java.util.List;

/**
 * Created by Unuse on 2017/6/2.
 */

public class FileDataListResult extends ResponseResult {

    private List<FileData> fileDataList;

    public List<FileData> getFileDataList() {
        return fileDataList;
    }

    public void setFileDataList(List<FileData> fileDataList) {
        this.fileDataList = fileDataList;
    }
}
