package com.babycard.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
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
import com.babycard.dao.CardPicDAO;
import com.babycard.dao.CardTag;
import com.babycard.dao.Daka;
import com.babycard.dao.DakaDAO;
import com.babycard.dao.Kehu;
import com.babycard.dao.Menu;
import com.babycard.dao.Search;
import com.babycard.dao.SearchDAO;
import com.babycard.dao.Study;
import com.babycard.dao.StudyDAO;
import com.babycard.dao.TagDAO;
import com.babycard.util.KehuUtil;
import com.babycard.util.LanguageHttp;

@WebServlet("/diandian/picsearch.html")
public class PicSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PicSearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	long languageId = 0;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		languageId = new LanguageHttp().getLanguageId(request);
		

		Kehu k = new KehuUtil().getKehu(request, response);
		if (k == null) {
			response.sendRedirect("/card/wx_login.jsp");
			return;
		}
		
		if(request.getParameter("qString")==null || request.getParameter("qString").trim().length()<1){
			return;
		}

		
		System.out.println(request.getParameter("qString"));
		
		try {

			Collection searchResult = new CardPicDAO()
					.getCardPicCollectionByCardmeaning(request.getParameter("qString"));

			
			request.setAttribute("result", searchResult);
			request.getRequestDispatcher("picsearch.jsp").forward(request, response);
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
