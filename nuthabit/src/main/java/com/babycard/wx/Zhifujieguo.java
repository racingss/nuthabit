package com.babycard.wx;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.gson.WeChat;
import com.gson.bean.WeChatBuyPost;
import com.gson.oauth.Pay;
import com.gson.util.Tools;
import com.babycard.dao.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Servlet implementation class Zhifujieguo
 */
@WebServlet("/diandian/weixinzhifuresult.html")
public class Zhifujieguo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Zhifujieguo() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("微信返回支付结果了");
		// post 过来的xml
		try {
			ServletInputStream in = req.getInputStream();
			// 转换微信post过来的xml内容

			String result = Tools.inputStream2String(in);
			System.out.println("postData:" + result);

			String result_code = result.substring(result.indexOf("result_code") + 21,
					result.indexOf("]]", result.indexOf("result_code") + 21));
			String out_trade_no = result.substring(result.indexOf("out_trade_no") + 22,
					result.indexOf("]]", result.indexOf("out_trade_no") + 21));
			String total_fee = result.substring(result.indexOf("total_fee") + 10,
					result.indexOf("<", result.indexOf("total_fee") + 10));
			String openid = result.substring(result.indexOf("openid") + 16,
					result.indexOf("]]", result.indexOf("openid") + 16));

			System.out.println("支付返回：result_code " + result_code);
			System.out.println("支付返回：out_trade_no " + out_trade_no);
			System.out.println("支付返回：total_fee " + total_fee);
			System.out.println("支付返回：openid " + openid);

			Dingdan d = new DingdanDAO().getDingdanByDingdanId(out_trade_no);

			/*
			 * 需要更新
			 */
			if (result_code.equals("SUCCESS") && d.getStatus() == d.STATUS_DAIZHIFU) {
				d.setStatus(d.STATUS_YIZHIFU);
				new DingdanDAO().updateDingdan(d);
				
				KehuCardMember m = new KehuCardMember();
				m.setKehuId(d.getKehuId());
				m.setMemberLevel(Long.parseLong(d.getFuwu1()));
				new KehuDAO().addMember(m);
			}

			PrintWriter out = new PrintWriter(resp.getOutputStream());

			out.print(
					"<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
			// flush输出流的缓冲
			out.flush();
			out.close();

			System.out.println("已返回支付结果的回复了！");

		} catch (Exception e) {
			e.printStackTrace();
		}

		req.getRequestDispatcher("weixinzhifu.jsp").forward(req, resp);

	}

	/**
	 * 直接写字符串
	 * 
	 * @param response
	 * @param msg
	 */
	private void writeString(HttpServletResponse response, String msg) {
		try {
			response.getWriter().write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
