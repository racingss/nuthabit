package com.nuthabit.servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nuthabit.dao.Kehu;
import com.nuthabit.dao.Myplan;
import com.nuthabit.dao.MyplanDAO;
import com.nuthabit.util.KehuUtil;

@WebServlet("/myplan/detail.html")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DetailServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Kehu k = null;
		if (request.getSession().getAttribute("kehu") != null) {
			k = (Kehu) request.getSession().getAttribute("kehu");
		} else {
			request.getSession().setAttribute("sessionURL", request.getRequestURL() + "?" + request.getQueryString());
			request.getRequestDispatcher("loginwx.jsp").forward(request, response);
			return;
		}

		MyplanDAO dao = new MyplanDAO();
		Myplan m = dao.get(request.getParameter("planId"));
		boolean hasSignin = dao.hasSignedin(m);
		request.setAttribute("plan", dao.get(request.getParameter("planId")));
		request.setAttribute("hasSignin", hasSignin);

		if (request.getParameter("delete") != null) {
			if (m.getKehuId().equals(k.getKehuId()))
				dao.deleteExperience(Long.parseLong(request.getParameter("exId")));
			else
				System.out.println("不是本人");
		}

		if (request.getParameter("daka") != null) {
			request.getRequestDispatcher("daka.jsp").forward(request, response);
			return;
		}

		request.setAttribute("detailColl", dao.getAllPlanHistyory(request.getParameter("planId")));
		request.setAttribute("experienceColl", dao.getPlanExperience(request.getParameter("planId")));
		request.setAttribute("thumbupColl", dao.getPlanThumbup(request.getParameter("planId")));

		request.getRequestDispatcher("planDetail.jsp").forward(request, response);
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