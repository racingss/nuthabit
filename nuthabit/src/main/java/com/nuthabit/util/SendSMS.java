package com.nuthabit.util;

import java.util.*;

import java.io.UnsupportedEncodingException;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import java.io.*;
import java.net.*;

public class SendSMS {
	
	

	/**
	 * encode a specified string useing BASE64Encoder
	 *
	 * @param str
	 *            the string to be encoded
	 * @return String
	 * @throws Exception
	 */
	public synchronized static String BASE64Encoder(String str) throws Exception {
		return new sun.misc.BASE64Encoder().encode(str.getBytes());

	}

	public static void send(String shouji, String neirong) {
		try {

			HttpClient client = new HttpClient();
			PostMethod post = new PostMethod("http://www.smswst.com/api/httpapi.aspx");
			post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码
			NameValuePair[] data = { new NameValuePair("action", "send"), new NameValuePair("account", "xssyu"),
					new NameValuePair("password", "778899"), new NameValuePair("mobile", shouji),
					new NameValuePair("content", "【象山速渔】欢迎使用象山速渔海鲜速递服务，您的验证码为:" + neirong + ",请尽快使用！"),
					new NameValuePair("sendTime", ""), new NameValuePair("AddSign", "N"), };
			post.setRequestBody(data);

			client.executeMethod(post);
			Header[] headers = post.getResponseHeaders();
			int statusCode = post.getStatusCode();
			System.out.println("statusCode:" + statusCode);
			for (Header h : headers) {
				System.out.println(h.toString());
			}
			String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
			System.out.println(result); // 打印返回消息状态

			post.releaseConnection();

			// 对返回结果解析sb.toString

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void sendYouhuiquan(String shouji) {
		try {

			HttpClient client = new HttpClient();
			PostMethod post = new PostMethod("http://www.smswst.com/api/httpapi.aspx");
			post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码
			NameValuePair[] data = { new NameValuePair("action", "send"), new NameValuePair("account", "xssyu"),
					new NameValuePair("password", "778899"), new NameValuePair("mobile", shouji),
					new NameValuePair("content", "【象山速渔】欢迎使用象山速渔海鲜速递服务，您有一份优惠券已经放入账户中,请尽快使用！"),
					new NameValuePair("sendTime", ""), new NameValuePair("AddSign", "N"), };
			post.setRequestBody(data);

			client.executeMethod(post);
			Header[] headers = post.getResponseHeaders();
			int statusCode = post.getStatusCode();
			System.out.println("statusCode:" + statusCode);
			for (Header h : headers) {
				System.out.println(h.toString());
			}
			String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
			System.out.println(result); // 打印返回消息状态

			post.releaseConnection();

			// 对返回结果解析sb.toString

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void sendYanchifahuo(String shouji) {
		try {

			HttpClient client = new HttpClient();
			PostMethod post = new PostMethod("http://www.smswst.com/api/httpapi.aspx");
			post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码
			NameValuePair[] data = { new NameValuePair("action", "send"), new NameValuePair("account", "xssyu"),
					new NameValuePair("password", "778899"), new NameValuePair("mobile", shouji),
					new NameValuePair("content", "【象山速渔】非常抱歉通知您，您的订单将延迟发货，稍后我们的客服人员将联系您！"),
					new NameValuePair("sendTime", ""), new NameValuePair("AddSign", "N"), };
			post.setRequestBody(data);

			client.executeMethod(post);
			Header[] headers = post.getResponseHeaders();
			int statusCode = post.getStatusCode();
			System.out.println("statusCode:" + statusCode);
			for (Header h : headers) {
				System.out.println(h.toString());
			}
			String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
			System.out.println(result); // 打印返回消息状态

			post.releaseConnection();

			// 对返回结果解析sb.toString

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void sendFahuo(String shouji) {
		try {

			HttpClient client = new HttpClient();
			PostMethod post = new PostMethod("http://www.smswst.com/api/httpapi.aspx");
			post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码
			NameValuePair[] data = { new NameValuePair("action", "send"), new NameValuePair("account", "xssyu"),
					new NameValuePair("password", "778899"), new NameValuePair("mobile", shouji),
					new NameValuePair("content", "【象山速渔】欢迎使用象山速渔海鲜速递服务，您的商品已发货，您可以在订单详情中查询快递单号。"),
					new NameValuePair("sendTime", ""), new NameValuePair("AddSign", "N"), };
			post.setRequestBody(data);

			client.executeMethod(post);
			Header[] headers = post.getResponseHeaders();
			int statusCode = post.getStatusCode();
			System.out.println("statusCode:" + statusCode);
			for (Header h : headers) {
				System.out.println(h.toString());
			}
			String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
			System.out.println(result); // 打印返回消息状态

			post.releaseConnection();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws Exception {
		

		send("13601621719","1191");

	}

}