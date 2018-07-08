package com.babycard.wx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;

import net.sf.json.JSONObject;

public class AccessToken {

	public static final String appId = "wx01dc3375ca2f8882";
	public static final String secret = "9d4c6cf58377cc59aa9139f404294c20";
	
	public static String token = null;
	private static String jsapi_ticket = null;
	private static long tokenTime = System.currentTimeMillis();

	public AccessToken() {
	}

	public synchronized static String getToken() {
		if (token == null || jsapi_ticket == null || System.currentTimeMillis() - tokenTime > 0x1b7740L) {
			sendGet();
			tokenTime = System.currentTimeMillis();
		}
		return token;
	}

	
	public static void sendGet() {
		BufferedReader in;
		System.setProperty("jsse.enableSNIExtension", "false");
		in = null;
		try {
			URL realUrl = new URL(
					"https://sh.api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId+"&secret="+secret);
			HttpsURLConnection conn = (HttpsURLConnection) realUrl.openConnection();
			conn.connect();
			Map map = conn.getHeaderFields();
			String key;
			for (Iterator iterator = map.keySet().iterator(); iterator.hasNext(); System.out
					.println((new StringBuilder(String.valueOf(key))).append("--->").append(map.get(key)).toString()))
				key = (String) iterator.next();

			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = in.readLine();
			JSONObject job = JSONObject.fromObject(line);
			token = job.get("access_token").toString();
			System.out.println((new StringBuilder("get:")).append(token).toString());
		} catch (Exception e) {
			System.out.println((new StringBuilder("����GET��������쳣��")).append(e).toString());
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		try {
			URL realUrl = new URL(
					(new StringBuilder("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="))
							.append(token).append("&type=jsapi").toString());
			HttpsURLConnection conn = (HttpsURLConnection) realUrl.openConnection();
			conn.connect();
			Map map = conn.getHeaderFields();
			String key;
			for (Iterator iterator1 = map.keySet().iterator(); iterator1.hasNext(); System.out
					.println((new StringBuilder(String.valueOf(key))).append("--->").append(map.get(key)).toString()))
				key = (String) iterator1.next();

			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = in.readLine();
			JSONObject job = JSONObject.fromObject(line);
			jsapi_ticket = job.get("ticket").toString();
			System.out.println((new StringBuilder("getjsapi_ticket:")).append(jsapi_ticket).toString());

		} catch (Exception e) {
			System.out.println((new StringBuilder("����GETjsapi_ticket��������쳣��")).append(e).toString());
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

	public static Map sign() {
		getToken();
		//String url = "http://www.suyufuwu.com/tijiaodingdan.html";
		String url = "http://www.suyufuwu.com/";
		Map ret = new HashMap();
		String nonce_str = create_nonce_str();
		String timestamp = create_timestamp();
		String signature = "";
		String string1 = (new StringBuilder("jsapi_ticket=")).append(jsapi_ticket).append("&noncestr=")
				.append(nonce_str).append("&timestamp=").append(timestamp).append("&url=").append(url).toString();
		System.out.println(string1);
		try {
			MessageDigest crypt = MessageDigest.getInstance("MD5");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ret.put("url", url);
		ret.put("jsapi_ticket", jsapi_ticket);
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);
		return ret;
	}

	private static String byteToHex(byte hash[]) {
		Formatter formatter = new Formatter();
		byte abyte0[];
		int j = (abyte0 = hash).length;
		for (int i = 0; i < j; i++) {
			byte b = abyte0[i];
			formatter.format("%02x", new Object[] { Byte.valueOf(b) });
		}

		String result = formatter.toString();
		formatter.close();
		return result;
	}

	private static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}

	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000L);
	}

	public static void main(String args[]) {
		try {
			Map ret = sign();
			java.util.Map.Entry entry;
			for (Iterator iterator = ret.entrySet().iterator(); iterator.hasNext(); System.out.println(
					(new StringBuilder()).append(entry.getKey()).append(", ").append(entry.getValue()).toString()))
				entry = (java.util.Map.Entry) iterator.next();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

