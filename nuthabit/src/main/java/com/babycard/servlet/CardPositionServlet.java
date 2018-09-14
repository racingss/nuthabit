package com.babycard.servlet;

import java.io.IOException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.babycard.dao.*;
import com.babycard.util.*;

@WebServlet("/diandian/cardposition.html")
public class CardPositionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CardPositionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 初始化

		try {
			request.setCharacterEncoding("UTF-8");

			CardPicDAO dao = new CardPicDAO();
			CardPic cp = null;

			long cardId = 0;
			if (request.getParameter("cardId") != null)
				cardId = Long.parseLong(request.getParameter("cardId"));

			long picId = 0;
			if (request.getParameter("picId") != null) {
				picId = Long.parseLong(request.getParameter("picId"));
				if (cardId == 0) {
					cp = dao.getCardPicBypicId(picId);
					cardId = cp.getCardId();
				}
			}

			long wordId = 0;
			if (request.getParameter("wordId") != null) {
				wordId = Long.parseLong(request.getParameter("wordId"));
				if (cardId == 0) {
					cardId = new CardWordDAO().getCardWordByWordId(wordId).getCardId();
				}
			}

			if (request.getParameter("topP") != null) {
				long topP = Long.parseLong(request.getParameter("topP"));
				dao.updatePicPositionTop(picId, topP);
			}

			if (request.getParameter("leftP") != null) {
				long leftP = Long.parseLong(request.getParameter("leftP"));
				dao.updatePicPositionLeft(picId, leftP);
			}

			if (request.getParameter("widthP") != null) {
				long widthP = Long.parseLong(request.getParameter("widthP"));
				if (request.getParameter("picId") != null) {
					dao.updatePicPositionWidth(picId, widthP);
				} else {
					dao.updatePicPositionWidthByCardId(cardId, widthP);
				}
			}

			if (request.getParameter("marginTop") != null) {
				long marginTop = Long.parseLong(request.getParameter("marginTop"));
				if (request.getParameter("picId") != null) {
					dao.updatePicPositionMarginTop(picId, marginTop);
				} else {
					dao.updatePicPositionMarginTopByCardId(cardId, marginTop);
				}
			}

			if (request.getParameter("marginTop2") != null) {
				long marginTop2 = Long.parseLong(request.getParameter("marginTop2"));
				if (request.getParameter("picId") != null) {
					dao.updatePicPositionMarginTop2(picId, marginTop2);
				} else {
					dao.updatePicPositionMarginTop2ByCardId(cardId, marginTop2);
				}
			}

			if (request.getParameter("meaning") != null && request.getParameter("meaning").trim().length() > 0) {
				new CardWordDAO().addCardWord(cardId, request.getParameter("meaning"));
				response.sendRedirect("/diandian/cardposition.html?cardId=" + cardId);
				return;
			}

			if (request.getParameter("deleteWordId") != null) {
				new CardWordDAO().deleteCardWord(Long.parseLong(request.getParameter("deleteWordId")));
			}

			if (request.getParameter("wordId") != null) {
				CardWord cw = new CardWordDAO().getCardWordByWordId(Long.parseLong(request.getParameter("wordId")));
				if (request.getParameter("leftW") != null)
					cw.setLeftP(Long.parseLong(request.getParameter("leftW")));
				if (cw.getLeftP() > 100) {
					new CardWordDAO().deleteCardWord(cw.getWordId());
				} else {
					if (request.getParameter("topW") != null)
						cw.setTopP(Long.parseLong(request.getParameter("topW")));
					if (request.getParameter("widthW") != null)
						cw.setWidthP(Long.parseLong(request.getParameter("widthW")));
					if (request.getParameter("sizeW") != null)
						cw.setSizeP(Double.parseDouble(request.getParameter("sizeW")));
					new CardWordDAO().updateCardWord(cw);
				}
			}

			Collection cardColl = new CardPicDAO().getCardPicByCardId(cardId);

			if (request.getParameter("init") != null)
				new ShuyishuUtil().getCoordinate(cardColl, 70, 70, 10);

			request.setAttribute("cardColl", cardColl);
			request.setAttribute("wordColl", new CardWordDAO().getCardWordByCardId(cardId));
			request.setAttribute("card", new CardDAO().getCardByCardId(cardId));

			request.getRequestDispatcher("cognitiveboardEdit.jsp").forward(request, response);

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
