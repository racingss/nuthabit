package com.babycard.servlet;

import java.io.IOException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.babycard.dao.*;
import com.babycard.util.*;

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

			Kehu k = new KehuUtil().getKehu(request, response);
			if (k == null) {
				// 增加记录CardId便于登录后直接转跳
				if (request.getParameter("cardId") != null) {
					request.getSession().setAttribute("cardId", request.getParameter("cardId"));
				}
				response.sendRedirect("/card/wx_login.jsp");
				return;
			}

			// 显示文字
			if (request.getParameter("wordFlag") != null) {
				request.getSession().setAttribute("wordFlag", request.getParameter("wordFlag"));
				return;
			}

			// 播放语音
			if (request.getParameter("soundFlag") != null) {
				request.getSession().setAttribute("soundFlag", request.getParameter("soundFlag"));
				return;
			}

			// 双语设置
			if (request.getParameter("secondFlag") != null) {
				request.getSession().setAttribute("secondFlag", request.getParameter("secondFlag"));
				return;
			}

			CardDAO dao = new CardDAO();
			long cardId = 0;
			if (request.getParameter("cardId") != null) {
				cardId = Long.parseLong(request.getParameter("cardId"));
			}

			// tutorial
//			if (!new TutorialDAO().isTutorial(k.getId(), Tutorial.CARDPLAY)) {
//				Tutorial t = new Tutorial();
//				t.setkId(k.getId());
//				t.setTutorialType(Tutorial.CARDPLAY);
//				new TutorialDAO().add(t);
//				request.getRequestDispatcher("tutorial.jsp").forward(request, response);
//				return;
//			}

			long picId = 0;
			if (request.getParameter("picId") != null) {
				picId = Long.parseLong(request.getParameter("picId"));
				// 收藏卡片和图片
				if (request.getParameter("fav") != null) {
					new CardPicDAO().favCardPic(picId, cardId);
				}

			}

			// 搜藏
			if (request.getParameter("favcard") != null) {
				Fav f = new Fav();
				f.setCardId(cardId);
				f.setKehuId(k.getId());

				new FavDAO().addFav(f);
				return;
			}
			// 取消收藏
			if (request.getParameter("favdelete") != null) {
				new FavDAO().closeFav(k.getId(), cardId);
				return;
			}

			// 评分
			if (request.getParameter("score") != null) {
				new CardScoreDAO().add(cardId, k.getId(), Long.parseLong(request.getParameter("score")));
				if (Long.parseLong(request.getParameter("score")) == 3) {
					Fav f = new Fav();
					f.setCardId(cardId);
					f.setKehuId(k.getId());
					new FavDAO().addFav(f);
				}
				return;
			}

			// 评论
			if (request.getParameter("comm") != null) {
				new CardCommDAO().add(cardId, k.getId(), request.getParameter("comm"));
				return;
			}

			

			// 语言
			long languageId = new LanguageHttp().getLanguageId(request);

			Card c = null;

			if (request.getParameter("chinese") != null && request.getParameter("chinese").trim().length() > 0) {
				// 中文
				CardMeaning cm = new CardMeaning();
				cm.setCardId(cardId);
				cm.setLanguageId(0);
				cm.setMeaning(request.getParameter("chinese"));
				dao.addCardMeaning(cm);
			}

			if (request.getParameter("english") != null && request.getParameter("english").trim().length() > 0) {
				// 英文
				CardMeaning cm = new CardMeaning();
				cm.setCardId(cardId);
				cm.setLanguageId(1);
				cm.setMeaning(request.getParameter("english"));
				dao.addCardMeaning(cm);
			}

			if (request.getParameter("next") != null) {
				c = dao.getNextByCardId(cardId);
			} else if (request.getParameter("pre") != null) {
				c = dao.getPreByCardId(cardId);
			} else {
				c = dao.getCardByCardId(cardId);
			}

			request.setAttribute("isFav", new FavDAO().isFav(k.getId(), c.getCardId()));
			
			// 扣除积分
			if (c.getkId() != k.getId() && request.getParameter("replay") == null) {
				KehuCardMember m = new KehuDAO().getMember(k.getKehuId());
				if (m == null || m.getCloseDate().getTime() < System.currentTimeMillis()) {
					if (!new KehuDAO().updateJifen(k.getId(), 1, false, "阅读：" + c.getCardId())) {
						response.sendRedirect("/diandian/subscribe.html");
						return;
					}
				}
			}

			// 获取卡片图片
			Collection cardColl = new CardPicDAO().getCardPicByCardId(c.getCardId());

			// 记录用户学习状态,可能会被废除
			Study s = new Study();
			s.setCardId(c.getCardId());
			s.setCreateDate(new Timestamp(System.currentTimeMillis()));
			s.setCustomerId(k.getId());
			s.setLanguageId(languageId);

			new StudyDAO().addStudy(s, cardColl);

			// 记录在cookie中
			// CardCookie cookie = new CardCookie();
			// cookie.add(request, response, c.getCardId());

			// 记录卡片阅读历史
			History h = new History();
			h.setCardId(c.getCardId());
			h.setKId(k.getId());
			new HistoryDAO().addHistory(h);

			if (cardColl.size() > 0) {
				c.cardMeaningColl = new CardMeaningDAO().getCardMeaning(c.getCardId());
				c.cardSoundColl = dao.getCardSoundList(c.getCardId(), k.getId());
				request.setAttribute("card", c);
				request.setAttribute("cardColl", cardColl);
				if (request.getParameter("page") != null) {
					request.getRequestDispatcher("cardplay1.jsp").forward(request, response);
				} else if (request.getParameter("test") != null) {
					request.getRequestDispatcher(request.getParameter("test")).forward(request, response);
				} else if (c.getShowType() == 1) {
					request.getRequestDispatcher("/diandian/cognitiveboard.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("/diandian/cognitiveboard.jsp").forward(request, response);
					//request.getRequestDispatcher("cardplay.jsp").forward(request, response);
				}
			} else {
				response.sendRedirect("carddetail.html?cardId=" + c.getCardId());
				return;
			}
		} catch (Exception e) {
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
