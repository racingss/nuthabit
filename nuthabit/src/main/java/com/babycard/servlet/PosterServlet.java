package com.babycard.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.babycard.dao.*;
import com.babycard.util.*;
import com.babycard.wx.AccessToken;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.swetake.util.Qrcode;

/**
 * Servlet implementation class Parents
 */
@WebServlet("/diandian/poster.html")
public class PosterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PosterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Kehu k = new KehuUtil().getKehu(request, response);
			if (k == null) {
				response.sendRedirect("/card/wx_login.jsp");
				return;
			}
			ImageUtil img = new ImageUtil();

			String erweimaUrl = "diandian/poster/erweima/";
			String headUrl = "diandian/poster/head/";
			String saveUrl = "diandian/poster/save/";
			String path = request.getSession().getServletContext().getRealPath("/");
			StringBuffer erweimaSBS = new StringBuffer(path).append(erweimaUrl).append(k.getId()).append(".jpg");
			StringBuffer headSBS = new StringBuffer(path).append(headUrl).append(k.getId()).append(".jpg");
			StringBuffer saveSBS = new StringBuffer(path).append(saveUrl).append(k.getId()).append(".jpg");
			StringBuffer webSBS = new StringBuffer(saveUrl).append(k.getId()).append(".jpg");
			//String poster = path + "diandian/poster/poster.jpg";
			String poster = path + "diandian/haibao/pengyouquan.jpeg";
			// 生成二维码
			String erweima = new AccessToken().erweimaUrl((int) k.getId());

			// 下载二维码
			img.downloadPicture(erweima, erweimaSBS.toString());

			// 下载头像
			//img.downloadPicture(k.getHeadimgurl(), headSBS.toString());
			// 头像圆形处理
			//img.round(headSBS.toString());

			// 合成海报
//			img.watermark(new File(poster), new File(erweimaSBS.toString()), 545, 265, 1.0f, 103, 103,
//					saveSBS.toString());
			img.watermark(new File(poster), new File(erweimaSBS.toString()), 9, 880, 1.0f, 224, 224,
					saveSBS.toString());
			System.out.println("生成海报:"+webSBS.toString());
			request.setAttribute("poster", webSBS.toString());
			request.getRequestDispatcher("poster.jsp").forward(request, response);


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
