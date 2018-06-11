package com.nuthabit.servlet;

import java.io.IOException;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nuthabit.dao.Kehu;
import com.nuthabit.dao.Myplan;
import com.nuthabit.dao.MyplanDAO;
import com.nuthabit.util.KehuUtil;

@WebServlet("/myplan/index.html")
public class MyplanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyplanServlet() {
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
			request.getRequestDispatcher("loginwx.jsp").forward(request, response);
			return;
		}

		// if (request.getSession().getAttribute("kehu") == null) {
		// request.getRequestDispatcher("qingdenglu.jsp").forward(request,
		// response);
		// return;
		// }
		//
		// Kehu k = (Kehu) request.getSession().getAttribute("kehu");

		MyplanDAO dao = new MyplanDAO();
		if (request.getParameter("add") != null) {
			Myplan m = new Myplan();
			m.setKehuId(k.getKehuId());
			m.setTimes(request.getParameter("times"));
			m.setTitle(request.getParameter("title"));
			m.setDiscription(request.getParameter("discription"));
			// m.setKehuNick(kehuNick);
			m.setBeginDate(request.getParameter("beginDate"));
			m.setEndDate(request.getParameter("endDate"));
			m.setPrivacyFlag(Long.parseLong(request.getParameter("privacyFlag")));
			dao.add(m);
		}

		if (request.getParameter("processedit") != null) {
			Myplan m = dao.get(request.getParameter("planId"));
			if (m.getKehuId().equals(k.getKehuId())) {
				m.setTimes(request.getParameter("times"));
				m.setTitle(request.getParameter("title"));
				m.setDiscription(request.getParameter("discription"));
				dao.update(m);
			} else {
				System.out.println("不是本人");
			}
		}

		if (request.getParameter("edit") != null) {
			Myplan m = dao.get(request.getParameter("planId"));
			if (m.getKehuId().equals(k.getKehuId())) {
				request.setAttribute("myplan", m);
				request.getRequestDispatcher("pubPlan.jsp").forward(request, response);
				return;
			} else {
				System.out.println("不是本人");
			}
		}

		if (request.getParameter("delete") != null) {
			Myplan m = dao.get(request.getParameter("planId"));
			if (m.getKehuId().equals(k.getKehuId())) {
				dao.delete(request.getParameter("planId"));
			} else {
				System.out.println("不是本人");
			}
		}

		Collection plancoll = dao.getAll(k.getKehuId());
		request.setAttribute("plancoll", plancoll);
		
		request.setAttribute("default", dao.getDefault(k.getKehuId()));
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
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
