package com.babycard.util;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.*;
import net.sf.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.babycard.dao.*;
import com.gson.WeChat;
import com.gson.bean.UserInfo;

public class KehuUtil {

	public static final String appId = "wx01dc3375ca2f8882";
	public static final String secret = "9d4c6cf58377cc59aa9139f404294c20";
	public static final String mchId = "1504346731";
	public static final String notify_url = "http://www.suyufuwu.com/diandian/weixinzhifuresult.html";
	public static final long JIFEN_REG = 3;

	public KehuUtil() {

	}

	public Kehu getKehu(HttpServletRequest request, HttpServletResponse response) {
		Kehu k = null;
		if (request.getSession().getAttribute("kehu") != null)
			k = (Kehu) request.getSession().getAttribute("kehu");
		else {
			Cookie[] cookies = request.getCookies();
			if (null == cookies) {
				System.out.println("没有cookie==============");
			} else {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("openId")) {
						k = new KehuDAO().getKehu("openId", cookie.getValue());
						request.getSession().setAttribute("kehu", k);
						System.out.println("cookielogin");
						break;
					}
				}
			}
		}

		// 处理一下登录和送积分
		if (k != null) {
			String dengluri = new java.sql.Timestamp(System.currentTimeMillis()).toString().substring(0, 10);
			if (k.getDengluri() == null || !k.getDengluri().substring(0, 10).equals(dengluri)) {
				new KehuDAO().denglu(k);
				k.setDengluri(new java.sql.Timestamp(System.currentTimeMillis()).toString());
				new KehuDAO().updateJifen(k.getId(), 1, true, "登录");
			}
		}

		return k;
	}

	public Kehu getKehu(HttpServletRequest request) {
		return getKehu(request, null);
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

	public UserInfo getUserInfoFromWx(HttpServletRequest request, HttpServletResponse response) {
		UserInfo u = null;
		String openid;
		BufferedReader in;
		openid = null;
		if (request.getParameter("code") == null) {
			System.err.println("没有拿到code");
			return null;
		}
		System.setProperty("jsse.enableSNIExtension", "false");
		in = null;
		String access_token = null;
		String unionid = null;
		String nickname = null;
		String refresh_token = null;
		try {
			URL realUrl = new URL("https://sh.api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId + "&secret="
					+ secret + "&code=" + request.getParameter("code") + "&grant_type=authorization_code");
			HttpsURLConnection conn = (HttpsURLConnection) realUrl.openConnection();
			conn.connect();
			Map map = conn.getHeaderFields();
			String key;
			for (Iterator iterator = map.keySet().iterator(); iterator.hasNext(); System.out
					.println((new StringBuilder(String.valueOf(key))).append("--->").append(map.get(key)).toString()))
				key = (String) iterator.next();

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

		} catch (Exception e) {
			System.out.println((new StringBuilder("get openid failed")).append(e).toString());
			// e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		try {
			u = WeChat.user.getUserInfo(access_token, openid);
			//
			System.out.println("微信取用户信息" + u.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return u;
	}

	public Kehu getKehuFromWX(HttpServletRequest request, HttpServletResponse response) {
		Kehu k = getKehu(request, response);
		if (k != null) {
			System.out.println("Get user login info from session");
			return k;
		}

		UserInfo u = getUserInfoFromWx(request, response);

		if (u == null) {
			System.out.println("can not get userinfo from wx");
			return null;
		}
		KehuDAO dao = new KehuDAO();
		try {
			k = dao.getKehu("openId", u.getOpenid());

			if (k == null) {
				// 注册
				System.out.println("register user info from wx");

				k = new Kehu();
				Calendar c1 = Calendar.getInstance();
				c1.setTime(new Date());
				k.setKehuId(new StringBuilder("CARD").append(c1.get(7 + 1) * c1.get(12)).append("9")
						.append(System.currentTimeMillis()).toString());

				k.setOpenId(u.getOpenid());
				k.setCity(u.getCity());
				k.setCountry(u.getCountry());
				k.setHeadimgurl(u.getHeadimgurl());
				// if (u.getNickname() == null || u.getNickname().indexOf("\\")
				// != -1)
				// k.setNickname("微信用户");
				// else
				k.setNickname(u.getNickname());
				k.setProvince(u.getProvince());
				k.setSex(Integer.toString(u.getSex()));

				SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				k.setZhuceri(format1.format(c1.getTime()));
				k.setDengluri(format1.format(c1.getTime()));

				// 设置推荐人
				// if (request.getSession().getAttribute("tuijianren") != null)
				// {
				// if
				// (dao.getDaili(request.getSession().getAttribute("tuijianren").toString())
				// != null) {
				// k.setTuijianren(request.getSession().getAttribute("tuijianren").toString());
				// new KehuDAO().updateTuijianren(k);
				// }
				// }

				k = dao.addKehu(k);
				dao.updateJifen(k.getId(), JIFEN_REG, true, "注册");
				k.setJifen(JIFEN_REG);
			}

			// 更新用户名
			verfyNickname(k, u, dao);

			request.getSession().setAttribute("kehu", k);

			// cookie加入
			Cookie cookie = new Cookie("openId", k.getOpenId());
			cookie.setMaxAge(30 * 24 * 60 * 60);// 设置为30min
			cookie.setPath("/");
			response.addCookie(cookie);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return k;
	}

	private void verfyNickname(Kehu k, UserInfo u, KehuDAO dao) {
		try {
			if (!k.getNickname().equals(u.getNickname())) {
				k.setNickname(u.getNickname());
				dao.updateKehu(k);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("验证用户名失败");
		}
	}
}