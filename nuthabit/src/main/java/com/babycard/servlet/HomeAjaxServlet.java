package com.babycard.servlet;

import java.io.IOException;

import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.babycard.dao.Baby;
import com.babycard.dao.*;
import com.babycard.util.*;

@WebServlet("/diandian/homeajax.html")
public class HomeAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeAjaxServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		if (request.getParameter("nexttime") != null)
			request.getSession().setAttribute("nexttime", "t");

		if (request.getParameter("birthyear") != null) {
			Kehu k = new KehuUtil().getKehu(request, response);
			Baby b = new BabyDAO().getBabyBykId(k.getId());
			if (b == null) {
				b = new Baby();
				b.setkId(k.getId());
				b.setBabyName("");
				b.setbYear(Long.parseLong(request.getParameter("birthyear")));
				b.setbMonth(Long.parseLong(request.getParameter("birthmonth")));
				b.setbDay(0);
				b.setSex(Long.parseLong(request.getParameter("sex")));
				new BabyDAO().addBaby(b);
			} else {
				b.setSex(Long.parseLong(request.getParameter("sex")));
				b.setbYear(Long.parseLong(request.getParameter("birthyear")));
				b.setbMonth(Long.parseLong(request.getParameter("birthmonth")));
				new BabyDAO().updateBaby(b);
			}
			k.b = b;
			return;
		}

		if (request.getParameter("babyName") != null) {
			Kehu k = new KehuUtil().getKehu(request, response);
			Baby b = new BabyDAO().getBabyBykId(k.getId());
			b.setBabyName(request.getParameter("babyName"));
			new BabyDAO().updateBaby(b);
			k.b = b;
			return;
		}

		if (request.getParameter("email") != null) {
			Kehu k = new KehuUtil().getKehu(request, response);
			k.setEmail(request.getParameter("email"));
			new KehuDAO().updateKehu(k);
			return;
		}

		if (request.getParameter("mobile") != null) {
			Kehu k = new KehuUtil().getKehu(request, response);
			k.setShouji(request.getParameter("mobile"));
			new KehuDAO().updateKehu(k);
			return;
		}

		if (request.getParameter("wx") != null) {
			Kehu k = new KehuUtil().getKehu(request, response);
			k.setWx(request.getParameter("wx"));
			new KehuDAO().updateKehu(k);
			return;
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