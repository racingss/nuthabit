package com.nuthabit.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
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

import com.nuthabit.dao.Kehu;
import com.nuthabit.dao.Myplan;
import com.nuthabit.dao.MyplanDAO;
import com.nuthabit.dao.MyplanExperience;
import com.nuthabit.dao.MyplanThumbup;
import com.nuthabit.util.KehuUtil;

@WebServlet("/myplan/thumbup.html")
public class ThumbupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final long MAX_SIZE = 10 * 1024 * 1024;// 设置上传文件最大为 10M

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ThumbupServlet() {
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

		try {
			MyplanThumbup t = new MyplanThumbup();
			t.setHeadimgurl(k.getHeadimgurl());
			t.setKehuId(k.getKehuId());
			t.setNickname(k.getNickname());
			t.setExperienceId(Long.parseLong(request.getParameter("experienceId")));
			t.setPlanId(request.getParameter("planId"));
			dao.thumbup(t);

			/*
			Collection thumbupExColl = dao.getPlanThumbup(request.getParameter("planId"));
			StringBuffer result = new StringBuffer("<ul>");
			Iterator exThIt = thumbupExColl.iterator();
			String tempNickname = "";
			int i = 0;
			while (exThIt.hasNext() && i++ < 4) {
				MyplanThumbup mt = (MyplanThumbup) exThIt.next();
				tempNickname = mt.getNickname();
				result.append("<li><img src=\"");
				result.append(mt.getHeadimgurl());
				result.append("\"></li>");
			}
			result.append("</ul><div><span>~");
			result.append(tempNickname);
			result.append("</span>等");
			result.append(Long.toString(thumbupExColl.size()));
			result.append("位好友点了赞</div>");

			System.out.println(result.toString());
			response.getWriter().append(result.toString());
			*/

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

	public static BufferedImage zoomInImage(BufferedImage originalImage, int width, int height) {
		BufferedImage newImage = new BufferedImage(width, height, originalImage.getType());

		Graphics g = newImage.getGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.dispose();
		return newImage;
	}

}
