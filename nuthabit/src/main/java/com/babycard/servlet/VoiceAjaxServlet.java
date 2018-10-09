package com.babycard.servlet;

import java.io.*;

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
import com.babycard.wx.AccessToken;
import com.gson.WeChat;
import com.gson.bean.Attachment;

@WebServlet("/diandian/voice.html")
public class VoiceAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VoiceAjaxServlet() {
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

			long cardId = Long.parseLong(request.getParameter("cardId"));
			long picId = Long.parseLong(request.getParameter("picId"));

			String mediaId = request.getParameter("mediaId");

			String accessToken = AccessToken.getToken();

			Attachment at = WeChat.getMedia(accessToken, mediaId);

			System.out.println(at.toString());
			String weburl = "diandian/voice/";
			String dest = request.getSession().getServletContext().getRealPath("/") + weburl + at.getFullName();

			System.out.println(dest);

			BufferedInputStream bufferedInput = at.getFileStream();
			BufferedOutputStream bufferedOutput = null;
			try {
				bufferedOutput = new BufferedOutputStream(new FileOutputStream(dest));

				byte[] buffer = new byte[Integer.parseInt(at.getContentLength())];
				int copySize;

				while ((copySize = bufferedInput.read(buffer)) > 0) {
					bufferedOutput.write(buffer, 0, copySize);
				}

				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					bufferedInput.close();
					bufferedOutput.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			Voice v = new Voice();
			v.setCardId(cardId);
			v.setkId(k.getId());
			v.setPicId(picId);
			v.setVoiceUrl(weburl + at.getFullName());

			System.out.println(v.toString());

			v = new VoiceDAO().add(v);

			System.out.println(v.toString());
			

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
