package com.babycard.dao.batch;

import com.babycard.dao.*;
import java.util.*;

public class CardUpload {

	public static char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
			'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		CardDAO cardDao = new CardDAO();
		CardPicDAO picDAO = new CardPicDAO();
		CardWordDAO wordDAO = new CardWordDAO();

		long preCardId = 0;
		for (int i = 0; i < alphabet.length; i++) {
			System.out.println(alphabet[i]);
			// 添加卡片
			Card c = new Card();
			c.setMeaning(String.valueOf(alphabet[i]));
			c.setSecondPic("diandian/alphabet/background/" + alphabet[i] + ".jpg");
			System.out.println(c.toString());
			c.setPreCardId(preCardId);
			c.setBookId(3);
			if (i < alphabet.length - 1)
				c.setNextCardId(preCardId + 2);
			c = cardDao.addCard(c);
			preCardId = c.getCardId();

			// 添加图片
			CardPic cp = new CardPic();
			cp.setCardId(c.getCardId());
			cp.setCardpic("diandian/alphabet/char/" + alphabet[i] + ".png");
			cp.setTopP(24);
			cp.setLeftP(10);
			cp.setWidthP(50);
			picDAO.addCardPic(cp);

			// 添加字母
			CardWord cw = new CardWord();
			cw.setCardId(c.getCardId());
			cw.setMeaning(String.valueOf(alphabet[i]));
			cw.setTopP(-25);
			cw.setLeftP(40);
			cw.setWidthP(20);
			cw.setSizeP(25);
			wordDAO.addCardWord(cw);

			// 添加文字

		}

	}

}