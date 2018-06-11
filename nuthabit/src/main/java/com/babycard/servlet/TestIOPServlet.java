package com.babycard.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
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
import com.babycard.dao.CardTag;
import com.babycard.dao.Kehu;
import com.babycard.dao.Sound;
import com.babycard.dao.SoundDAO;
import com.babycard.dao.Study;
import com.babycard.dao.StudyDAO;
import com.babycard.util.KehuUtil;
import com.babycard.util.LanguageHttp;

@WebServlet("/card/test_iop.html")
public class TestIOPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestIOPServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		Kehu k = new KehuUtil().getKehu(request, response);
		if(k==null){
			response.sendRedirect("/card/wx_login.jsp");
			return;
		}
		

		
		// 语言
		long languageId = new LanguageHttp().getLanguageId(request);

		// 测试初始化
		if (request.getParameter("cardId") != null) {
			long cardId = Long.parseLong(request.getParameter("cardId"));
			Collection testColl = new CardDAO().getCardPicByCardId(cardId);
			request.getSession().setAttribute("testColl", testColl);
			request.getRequestDispatcher("test_cardpic.jsp").forward(request, response);
			return;
		}

		Collection testColl = (Collection) request.getSession().getAttribute("testColl");
		Iterator it = testColl.iterator();
		while (it.hasNext()) {
			CardPic cp = (CardPic) it.next();

			// 处理测试结果
			if (request.getParameter("picId") != null) {
				if (cp.getPicId() == Long.parseLong(request.getParameter("picId"))) {
					cp.result = request.getParameter("result");
					return;
				}

			}
		}

		request.getRequestDispatcher("test_cardpic.jsp").forward(request, response);

		// 屏蔽老版本程序
		if (true)
			return;
		StudyDAO daoStudy = new StudyDAO();
		// 回答
		if (request.getParameter("result") != null) {
			Study sResult = daoStudy.getStudy(k.getId(), Long.parseLong(request.getParameter("cardId")));
			sResult.setReviewDate(null);
			if (request.getParameter("result").equals("s")) {
				sResult.setReviewLevel(sResult.getReviewLevel() + 1);
			}
			daoStudy.update(sResult);
		}

		Study s = daoStudy.getStudy(k.getId());

		CardDAO dao = new CardDAO();
		Card c = dao.getCardByCardId(s.getCardId());
		CardMeaning cm = new CardMeaningDAO().getCardMeaning(c.getCardId(), 0, languageId);

		// 获取卡片图片
		Collection cardColl = dao.getCardPicByCardId(c.getCardId());
		request.setAttribute("card", c);
		request.setAttribute("cm", cm);
		long tagId = dao.getTagId(c.getCardId());
		Card cardTest = dao.getTestCard(tagId, c.getCardId());
		request.setAttribute("cardTest", cardTest);
		request.setAttribute("cardColl", cardColl);
		request.setAttribute("cardCollTest", dao.getCardPicByCardId(cardTest.getCardId()));

		// 获取成功和失败语音，临时
//		Object soundObjsSucc[] = new SoundDAO().getSoundColl("SUCC_CN");
//		String soundSuss = ((Sound) soundObjsSucc[new Random().nextInt(soundObjsSucc.length)]).getSoundUrl();
//
//		Object soundObjs[] = new SoundDAO().getSoundColl("FAILED_CN");
//		String soundFail = ((Sound) soundObjs[new Random().nextInt(soundObjs.length)]).getSoundUrl();
//		request.setAttribute("soundSuss", soundSuss);
//		request.setAttribute("soundFail", soundFail);
		

		request.getRequestDispatcher("test_iop.jsp").forward(request, response);
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
