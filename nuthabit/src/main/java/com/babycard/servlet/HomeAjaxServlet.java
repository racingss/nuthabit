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
		System.out.println("next time");
		if (request.getParameter("nexttime") != null)
			request.getSession().setAttribute("nexttime", "t");
		
		if (request.getParameter("birthyear") != null) {
			Kehu k = new KehuUtil().getKehu(request, response);
			Baby b = new Baby();
			b.setkId(k.getId());
			b.setBabyName("");
			b.setbYear(Long.parseLong(request.getParameter("birthyear")));
			b.setbMonth(Long.parseLong(request.getParameter("birthmonth")));
			b.setbDay(0);
			b.setSex(Long.parseLong(request.getParameter("sex")));
			new BabyDAO().addBaby(b);
			k.b=b;
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