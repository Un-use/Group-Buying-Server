package com.unuse.file.service;

import com.unuse.common.ResponseException;
import com.unuse.common.ResponseResult;
import com.unuse.config.service.ConfigServerService;
import com.unuse.file.api.IFile;
import com.unuse.mall.api.MallComment;
import com.unuse.mall.api.MallItem;
import com.unuse.mall.api.MallReply;
import com.unuse.mall.api.MallReturnGoods;
import com.unuse.mall.service.MallServerService;
import com.unuse.user.api.User;
import com.unuse.user.service.UserServerService;
import com.unuse.util.FileUtil;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by CNQ on 2016/11/10.
 */

@Service
public class FileServerService {

    private Logger logger = Logger.getLogger(FileServerService.class);

    @Autowired
    private ConfigServerService configServerService;

    @Autowired
    private MallServerService mallServerService;

    @Autowired
    private UserServerService userServerService;


    private List<String> fileNames = new ArrayList<String>(); // 存储保存到服务器的文件名


    public void clearFileNames() {
        fileNames.clear();
    }

    public void saveSingleFile(MultipartFile file, String fileName, Integer type, Long id, Date now) throws Exception {
        String savePath = getSavePath(type);
        String subFolder = getSubFolder(type, id);

        String filePath;
        if (null == subFolder) {
            filePath = String.format("%s/%d_%s", savePath, now.getTime(), fileName);
        } else {
            filePath = String.format("%s/%s/%d_%s", savePath, subFolder, now.getTime(), fileName);
        }

        // 保存文件到指定文件夹
        FileUtils.copyInputStreamToFile(file.getInputStream(), new File(filePath));

        fileNames.add(String.format("%d_%s", now.getTime(), fileName));
    }

    public void saveFile(MultipartFile file, String fileName, Integer type, Long id, Boolean isZip) throws Exception {
        Date now = new Date();
        String tempPath = getSavePath(IFile.FileSource.TEMP);
        String tempFile = String.format("%s/%d_%s", tempPath, now.getTime(), fileName);
        String tempFolder = String.format("%s/uz%d", tempPath, now.getTime());

        // 创建temp文件
        file.transferTo(new File(tempFile));

        logger.info("=============> 上传文件 <=============");

        try {

            if (isZip) {
                saveZipFile(tempFile, tempFolder, type, id, now);
            } else {
                saveSingleFile(tempFile, fileName, type, id, now);
            }

        } catch (Exception e) {
            logger.info("=============> 上传文件失败 <=============");

            throw new ResponseException(ResponseResult.RES_RUNTIME_EXCEPTION, "上传文件失败");
        } finally {

            FileUtil.deleteFile(tempFile);
        }

    }

    private void saveZipFile(String filePath, String fileFolder, Integer type, Long id, Date now) throws Exception {
        FileUtil.unZipFile(filePath, fileFolder);

        saveFolderFile(fileFolder, type, id, now);

        FileUtil.deleteAllFile(new File(fileFolder));
    }

    private void saveFolderFile(String fileFolder, Integer type, Long id, Date now) throws Exception {

        File file = new File(fileFolder);

        File[] files = file.listFiles();

        if (null == files) {
            return;
        }

        for (File temp : files) {
            if (temp.isFile()) {
                saveSingleFile(temp.getAbsolutePath(), temp.getName(), type, id, now);
            } else if (temp.isDirectory()){
                String childFolder = fileFolder + "/" + temp.getName();
                saveFolderFile(childFolder, type, id, now);
            }
        }

    }

    private void saveSingleFile(String filePath, String fileName, Integer type, Long id, Date now) throws Exception {
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);

        String savePath = getSavePath(type);
        String subFolder = getSubFolder(type, id);

        String saveFilePath;
        if (null == subFolder) {
            saveFilePath = String.format("%s/%d_%s", savePath, now.getTime(), fileName);
        } else {
            saveFilePath = String.format("%s/%s/%d_%s", savePath, subFolder, now.getTime(), fileName);
        }

        FileUtils.copyInputStreamToFile(fis, new File(saveFilePath));

		fis.close();

        fileNames.add(String.format("%d_%s", now.getTime(), fileName));
    }

    private String getSavePath(Integer type) {
        String savePath = null;

        switch (type) {
            case IFile.FileSource.ITEM:
            case IFile.FileSource.ITEM_COMMENT:
            case IFile.FileSource.ITEM_REPLY:
			case IFile.FileSource.ITEM_RETURN_GOODS:
                savePath = configServerService.getItemPath();
                break;
            case IFile.FileSource.USER:
                savePath = configServerService.getUserPath();
                break;
            case IFile.FileSource.TEMP:
                savePath = configServerService.getTempPath();
                break;
        }

        return savePath;
    }

    private String getSubFolder(Integer type, Long id) {
        String subFolder = null;

        MallItem mallItem = null;
        switch (type) {
            case IFile.FileSource.ITEM:
                mallItem = mallServerService.getMallItemByItemId(id);
                if (null != mallItem) {
                    subFolder = mallItem.getSecret() + "_" + id;
                }
                break;
            case IFile.FileSource.ITEM_COMMENT:
                mallItem = mallServerService.getMallItemByItemId(id);
                if (null != mallItem) {
                    subFolder = mallItem.getSecret() + "_" + id + "/comment";
                }
                break;
            case IFile.FileSource.ITEM_REPLY:
                mallItem = mallServerService.getMallItemByItemId(id);
                if (null != mallItem) {
                    subFolder = mallItem.getSecret() + "_" + id + "/reply";
                }
                break;
			case IFile.FileSource.ITEM_RETURN_GOODS:
				mallItem = mallServerService.getMallItemByItemId(id);
				if (null != mallItem) {
					subFolder = mallItem.getSecret() + "_" + id + "/returnGoods";
				}
				break;
            case IFile.FileSource.USER:
                subFolder = "u_" + id;
                break;
        }

        return subFolder;
    }

    public void updateItemFile(Long id, Integer type, Boolean isMain) {
        if (null == fileNames || fileNames.isEmpty()) {
            return;
        }

        switch (type) {
            case IFile.FileSource.ITEM:
                MallItem mallItem = new MallItem();
                mallItem.setItemId(id);
                if (isMain) {
                    mallItem.setMainPictureList(fileNames);
                } else {
                    mallItem.setDetailPictureList(fileNames);
                }

                mallServerService.updateMallItem(mallItem);
                break;
            case IFile.FileSource.ITEM_COMMENT:
                MallComment mallComment = new MallComment();
                mallComment.setCommentId(id);
                mallComment.setPictureList(fileNames);

                mallServerService.updateMallComment(mallComment);
                break;
            case IFile.FileSource.ITEM_REPLY:
                MallReply mallReply = new MallReply();
                mallReply.setReplyId(id);
                mallReply.setPictureList(fileNames);

                mallServerService.updateMallReply(mallReply);
                break;
			case IFile.FileSource.ITEM_RETURN_GOODS:
				MallReturnGoods mallReturnGoods = new MallReturnGoods();
				mallReturnGoods.setId(id.intValue());
				mallReturnGoods.setPictureList(fileNames);

				mallServerService.updateMallReturnGoods(mallReturnGoods);
				break;
            case IFile.FileSource.USER:
                User user = new User();
                user.setUid(id);
                user.setAvatar(fileNames.get(0));

				userServerService.updateUser(user);
                break;
        }

        clearFileNames();
    }
}