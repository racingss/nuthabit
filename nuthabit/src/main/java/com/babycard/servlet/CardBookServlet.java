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

@WebServlet("/card/cardbook.html")
public class CardBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CardBookServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 初始化

		try {
			request.setCharacterEncoding("UTF-8");

			Kehu k = new KehuUtil().getKehu(request, response);
			if (k == null) {
				response.sendRedirect("/card/wx_login.jsp");
				return;
			}

			if (k.getGuanlibiaoji() < 1) {
				System.out.println("没有权限");
				return;
			}

			long cardId = 0;
			if (request.getParameter("cardId") != null) {
				cardId = Long.parseLong(request.getParameter("cardId"));
			}

			Book b = new BookDAO().getBookByFirstCardId(cardId);
			request.setAttribute("book", b);

			Collection cardColl = new CardDAO().getBookCardList(new ArrayList(), cardId);
			request.setAttribute("cardColl", cardColl);

			request.getRequestDispatcher("/card/bookcard.jsp").forward(request, response);

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
