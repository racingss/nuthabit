package com.babycard.servlet;

import java.io.IOException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.babycard.dao.*;
import com.babycard.util.*;

@WebServlet("/diandian/sell.html")
public class SellServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SellServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setCharacterEncoding("UTF-8");

			Kehu k = new KehuUtil().getKehu(request, response);
			if (k == null) {
				response.sendRedirect("/card/wx_login.jsp");
				return;
			}

			// 初始页面
			if (request.getParameter("init") != null) {
				request.getRequestDispatcher("sell.jsp").forward(request, response);
				return;
			}

			// 新增或修改

			long id = 0;
			if (request.getParameter("id") != null) {
				id = Long.parseLong(request.getParameter("id"));
			}

			ChanpinDAO dao = new ChanpinDAO();
			Chanpin c = null;

			if (id == 0) {
				// 新增
				c = new Chanpin();
			} else {
				// 修改
				c = dao.getChanpinById(id);
			}

			if (request.getParameter("jiancheng") != null) {
				c.setJiancheng(request.getParameter("jiancheng"));
			}

			request.getRequestDispatcher("/diandian/chanpinlist.jsp").forward(request, response);

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
