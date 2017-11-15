package com.nuthabit.servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nuthabit.dao.MyplanDAO;

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

		String kehuId = "adon";

		MyplanDAO dao = new MyplanDAO();

		request.setAttribute("plan", dao.get(Long.parseLong(request.getParameter("id"))));
		
		if(request.getParameter("daka")!=null){
			request.getRequestDispatcher("daka.jsp").forward(request, response);
			return;
		}

		request.setAttribute("detailColl", dao.getAllPlanHistyory(Long.parseLong(request.getParameter("id"))));

		request.getRequestDispatcher("detail.jsp").forward(request, response);
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