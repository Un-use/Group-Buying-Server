package com.unuse.config.service;

import com.unuse.config.Configer;
import com.unuse.config.mapper.ConfigMapper;
import com.unuse.mall.api.MallItem;
import com.unuse.user.api.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by unuse on 17/2/15.
 */

@Service
public class ConfigServerService {

    @Autowired
    private ConfigMapper configMapper;

    public String getItemPath() {
        String itemPath = Configer.getItemPath();

        if (null == itemPath) {
            itemPath = configMapper.getConfigValueByName("unuse.item.path");
            Configer.setItemPath(itemPath);
        }

        return itemPath;
    }

    public String getUserPath() {
        String userPath = Configer.getUserPath();

        if (null == userPath) {
            userPath = configMapper.getConfigValueByName("unuse.user.path");
            Configer.setUserPath(userPath);
        }

        return userPath;
    }

    public String getTempPath() {
        String tempPath = Configer.getTempPath();

        if (null == tempPath) {
            tempPath = configMapper.getConfigValueByName("unuse.temp.path");
            Configer.setTempPath(tempPath);
        }

        return tempPath;
    }

    public String getItemURL() {
        String itemURL = Configer.getItemURL();

        if (null == itemURL) {
            itemURL = configMapper.getConfigValueByName("unuse.item.url");
            Configer.setItemURL(itemURL);
        }

        return itemURL;
    }

	public String getUserURL() {
		String userURL = Configer.getUserURL();

		if (null == userURL) {
			userURL = configMapper.getConfigValueByName("unuse.user.url");
			Configer.setUserURL(userURL);
		}

		return userURL;
	}

    public UserData getUserDataCache(Long uid) {
        return getUserDataMapCache().get(uid);
    }

	public void setUserDataCache(UserData userData) {
		getUserDataMapCache().put(userData.getUid(), userData);
	}

	private Map<Long, UserData> getUserDataMapCache() {
		Map<Long, UserData> userDataMap = Configer.getUserDataMap();

		if (null == userDataMap) {
			userDataMap = new HashMap<Long, UserData>();
			Configer.setUserDataMap(userDataMap);
		}

		return userDataMap;
	}

	public MallItem getMallItemCache(Long itemId) {
		return getMallItemMapCache().get(itemId);
	}

	public void setMallItemCache(Long itemId, MallItem mallItem) {
		getMallItemMapCache().put(itemId, mallItem);
	}

	private Map<Long, MallItem> getMallItemMapCache() {
		Map<Long, MallItem> mallItemMap = Configer.getMallItemMap();

		if (null == mallItemMap) {
			mallItemMap = new HashMap<Long, MallItem>();
			Configer.setMallItemMap(mallItemMap);
		}

		return mallItemMap;
	}

	public Map<Integer, String> getCidStringMapCache() {
		return Configer.getCidStringMapCache();
	}


	public void setCidStringMapCache(Map<Integer, String> cidStringMap) {
		Configer.setCidStringMapCache(cidStringMap);
	}
}
