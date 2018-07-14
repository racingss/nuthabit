package com.babycard.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import com.babycard.dao.Card;
import com.babycard.dao.CardDAO;
import com.babycard.dao.CardPic;
import com.babycard.dao.Sound;
import com.babycard.dao.SoundDAO;

public class TestHttp {

	public CardPic getTest(HttpServletRequest request) {
		Collection testColl = (Collection) request.getSession().getAttribute("testColl");
		Iterator it = testColl.iterator();
		while (it.hasNext()) {
			CardPic cp = (CardPic) it.next();
			if (cp.result.equals("0"))
				return cp;
		}
		return null;
	}

	public Card getCard(HttpServletRequest request) {
		Collection testColl = (Collection) request.getSession().getAttribute("testColl");
		Iterator it = testColl.iterator();
		while (it.hasNext()) {
			CardPic cp = (CardPic) it.next();
			return Card.getStaticCard(cp.getCardId());
		}
		return null;
	}

	public int getTestResult(HttpServletRequest request) {
		Collection testColl = (Collection) request.getSession().getAttribute("testColl");
		int r = 0;
		Iterator it = testColl.iterator();
		while (it.hasNext()) {
			CardPic cp = (CardPic) it.next();
			if (cp.result.equals("2"))
				r++;
		}
		return (int) (r * 100.00 / testColl.size());
	}

	public String getSound(String soundType, long languageId) {
		Object soundObjsSucc[] = new SoundDAO().getSoundColl(soundType, languageId);
		return ((Sound) soundObjsSucc[new Random().nextInt(soundObjsSucc.length)]).getSoundUrl();
	}

	public CardPic getCompare(HttpServletRequest request, long picId) {
		Collection testColl = null;

		if (request.getAttribute("reviewFlag") != null) {
			testColl = new CardDAO().getCardPicByCardId(Long.parseLong(request.getAttribute("cardId").toString()));
		} else {
			testColl = (Collection) request.getSession().getAttribute("testColl");
		}
		Object compareArray[] = testColl.toArray();

		// 第一次随机
		CardPic cp = (CardPic) (compareArray[new Random().nextInt(compareArray.length)]);
		if (cp.getPicId() != picId)
			return cp;

		// 第二次随机
		cp = (CardPic) (compareArray[new Random().nextInt(compareArray.length)]);
		if (cp.getPicId() != picId)
			return cp;

		// 第三次随机
		cp = (CardPic) (compareArray[new Random().nextInt(compareArray.length)]);
		if (cp.getPicId() != picId)
			return cp;
		// 第四次遍历
		Iterator it = testColl.iterator();
		while (it.hasNext()) {
			cp = (CardPic) it.next();
			if (cp.getPicId() != picId)
				return cp;
		}

		// 特殊情况
		return null;
	}

}
