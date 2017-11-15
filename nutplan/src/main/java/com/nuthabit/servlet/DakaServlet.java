package com.nuthabit.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

import com.nuthabit.dao.Myplan;
import com.nuthabit.dao.MyplanDAO;
import com.nuthabit.dao.MyplanHistory;

@WebServlet("/myplan/daka.html")
public class DakaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final long MAX_SIZE = 10 * 1024 * 1024;// 设置上传文件最大为 10M

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DakaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String kehuId = "adon";

		MyplanDAO dao = new MyplanDAO();

		// TODO Auto-generated method stub
		response.setContentType("text/html");
		// 设置字符编码为UTF-8, 这样支持汉字显示
		response.setCharacterEncoding("UTF-8");
		// 实例化一个硬盘文件工厂,用来配置上传组件ServletFileUpload
		DiskFileItemFactory dfif = new DiskFileItemFactory();
		dfif.setSizeThreshold(4096);// 设置上传文件时用于临时存放文件的内存大小,这里是4K.多于的部分将临时存在硬盘
		// String ROOT_PATH = ;// 缓存临时文件夹
		String path = request.getSession().getServletContext().getRealPath("/") + "myplan/upload/historypic/";
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
		String pic1 = "";
		String pic2 = "";
		String pic3 = "";
		String pic = null;

		String review = null;
		String status = null;
		String id = null;

		while (fileItr.hasNext() && i <= 3) {

			FileItem fileItem = null;
			String path2 = null;
			long size = 0;
			// 得到当前文件
			fileItem = (FileItem) fileItr.next();
			if (fileItem.isFormField()) { // 获得文本域表单数据
				if ("review".equals(fileItem.getFieldName())) {
					review = fileItem.getString("UTF-8");

				}
				if ("status".equals(fileItem.getFieldName())) {
					status = fileItem.getString("UTF-8");
				}
				if ("id".equals(fileItem.getFieldName())) {
					id = fileItem.getString("UTF-8");
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
			if (i == 1)
				pic1 = pic;
			else if (i == 2)
				pic2 = pic;
			else if (i == 3)
				pic3 = pic;

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

		Myplan m = dao.get(Long.parseLong(id));
		MyplanHistory h = new MyplanHistory();
		h.setPic1(pic1);
		h.setPic2(pic2);
		h.setPic3(pic3);
		h.setReview(review);
		h.setStatus(Long.parseLong(status));
		dao.process(m, h);

		response.sendRedirect("index.html");
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
