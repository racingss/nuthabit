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
			response.setCharacterEncoding("UTF-8");
			long cardId = Long.parseLong(request.getParameter("cardId"));

			CardPicDAO dao = new CardPicDAO();
			CardPic cp = null;

			if (request.getParameter("topP") != null) {
				long picId = Long.parseLong(request.getParameter("picId"));
				long topP = Long.parseLong(request.getParameter("topP"));
				dao.updatePicPositionTop(picId, topP);
			}

			if (request.getParameter("leftP") != null) {
				long picId = Long.parseLong(request.getParameter("picId"));
				long leftP = Long.parseLong(request.getParameter("leftP"));
				dao.updatePicPositionLeft(picId, leftP);
			}

			if (request.getParameter("widthP") != null) {
				long widthP = Long.parseLong(request.getParameter("widthP"));
				if (request.getParameter("picId") != null) {
					long picId = Long.parseLong(request.getParameter("picId"));
					dao.updatePicPositionWidth(picId, widthP);
				} else {
					dao.updatePicPositionWidthByCardId(cardId, widthP);
				}
			}

			if (request.getParameter("marginTop") != null) {
				long marginTop = Long.parseLong(request.getParameter("marginTop"));
				if (request.getParameter("picId") != null) {
					long picId = Long.parseLong(request.getParameter("picId"));
					dao.updatePicPositionMarginTop(picId, marginTop);
				} else {
					dao.updatePicPositionMarginTopByCardId(cardId, marginTop);
				}
			}

			if (request.getParameter("marginTop2") != null) {
				long marginTop2 = Long.parseLong(request.getParameter("marginTop2"));
				if (request.getParameter("picId") != null) {
					long picId = Long.parseLong(request.getParameter("picId"));
					dao.updatePicPositionMarginTop2(picId, marginTop2);
				} else {
					dao.updatePicPositionMarginTop2ByCardId(cardId, marginTop2);
				}
			}

			Collection cardColl = new CardPicDAO().getCardPicByCardId(cardId);

			if (request.getParameter("init") != null)
				new ShuyishuUtil().getCoordinate(cardColl, 70, 70, 10);

			request.setAttribute("cardColl", cardColl);

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
