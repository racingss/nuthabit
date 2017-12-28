package com.nuthabit.servlet;

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

import com.nuthabit.dao.Kehu;
import com.nuthabit.dao.LessonDAO;
import com.nuthabit.dao.Myplan;
import com.nuthabit.dao.MyplanDAO;
import com.nuthabit.dao.MyplanHistory;
import com.nuthabit.dao.MyplanLesson;

@WebServlet("/myplan/addlesson.html")
public class AddLessonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final long MAX_SIZE = 10 * 1024 * 1024;// 设置上传文件最大为 10M

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddLessonServlet() {
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
			request.getSession().setAttribute("sessionURL", request.getRequestURL()+"?"+request.getQueryString());
			request.getRequestDispatcher("loginwx.jsp").forward(request, response);
			return;
		}

		if (request.getParameter("title") == null) {
			request.getRequestDispatcher("addlesson.jsp").forward(request, response);
			return;
		}
		
		if(request.getParameter("add")!=null){
			MyplanLesson m =  new MyplanLesson();
			m.setDiscription(request.getParameter("discription"));
			m.setHeadimgurl(k.getHeadimgurl());
			m.setKehuId(k.getKehuId());
			m.setKehuNick(k.getNickname());
			m.setPrice(Long.parseLong(request.getParameter("price")));
			m.setTitle(request.getParameter("title"));
			m.setCreateDate(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			LessonDAO dao = new LessonDAO();
			dao.add(m);
			request.setAttribute("lesson", dao.getMyplanLesson(m.getLessonId()));
			request.getRequestDispatcher("addlesson.jsp").forward(request, response);
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

	public static BufferedImage zoomInImage(BufferedImage originalImage, int width, int height) {
		BufferedImage newImage = new BufferedImage(width, height, originalImage.getType());

		Graphics g = newImage.getGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.dispose();
		return newImage;
	}

}
