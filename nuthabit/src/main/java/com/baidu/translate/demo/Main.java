package com.baidu.translate.demo;

import com.baidu.translate.demo.TransApi;

import net.sf.json.JSONObject;

public class Main {

	// 在平台申请的APP_ID 详见
	// http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
	public static final String APP_ID = "20170424000045560";
	public static final String SECURITY_KEY = "fJIUUFV0D8PhtHo80eGG";

	public static void main(String[] args) {
		TransApi api = new TransApi(APP_ID, SECURITY_KEY);

		String query = "鹅鹅鹅，曲项向天歌。白毛浮绿水，红掌拨清波。";
		String r = api.getTransResult(query, "wyw", "zh");
		JSONObject json = JSONObject.fromObject(r.toString());
		String str1 = json.get("trans_result").toString();
		// 去掉[]
		str1 = str1.replace("[", "");
		str1 = str1.replace("]", "");
		JSONObject json1 = JSONObject.fromObject(str1);

		System.out.println("源语言：" + json1.get("src"));
		System.out.println("目标语言：" + json1.get("dst"));
	}

}
