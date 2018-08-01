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

@WebServlet("/card/cardmanage.html")
public class CardManage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CardManage() {
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

			CardDAO dao = new CardDAO();
			Card c = null;
			// 语言
			long languageId = new LanguageHttp().getLanguageId(request);

			long cardId = 0;
			if (request.getParameter("cardId") != null) {
				cardId = Long.parseLong(request.getParameter("cardId"));
			}

			c = dao.getCardByCardId(cardId);

			if (k.getId() != c.getkId() && k.getGuanlibiaoji() == 0) {
				System.out.println("不是管理员，不能管理这卡片");
				return;
			}

			// 年龄相关
			if (request.getParameter("age") != null) {
				// 更改卡片年龄设置
				new CardDAO().updateCardAge(Long.parseLong(request.getParameter("cardId")),
						Long.parseLong(request.getParameter("age")));
				return;
			}

			// 标签相关
			if (request.getParameter("tagId") != null) {
				// 更改卡片年龄设置
				new CardDAO().updateCardTag(Long.parseLong(request.getParameter("cardId")),
						Long.parseLong(request.getParameter("tagId")));
				return;
			}

			long picId = 0;
			if (request.getParameter("picId") != null) {
				picId = Long.parseLong(request.getParameter("picId"));
			}

			// 删除卡片图片
			if (request.getParameter("delete") != null) {
				new CardPicDAO().deleteCardPic(picId, cardId);
				// 重新初始化
				CardPic.cardPicCOll = new ArrayList();
				response.sendRedirect("cardlist.html?static=t&cardId=" + cardId);
				return;
			}

			// 更新主图
			if (request.getParameter("main") != null) {
				new CardPicDAO().mainCardPic(picId);
				// 重新初始化
				CardPic.cardPicCOll = new ArrayList();
				return;
			}

			// 年龄相关
			if (request.getParameter("age") != null) {
				// 更改卡片年龄设置
				new CardDAO().updateCardAge(Long.parseLong(request.getParameter("cardId")),
						Long.parseLong(request.getParameter("age")));
				return;
			}

			// 删除整套卡片
			if (request.getParameter("trash") != null) {
				dao.deleteCard(cardId, k.getId());
				response.sendRedirect("/diandian/");
				return;
			}

			// 删除声音
			if (request.getParameter("soundId") != null) {
				dao.deleteCardSound(Long.parseLong(request.getParameter("soundId")));
				CardSound.cardSoundColl = new ArrayList();
				return;
			}

			// 自动生成声音
			if (request.getParameter("reflashSound") != null) {
				long distLanguage = Long.parseLong(request.getParameter("distLanguage"));

				CardMeaning cm = new CardMeaningDAO().getCardMeaning(cardId, picId, distLanguage);

				// 如果是中英文重设需要抓取语音
				if (distLanguage == 0 || distLanguage == 1) {
					System.out.println("重新抓取语音了");
					new BaiduTools(picId, distLanguage, cardId, cm.getMeaning(),
							request.getSession().getServletContext().getRealPath("/")).buildMeaning();

				}

				CardSound.cardSoundColl = new ArrayList();
				return;
			}

			// 更新卡片名称
			if (request.getParameter("distLanguage") != null) {
				long distLanguage = Long.parseLong(request.getParameter("distLanguage"));

				new CardMeaningDAO().addCardMeaning(request.getParameter("meaning"), distLanguage,
						Long.parseLong(request.getParameter("cardId")), picId);

				// 如果是中英文重设需要抓取语音
				if (distLanguage == 0 || distLanguage == 1) {
					System.out.println("抓取语音了");
					new BaiduTools(picId, distLanguage, Long.parseLong(request.getParameter("cardId")),
							request.getParameter("meaning"), request.getSession().getServletContext().getRealPath("/"))
									.buildMeaning();
				}

				// 重新初始化
				CardMeaning.meaningColl = null;
			}

			// 更新卡片教学示例
			if (request.getParameter("detail") != null) {
				dao.updateCardDetail(cardId, request.getParameter("detail"));
				return;
			}

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
