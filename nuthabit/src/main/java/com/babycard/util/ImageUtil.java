package com.babycard.util;

import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import javax.imageio.*;

public class ImageUtil {
	public boolean downloadPicture(String urlList, String distUrl) {
		URL url = null;
		int imageNumber = 0;

		try {
			url = new URL(urlList);
			DataInputStream dataInputStream = new DataInputStream(url.openStream());

			String imageName = distUrl;

			FileOutputStream fileOutputStream = new FileOutputStream(new File(imageName));
			ByteArrayOutputStream output = new ByteArrayOutputStream();

			byte[] buffer = new byte[1024];
			int length;

			while ((length = dataInputStream.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}
			byte[] context = output.toByteArray();
			fileOutputStream.write(output.toByteArray());
			dataInputStream.close();
			fileOutputStream.close();
			return true;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * @Title: 构造图片
	 * @Description: 生成水印并返回java.awt.image.BufferedImage
	 * @param file
	 *            源文件(图片)
	 * @param waterFile
	 *            水印文件(图片)
	 * @param x
	 *            距离右下角的X偏移量
	 * @param y
	 *            距离右下角的Y偏移量
	 * @param alpha
	 *            透明度, 选择值从0.0~1.0: 完全透明~完全不透明
	 * @return BufferedImage
	 * @throws IOException
	 */
	public void watermark(File file, File waterFile, int x, int y, float alpha, int width, int height, String savePath)
			throws IOException {
		// 获取底图
		BufferedImage buffImg = ImageIO.read(file);
		// 获取层图
		BufferedImage waterImg = ImageIO.read(waterFile);
		// 创建Graphics2D对象，用在底图对象上绘图
		Graphics2D g2d = buffImg.createGraphics();
		int waterImgWidth = waterImg.getWidth();// 获取层图的宽度
		int waterImgHeight = waterImg.getHeight();// 获取层图的高度
		// 在图形和图像中实现混合和透明效果
		// g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
		// alpha));
		// 绘制
		g2d.drawImage(waterImg, x, y, width, height, null);
		g2d.dispose();// 释放图形上下文使用的系统资源
		int temp = savePath.lastIndexOf(".") + 1;
		try {
			ImageIO.write(buffImg, savePath.substring(temp), new File(savePath));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void round(String filename) throws IOException {
		BufferedImage bi1 = ImageIO.read(new File(filename));
		// 根据需要是否使用 BufferedImage.TYPE_INT_ARGB
		BufferedImage bi2 = new BufferedImage(bi1.getWidth(), bi1.getHeight(), BufferedImage.TYPE_INT_RGB);
		Ellipse2D.Double shape = new Ellipse2D.Double(0, 0, bi1.getWidth(), bi1.getHeight());
		Graphics2D g2 = bi2.createGraphics();
		g2.setBackground(Color.WHITE);
		g2.fill(new Rectangle(bi2.getWidth(), bi2.getHeight()));
		g2.setClip(shape);
		// 使用 setRenderingHint 设置抗锯齿
		g2.drawImage(bi1, 0, 0, null);
		g2.dispose();

		try {
			ImageIO.write(bi2, "jpg", new File(filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void convert(String source, String formatName, String result) {
		try {
			File f = new File(source);
			f.canRead();
			BufferedImage src = ImageIO.read(f);
			ImageIO.write(src, formatName, new File(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ImageUtil util = new ImageUtil();
		util.downloadPicture("http://res-tts.iciba.com/a/0/5/a05b295dab690e4567518beac9ba8c95.mp3",
				"/Users/wangjunwu/Documents/bigfish.mp3");

		// try {
		// String sourceFilePath = "/Users/wangjunwu/Desktop/test.jpeg";
		// String waterFilePath = "/Users/wangjunwu/Desktop/testhead.jpg";
		// String saveFilePath = "/Users/wangjunwu/Desktop/testhaibao.jpg";
		// ImageUtil newImageUtils = new ImageUtil();
		// newImageUtils.round(waterFilePath);
		// // 构建叠加层
		// BufferedImage buffImg = newImageUtils.watermark(new
		// File(sourceFilePath), new File(waterFilePath), 70, 70, 1.0f);
		// // 输出水印图片
		// newImageUtils.generateWaterFile(buffImg, saveFilePath);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//

		// ImageDownload.downloadPicture(
		// "http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTL2HykKnicSLY0ojDL0wezTiaJj0rw46rDclskgV4SNWXiawiaccrACRwezrLAZzGLHkia3krJnIPFODrQ/132");
	}

}
