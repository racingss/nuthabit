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

@WebServlet("/diandian/book.html")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setCharacterEncoding("UTF-8");

			Kehu k = new KehuUtil().getKehu(request, response);
			if (k == null) {
				// 增加记录bookId便于登录后直接转跳
				if (request.getParameter("bookId") != null) {
					request.getSession().setAttribute("bookId", request.getParameter("bookId"));
				}
				response.sendRedirect("/card/wx_login.jsp");
				return;
			}

			BookDAO bookdao = new BookDAO();
			long bookId = 0;
			if (request.getParameter("bookId") != null) {
				bookId = Long.parseLong(request.getParameter("bookId"));
			}

			Book b = bookdao.getBookById(bookId);
			request.setAttribute("book", b);

			Card c = null;
			CardDAO dao = new CardDAO();
			request.setAttribute("bookColl", dao.getCardListByBookId(b.getBookId()));

			/*
			 * long cardId = 0; if (request.getParameter("cardId") != null) {
			 * cardId = Long.parseLong(request.getParameter("cardId"));
			 * 
			 * } else { cardId = b.getFirstCardId(); }
			 * 
			 * c = dao.getCardByCardId(cardId); request.setAttribute("card", c);
			 * request.setAttribute("cardColl", new
			 * CardPicDAO().getCardPicByCardId(cardId));
			 * request.setAttribute("wordColl", new
			 * CardWordDAO().getCardWordByCardId(cardId)); //临时
			 * request.setAttribute("isFav", 0);
			 */

			request.getRequestDispatcher("/diandian/book.jsp").forward(request, response);

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
