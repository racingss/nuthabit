package com.babycard.util;

import com.babycard.dao.KehuDAO;

public class ImageThread implements Runnable {

	String urlList = null;
	String distUrl = null;
	String headUrl = null;
	long kId = 0;

	public ImageThread(String urlList, String distUrl, long kId,String headUrl) {
		this.urlList = urlList;
		this.distUrl = distUrl;
		this.kId = kId;
		this.headUrl = headUrl;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("download begin:" + urlList);
		try {
			if (new ImageUtil().downloadPicture(urlList, distUrl)) {
				new KehuDAO().updateKehuHeadimg(kId, headUrl);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("download end");
	}

}
