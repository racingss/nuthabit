package com.nuthabit.dao;

import java.sql.SQLException;

public class MyplanHistory {
	private long id = 0;
	private String planId = null;
	private String planDate = null;
	private String review = null;
	private long status = 0;
	private String pic1 = null;
	private String pic2 = null;
	private String pic3 = null;
	private long privacyFlag = 0;

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

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
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

	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}

	public MyplanHistory() {

	}

	public MyplanHistory(java.sql.ResultSet rs) {
		try {
			this.setId(rs.getLong("id"));
			this.setPlanDate(rs.getString("planDate"));
			this.setPlanId(rs.getString("planId"));
			this.setReview(rs.getString("review"));
			this.setStatus(rs.getLong("status"));
			this.setPic1(rs.getString("pic1"));
			this.setPic2(rs.getString("pic2"));
			this.setPic3(rs.getString("pic3"));
			this.setPrivacyFlag(rs.getLong("privacyFlag"));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
