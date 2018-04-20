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
import com.babycard.dao.CardPic;
import com.babycard.dao.CardTag;
import com.babycard.dao.Kehu;
import com.babycard.dao.Study;
import com.babycard.dao.StudyDAO;

@WebServlet("/card/addcardpic.html")
public class AddCardPicAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCardPicAjaxServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		Kehu k = new Kehu();
		k.setId(1);

		CardDAO dao = new CardDAO();
		long cardId = 0;
		if (request.getParameter("cardId") != null) {
			cardId = Long.parseLong(request.getParameter("cardId"));
		}

		dao.addCardPic(cardId, request.getParameter("displayurl"), request.getParameter("weburl"));
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
