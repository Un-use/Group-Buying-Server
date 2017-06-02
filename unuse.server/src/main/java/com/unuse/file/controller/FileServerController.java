package com.unuse.file.controller;

import com.unuse.common.BaseController;
import com.unuse.common.ResponseResult;
import com.unuse.file.api.FileDataListResult;
import com.unuse.file.api.IFile;
import com.unuse.file.service.FileServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * Created by CNQ on 2016/11/9.
 */

@Controller
public class FileServerController extends BaseController {

    @Autowired
    private FileServerService fileServerService;


    @RequestMapping(value = IFile.API_PATH_FILE_SINGLE_UPLOAD, method = RequestMethod.POST)
    @ResponseBody
    public FileDataListResult uploadSingleFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("type") Integer type,
            @RequestParam("id") Long id,
            @RequestParam(value = "isMain", required = false) Boolean isMain,
            @RequestParam(value = "isZip", required = false) Boolean isZip) throws Exception {

        FileDataListResult result = new FileDataListResult();

        if (null == isZip) {
            isZip = false;
        }

        if (null == isMain) {
			isMain = false;
        }

        fileServerService.clearFileNames();

        fileServerService.saveFile(file, file.getOriginalFilename(), type, id, isZip);

        result.setFileDataList(fileServerService.getFileDataList(id, type, isMain));

        return result;
    }

    @RequestMapping(value = IFile.API_PATH_FILE_MULTIPLE_UPLOAD, method = RequestMethod.POST)
    @ResponseBody
    public FileDataListResult uploadMultipleFile(
            @RequestParam("files") MultipartFile[] files,
            @RequestParam("type") Integer type,
            @RequestParam("id") Long id,
			@RequestParam(value = "isMain", required = false) Boolean isMain) throws Exception {

        FileDataListResult result = new FileDataListResult();

		if (null == isMain) {
			isMain = false;
		}

        fileServerService.clearFileNames();

        Date now = new Date();
        for (MultipartFile file : files) {
            if (null == file) {
                continue;
            }

            fileServerService.saveSingleFile(file, file.getOriginalFilename(), type, id, now);
        }

        result.setFileDataList(fileServerService.getFileDataList(id, type, isMain));

        return result;
    }

}
