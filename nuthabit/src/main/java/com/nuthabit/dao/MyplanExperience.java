package com.nuthabit.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

public class MyplanExperience {
	private long id = 0;
	private String planId = null;
	private String planDate = null;
	private String review = null;
	private long mainId = 0;
	private String pic1 = null;
	private String pic2 = null;
	private String pic3 = null;
	private long privacyFlag = 0;
	private String kehuId = null;
	private String nickname = null;
	private String headimgurl = null;
	private long thumbup = 0;

	public long getThumbup() {
		return thumbup;
	}

	public void setThumbup(long thumbup) {
		this.thumbup = thumbup;
	}

	public String getKehuId() {
		return kehuId;
	}

	public void setKehuId(String kehuId) {
		this.kehuId = kehuId;
	}

	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public long getPrivacyFlag() {
		return privacyFlag;
	}

	public void setPrivacyFlag(long privacyFlag) {
		this.privacyFlag = privacyFlag;
	}

	public String getPic1() {
		return pic1;
	}

	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}

	public String getPic2() {
		return pic2;
	}

	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}

	public String getPic3() {
		return pic3;
	}

	public void setPic3(String pic3) {
		this.pic3 = pic3;
	}

	public long getMainId() {
		return mainId;
	}

	public void setMainId(long mainId) {
		this.mainId = mainId;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getPlanDate() {
		return planDate;
	}

	/*
	 * todolist 临时使用
	 */
	public String getPlanTitle() {
		if (planId == null)
			return "";
		Myplan m = new MyplanDAO().get(planId);
		if (m != null)
			return m.getTitle();
		return "";
	}

	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}

	public MyplanExperience() {

	}

	public MyplanExperience(java.sql.ResultSet rs) {
		try {
			this.setId(rs.getLong("id"));
			this.setPlanDate(rs.getString("planDate"));
			this.setPlanId(rs.getString("planId"));
			this.setReview(rs.getString("review"));
			this.setMainId(rs.getLong("mainId"));
			this.setPic1(rs.getString("pic1"));
			this.setPic2(rs.getString("pic2"));
			this.setPic3(rs.getString("pic3"));
			this.setPrivacyFlag(rs.getLong("privacyFlag"));
			this.setKehuId(rs.getString("kehuId"));
			this.setHeadimgurl(rs.getString("headimgurl"));
			this.setNickname(rs.getString("nickname"));
			this.setThumbup(rs.getLong("thumbup"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
