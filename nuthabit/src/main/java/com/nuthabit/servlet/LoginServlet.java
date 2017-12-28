package com.nuthabit.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nuthabit.dao.Kehu;
import com.nuthabit.dao.KehuDAO;
import com.nuthabit.dao.MyplanDAO;
import com.nuthabit.util.SendSMS;

@WebServlet("/myplan/login.html")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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

		if (request.getParameter("sms") != null) {
			// Send sms
			String rand = Integer.toString((int) (Math.random() * (9999 - 1000 + 1)) + 1000);// 产生1000-9999的随机数
			System.out.println("临时sms" + rand);
			request.getSession().setAttribute("rand", rand);
			request.getSession().setAttribute("randtime", Long.toString(System.currentTimeMillis()));
//			SendSMS.send(request.getParameter("mobile"), rand);
			response.setCharacterEncoding("UTF-8");
//			response.getWriter().append("9");
			response.getWriter().append(rand);
			return;
		}

		if (request.getParameter("mobile") != null) {
			Object randObj = request.getSession().getAttribute("rand");
			if (randObj == null) {
				request.setAttribute("msg", "您的激活码已超时，请重新获取");
				request.getRequestDispatcher("loginwx.jsp").forward(request, response);
				return;
			}
			String rand = randObj.toString();
			if (!request.getParameter("pwd").equals(rand)) {
				request.setAttribute("msg", "您的激活码不正确");
				request.getRequestDispatcher("loginwx.jsp").forward(request, response);
				return;
			}

			KehuDAO dao = new KehuDAO();
			Kehu k = dao.getKehu("shouji", request.getParameter("mobile"));
			if (k == null) {
				k = new Kehu();
				k.setShouji(request.getParameter("mobile"));
				dao.addKehu(k);
				System.out.println("新用户注册");
			}else{
				System.out.println("老用户登录");
			}
			
			request.getSession().setAttribute("kehu", k);
			if(request.getSession().getAttribute("sessionURL")!=null){
				response.sendRedirect(request.getSession().getAttribute("sessionURL").toString());
				return;
			}
			response.sendRedirect("index.html");
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
