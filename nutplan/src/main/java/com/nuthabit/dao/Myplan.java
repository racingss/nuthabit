package com.nuthabit.dao;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

public class Myplan {
	private long id = 0;
	private String kehuId = null;
	private String title = null;
	private String lastDate = null;
	private String times = null;
	private long continued = 0;
	private String discription = null;
	private String pic = null;
	private String planId = null;
	private String kehuNick = null;
	private String beginDate = null;
	private String endDate = null;
	private long privacyFlag = 0;
	private long serviceFlag = 0;

	public long getPrivacyFlag() {
		return privacyFlag;
	}

	public void setPrivacyFlag(long privacyFlag) {
		this.privacyFlag = privacyFlag;
	}

	public long getServiceFlag() {
		return serviceFlag;
	}

	public void setServiceFlag(long serviceFlag) {
		this.serviceFlag = serviceFlag;
	}

	public String getPlanId() {
		if (planId != null)
			return planId;

		Calendar c1 = Calendar.getInstance();
		c1.setTime(new Date());
		return (new StringBuilder("AUTO")).append(c1.get(7 + 1) * c1.get(12)).append("9")
				.append(System.currentTimeMillis()).toString();

	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getKehuNick() {
		return kehuNick;
	}

	public void setKehuNick(String kehuNick) {
		this.kehuNick = kehuNick;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getKehuId() {
		return kehuId;
	}

	public void setKehuId(String kehuId) {
		this.kehuId = kehuId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLastDate() {
		return lastDate;
	}

	public boolean isToday() {
		String today = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new Date());
		if (lastDate == null || lastDate.equals("null") || !lastDate.substring(0, 10).equals(today))
			return true;
		return false;
	}

	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public long getContinued() {
		return continued;
	}

	public void setContinued(long continued) {
		this.continued = continued;
	}

	public Myplan() {

	}

	public Myplan(java.sql.ResultSet rs) {
		try {
			this.setContinued(rs.getLong("continued"));
			this.setId(rs.getLong("id"));
			this.setKehuId(rs.getString("kehuId"));
			this.setLastDate(rs.getString("lastDate"));
			this.setTimes(rs.getString("times"));
			this.setTitle(rs.getString("title"));
			this.setDiscription(rs.getString("discription"));
			this.setPic(rs.getString("pic"));
			this.setPlanId(rs.getString("planId"));
			this.setKehuNick(rs.getString("kehuNick"));
			this.setBeginDate(rs.getString("beginDate"));
			this.setEndDate(rs.getString("endDate"));
			this.setPrivacyFlag(rs.getLong("privacyFlag"));
			this.setServiceFlag(rs.getLong("serviceFlag"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
