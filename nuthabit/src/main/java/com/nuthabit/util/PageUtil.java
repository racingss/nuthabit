package com.nuthabit.util;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import com.nuthabit.dao.Kehu;
import com.nuthabit.dao.MyplanThumbup;

public class PageUtil {
	
	public static String thumbupCss(Collection thumbupExColl,HttpServletRequest request) {
		Kehu k = null;
		if (request.getSession().getAttribute("kehu") != null) {
			k = (Kehu) request.getSession().getAttribute("kehu");
		} else {
			return "";
		}
		
		Iterator exThIt = thumbupExColl.iterator();
        while(exThIt.hasNext()){
        	MyplanThumbup mt = (MyplanThumbup)exThIt.next();
        	if(mt.getNickname().endsWith(k.getNickname()))
        		return " active";
        }
		return "";
	}

}
