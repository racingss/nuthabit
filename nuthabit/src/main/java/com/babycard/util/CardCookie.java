package com.babycard.util;

import java.util.*;

import javax.servlet.http.*;

public class CardCookie {
	final static String COOKIENAME = "cardId";

	public void add(HttpServletRequest request, HttpServletResponse response, long cardId) {
		delCookie(request, response, cardId);
		addCookie(request, response, cardId);
	}

	private void addCookie(HttpServletRequest request, HttpServletResponse response, long cardId) {
		Cookie[] cookies = request.getCookies();
		String cardString = null;
		if (null == cookies) {
			System.out.println("没有cookie==============");
			cardString = "";
		} else {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(COOKIENAME)) {
					cardString = cookie.getValue();
					break;
				}
			}
		}
		cardString = cardId + "," + cardString;

		Cookie cookie = new Cookie(COOKIENAME, cardString);
		cookie.setMaxAge(30 * 24 * 60 * 60);// 设置为30min
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	private void delCookie(HttpServletRequest request, HttpServletResponse response, long cardId) {
		Cookie[] cookies = request.getCookies();
		if (null == cookies) {
			System.out.println("没有cookie==============");
		} else {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(COOKIENAME)) {
					String temp = cookie.getValue().replaceAll(cardId + ",", "");
					temp = temp.replaceAll(Long.toString(cardId), "");
					cookie.setValue(temp);
					break;
				}
			}
		}
	}

	public Collection showRecent(HttpServletRequest request, HttpServletResponse response) {

		Cookie[] cookies = request.getCookies();
		Collection coll = new ArrayList();
		if (null == cookies) {
			System.out.println("没有cookie==============");
		} else {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(COOKIENAME)) {
					StringTokenizer s = new StringTokenizer(cookie.getValue(), ",");
					int i = 0;
					while (s.hasMoreElements()) {
						if (i++ > 9)
							break;
						String temp = s.nextToken();
						if (temp != null && !temp.equals("null"))
							coll.add(temp);
					}
					break;
				}
			}
		}
		return coll;
	}

	Map<String, String> cookieMap = new HashMap<String, String>();

}
