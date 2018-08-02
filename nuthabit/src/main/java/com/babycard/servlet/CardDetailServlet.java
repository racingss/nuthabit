package com.babycard.servlet;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.babycard.dao.*;
import com.babycard.util.*;

/**
 * Servlet implementation class CardDetailServlet
 */
@WebServlet("/card/carddetail.html")
public class CardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CardDetailServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Kehu k = new KehuUtil().getKehu(request, response);
		if (k == null) {
			response.sendRedirect("/card/wx_login.jsp");
			return;
		}

		CardDAO dao = new CardDAO();
		long cardId = 0;
		if (request.getParameter("cardId") != null) {
			cardId = Long.parseLong(request.getParameter("cardId"));
		}
		
		if (request.getParameter("status") != null) {
			if (k.getGuanlibiaoji() == 1) {
				new CardDAO().updateCardStatus(cardId, Long.parseLong(request.getParameter("status")));
			} else {
				System.out.println("为什么会有人来改：" + cardId);
			}
		}


		Card c = dao.getCardByCardId(cardId);
		if (c.getkId() != k.getId() && k.getGuanlibiaoji() == 0) {
			System.out.println("不是本人，不能操作");
			return;
		}

		
		if (request.getParameter("cleansound") != null) {
			new CardPicDAO().updateSound(Long.parseLong(request.getParameter("picId")), "");
		}

		// 获取卡片图片
		Collection cardColl = dao.getCardPicByCardId(c.getCardId());

		c.cardMeaningColl = new CardMeaningDAO().getCardMeaning(c.getCardId());
		c.cardSoundColl = dao.getCardSoundList(c.getCardId(), k.getId());

		request.setAttribute("card", c);
		request.setAttribute("cardColl", cardColl);
		request.getRequestDispatcher("carddetail.jsp").forward(request, response);

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
