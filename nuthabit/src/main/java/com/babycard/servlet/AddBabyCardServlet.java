package com.babycard.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/card/addbabycard.html")
public class AddBabyCardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddBabyCardServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		Card c = null;
		CardPic p = null;
		CardDAO dao = new CardDAO();

		if (request.getParameter("cardId") == null) {
			if (request.getParameter("meaning") == null || request.getParameter("meaning").trim().length() < 1) {
				return;
			}

			// 新增卡片
			c = dao.addCard(request.getParameter("meaning"));
			// 新增卡片图片
			dao.addCardPic(c.getCardId(), request.getParameter("cardpic"));
			// 新增卡片含义
			CardMeaning m = new CardMeaning();
			m.setLanguageId(0);
			m.setMeaning(request.getParameter("meaning"));
			m.setCardId(c.getCardId());
			dao.addCardMeaning(m);
		} else {
			c = dao.getCardByCardId(Long.parseLong(request.getParameter("cardId")));
		}

		// 上传或修改标签
		try {
			if (request.getParameter("tag") != null) {
				StringTokenizer st = new StringTokenizer(request.getParameter("tag"));
				while (st.hasMoreElements()) {
					String tag = st.nextToken();
					if (tag.trim().length() < 1)
						continue;

					CardTag cardtag = dao.addCardTag(tag);
					dao.BindCardTag(c.getCardId(), cardtag.getTagId());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("/card/cardlist.html?static=t&cardId=" + c.getCardId());
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
