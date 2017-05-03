package com.unuse.auth.service;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import com.unuse.auth.api.MenuItem;
import com.unuse.auth.mapper.AuthMapper;
import com.unuse.common.SessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Unuse on 2016/10/23.
 */

@Service
public class AuthServerService {

    @Autowired
    private AuthMapper authMapper;


    /** MenuItem **/

    public List<MenuItem> getMenuItemList() {
        return authMapper.getMenuItemList();
    }


    /** 短信验证 **/

    public String sendSMSByPhone(String phone, String code) {
        String url = "https://eco.taobao.com/router/rest";
        String appKey = "23634105";
        String secret = "ae3ac53fc3cd9c1ef4dcb6417da4cfb8";

        TaobaoClient client = new DefaultTaobaoClient(url, appKey, secret);
        AlibabaAliqinFcSmsNumSendRequest request = new AlibabaAliqinFcSmsNumSendRequest();
        request.setSmsType("normal");
        request.setSmsFreeSignName("团购");
        request.setSmsParamString("{\"name\":\""+ code + "\"}");
        request.setRecNum(phone);
        request.setSmsTemplateCode("SMS_46795145");

        String responseResult = null;
        try {
            AlibabaAliqinFcSmsNumSendResponse response = client.execute(request);
            responseResult = response.getBody();
        } catch (ApiException e) {
            e.printStackTrace();
        }

        if (null == responseResult) {
            return "短信发送失败!";
        }
        if (responseResult.contains("\"err_code\":\"0\"")) {
            return "短信发送成功!";
        } else {
            return "短信发送失败!";
        }
    }


	/** Session Data **/

	public void addSessionData(Long uid, String token) {
		SessionData sessionData = new SessionData();
		sessionData.setToken(token);
		sessionData.setUid(uid);

		authMapper.addSessionData(sessionData);
	}

	public SessionData getSessionDataByToken(String token) {
		return authMapper.getSessionDataByToken(token);
	}

}
