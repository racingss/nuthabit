package com.babycard.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.babycard.dao.CardPic;
import com.babycard.dao.CardPicDAO;
import com.babycard.dao.Kehu;
import com.babycard.dao.Menu;
import com.babycard.dao.Study;
import com.babycard.dao.StudyDAO;
import com.babycard.util.KehuUtil;
import com.babycard.util.LanguageHttp;

@WebServlet("/card/review.html")
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReviewServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {

			Kehu k = new KehuUtil().getKehu(request, response);
			if (k == null) {
				response.sendRedirect("/card/wx_login.jsp");
				return;
			}
			long languageId = new LanguageHttp().getLanguageId(request);

			StudyDAO daoStudy = new StudyDAO();

			if (request.getParameter("delete") != null) {
				daoStudy.deleteStudyByCustomerIdPicId(k.getId(), Long.parseLong(request.getParameter("picId")),languageId);
			}

			// 复习队列
			Collection testColl = new ArrayList();
			request.getSession().setAttribute("testColl", testColl);

			// 回答
			if (request.getParameter("result") != null) {
				Study sResult = daoStudy.getStudyByCustomerIdPicId(k.getId(),
						Long.parseLong(request.getParameter("picId")),languageId);
				sResult.setReviewDate(null);
				if (request.getParameter("result").equals("2")) {
					sResult.setReviewLevel(sResult.getReviewLevel() + 1);
				} else {
					if (sResult.getReviewLevel() > 0)
						sResult.setReviewLevel(sResult.getReviewLevel() - 1);

				}
				sResult.setReviewFlag(0);
				daoStudy.update(sResult);
				return;
			}

			// 取得一条需要复习的记录
			Study s = daoStudy.getStudy(k.getId(), 0,languageId);
			if (s != null) {
				CardPic cp = new CardPicDAO().getCardPicBypicId(s.getPicId());
				request.setAttribute("test", cp);
				request.getRequestDispatcher("/card/test_review.jsp").forward(request, response);
				return;
			}

			// 2、测试

			s = daoStudy.getStudy(k.getId(), 1,languageId);
			if (s != null) {
				CardPic cp = new CardPicDAO().getCardPicBypicId(s.getPicId());
				request.setAttribute("test", cp);
				testColl.add(cp);
				request.setAttribute("reviewFlag", "t");
				request.setAttribute("cardId", cp.getCardId());
				request.getRequestDispatcher("/card/test_cardpic.jsp").forward(request, response);
				return;
			}

			// 3、提示下次测试时间
			String nextTime = daoStudy.getStudyNextTime(k.getId(),languageId);
			String temp = new Timestamp(System.currentTimeMillis()).toString().substring(0, 10);
			if (nextTime != null) {
				if (nextTime.indexOf(temp) != -1) {
					nextTime = Menu.getMenu("today", languageId) + nextTime.substring(11, 16);
				} else {
					nextTime = nextTime.substring(0, 16);
				}
				request.setAttribute("msgDetail", Menu.getMenu("next_review_time", languageId) + ":<br/>" + nextTime);
			} else {
				request.setAttribute("msgDetail", Menu.getMenu("read_first", languageId));
			}

			request.setAttribute("msgTitle", Menu.getMenu("no_need_review", languageId));
			request.getRequestDispatcher("/diandian/message.jsp").forward(request, response);
			return;

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
