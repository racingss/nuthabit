package com.babycard.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
import com.babycard.util.*;

@WebServlet("/diandian/index.html")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static Collection tagColl = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setCharacterEncoding("UTF-8");

			Kehu k = null;
			// 仅供测试
			if (request.getParameter("adon") != null) {
				k = new KehuDAO().getKehuById(2670);
				request.getSession().setAttribute("kehu", k);
				//cookie加入
				Cookie cookie = new Cookie("openId", k.getOpenId());
				cookie.setMaxAge(30 * 24 * 60 * 60);// 设置为30min
				cookie.setPath("/");
				response.addCookie(cookie);
			} else {
				k = new KehuUtil().getKehu(request, response);
			}

			if (k == null) {
				response.sendRedirect("/card/wx_login.jsp");
				return;
			}
			
			if(request.getParameter("languageId")!=null){
				Cookie cookie = new Cookie("languageId", request.getParameter("languageId"));
				cookie.setMaxAge(30 * 24 * 60 * 60);// 设置为30min
				cookie.setPath("/");
				response.addCookie(cookie);
			}

			// 语言切换
			long languageId = new LanguageHttp().getLanguageId(request);
			k.languageId = languageId;

			CardDAO dao = new CardDAO();

			// taglist
			request.setAttribute("tagColl", new TagDAO().getMainTagList());

			// 我的收藏
			request.setAttribute("favColl", dao.getCardListFav(k.getId()));
			// 最新上架
			request.setAttribute("newColl", dao.getCardListByIndex(999));
			// 我创建的
			request.setAttribute("myColl", dao.getCardListByKid(k.getId()));
			// 越近阅读
			CardCookie cookie = new CardCookie();
			request.setAttribute("myRecentColl", cookie.showRecent(request, response));
			// 搜索
			request.setAttribute("searchHistory", new SearchDAO().getSearchBykId(k.getId()));

			request.getRequestDispatcher("/diandian/home.jsp").forward(request, response);
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
