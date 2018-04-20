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

@WebServlet("/card/create_own_card_add_word.html")
public class CreateOwnerCardAddWordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateOwnerCardAddWordServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		Kehu k = new Kehu();
		k.setId(1);

		Card c = null;
		CardPic p = null;
		CardDAO dao = new CardDAO();

		if (request.getParameter("meaning") != null && request.getParameter("meaning").length() > 0) {
			String picUrl = request.getSession().getAttribute("picurl").toString();
			CardTag ct = (CardTag) request.getSession().getAttribute("cardTag");

			// 新增卡片
			System.out.println("新增卡片：" + request.getParameter("meaning") + picUrl);
			c = dao.addCard(request.getParameter("meaning"), picUrl, ct.getTagId());
			
			
		} else {
			request.getRequestDispatcher("create_own_card_upload_pic.jsp").forward(request, response);
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
