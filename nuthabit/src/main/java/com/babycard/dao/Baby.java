package com.babycard.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Baby {
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

	public static String getCurrentYear() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		Date date = new Date();
		return sdf.format(date);
	}

	public static String getCurrentMonth() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		Date date = new Date();
		return sdf.format(date);
	}

	public long getAge() {

		long age = 1;
		long cYear = Long.parseLong(getCurrentYear());
		long cMonth = Long.parseLong(getCurrentMonth());

		if (this.bYear == 0)
			age = 1;
		else {
			age = cYear - this.bYear;

			if (this.bMonth > cMonth)
				age -= 1;

			if (age > 3)
				age = 3;
			if (age < 1)
				age = 1;
		}

		return age;
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

	public static void main(String arg[]) {
		for (int i = 2012; i < 2020; i++) {
			for (int j = 1; j <= 12; j++) {
				Baby b = new Baby();
				b.setbYear(i);
				b.setbMonth(j);
				System.out.println(i + ":"+j+":" + b.getAge());
			}
		}
	}

}
