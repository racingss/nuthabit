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

@WebServlet("/diandian/address.html")
public class DizhiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DizhiServlet() {
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

			DizhiDAO dao = new DizhiDAO();
			Dizhi d = null;
			
			if(request.getParameter("province")!=null){
				d = new Dizhi();
				d.setProvince(request.getParameter("province"));
				d.setCity(request.getParameter("city"));
				d.setQu(request.getParameter("district"));
				d.setXiangxi(request.getParameter("street"));
				d.setShoujianren(request.getParameter("person"));
				d.setDianhua(request.getParameter("mobile"));
				d.setKehuId(k.getKehuId());
				dao.addDizhi(d);
			}
			
			

			d = dao.getMainDizhiByKehuId(k.getKehuId());
			request.setAttribute("address", d);

			request.getRequestDispatcher("/diandian/address.jsp").forward(request, response);

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
