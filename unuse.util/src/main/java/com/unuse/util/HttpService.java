package com.unuse.util;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Create by Unuse on 2016-09-01
 */
public class HttpService {
	
	private static OkHttpClient client = null;

	private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

	private static final String URLPath = "http://127.0.0.1:8080/unuse.server";

	private static String token = null;

	public static String getToken() {
		return token;
	}

	public static void setToken(String token) {
		HttpService.token = token;
	}

	public static String getURLPath() {
		return URLPath;
	}

	public static void setURLPath(String urlPath) {}

	public <T> T runService(Object param, String api, Class<T> classOfT) {
		return runService(param, api, (Type) classOfT, false);
	}
	
	public <T> T runService(String param, String api, Class<T> classOfT) {
		return runService(param, api, (Type) classOfT, true);
	}

	public <T> T runService(Map<String, Object> param, String api, Class<T> classOfT, boolean isGet) {
		String requestParam = null;
		if (isGet) {
			StringBuffer bf = new StringBuffer();
			if (null != param) {
				for (String key : param.keySet()) {
					bf.append("&" + key + "=" + param.get(key));
				}
                requestParam = bf.substring(1);
			} else {
                requestParam = "";
            }
		}
		return runService(requestParam, null, param, api, (Type) classOfT, isGet);
	}
	
	private <T> T runService(Object param, String api, Type classOfT, boolean isGet) {
		String requestParam = null;
		Object requestObject = null;
		
		if (null != param) {
			if (param.getClass().equals(String.class)) {
				requestParam = (String) param;
			} else if (!isGet) {
				requestObject = param;
			}
		}
		
		return runService(requestParam, requestObject, null, api, classOfT, isGet);
	}

	private <T> T runService(String requestParam, Object requestObject, Map<String, Object> param, String api, Type resClass, boolean isGet) {
		T res = null;
		
		if (null == client) {
			client = new OkHttpClient.Builder()
					.connectTimeout(5, TimeUnit.SECONDS)
					.readTimeout(2, TimeUnit.MINUTES)
					.writeTimeout(5, TimeUnit.SECONDS)
					.build();
		}
		
		Request request = null;

        String url = URLPath;
		if (StringUtils.isEmpty(requestParam)) {
			url += api + "?";
		} else {
			url += api + "?" + requestParam + "&";
		}
		if (null != token) {
			url += "token=" + token;
		}

		System.out.println(url);

		if (isGet) {
			request = new Request.Builder()
					.url(url)
					.header("Accept", "application/json; charset=utf-8")
					.addHeader("Content-type", "application/json; charset=utf-8")
					.build();

		} else {
			String requestBodyJson = "";
			RequestBody requestBody = null;
			
			if (null != requestObject) {
				if (String.class.isInstance(requestObject)) {
					requestBodyJson = (String) requestObject;
				} else {
					requestBodyJson = JSON.toJSONString(requestObject);
				}
				requestBody = RequestBody.create(MEDIA_TYPE_JSON, requestBodyJson);
				
			} else if (null != param) {
				FormBody.Builder builder = new FormBody.Builder();
				for (String key : param.keySet()) {
					builder.add(key, param.get(key).toString());
				}
				requestBody = builder.build();
				
			}

			request = new Request.Builder()
					.url(url)
					.post(requestBody)
					.build();
			
		}
		
		try {
			Response response = client.newCall(request).execute();
			
			if (!response.isSuccessful()) {
				throw new IOException("服务器错误: " + response);
			}

			System.out.println(response.body().string());
			
			res = JSON.parseObject(response.body().string(), resClass);
			
		} catch (IOException e) {
			e.printStackTrace();
			res = null;
		}

		return res;
	}
}
