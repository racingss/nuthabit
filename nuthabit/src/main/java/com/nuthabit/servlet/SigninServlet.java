package com.nuthabit.servlet;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nuthabit.dao.Kehu;
import com.nuthabit.dao.Myplan;
import com.nuthabit.dao.MyplanDAO;
import com.nuthabit.dao.MyplanHistory;

/**
 * 打卡事件处理
 * Page : planDetail.jsp
 * @author jefferyxu
 *
 */
@WebServlet("/myplan/signin.html")
public class SigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final long MAX_SIZE = 10 * 1024 * 1024;// 设置上传文件最大为 10M

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SigninServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

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

		MyplanDAO dao = new MyplanDAO();
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		if (request.getParameter("ajax") != null) {
			Myplan m = dao.get(Long.parseLong(request.getParameter("id")));
			response.getWriter().append(Long.toString(dao.signin(m)));
			return;
		}
		request.setAttribute("plan", new MyplanDAO().get(Long.parseLong(request.getParameter("id"))));
		
		request.getRequestDispatcher("signin.jsp").forward(request, response);
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

	public static BufferedImage zoomInImage(BufferedImage originalImage, int width, int height) {
		BufferedImage newImage = new BufferedImage(width, height, originalImage.getType());

		Graphics g = newImage.getGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.dispose();
		return newImage;
	}

}
