package com.unuse.config;


import com.unuse.mall.api.MallItem;
import com.unuse.user.api.UserData;

import java.util.Map;

/**
 * Created by CNQ on 2016/11/10.
 */

public class Configer {

    private static String itemPath = null;

    private static String userPath = null;

    private static String tempPath = null;

    private static String itemURL  = null;

    private static String userURL  = null;

    private static Map<Long, UserData> userDataMap = null;

	private static Map<Long, MallItem> mallItemMap = null;

	private static Map<Integer, String> cidStringMap = null;

    public static String getItemPath() {
        return itemPath;
    }

    public static void setItemPath(String itemPath) {
        Configer.itemPath = itemPath;
    }

    public static String getUserPath() {
        return userPath;
    }

    public static void setUserPath(String userPath) {
        Configer.userPath = userPath;
    }

	public static String getTempPath() {
		return tempPath;
	}

	public static void setTempPath(String tempPath) {
		Configer.tempPath = tempPath;
	}

	public static String getItemURL() {
        return itemURL;
    }

    public static void setItemURL(String itemURL) {
        Configer.itemURL = itemURL;
    }

    public static String getUserURL() {
        return userURL;
    }

    public static void setUserURL(String userURL) {
        Configer.userURL = userURL;
    }

	public static Map<Long, UserData> getUserDataMap() {
        return userDataMap;
    }

    public static void setUserDataMap(Map<Long,UserData> userDataMap) {
        Configer.userDataMap = userDataMap;
    }

	public static Map<Long, MallItem> getMallItemMap() {
		return mallItemMap;
	}

	public static void setMallItemMap(Map<Long, MallItem> mallItemMap) {
		Configer.mallItemMap = mallItemMap;
	}

	public static Map<Integer, String> getCidStringMapCache() {
		return cidStringMap;
	}

	public static void setCidStringMapCache(Map<Integer, String> cidStringMap) {
		Configer.cidStringMap = cidStringMap;
	}
}
