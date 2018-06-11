package com.nuthabit.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
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
import com.nuthabit.util.KehuUtil;

@WebServlet("/myplan/addplan.html")
public class AddPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final long MAX_SIZE = 10 * 1024 * 1024;// 设置上传文件最大为 10M

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddPlanServlet() {
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
			request.getSession().setAttribute("sessionURL", request.getRequestURL() + "?" + request.getQueryString());
			request.getRequestDispatcher("loginwx.jsp").forward(request, response);
			return;
		}
		MyplanDAO dao = new MyplanDAO();
		response.setContentType("text/html");
		// 设置字符编码为UTF-8, 这样支持汉字显示
		response.setCharacterEncoding("UTF-8");
		// 实例化一个硬盘文件工厂,用来配置上传组件ServletFileUpload
		DiskFileItemFactory dfif = new DiskFileItemFactory();
		dfif.setSizeThreshold(4096);// 设置上传文件时用于临时存放文件的内存大小,这里是4K.多于的部分将临时存在硬盘
		// String ROOT_PATH = ;// 缓存临时文件夹
		String path = request.getSession().getServletContext().getRealPath("/") + "myplan/upload/planpic/";
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
		String zhaopian1 = "";
		String zhaopian2 = "";
		String zhaopian3 = "";
		String pic = "";
		String processedit = "F";

		String times = null;
		String title = null;
		String discription = null;
		String id = null;
		long defaultFlag = 0;

		while (fileItr.hasNext()) {// && i < 3) {

			FileItem fileItem = null;
			String path2 = null;
			long size = 0;
			// 得到当前文件
			fileItem = (FileItem) fileItr.next();
			if (fileItem.isFormField()) { // 获得文本域表单数据
				if ("times".equals(fileItem.getFieldName())) {
					times = fileItem.getString("UTF-8");

				}
				if ("title".equals(fileItem.getFieldName())) {
					title = fileItem.getString("UTF-8");
				}
				if ("discription".equals(fileItem.getFieldName())) {
					discription = fileItem.getString("UTF-8");
				}
				if ("processedit".equals(fileItem.getFieldName())) {
					processedit = fileItem.getString("UTF-8");
				}
				if ("id".equals(fileItem.getFieldName())) {
					id = fileItem.getString("UTF-8");
				}

				if ("defaultFlag".equals(fileItem.getFieldName())) {
					defaultFlag = Long.parseLong(fileItem.getString("UTF-8"));
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
			// 原来的文件名
			pic = System.currentTimeMillis() + (int) (Math.random() * 6 + 2)
					+ path2.substring(path2.indexOf("."), path2.length());
			String distp = path + pic;

			System.out.println(i + "PATH******:" + distp);

			try {
				// 保存文件
				File fileOne = new File(distp);
				fileItem.write(fileOne);

				// 缩放文件
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

				response.setStatus(200);
				if (i == 1)
					zhaopian1 = distp.substring(distp.indexOf("upload"), distp.length());
				else if (i == 2)
					zhaopian2 = distp.substring(distp.indexOf("upload"), distp.length());
				else if (i == 3)
					zhaopian3 = distp.substring(distp.indexOf("upload"), distp.length());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			Myplan m = null;

			if (id != null) {
				m = dao.get(Long.parseLong(id));
			} else {
				m = new Myplan();
			}

			m.setKehuId(k.getKehuId());
			if (times != null && times.length() > 0)
				m.setTimes(times);
			if (title != null && title.length() > 0)
				m.setTitle(title);
			if (discription != null && discription.length() > 0)
				m.setDiscription(discription);
			if (pic != null && pic.length() > 0)
				m.setPic(pic);
			m.setKehuId(k.getKehuId());
			m.setKehuNick(k.getNickname());
			m.setHeadimgurl(k.getHeadimgurl());
			m.setDefaultFlag(defaultFlag);

			// System.out.println("AddplanServlet:"+m.toString());

			if (processedit == null || !processedit.equalsIgnoreCase("t")) {
				m.setCreateDate(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				dao.add(m);
			} else {
				dao.update(m);
			}

			response.sendRedirect("index.html");
		} catch (NumberFormatException e) {
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
