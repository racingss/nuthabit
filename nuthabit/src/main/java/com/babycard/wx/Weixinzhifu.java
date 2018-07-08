package com.babycard.wx;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.babycard.util.KehuUtil;

public class Weixinzhifu {

	public void tijiaoweixin(HttpServletRequest request, HttpServletResponse response, String openId, String dindanId,long jine)
			throws UnsupportedEncodingException, ServletException, IOException {
		request.setAttribute("wx", 1);
		String nonceStr = this.create_nonce_str();
		Map<String, String> ret = AccessToken.sign();
		SortedMap params = new TreeMap();
		params.put("appid", KehuUtil.appId);
		params.put("body", URLEncoder.encode("卡片点点", "gbk"));
		params.put("mch_id", KehuUtil.mchId);
		params.put("nonce_str", nonceStr);
		params.put("notify_url", KehuUtil.notify_url);
		params.put("openid", openId);
		params.put("out_trade_no", dindanId);
		params.put("spbill_create_ip", getIp(request));
		params.put("total_fee", jine);
		params.put("trade_type", "JSAPI");

		String temp1 = com.gson.oauth.Pay.getSignValue(params);
		System.out.println("好不容易MD5签名：" + temp1);

		StringBuffer str = new StringBuffer();
		str.append("<xml>");
		str.append("<appid><![CDATA["+KehuUtil.appId+"]]></appid>");
		str.append("<body><![CDATA[" + URLEncoder.encode("卡片点点", "gbk") + "]]></body>");
		str.append("<mch_id><![CDATA["+KehuUtil.mchId+"]]></mch_id>");
		str.append("<nonce_str><![CDATA[" + nonceStr + "]]></nonce_str>");
		str.append("<notify_url><![CDATA["+KehuUtil.notify_url+"]]></notify_url>");
		str.append("<openid><![CDATA[" + openId + "]]></openid>");
		str.append("<out_trade_no><![CDATA[" + dindanId + "]]></out_trade_no>");
		str.append("<spbill_create_ip><![CDATA[" + getIp(request) + "]]></spbill_create_ip>");
		str.append("<total_fee>" + jine + "</total_fee>");
		str.append("<trade_type><![CDATA[JSAPI]]></trade_type>");
		str.append("<sign><![CDATA[" + temp1 + "]]></sign>");
		str.append("</xml>");
		System.out.println("转换成XML了吗：" + str.toString());

		this.sendPost("https://api.mch.weixin.qq.com/pay/unifiedorder", str.toString());

		SortedMap params2 = new TreeMap();
		params2.put("appId", KehuUtil.appId);
		params2.put("nonceStr", nonceStr);
		params2.put("package", "prepay_id=" + prepay_id);
		params2.put("signType", "MD5");
		params2.put("timeStamp", ret.get("timestamp"));

		String paySign = com.gson.oauth.Pay.getSignValue(params2);

		// appId
		request.setAttribute("appid", KehuUtil.appId);
		// timeStamp
		request.setAttribute("timeStamp", ret.get("timestamp"));
		// nonceStr
		request.setAttribute("nonceStr", nonceStr);
		// package
		request.setAttribute("package", "prepay_id=" + prepay_id);
		// paySign
		request.setAttribute("paySign", paySign);
		// 判定微信
		request.setAttribute("isweixin", 1);

		request.getRequestDispatcher("weixinzhifu.jsp").forward(request, response);
	}

	public static String getIp(HttpServletRequest request) {
		if (request == null)
			return "";
		String ip = request.getHeader("X-Requested-For");
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	private String prepay_id = null;

	public String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			// conn.setRequestProperty("accept", "*/*");
			// conn.setRequestProperty("connection", "Keep-Alive");
			// conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible;
			// MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += new String(line.getBytes(), "utf-8");
				System.out.println("收到返回：" + line);
			}

			System.out.println("收到的所有返回：" + result);
			prepay_id = result.substring(result.indexOf("prepay_id") + 19,
					result.indexOf("]]", result.indexOf("prepay_id") + 19));
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	private static String create_nonce_str() {
		String temp = UUID.randomUUID().toString();
		temp = temp.replaceAll("-", "");
		return temp;
	}
}

