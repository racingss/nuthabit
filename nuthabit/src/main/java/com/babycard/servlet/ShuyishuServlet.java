package com.babycard.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import com.babycard.dao.*;
import com.babycard.util.KehuUtil;

@WebServlet("/diandian/shuyishu.html")
public class ShuyishuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShuyishuServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setCharacterEncoding("UTF-8");

			// TODO Auto-generated method stub
			Kehu k = new KehuUtil().getKehu(request, response);

			if (k == null) {
				request.getSession().setAttribute("shuyishu", "t");
				response.sendRedirect("/card/wx_login.jsp");
				return;
			}

			if (request.getParameter("num") != null) {
				Shuyishu s = new ShuyishuDAO().getShuyishuById(Long.parseLong(request.getParameter("imgId")), true);
				request.setAttribute("s", s);
				request.getRequestDispatcher("shuyishu.jsp").forward(request, response);
				return;
			}

			request.setAttribute("sysColl", new ShuyishuDAO().getShuyishuColl());

			request.getRequestDispatcher("shuyishu_begin.jsp").forward(request, response);
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
