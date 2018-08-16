package com.babycard.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
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
import com.babycard.dao.CardTag;
import com.babycard.dao.Daka;
import com.babycard.dao.DakaDAO;
import com.babycard.dao.Kehu;
import com.babycard.dao.Study;
import com.babycard.dao.StudyDAO;
import com.babycard.dao.TagDAO;
import com.babycard.util.KehuUtil;

@WebServlet("/diandian/daka.html")
public class DakaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DakaServlet() {
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

		long kId = k.getId();
		DakaDAO dao = new DakaDAO();
		Daka d = dao.get(kId);
		if (d == null) {
			// 第一次打卡
			dao.add(kId);
			System.out.println("第一次打卡");
		} else {
			// 判断打卡能否连续
			Calendar c1 = Calendar.getInstance();
			c1.setTime(new Date());
			c1.add(c1.DAY_OF_MONTH, -1);
			Date yesterday = c1.getTime();
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			String yes = format1.format(c1.getTime());

			if (yes.equals(d.getDakaTime().toString().substring(0, 10))) {
				// 可以打卡
				dao.add(kId);
				System.out.println("打卡了：" + (d.getDakaNum() + 1));
			} else {
				// 超时无法打卡ß
				System.out.println("不能打卡");
			}
		}
		
		request.setAttribute("daka", dao.get(kId));
		request.getRequestDispatcher("daka.jsp").forward(request, response);
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
