package com.babycard.servlet;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

import com.gson.oauth.Pay;
import com.gson.util.ConfKit;
import com.gson.util.XStreamFactory;
import com.babycard.dao.*;
import com.babycard.util.KehuUtil;
import com.babycard.wx.*;
import com.thoughtworks.xstream.XStream;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class Tijiaodingdan
 */
@WebServlet("/diandian/order.html")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderServlet() {
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

		// 查询订单
		if (request.getParameter("type") != null) {
			queryOrder(request, response);
			return;
		}

		// TODO Auto-generated method stub
		Kehu k = new KehuUtil().getKehu(request, response);

		if (k == null) {
			response.sendRedirect("/card/wx_login.jsp");
			return;
		}

		// 订单支付
		Dingdan d = new Dingdan();
		
		try {
			d.setKehuId(k.getKehuId());
			d.setFuwu1(request.getParameter("level"));

			Calendar c1 = Calendar.getInstance();
			c1.setTime(new Date());
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			d.setRiqi(format1.format(c1.getTime()));

			
			d.setDingdanId("DD" + c1.get(c1.DAY_OF_WEEK + 1) * c1.get(c1.MINUTE) + "8" + System.currentTimeMillis());

			d.setStatus(d.STATUS_DAIZHIFU);

			long amount = Long.parseLong(request.getParameter("amount"));

			d.setZhifujine(amount);
			d.setZongjine(amount);

			new DingdanDAO().newDingdan(d);

			request.setAttribute("dingdan", d);

			/*
			 * 开始提交微信支付
			 */

			//new Weixinzhifu().tijiaoweixin(request, response, k.getOpenId(), d.getDingdanId(), d.getZhifujine());
			new Weixinzhifu().tijiaoweixin(request, response, k.getOpenId(), d.getDingdanId(), 1);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void queryOrder(HttpServletRequest request, HttpServletResponse response) {
		try {
			String dingdanId = request.getParameter("dingdanId");
			String r = Long.toString(new DingdanDAO().getDingdanByDingdanId(dingdanId).getStatus());
			response.getWriter().append(r);
			return;
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
