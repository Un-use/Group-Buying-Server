package com.unuse.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.StringTokenizer;

/**
 * Create by Unuse on 2016-09-30
 */
public class FileUtil {

	/**
	 * 判断文件是否存在
	 * 
	 * @param filePath
	 * @return true or false
	 */
	public static final boolean isExists(String filePath) {
		if (null == filePath) {
			return false;
		}
		
		File file = new File(filePath);
		return file.exists();
	}
	
	/**
	 * Linux或者Unix创建文件夹
	 * 
	 * @param filePath
	 * @return true or false
	 */
	public static final boolean createFolders(String filePath) {
		if (null != filePath) {
			StringTokenizer st = new StringTokenizer(filePath, "/");
			StringBuilder sb = new StringBuilder("/");
			
			while (st.hasMoreTokens()) {
				sb.append(st.nextToken() + "/");
				File temp = new File(sb.toString());
				if (!isExists(filePath)) {
					temp.mkdir();
				}
			}
			
			return true;
		}
		return false;
	}
	
	/**
	 * Windows创建文件夹
	 * 
	 * @param filePath
	 * @return true or false
	 */
	public static final boolean createWindiwsFolders(String filePath) {
		if (null != filePath) {
			StringTokenizer st = new StringTokenizer(filePath, "\\");
			StringBuilder sb = new StringBuilder("\\");
			
			while (st.hasMoreTokens()) {
				sb.append(st.nextToken() + "\\");
				
				File temp = new File(sb.toString());
				if (!isExists(filePath)) {
					temp.mkdir();
				}
			}
			
			return true;
		}
		return false;
	}
	
	/**
	 * 删除文件
	 * 
	 * @param filePath
	 * @return true or false
	 */
	public static final void deleteFile(String filePath) {
		if (null == filePath) {
			return;
		}

		File file = new File(filePath);

		if (file.exists()) {
			file.delete();
		}

	}

	/**
	 * 删除全部文件
	 *
	 * @param file
	 * @return true or false
	 */
	public static final void deleteAllFile(File file) {
		if (!file.exists()) {
			return;
		}

		if (file.isFile()) {
			file.delete();
			return;
		}

		File[] files = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			deleteAllFile(files[i]);
		}

		file.delete();

	}
	
	/**
	 * 将文件从fromPath复制到toPath
	 * 
	 * @param fromPath
	 * @param toPath
	 * @throws IOException
	 */
	public static final void copyFile(String fromPath, String toPath) throws IOException {
		if (null == fromPath) {
			return;
		}
		
		File from = new File(fromPath);
		if (!from.exists()) {
			return;
		}
		
		File to = new File(toPath);
		
		Files.copy(from.toPath(), to.toPath(), StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING);
		
	}

	/**
	 * 将ZIP文件解压
	 *
	 * @param filePath
	 */
	public static void unZipFile(String filePath, String folder) throws Exception {
		if (null == filePath) {
			return;
		}

		Runtime runtime = Runtime.getRuntime();
		String cmd = String.format("unzip -o %s -d %s", filePath, folder);
		Process process = runtime.exec(cmd);
		process.waitFor();

		return;
	}

	/**
	 * 将保存的文件设置可读的权限
	 *
	 * @param filePath
	 */
	public static void setFilePermission(String filePath) throws IOException {
		if (null == filePath) {
			return;
		}

		Runtime.getRuntime().exec("chmod 644 " + filePath);

		return;
	}
}
