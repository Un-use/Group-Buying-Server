package com.unuse.file.api;

/**
 * Created by CNQ on 2016/11/9.
 */
public interface IFile {

    class FileSource {
        public final static int USER            	= 1000;
        public final static int ITEM            	= 2000;
        public final static int TEMP            	= 3000;
        public final static int ITEM_COMMENT   		= 4000;
		public final static int ITEM_REPLY      	= 5000;
		public final static int ITEM_RETURN_GOODS   = 6000;
    }

    /**
     * Call this API to upload a file (zip file or single file)
     *
     * @type: POST
     *
     * @RequestParam:
     * file    : [MultipartFile][must]  file to upload
     * type    : [Integer][must]  type of FileSource
     * id      : [Long][must]  item ID
     * isMain  : [Boolean][optional]  whether is main picture (when type=IFile.FileSource.ITEM, this param is must)
     * isZip   : [Boolean][optional]  whether is zip file
     *
     * @return: object of ResponseResult
     *
     */
    String API_PATH_FILE_SINGLE_UPLOAD = "/file/single/upload";

    /**
     * Call this API to upload multiple file
     *
     * @type: POST
     *
     * @RequestParam:
     * files   : [MultipartFile[]][must]  files to upload
     * type    : [Integer][must]  type of FileSource
     * id      : [Long][must]  item ID
     * isMain  : [Boolean][optional]  whether is main picture (when type=IFile.FileSource.ITEM, this param is must)
     *
     * @return: object of ResponseResult
     *
     */
    String API_PATH_FILE_MULTIPLE_UPLOAD = "/file/multiple/upload";

}
