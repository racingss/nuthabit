package com.nuthabit.util;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.*;
import net.sf.json.JSONObject;
import com.nuthabit.dao.*;

public class KehuUtil {

	public KehuUtil() {
	}

	public String getKehuId(HttpServletRequest request, HttpServletResponse response) {
		String kehuId = null;
		if (request.getSession().getAttribute("kehuId") != null)
			kehuId = request.getSession().getAttribute("kehuId").toString();

		if (request.getSession().getAttribute("kehu") != null) {
			kehuId = ((Kehu) request.getSession().getAttribute("kehu")).getKehuId();
		}

		if (kehuId == null) {
			// if (getCookieByName(request, "kehuId") != null) {
			// kehuId = getCookieByName(request, "kehuId");
			// } else {
			Calendar c1 = Calendar.getInstance();
			c1.setTime(new Date());
			kehuId = (new StringBuilder("AUTO")).append(c1.get(7 + 1) * c1.get(12)).append("9")
					.append(System.currentTimeMillis()).toString();
			// addCookie(response, "kehuId", kehuId);
			// }
			request.getSession().setAttribute("kehuId", kehuId);
		}
		return kehuId;
	}

	public String getCookieByName(HttpServletRequest request, String name) {
		try {
			if (request.getCookies() != null) {
				Cookie[] cookies = request.getCookies();
				Cookie[] arrayOfCookie1;
				int j = (arrayOfCookie1 = cookies).length;
				for (int i = 0; i < j; i++) {
					Cookie cookie = arrayOfCookie1[i];
					if (cookie.getName().equals(name)) {
						return cookie.getValue();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void addCookie(HttpServletResponse response, String name, String value) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		cookie.setMaxAge(0x9660180);
		response.addCookie(cookie);
	}

	public Kehu getKehuByWeixin(HttpServletRequest request, HttpServletResponse response) {
		String openid;
		BufferedReader in;
		openid = null;
		System.setProperty("jsse.enableSNIExtension", "false");
		in = null;
		String access_token = null;
		// String unionid = null;
		String refresh_token = null;
		Kehu k = null;
		KehuDAO dao = new KehuDAO();
		try {
			URL realUrl = new URL(
					"https://sh.api.weixin.qq.com/sns/oauth2/access_token?appid=wx68ddf714b6a29ea5&secret=cd3b3bd428f5d8cb0059022bfeb03b58&code="
							+ request.getParameter("code") + "&grant_type=authorization_code");
			HttpsURLConnection conn = (HttpsURLConnection) realUrl.openConnection();
			conn.connect();

			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = in.readLine();
			System.out.println("微信取OpenId返回：" + line);
			JSONObject job = JSONObject.fromObject(line);
			openid = job.get("openid").toString();
			access_token = job.get("access_token").toString();
			refresh_token = job.get("refresh_token").toString();
			System.out.println((new StringBuilder("get openid:")).append(openid).toString());
			System.out.println((new StringBuilder("get access_token:")).append(access_token).toString());
			request.getSession().setAttribute("openId", openid);

			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);

			in.close();
			job.clear();
			conn.disconnect();

			k = dao.getKehu("openId", openid);
			if (k != null && request.getSession().getAttribute("kehu")==null)
				return k;

			k = updateKehuByWeixin(request, response, openid, access_token);

		} catch (Exception e) {
			System.out.println((new StringBuilder("KehuUtilException:")).append(e).toString());
			// e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return k;
	}

	public Kehu updateKehuByWeixin(HttpServletRequest request, HttpServletResponse response, String openid,
			String access_token) throws Exception {
		KehuDAO dao = new KehuDAO();
		BufferedReader in;
		Kehu k;
		URL realUrl;
		HttpsURLConnection conn;
		String line;
		JSONObject job;
		realUrl = new URL("https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid
				+ "&lang=zh_CN ");
		conn = (HttpsURLConnection) realUrl.openConnection();
		conn.connect();

		in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));// new
																						// BufferedReader(new
																						// InputStreamReader(conn.getInputStream()));
		line = in.readLine();
		System.out.println("微信取nickname返回：" + line);
		job = JSONObject.fromObject(line);
		String nickname = job.get("nickname").toString();
		System.out.println("nickname:" + nickname);
		String sex = job.get("sex").toString();
		String province = job.get("province").toString();
		String city = job.get("city").toString();
		System.out.println("city:" + city);
		String country = job.get("country").toString();
		String headimgurl = job.get("headimgurl").toString();
		System.out.println("headimgurl:" + headimgurl);

		if(request.getSession().getAttribute("kehu")!=null){
			k = (Kehu)request.getSession().getAttribute("kehu");
		}else{
			k = new Kehu();
		}
		
		k.setOpenId(openid);
		k.setKehuId(getKehuId(request, response));
		k.setOpenId(openid);
		Calendar c1 = Calendar.getInstance();
		c1.setTime(new Date());
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		if(request.getSession().getAttribute("kehu")!=null){
		}else{
			k.setZhuceri(format1.format(c1.getTime()));
		}

		k.setDengluri(format1.format(c1.getTime()));
		k.setHeadimgurl(headimgurl);
		k.setCity(city);
		k.setProvince(province);
		k.setCountry(country);

		if(request.getSession().getAttribute("kehu")!=null){
			return dao.updateKehu(k);
		}else{
			return dao.addKehu(k);
		}
		
	}

	public static Kehu getKehu(HttpServletRequest request, HttpServletResponse response) {
		Kehu k = null;
		if (request.getSession().getAttribute("kehu") != null)
			k = (Kehu) request.getSession().getAttribute("kehu");
		return k;
	}
	
	public Kehu getKehuById(String id) throws Exception {
		KehuDAO dao = new KehuDAO();
		return dao.findById(Integer.valueOf(id));
	}
}