package com.babycard.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.babycard.dao.Kehu;
import com.babycard.dao.KehuDAO;

public class LanguageHttp {
	public long getLanguageId(HttpServletRequest request) {
		long languageId = 0;
//		if (request.getSession().getAttribute("languageId") != null) {
//			languageId = Long.parseLong(request.getSession().getAttribute("languageId").toString());
//		} else {
//			Cookie[] cookies = request.getCookies();
//			if (null == cookies) {
//				System.out.println("没有cookie languageId==============");
//			} else {
//				for (Cookie cookie : cookies) {
//					if (cookie.getName().equals("languageId")) {
//						languageId = Long.parseLong(cookie.getValue());
//						break;
//					}
//				}
//			}
//		}
		Kehu k = new KehuUtil().getKehu(request);
		
		if (request.getParameter("languageId") != null) {
			languageId = Long.parseLong(request.getParameter("languageId"));
			
			new KehuDAO().updateLanguageId(k.getId(), languageId);
			k.setLanguageId(languageId);
//			request.getSession().setAttribute("languageId", languageId);
		}else{
			languageId = k.getLanguageId();
		}
		return languageId;
	}

	public long getLanguageId_2(HttpServletRequest request) {
		long languageId_2 = -1;
		if (request.getSession().getAttribute("languageId_2") != null) {
			languageId_2 = Long.parseLong(request.getSession().getAttribute("languageId_2").toString());
		}
		if (request.getParameter("languageId_2") != null) {
			languageId_2 = Long.parseLong(request.getParameter("languageId_2"));
			request.getSession().setAttribute("languageId_2", languageId_2);
		}
		return languageId_2;
	}

}
