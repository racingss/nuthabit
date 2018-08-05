package com.babycard.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Baby {
	private static final long YEAR = 2018;
	private long babyId = 0;
	private long kId = 0;
	private String babyName = null;
	private long bYear = 0;
	private long bMonth = 0;
	private long bDay = 0;
	private String headImg = null;
	private long sex = 0;

	public long getSex() {
		return sex;
	}

	public void setSex(long sex) {
		this.sex = sex;
	}

	public long getBabyId() {
		return babyId;
	}

	public void setBabyId(long babyId) {
		this.babyId = babyId;
	}

	public long getkId() {
		return kId;
	}

	public void setkId(long kId) {
		this.kId = kId;
	}

	public String getBabyName() {
		return babyName;
	}

	public void setBabyName(String babyName) {
		this.babyName = babyName;
	}

	public long getbYear() {
		return bYear;
	}

	public void setbYear(long bYear) {
		this.bYear = bYear;
	}

	public long getbMonth() {
		return bMonth;
	}

	public void setbMonth(long bMonth) {
		this.bMonth = bMonth;
	}

	public long getbDay() {
		return bDay;
	}

	public void setbDay(long bDay) {
		this.bDay = bDay;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public long getAge(){
		if (this.bYear == 0)
			return 0;
		return YEAR - this.bYear;
	}

	@Override
	public String toString() {
		return "Baby [babyId=" + babyId + ", kId=" + kId + ", babyName=" + babyName + ", bYear=" + bYear + ", bMonth="
				+ bMonth + ", bDay=" + bDay + ", headImg=" + headImg + "]";
	}

	public Baby() {

	}

	public Baby(ResultSet rs) {
		try {
			this.setBabyId(rs.getLong("babyId"));
			this.setBabyName(rs.getString("babyName"));
			this.setbDay(rs.getLong("bDay"));
			this.setbMonth(rs.getLong("bMonth"));
			this.setbYear(rs.getLong("bYear"));
			this.setHeadImg(rs.getString("headImg"));
			this.setSex(rs.getLong("sex"));
			this.setkId(rs.getLong("kId"));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
