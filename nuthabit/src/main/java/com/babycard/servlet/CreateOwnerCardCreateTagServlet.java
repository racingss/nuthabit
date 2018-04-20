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
import com.babycard.dao.TagDAO;

@WebServlet("/card/create_own_card_create_tag.html")
public class CreateOwnerCardCreateTagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateOwnerCardCreateTagServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		Kehu k = new Kehu();
		k.setId(1);

		if (request.getParameter("tag") == null && request.getParameter("tagId") == null) {
			request.getRequestDispatcher("create_own_card_create_tag.jsp").forward(request, response);
			return;
		}

		CardTag ct = null;
		if (request.getParameter("tagId") == null) {
			ct = new CardTag();
			ct.setKehuId(k.getId());
			ct.setTag(request.getParameter("tag"));
			ct = new CardDAO().addCardTag(ct);
		} else {
			ct = new TagDAO().getTag(Long.parseLong(request.getParameter("tagId")));
		}
		
		request.getSession().setAttribute("cardTag", ct);
		request.setAttribute("popup", "f");

		request.getRequestDispatcher("create_own_card_upload_pic.jsp").forward(request, response);
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
