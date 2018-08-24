package com.babycard.dao;

import java.sql.*;

public class Shuyishu {
	private long id = 0;
	private String bakUrl = null;
	private String imgUrl = null;
	private long clickNum = 0;

	public long getClickNum() {
		return clickNum;
	}

	public void setClickNum(long clickNum) {
		this.clickNum = clickNum;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBakUrl() {
		return bakUrl;
	}

	public void setBakUrl(String bakUrl) {
		this.bakUrl = bakUrl;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Shuyishu() {

	}

	public Shuyishu(ResultSet rs) {
		try {
			this.setBakUrl(rs.getString("bakUrl"));
			this.setId(rs.getLong("id"));
			this.setImgUrl(rs.getString("imgUrl"));
			this.setClickNum(rs.getLong("clickNum"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
