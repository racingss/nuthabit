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

@WebServlet("/diandian/piclist.html")
public class PicListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PicListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	long languageId = 0;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		languageId = new LanguageHttp().getLanguageId(request);
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		Kehu k = new KehuUtil().getKehu(request, response);
		if (k == null) {
			response.sendRedirect("/card/wx_login.jsp");
			return;
		}

		try {

			long page = 1;
			if (request.getParameter("page") != null) {
				page = Long.parseLong(request.getParameter("page"));
			}

			long level = 1;
			if (k.b != null)
				level = k.b.getAge();
			if (request.getParameter("level") != null) {
				level = Long.parseLong(request.getParameter("level"));
			}

			Collection searchResult = new ArrayList();

			if (request.getParameter("tagId") != null) {
				// 根据分类
				searchResult = new CardDAO().getCardListByTag(Long.parseLong(request.getParameter("tagId")), page);
			} else if (request.getParameter("qString") != null && request.getParameter("qString").length() > 0) {
				// 根据关键字
				String qString = request.getParameter("qString");
				searchResult = new CardDAO().searchCard(qString, page);

				// 记录搜索
				Search s = new Search();
				s.setkId(k.getId());
				s.setqString(qString);
				s.setrCount(searchResult.size());
				new SearchDAO().addSearch(s);
			} else if (request.getParameter("pop") != null) {
				// 最受欢迎
				searchResult = new CardDAO().getAllCardListOrderByFavCount(page);

			} else if (request.getParameter("new") != null) {
				// 最新
				searchResult = new CardDAO().getAllCardListOrderByNew(page);
			} else {
				// 根据级别
				searchResult = new CardDAO().getCardListByAge(level, page);
			}

			if (page != 1) {
				returnAjax(response, searchResult);
				return;
			}

			request.setAttribute("result", searchResult);
			request.getRequestDispatcher("piclist.jsp").forward(request, response);
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

	private void returnAjax(HttpServletResponse response, Collection searchResult) throws IOException {
		if (searchResult.size() == 0) {
			response.getWriter().append("0");
			return;
		}

		Iterator it = searchResult.iterator();
		StringBuffer sb = new StringBuffer();
		while (it.hasNext()) {
			Card c = (Card) it.next();
			sb.append("<a href=\"/card/cardlist.html?cardId=");
			sb.append(c.getCardId());
			sb.append("\">");
			sb.append("<div class=\"everycard\"><div class=\"cardhead\"><img src=\"");
			sb.append(c.getImg());
			sb.append("\" class=\"cardimg cardsub\" cardId=\"");
			sb.append(c.getCardId());
			sb.append("\"><br/>");
			sb.append(c.getMeaning(languageId, c.getCardId()));
			sb.append("</div><div class=\"carddetail\">");

			Collection picColl = new CardPicDAO().getCardPicByCardId(c.getCardId());
			Iterator picIt = picColl.iterator();
			while (picIt.hasNext()) {
				CardPic cp = (CardPic) picIt.next();
				sb.append("<div class=\"picdetail\"><div class=\"pichead\"><img src=\"");
				sb.append(cp.getImgurl());
				sb.append("\" class=\"picimg  cardsub\" cardId=\"");
				sb.append(c.getCardId());
				sb.append("\"></div><div class=\"picword\">");
				sb.append(CardMeaning.getStaticCard(cp.getPicId(), languageId).getMeaning());
				sb.append("</div><div class=\"picselect\"></div></div>");
			}
			sb.append("</div></div>");
			sb.append("</a>");
		}
		response.getWriter().append(sb.toString());
	}

}
