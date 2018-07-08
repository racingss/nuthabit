package com.babycard.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import com.babycard.dao.*;
import com.babycard.util.KehuUtil;
import com.babycard.util.LanguageHttp;

@WebServlet("/diandian/search.html")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static Collection tagColl = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");

			Kehu k = new KehuUtil().getKehu(request, response);
			if (k == null) {
				response.sendRedirect("/card/wx_login.jsp");
				return;
			}

			long languageId = new LanguageHttp().getLanguageId(request);
			long page = 1;
			if (request.getParameter("page") != null)
				page = Long.parseLong(request.getParameter("page"));

			String qString = null;
			if (request.getSession().getAttribute("qString") != null)
				qString = request.getSession().getAttribute("qString").toString();
			if (request.getParameter("qString") != null) {
				qString = request.getParameter("qString");
				request.setAttribute("qString", qString);
				request.getSession().setAttribute("qString", qString);
			}

			// 根据类别
			if (request.getParameter("tagId") != null) {
				request.getSession().removeAttribute("qString");
				CardTag t = CardTag.getCartTagByTagId(Long.parseLong(request.getParameter("tagId")));
				request.setAttribute("qString", Menu.getMenu("tag_" + t.getTagId(), languageId));

				Collection searchResult = new CardDAO().getCardListByTag(Long.parseLong(request.getParameter("tagId")),
						page);

				if (page != 1) {
					returnAjax(response, searchResult);
				} else {
					request.setAttribute("searchResult", searchResult);
					request.getRequestDispatcher("searchResult.jsp").forward(request, response);
				}

				return;
			}

			// 根据年龄
			if (request.getParameter("age") != null) {
				request.getSession().removeAttribute("qString");
				request.setAttribute("qString", Menu.getMenu("age_" + request.getParameter("age"), languageId));
				Collection searchResult = new CardDAO().getCardListByAge(Long.parseLong(request.getParameter("age")),
						page);
				if (page != 1) {
					returnAjax(response, searchResult);
				} else {
					request.setAttribute("searchResult", searchResult);
					request.getRequestDispatcher("searchResult.jsp").forward(request, response);
				}

				return;
			}

			// 根据内容
			if (qString != null) {
				request.setAttribute("qString", qString);

				Collection searchResult = null;
				long subAge = 0;
				if (request.getParameter("subAge") != null) {
					subAge = Long.parseLong(request.getParameter("subAge"));
					searchResult = new CardDAO().searchCard(qString, page,subAge);
				} else {
					searchResult = new CardDAO().searchCard(qString, page);
				}

				// 记录搜索
				Search s = new Search();
				s.setkId(k.getId());
				s.setqString(qString);
				s.setrCount(searchResult.size());
				new SearchDAO().addSearch(s);

				if (page != 1) {
					returnAjax(response, searchResult);
				} else {
					request.setAttribute("searchResult", searchResult);
					request.getRequestDispatcher("searchResult.jsp").forward(request, response);
				}

				return;
			} else {
				request.getRequestDispatcher("home.jsp").forward(request, response);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			sb.append("\"><img src=\"");
			sb.append(c.getImg());
			sb.append("\"><i class=\"i");
			sb.append(c.getAge());
			sb.append(c.getAge() + 1);
			sb.append("\">");
			sb.append(c.getAge());
			sb.append("~");
			sb.append(c.getAge() + 1);
			sb.append("</i></a>");
		}
		response.getWriter().append(sb.toString());
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
