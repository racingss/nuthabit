package com.babycard.servlet;

import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.babycard.dao.CardDAO;
import com.babycard.dao.CardMeaningDAO;
import com.babycard.dao.CardPic;
import com.babycard.dao.CardPicDAO;
import com.babycard.dao.CardSound;
import com.babycard.dao.Kehu;
import com.babycard.dao.SoundDAO;
import com.babycard.util.KehuUtil;

@WebServlet("/card/uploadbabycard.html")
public class UploadBabyCardServlet extends HttpServlet {
	Log log = LogFactory.getLog(this.getClass().getName());
	private static final long serialVersionUID = 1L;
	final long MAX_SIZE = 10 * 1024 * 1024;// 设置上传文件最大为 10M

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadBabyCardServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		Kehu k = new KehuUtil().getKehu(request, response);
		if (k == null) {
			response.sendRedirect("/card/wx_login.jsp");
			return;
		}

		// TODO Auto-generated method stub
		response.setContentType("text/html");
		// 设置字符编码为UTF-8, 这样支持汉字显示
		response.setCharacterEncoding("UTF-8");
		// 实例化一个硬盘文件工厂,用来配置上传组件ServletFileUpload
		DiskFileItemFactory dfif = new DiskFileItemFactory();
		dfif.setSizeThreshold(4096);// 设置上传文件时用于临时存放文件的内存大小,这里是4K.多于的部分将临时存在硬盘
		// String ROOT_PATH = ;// 缓存临时文件夹
		String weburl = "myplan/upload/historypic/";
		String path = request.getSession().getServletContext().getRealPath("/") + weburl;
		File directory = new File(path);
		if (!directory.exists()) {
			FileUtils.forceMkdir(directory);
		}
		dfif.setRepository(directory);// 设置存放临时文件的目录,web根目录下的images目录
		// 用以上工厂实例化上传组件
		ServletFileUpload sfu = new ServletFileUpload(dfif);
		// 设置最大上传尺寸
		sfu.setSizeMax(MAX_SIZE);
		PrintWriter out = response.getWriter();
		// 从request得到 所有 上传域的列表
		List fileList = null;
		try {
			fileList = sfu.parseRequest(request);
		} catch (FileUploadException e) {// 处理文件尺寸过大异常
			if (e instanceof SizeLimitExceededException) {
				System.out.print("{message:'文件尺寸超过规定大小:" + MAX_SIZE + "字节'}");
				return;
			}
			e.printStackTrace();
		}
		// 没有文件上传
		if (fileList == null || fileList.size() == 0) {
			System.out.print("{message:'请选择上传文件'}");
			return;
		}

		// 得到所有上传的文件
		Iterator fileItr = fileList.iterator();
		// 循环处理所有文件
		int i = 0;
		long cardId = 0;
		long picId = 0;
		long mainPicId = 0;
		long languageId = 0;
		long meaningId = 0;
		String slide = null;
		String sound = null;
		String soundQue = null;
		String cover = null;
		String effect = null;

		while (fileItr.hasNext() && i <= 8) {

			FileItem fileItem = null;
			String path2 = null;
			long size = 0;
			// 得到当前文件
			fileItem = (FileItem) fileItr.next();
			if (fileItem.isFormField()) { // 获得文本域表单数据
				if ("cardId".equals(fileItem.getFieldName())) {
					cardId = Long.parseLong(fileItem.getString("UTF-8"));
				}
				if ("picId".equals(fileItem.getFieldName())) {
					picId = Long.parseLong(fileItem.getString("UTF-8"));
				}

				if ("mainPicId".equals(fileItem.getFieldName())) {
					mainPicId = Long.parseLong(fileItem.getString("UTF-8"));
				}

				if ("sound".equals(fileItem.getFieldName())) {
					sound = fileItem.getString("UTF-8");
				}

				if ("effect".equals(fileItem.getFieldName())) {
					effect = fileItem.getString("UTF-8");
				}

				if ("cover".equals(fileItem.getFieldName())) {
					cover = fileItem.getString("UTF-8");
				}

				if ("slide".equals(fileItem.getFieldName())) {
					slide = fileItem.getString("UTF-8");
				}

				if ("soundQue".equals(fileItem.getFieldName())) {
					soundQue = fileItem.getString("UTF-8");
				}

				if ("meaningId".equals(fileItem.getFieldName())) {
					meaningId = Long.parseLong(fileItem.getString("UTF-8"));
				}

				if ("languageId".equals(fileItem.getFieldName())) {
					languageId = Long.parseLong(fileItem.getString("UTF-8"));
				}

				continue;// 非file域不处理
			}

			if (fileItem.getName() == null || fileItem.getName().length() < 1) {
				continue;
			}

			i++;
			// 得到文件的完整路径
			path2 = fileItem.getName();
			// 得到文件的大小
			size = fileItem.getSize();
			if ("".equals(path) || size == 0 && i == 1) {
				System.out.print("{message:'请选择上传文件'}");
				continue;
			}

			System.out.println("准备上传：" + path2);

			// 判断文件类型
			int filetype = 0;// 0未知 1图片 2音频
			String ext = path2.substring(path2.indexOf("."), path2.length());
			if (ext.indexOf("jpg") != -1 || ext.indexOf("JPG") != -1 || ext.indexOf("gif") != -1
					|| ext.indexOf("GIF") != -1 || ext.indexOf("png") != -1 || ext.indexOf("PNG") != -1
					|| ext.indexOf("jpeg") != -1 || ext.indexOf("JPEG") != -1) {
				filetype = 1;
			} else if (ext.indexOf("mp3") != -1) {
				filetype = 2;
			}

			// 文件名
			String pic = System.currentTimeMillis() + (int) (Math.random() * 6 + 2)
					+ path2.substring(path2.indexOf("."), path2.length());

			String distp = path + pic;

			System.out.println(i + "PATH******:" + distp);
			System.out.println(cardId);
			System.out.println(mainPicId);

			try {
				// 保存文件
				File fileOne = new File(distp);
				fileItem.write(fileOne);

				// 缩放文件
				if (filetype == 1) {
					if (fileOne.length() > 500 * 1024) {
						System.out.println("图片超过500k，需要压缩" + fileOne.length());
						BufferedImage source = ImageIO.read(fileOne);
						int width = source.getWidth();// 图片宽度
						int height = source.getHeight();// 图片高度
						int targetW = 800;
						int targetH = 0;
						if (width > targetW) {
							targetH = (int) (height * ((double) targetW / (double) width));
							BufferedImage imageResult = zoomInImage(source, targetW, targetH);
							File outFile = new File(distp);
							ImageIO.write(imageResult, "jpg", outFile);
							System.out.println("缩放成功");
						}
					}
				}

				response.setStatus(200);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			request.getSession().setAttribute("picurl", weburl + pic);
			request.setAttribute("picurl", weburl + pic);

			if (cover != null) {
				// 上传封面
				new CardDAO().updateCardDefaultPic(cardId, weburl + pic);
				response.sendRedirect("/card/carddetail.html?cardId=" + cardId);
				return;
			}

			if (filetype == 1 && cardId != 0) {
				if (mainPicId != 0) {
					// 插一张图片
					new CardPicDAO().addOntherCardPic(mainPicId, weburl + pic);
					// 重新初始化
					CardPic.cardPicCOll = new ArrayList();
					if (!fileItr.hasNext()) {
						response.sendRedirect("/card/carddetail.html?cardId=" + cardId);
						return;
					}
				} else if (picId == 0) {
					// 新增
					System.out.println("新增");
					CardPic cp = new CardDAO().addCardPic(cardId, weburl + pic);
					if (!fileItr.hasNext()) {
						response.sendRedirect(
								"create_own_card_add_word.jsp?picId=" + cp.getPicId() + "&cardId=" + cardId);
						return;
					}
				} else {
					// 替换
					System.out.println("替换");
					new CardDAO().replaceCardPic(picId, weburl + pic);
					if (!fileItr.hasNext()) {
						response.sendRedirect("/card/carddetail.html?cardId=" + cardId);
						return;
					}
				}
			}

			if (effect != null) {
				// 提交音效
				new CardPicDAO().updateSound(mainPicId, weburl + pic);
				response.sendRedirect("/card/carddetail.html?cardId=" + cardId);
				return;
			} else if (filetype == 2) {
				// 提交语音
				CardSound cs = new CardSound();
				cs.setCardId(cardId);
				cs.setKehuId(k.getId());
				cs.setLanguageId(languageId);
				cs.setSound("/"+weburl + pic);
				cs.setPicId(picId);
				new SoundDAO().addCardSound(cs, false);
				response.sendRedirect("/card/carddetail.html?cardId=" + cardId);
				return;
			} else if (soundQue != null) {
				// 提交问题语音
				new CardDAO().cardMeaningSoundQue(meaningId, weburl + pic);
				response.sendRedirect("/card/carddetail.html?cardId=" + cardId);
				return;
			}

		}
		request.getRequestDispatcher("create_own_card_add_word.jsp").forward(request, response);

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