package com.babycard.servlet;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.*;
import java.util.Collection;
import java.util.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
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
import org.dom4j.*;
import org.dom4j.io.*;

import com.babycard.dao.*;
import com.babycard.util.HttpPostUtil;
import com.babycard.util.KehuUtil;
import com.babycard.wx.AccessToken;
import com.gson.WeChat;
import com.gson.oauth.Message;
import com.gson.util.Tools;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

@WebServlet("/wx")
public class WxTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static long haibao_time = 0;
	private static String media_id = null;

	private static long kefu_time = 0;
	private static String kefu_id = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WxTestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static Collection tagColl = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (true) {
			doPost(request, response);
			return;
		}

		String path = request.getServletPath();
		String pathInfo = path.substring(path.lastIndexOf("/"));
		String _token;
		String outPut = "0";
		if (pathInfo != null) {
			_token = pathInfo.substring(1);
			String signature = request.getParameter("signature");// 微信加密签名
			String timestamp = request.getParameter("timestamp");// 时间戳
			String nonce = request.getParameter("nonce");// 随机数
			String echostr = request.getParameter("echostr");//
			// 验证
			if (WeChat.checkSignature(_token, signature, timestamp, nonce)) {
				outPut = echostr;
			}
		}
		response.getWriter().write(outPut);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		try {
			Map<String, String> map = xmlToMap(request);
			String fromUserName = map.get("FromUserName");
			String toUserName = map.get("ToUserName");
			// 消息类型
			String msgType = map.get("MsgType");
			String event = map.get("Event");
			System.out.println("get message from wx:" + msgType);
			String message = null;

			if (MsgTypeParam.MESSAGE_EVENT.equals(msgType)) {

				if (MsgTypeParam.MESSAGE_SUBSCRIBE.equals(event)) {

					String text = "欢迎您使用卡片点点（Cardpopo）\n我们是家长的学前教育好帮手，专门为您提供了一套儿童认知的教育平台软件。您可以点击下方菜单《<a href=\"http://www.suyufuwu.com/diandian\">卡片点点</a>》进入使用。在您的使用过程中，如碰到任何问题或有任何想法，都可以随时联系我们的创始人兼全职客服Adon，微信号wangadon（添加时请注明cardpopo）。";
					message = initText(toUserName, fromUserName, text);

					// 调用初始化文本消息方法
					String key = map.get("EventKey");
					if (key != null) {
						System.out.println(key);
						long kId = 0;
						if (key.indexOf("qrscene_") != -1) {
							kId = Long.parseLong(key.substring(8));
							Kehu inviteKehu = new KehuDAO().getKehuById(kId);
							String text1 = "您是" + inviteKehu.getNickname() + "邀请过来的";
							new AccessToken().sendMsg(fromUserName, text1);
							// 登记用户
							new KehuUtil().registerWhenGuanzhu(fromUserName, kId);
						}
					}

					if (media_id == null || System.currentTimeMillis() - haibao_time > 60 * 1000 * 60 * 24 * 2) {
						// 上传海报
						uploadHaibao(request);
					}

					new AccessToken().sendImg(fromUserName, media_id);
				} else {
					System.out.println(event);
				}

			} else if (MsgTypeParam.MESSAGE_TEXT.equals(msgType)) {
				String content = map.get("Content");
				System.out.println(content);
				String text = "在您的使用过程中，如碰到任何问题或有任何想法，都可以随时联系我们的创始人兼全职客服Adon，微信号wangadon（添加时请注明cardpopo）。";
				// 调用初始化文本消息方法
				message = initText(toUserName, fromUserName, text);

				if (kefu_id == null || System.currentTimeMillis() - kefu_time > 60 * 1000 * 60 * 24 * 2) {
					// 上传海报
					uploadKefu(request);
				}

				new AccessToken().sendImg(fromUserName, kefu_id);

			}
			out.print(message);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	private void uploadHaibao(HttpServletRequest request) {
		try {
			HttpPostUtil util = new HttpPostUtil("https://api.weixin.qq.com/cgi-bin/media/upload");
			util.addParameter("access_token", AccessToken.getToken());
			util.addParameter("type", "image");
			util.addParameter("media", new File(
					request.getSession().getServletContext().getRealPath("/") + "diandian/haibao/pengyouquan.jpeg"));
			String temp = util.send();
			System.out.println(temp);
			media_id = temp.substring(temp.indexOf("media_id") + 11, temp.indexOf("created_at") - 3);
			haibao_time = System.currentTimeMillis();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void uploadKefu(HttpServletRequest request) {
		try {
			HttpPostUtil util = new HttpPostUtil("https://api.weixin.qq.com/cgi-bin/media/upload");
			util.addParameter("access_token", AccessToken.getToken());
			util.addParameter("type", "image");
			util.addParameter("media", new File(
					request.getSession().getServletContext().getRealPath("/") + "diandian/haibao/erweima_adon.jpg"));
			String temp = util.send();
			System.out.println(temp);
			kefu_id = temp.substring(temp.indexOf("media_id") + 11, temp.indexOf("created_at") - 3);
			kefu_time = System.currentTimeMillis();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		InputStream ins = request.getInputStream();
		Document doc = reader.read(ins);
		Element root = doc.getRootElement();
		List<Element> list = root.elements();
		for (Element e : list) {
			map.put(e.getName(), e.getText());
		}
		ins.close();
		return map;
	}

	public String initText(String toUserName, String fromUserName, String content) {
		String message = "";
		TextMessage text = new TextMessage();
		text.setFromUserName(toUserName);
		text.setToUserName(fromUserName);
		text.setMsgType(MsgTypeParam.MESSAGE_TEXT);
		text.setCreateTime(new Date().getTime() + "");
		text.setContent(content);
		message = textMessageToXml(text);
		return message;
	}

	public static String textMessageToXml(TextMessage textMessage) {
		XStream xStream = new XStream(new StaxDriver());
		xStream.alias("xml", textMessage.getClass());
		return xStream.toXML(textMessage);
	}

}

class MsgTypeParam {

	public static final String MESSAGE_TEXT = "text";// 文本
	public static final String MESSAGE_IMAGE = "image";// 图片
	public static final String MESSAGE_NEWS = "news";
	public static final String MESSAGE_VOICE = "voice";// 语音
	public static final String MESSAGE_VIDEO = "video";// 视频
	public static final String MESSAGE_MUSIC = "music";// 音乐
	public static final String MESSAGE_LOCATION = "location";// 位置
	public static final String MESSAGE_LINK = "link";// 链接消息
	public static final String MESSAGE_EVENT = "event";// 事件
	public static final String MESSAGE_SUBSCRIBE = "subscribe";// 关注
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";// 取消关注
	public static final String MESSAGE_CLICK = "CLICK";// 点击
	public static final String MESSAGE_VIEW = "VIEW";// 点击菜单跳转链接时的事件推送
	public static final String MESSAGE_SCANCODE = "scancode_push";// 扫码
}

class TextMessage {
	private String ToUserName;// 开发者微信号
	private String FromUserName;// 发送方帐号（一个OpenID）
	private String CreateTime;// 消息创建时间 （整型）
	private String MsgType;// 消息类型
	private String Content;// 内容
	private String MsgId;// 消息id，64位整型

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getMsgId() {
		return MsgId;
	}

	public void setMsgId(String msgId) {
		MsgId = msgId;
	}

	@Override
	public String toString() {
		return "TextMessage [ToUserName=" + ToUserName + ", FromUserName=" + FromUserName + ", CreateTime=" + CreateTime
				+ ", MsgType=" + MsgType + ", Content=" + Content + ", MsgId=" + MsgId + "]";
	}

}
