package com.baidu.speech.restapi.ttsdemo;

import com.baidu.speech.restapi.common.DemoException;
import com.baidu.speech.restapi.common.ConnUtil;
import com.baidu.speech.restapi.common.TokenHolder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class TtsMain {

	public static void main(String[] args) throws IOException, DemoException {
		System.out.println(TtsMain.build("老虎", "sound/"));
	}

	// 填写网页上申请的appkey 如 $apiKey="g8eBUMSokVB1BHGmgxxxxxx"
	private static final String appKey = "HRz6CDiOEDKQa60pgiMS9Uag";

	// 填写网页上申请的APP SECRET 如 $secretKey="94dc99566550d87f8fa8ece112xxxxx"
	private static final String secretKey = "BG50iBa7zlFhQ4EPkgRMiYHrFPpo0uHn";

	// text 的内容为"欢迎使用百度语音合成"的urlencode,utf-8 编码
	// 可以百度搜索"urlencode"

	// 发音人选择, 0为普通女声，1为普通男生，3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女声
	private static final int per = 4;
	// 语速，static 取值0-9，默认为5中语速
	private static final int spd = 4;
	// 音调，取值0-9，默认为5中语调
	private static final int pit = 5;
	// 音量，取值0-9，默认为5中音量
	private static final int vol = 5;

	public static final String url = "http://tsn.baidu.com/text2audio"; // 可以使用https

	private static String cuid = "1234567JAVA";

	public static String build(String text) {
		try {
			return build(text, "sound/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DemoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String build(String text, String path) throws IOException, DemoException {
		TokenHolder holder = new TokenHolder(appKey, secretKey, TokenHolder.ASR_SCOPE);
		holder.resfresh();
		String token = holder.getToken();

		String url2 = url + "?tex=" + ConnUtil.urlEncode(text);
		url2 += "&per=" + per;
		url2 += "&spd=" + spd;
		url2 += "&pit=" + pit;
		url2 += "&vol=" + vol;
		url2 += "&cuid=" + cuid;
		url2 += "&tok=" + token;
		url2 += "&lan=zh&ctp=1";
		// System.out.println(url2); // 反馈请带上此url，浏览器上可以测试
		HttpURLConnection conn = (HttpURLConnection) new URL(url2).openConnection();
		conn.setConnectTimeout(5000);
		String contentType = conn.getContentType();
		String result = System.currentTimeMillis() + ".mp3";
		if (contentType.contains("mp3")) {
			byte[] bytes = ConnUtil.getResponseBytes(conn);
			// File file = new File("sound/"+result); // 打开mp3文件即可播放
			File file = new File(path + result); // 打开mp3文件即可播放
			// System.out.println( file.getAbsolutePath());
			FileOutputStream os = new FileOutputStream(file);
			os.write(bytes);
			os.close();
			System.out.println("mp3 file write to " + file.getAbsolutePath());
			return "sound/" + result;
		} else {
			System.err.println("ERROR: content-type= " + contentType);
			String res = ConnUtil.getResponseString(conn);
			System.err.println(res);
			return null;
		}
	}
}
