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
import com.babycard.dao.CardMeaningDAO;
import com.babycard.dao.CardPic;
import com.babycard.dao.CardSound;
import com.babycard.dao.CardTag;
import com.babycard.dao.Kehu;
import com.babycard.dao.SoundDAO;
import com.babycard.dao.Study;
import com.babycard.dao.StudyDAO;
import com.babycard.util.BaiduTools;
import com.babycard.util.KehuUtil;
import com.babycard.util.LanguageHttp;
import com.baidu.speech.restapi.common.DemoException;
import com.baidu.speech.restapi.ttsdemo.TtsMain;

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
		Kehu k = new KehuUtil().getKehu(request, response);

		if (k == null) {
			response.sendRedirect("/card/wx_login.jsp");
			return;
		}

		Card c = null;
		CardPic p = null;
		CardDAO dao = new CardDAO();

		// 语言
		long languageId = new LanguageHttp().getLanguageId(request);

		// 文字相关
		if (request.getParameter("meaning") != null && request.getParameter("meaning").length() > 0) {

			// 给单张图片设置文字
			long picId = 0;
			if (request.getParameter("picId") != null) {
				picId = Long.parseLong(request.getParameter("picId"));
			}

			if (picId != 0) {
				new CardMeaningDAO().addCardMeaning(request.getParameter("meaning"), languageId,
						Long.parseLong(request.getParameter("cardId")), Long.parseLong(request.getParameter("picId")));

				String dst = null;
				// 如果是中文重设需要全部翻译
				if (languageId == 0) {
					System.out.println("翻译英文");
					CardMeaningDAO mDAO = new CardMeaningDAO();
					dst = mDAO.buildMeaning(picId, 1);
				}

				// 如果是中英文重设需要抓取语音
				if (languageId == 0 || languageId == 1) {
					System.out.println("抓取中文语音");
					new BaiduTools(picId, 0, Long.parseLong(request.getParameter("cardId")),
							request.getParameter("meaning"), request.getSession().getServletContext().getRealPath("/"))
									.buildMeaning();
					System.out.println("抓取英文语音");
					new BaiduTools(picId, 1, Long.parseLong(request.getParameter("cardId")), dst,
							request.getSession().getServletContext().getRealPath("/")).buildMeaning();
				}

				// 重新初始化
				CardMeaning.meaningColl = null;
				return;
			}

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
