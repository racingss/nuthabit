package com.babycard.dao.batch;

import com.babycard.dao.*;
import java.util.*;

public class CardUpload {

	public static char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
			'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	private static void firstabc() {
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		newBookCard("猫陷阱",13,"maoxianjin");

	}

	private static void newBookCard(String bookName,int cardNums,String bookUrl) {
		Book b = new Book();
		b.setBookName(bookName);
		b.setCardNums(cardNums);
		b.setDefaultPic("diandian/bookimg/"+bookUrl+"/1.jpg");
		b.setCover("diandian/bookimg/"+bookUrl+".jpg");
		new BookDAO().addBook(b);
		b = new BookDAO().getBookByBookName(bookName);
		
		long preCardId = 0;
		long nextCardId = 0;
		for (int i = 1; i <= cardNums; i++) {
			Card c = new Card();
			c.setMeaning(bookName+"（" + i + "）");
			c.setCardIndex(i);
			//c.setDefaultPic("diandian/bookimg/whoami/" + i + ".png");
			c.setBookId(b.getBookId());
			c.setSecondPic("diandian/bookimg/"+bookUrl+"/" + i + ".jpg");
			c.setPreCardId(preCardId);
			c.setNextCardId(nextCardId);
			c = new CardDAO().addCard(c);
			System.out.println(c.toString());
			preCardId = c.getCardId();
			nextCardId = c.getCardId() + 2;
		}
	}

}
