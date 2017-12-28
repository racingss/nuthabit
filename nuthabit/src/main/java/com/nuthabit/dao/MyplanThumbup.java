package com.nuthabit.dao;

import java.sql.SQLException;

public class MyplanThumbup {
	private long id = 0;
	private String planId = null;
	private String planDate = null;
	private long experienceId = 0;
	private String kehuId = null;
	private String nickname = null;
	private String headimgurl = null;

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

	public MyplanThumbup() {

	}

	public long getExperienceId() {
		return experienceId;
	}

	public void setExperienceId(long experienceId) {
		this.experienceId = experienceId;
	}

	public MyplanThumbup(java.sql.ResultSet rs) {
		try {
			this.setId(rs.getLong("id"));
			this.setPlanDate(rs.getString("planDate"));
			this.setPlanId(rs.getString("planId"));
			this.setKehuId(rs.getString("kehuId"));
			this.setHeadimgurl(rs.getString("headimgurl"));
			this.setNickname(rs.getString("nickname"));
			this.setExperienceId(rs.getLong("experienceId"));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
