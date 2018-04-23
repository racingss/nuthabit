package com.babycard.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.babycard.dao.Card;
import com.babycard.dao.CardDAO;
import com.babycard.dao.CardMeaning;
import com.babycard.dao.CardMeaningDAO;
import com.babycard.dao.CardPic;
import com.babycard.dao.CardTag;
import com.babycard.dao.Kehu;
import com.babycard.dao.Study;
import com.babycard.dao.StudyDAO;

@WebServlet("/card/cardlist.html")
public class CardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CardListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setCharacterEncoding("UTF-8");

			Kehu k = new Kehu();
			k.setId(1);

			CardDAO dao = new CardDAO();
			long cardId = 0;
			if (request.getParameter("cardId") != null) {
				cardId = Long.parseLong(request.getParameter("cardId"));
			}

			long picId = 0;
			if (request.getParameter("picId") != null) {
				picId = Long.parseLong(request.getParameter("picId"));
				// 删除卡片图片
				if (request.getParameter("delete") != null) {
					dao.deleteCardPic(picId, cardId);
				}
				// 收藏卡片和图片
				if (request.getParameter("fav") != null) {
					dao.favCardPic(picId, cardId);
				}
			}

			
			
			long cardIndex = -1;
			if (request.getParameter("cardIndex") != null) {
				cardIndex = Long.parseLong(request.getParameter("cardIndex"));
			}

			// 删除声音
			if (request.getParameter("soundId") != null) {
				dao.deleteCardSound(Long.parseLong(request.getParameter("soundId")));
			}

			// 语言切换
			long languageId = 0;
			if (request.getSession().getAttribute("languageId") != null) {
				languageId = Long.parseLong(request.getSession().getAttribute("languageId").toString());
			}
			if (request.getParameter("languageId") != null) {
				languageId = Long.parseLong(request.getParameter("languageId"));
				request.getSession().setAttribute("languageId", languageId);
			}

			// 标签切换
			long tagId = 0;
			if (request.getSession().getAttribute("tagId") != null) {
				tagId = Long.parseLong(request.getSession().getAttribute("tagId").toString());
			}
			if (request.getParameter("tagId") != null) {
				tagId = Long.parseLong(request.getParameter("tagId"));
				request.getSession().setAttribute("tagId", tagId);
			}

			Card c = null;
			boolean staticFlag = false;
			if (request.getParameter("static") != null)
				staticFlag = true;

			if (request.getParameter("chinese") != null && request.getParameter("chinese").trim().length() > 0) {
				// 中文
				CardMeaning cm = new CardMeaning();
				cm.setCardId(cardId);
				cm.setLanguageId(0);
				cm.setMeaning(request.getParameter("chinese"));
				dao.addCardMeaning(cm);
				staticFlag = true;
			}

			if (request.getParameter("english") != null && request.getParameter("english").trim().length() > 0) {
				// 英文
				CardMeaning cm = new CardMeaning();
				cm.setCardId(cardId);
				cm.setLanguageId(1);
				cm.setMeaning(request.getParameter("english"));
				dao.addCardMeaning(cm);
				staticFlag = true;
			}

			if (staticFlag) {
				c = dao.getCardByCardId(cardId);
			} else {
				c = dao.getCardByCardIndex(cardIndex, request.getParameter("flag"), tagId);
			}

			// 获取卡片
			if (c == null) {
				request.getRequestDispatcher("cardbytag.html?tagId="+tagId).forward(request, response);
				return;
			}

			// 记录用户学习状态
			Study s = new Study();
			s.setCardId(c.getCardId());
			s.setCreateDate(new Timestamp(System.currentTimeMillis()));
			s.setCustomerId(k.getId());
			new StudyDAO().addStudy(s);

			// 获取卡片图片
			Collection cardColl = dao.getCardPicByCardId(c.getCardId());
			if (cardColl.size() > 0) {
				c.cardMeaningColl = new CardMeaningDAO().getCardMeaning(c.getCardId());
				c.cardSoundColl = dao.getCardSoundList(c.getCardId(), k.getId());
				request.setAttribute("card", c);
				request.setAttribute("cardColl", cardColl);
				request.getRequestDispatcher("card.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("cardbytag.html?tagId="+tagId).forward(request, response);
				return;
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
