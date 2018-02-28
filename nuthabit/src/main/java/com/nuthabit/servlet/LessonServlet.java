package com.nuthabit.servlet;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
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

import com.nuthabit.dao.*;

@WebServlet("/myplan/lesson.html")
public class LessonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final long MAX_SIZE = 10 * 1024 * 1024;// 设置上传文件最大为 10M

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LessonServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		LessonDAO lessonDAO = new LessonDAO();
		
		String lessonId = request.getParameter("lessonId");
		long dayId = 1;
		if (request.getParameter("dayId") != null)
			dayId = Long.parseLong(request.getParameter("dayId"));
		
		MyplanLesson l = lessonDAO.getMyplanLesson(lessonId);
		MyplanLessonDay d = lessonDAO.getMyplanLessonDay(dayId);
		Collection dayColl = lessonDAO.getMyplanLessonDayColl(lessonId);
		
		
		request.setAttribute("lesson", l);
		request.setAttribute("day", d);
		request.setAttribute("dayId", dayId);
		request.setAttribute("dayColl", dayColl);
		

		request.getRequestDispatcher("lessonday.jsp").forward(request, response);
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
