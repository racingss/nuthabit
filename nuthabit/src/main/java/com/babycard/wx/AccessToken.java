package com.babycard.wx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import com.babycard.dao.Kehu;
import com.babycard.dao.KehuDAO;
import com.babycard.util.KehuUtil;
import com.gson.bean.TemplateData;
import com.gson.bean.UserInfo;
import com.gson.oauth.Menu;
import com.gson.oauth.Message;
import com.gson.oauth.Qrcod;
import com.gson.util.ConfKit;
import com.gson.util.HttpKit;

import net.sf.json.JSONObject;

public class AccessToken {

	public static final String appId = "wx01dc3375ca2f8882";
	public static final String secret = "9d4c6cf58377cc59aa9139f404294c20";

	public static String token = null;
	private static String jsapi_ticket = null;
	private static long tokenTime = System.currentTimeMillis();

	private static final String ACCESSTOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
	private static final String JSAPI_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi&access_token=";
	private static final String USER_INFO_URI = "https://api.weixin.qq.com/cgi-bin/user/info";

	public AccessToken() {
	}

	public synchronized static String getToken() throws Exception {
		if (token == null || jsapi_ticket == null || System.currentTimeMillis() - tokenTime > 0x1b7740L) {
			// sendGet();
			token = getAccessToken();
			jsapi_ticket = getTicket(token);
			tokenTime = System.currentTimeMillis();
		}
		return token;
	}

	public static String getAccessToken() throws Exception {
		String jsonStr = HttpKit.get(ACCESSTOKEN_URL.concat("&appid=") + appId + "&secret=" + secret);
		System.out.println(jsonStr);
		Map<String, Object> map = com.alibaba.fastjson.JSONObject.parseObject(jsonStr);
		return map.get("access_token").toString();
	}

	public static String getTicket(String accessToken) throws InterruptedException, ExecutionException,
			NoSuchAlgorithmException, KeyManagementException, IOException, NoSuchProviderException {
		String jsonStr = HttpKit.get(JSAPI_TICKET.concat(accessToken));
		// return com.alibaba.fastjson.JSONObject.parseObject(jsonStr);
		System.out.println(jsonStr);
		Map<String, Object> map = com.alibaba.fastjson.JSONObject.parseObject(jsonStr);
		return map.get("ticket").toString();
	}

	public static void sendGet() {
		BufferedReader in;
		System.setProperty("jsse.enableSNIExtension", "false");
		in = null;
		try {
			URL realUrl = new URL("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId
					+ "&secret=" + secret);
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
		Map ret = new HashMap();

		try {
			getToken();
			String url = "http://www.suyufuwu.com/";
			String nonce_str = create_nonce_str();
			String timestamp = create_timestamp();
			String signature = "";
			String string1 = (new StringBuilder("jsapi_ticket=")).append(jsapi_ticket).append("&noncestr=")
					.append(nonce_str).append("&timestamp=").append(timestamp).append("&url=").append(url).toString();
			System.out.println(string1);

			MessageDigest crypt = MessageDigest.getInstance("MD5");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
			ret.put("url", url);
			ret.put("jsapi_ticket", jsapi_ticket);
			ret.put("nonceStr", nonce_str);
			ret.put("timestamp", timestamp);
			ret.put("signature", signature);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	public static final String encodeSH1(String str) {
		if (str == null) {
			return null;
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
			messageDigest.update(str.getBytes());
			return getFormattedText(messageDigest.digest());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
			'e', 'f' };

	private static String getFormattedText(byte[] bytes) {
		int len = bytes.length;
		StringBuilder buf = new StringBuilder(len * 2);
		// 把密文转换成十六进制的字符串形式
		for (int j = 0; j < len; j++) {
			buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
			buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
		}
		return buf.toString();
	}

	public UserInfo getUserInfo(String openid) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("access_token", token);
		params.put("openid", openid);
		String jsonStr = HttpKit.get(USER_INFO_URI, params);
		System.out.println("jsonStr find subscribe:" + jsonStr);

		if (StringUtils.isNotEmpty(jsonStr)) {
			com.alibaba.fastjson.JSONObject obj = com.alibaba.fastjson.JSONObject.parseObject(jsonStr);
			if (obj.get("errcode") != null) {
				throw new Exception(obj.getString("errmsg"));
			}

			UserInfo user = com.alibaba.fastjson.JSONObject.toJavaObject(obj, UserInfo.class);
			return user;
		}
		return null;
	}

	public Map webSign(String url, HttpServletRequest request, HttpServletResponse response) {
		Map ret = new HashMap();

		try {
			getToken();
			String nonce_str = create_nonce_str();
			String timestamp = create_timestamp();
			String signature = "";
			String string1 = (new StringBuilder("jsapi_ticket=")).append(jsapi_ticket).append("&noncestr=")
					.append(nonce_str).append("&timestamp=").append(timestamp).append("&url=").append(url).toString();
			System.out.println("string1:" + string1);

			// MessageDigest crypt = MessageDigest.getInstance("MD5");
			// crypt.reset();
			// crypt.update(string1.getBytes("UTF-8"));
			// signature = byteToHex(crypt.digest());
			signature = encodeSH1(string1);
			System.out.println("signature:" + signature);
			ret.put("url", url);
			ret.put("jsapi_ticket", jsapi_ticket);
			ret.put("nonceStr", nonce_str);
			ret.put("timestamp", timestamp);
			ret.put("signature", signature);

			if (request.getSession().getAttribute("subscribe") == null) {

				Kehu k = new KehuUtil().getKehu(request, response);
				if (k != null) {
					UserInfo user = getUserInfo(k.getOpenId());
					System.out.println("!!!get subscribe:" + user.getSubscribe());
					request.getSession().setAttribute("subscribe", user.getSubscribe());
				}

			}

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	public void sendMsg(String openId, String text) {
		try {
			getToken();
			Message msg = new Message();
			msg.sendText(token, openId, text);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendImg(String openId, String media_id) {
		try {
			getToken();
			Message msg = new Message();
			msg.SendImage(token, openId, media_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String erweimaUrl(int kId) {
		Qrcod code = new Qrcod();
		try {
			getToken();

			com.alibaba.fastjson.JSONObject resultObj = code.createScene(token, 2592000, kId);

			// System.out.println(resultObj.toString());
			//
			// System.out.println(
			// "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" +
			// resultObj.get("ticket").toString());
			return code.showqrcodeUrl(resultObj.get("ticket").toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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

	private static void menu() {
		try {
			// Menu m = new Menu();
			// String accessToken = AccessToken.getToken();
			String params = "{\"button\":[ {\"type\":\"view\",\"name\":\"卡片点点\",\"url\":\"http://www.suyufuwu.com/diandian/\" }],\"button\":[{\"type\":\"view\",\"name\":\"动物大百科\",\"url\":\"http://www.suyufuwu.com/diandian/book.html?bookId=2\" }],\"button\":[{\"type\":\"view\",\"name\":\"MyFirstABC\",\"url\":\"http://www.suyufuwu.com/diandian/book.html?bookId=3\" }]}";
			System.out.println(params);
			// System.out.println(m.createMenu(accessToken, params));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void templateHuodong(String openId, String nickName, int num1, int num2) {
		try {
			Message m = new Message();
			TemplateData t = null;
			if (num2 > 0)
				t = new TemplateData(openId, "Ky7DBkrWo7uikn8tTiI7QoQwIZXrGL2bs7FeFBR8tN0",
						"http://www.suyufuwu.com/diandian/poster.html");
			else
				t = new TemplateData(openId, "Ky7DBkrWo7uikn8tTiI7QoQwIZXrGL2bs7FeFBR8tN0",
						"http://www.suyufuwu.com/diandian/showsubscribe.html");
			t.setTopcolor("#08c");
			t.push("first", nickName + "扫描了您的邀请卡");
			t.push("keyword1", "299元终身免费用户");
			t.push("keyword2", num1 + "/5");
			if (num2 > 0)
				t.push("remark", "在邀请" + num2 + "个就可以终身免费使用了，再接再厉吧，如果要刷新您的海报，可以点击这里重新生成");
			else
				t.push("remark", "您的任务已经达成啦,非常感谢!");
			String accessToken = AccessToken.getToken();
			com.alibaba.fastjson.JSONObject j = m.templateSend(accessToken, t);
			System.out.println(j.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void templateTuijian(String openId, String nickName) {
		try {
			Message m = new Message();
			TemplateData t = new TemplateData(openId, "14Mg_EHvSXVQPohuab_FIZYZmD_Y5jaELTcla1JUD70",
					"http://www.suyufuwu.com/diandian/parents.html");
			t.setTopcolor("#08c");
			t.push("first", "您好，有用户通过您的推荐进入平台！");
			t.push("keyword1", nickName);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date = new Date();
			t.push("keyword2", sdf.format(date));
			t.push("remark", "您获得了5个积分，可以用于兑换礼品");
			String accessToken = AccessToken.getToken();
			com.alibaba.fastjson.JSONObject j = m.templateSend(accessToken, t);
			System.out.println(j.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void templateRenwu(String openId) {
		try {
			Message m = new Message();
			TemplateData t = new TemplateData(openId, "vgqEbhKnUehAbxEsDXkfT7VgF1pw9GY4pU32b5PnpCA",
					"http://www.suyufuwu.com/diandian/booklist.html");
			t.setTopcolor("#08c");
			t.push("first", "您有新的学习任务啦");
			t.push("keyword1", "动物派对/比一比/猜猜我是谁");
			t.push("keyword2", "小动物猜猜看");
			t.push("keyword3", "2018年10月8号");
			t.push("keyword4", "3天内");
			t.push("keyword5", "5个积分");
			t.push("remark", "千万别忘记咯，卡片点点为您精心准备");
			String accessToken = AccessToken.getToken();
			com.alibaba.fastjson.JSONObject j = m.templateSend(accessToken, t);
			System.out.println(j.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		// System.out.println(new AccessToken().erweimaUrl(2749));
		// new AccessToken().menu();

		//new AccessToken().templateTuijian("oH97e0mCWkwYzWur4YAMvXxFR85M", "小叮当");
		if (true) {
			return;
		}

		Collection coll = new KehuDAO().getAll();
		Iterator it = coll.iterator();
		int i = 0;
		while (it.hasNext()) {
			Kehu k = (Kehu) it.next();
			System.out.println(i++ + ":" + k.getOpenId());
			try {
				if(i<=78)
					continue;
				//Thread.sleep(5000);
				new AccessToken().templateRenwu(k.getOpenId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//

	}

}
