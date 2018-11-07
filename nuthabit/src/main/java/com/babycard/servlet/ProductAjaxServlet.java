package com.babycard.servlet;

import java.io.IOException;

import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.babycard.dao.*;
import com.babycard.util.*;

@WebServlet("/diandian/productajax.html")
public class ProductAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductAjaxServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String zhutu = request.getParameter("zhutu");
		String jiage = "0";
		if (request.getParameter("jiage") != null)
			jiage = request.getParameter("jiage");
		String jiancheng = request.getParameter("jiancheng");

		Chanpin c =(Chanpin)request.getSession().getAttribute("chanpin");
		c.setZhutu(zhutu);
		try {
			c.setJiage(Long.parseLong(jiage));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.setJiancheng(jiancheng);
		
		request.getSession().setAttribute("chanpin", c);
		
		
		System.out.println(c.toString());

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