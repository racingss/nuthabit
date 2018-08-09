package com.babycard.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.babycard.dao.Kehu;
import com.babycard.dao.KehuDAO;

public class LanguageHttp {
	public long getLanguageId(HttpServletRequest request) {
		long languageId = 1;
		// if (request.getSession().getAttribute("languageId") != null) {
		// languageId =
		// Long.parseLong(request.getSession().getAttribute("languageId").toString());
		// } else {
		// Cookie[] cookies = request.getCookies();
		// if (null == cookies) {
		// System.out.println("没有cookie languageId==============");
		// } else {
		// for (Cookie cookie : cookies) {
		// if (cookie.getName().equals("languageId")) {
		// languageId = Long.parseLong(cookie.getValue());
		// break;
		// }
		// }
		// }
		// }
		Kehu k = new KehuUtil().getKehu(request);

		if (request.getParameter("languageId") != null) {
			languageId = Long.parseLong(request.getParameter("languageId"));

			new KehuDAO().updateLanguageId(k.getId(), languageId);
			k.setLanguageId(languageId);
		} else {
			languageId = k.getLanguageId();
		}
		return languageId;
	}

	public long getLanguageId_2(HttpServletRequest request) {
		long languageId_2 = 0;
		Kehu k = new KehuUtil().getKehu(request);

		if (request.getParameter("languageId_2") != null) {
			languageId_2 = Long.parseLong(request.getParameter("languageId_2"));
			new KehuDAO().updateLanguageId_2(k.getId(), languageId_2);
			k.setLanguageId_2(languageId_2);
		} else {
			languageId_2 = k.getLanguageId_2();
		}
		return languageId_2;
	}

}
