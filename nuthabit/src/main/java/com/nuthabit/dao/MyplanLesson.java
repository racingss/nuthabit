package com.nuthabit.dao;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

public class MyplanLesson {
	private long id = 0;
	private String lessonId = null;
	private String kehuId = null;
	private String kehuNick = null;
	private String headimgurl = null;
	private String title = null;
	private String discription = null;
	private long joinNumber = 0;
	private String pic = null;
	private long price = 0;
	private String createDate = null;
	private long score = 0;
	private long status = 0;

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLessonId() {
		if (lessonId != null)
			return lessonId;

		Calendar c1 = Calendar.getInstance();
		c1.setTime(new Date());
		lessonId = (new StringBuilder("")).append(c1.get(7 + 1) * c1.get(12)).append("9")
				.append(System.currentTimeMillis()).toString();
		return lessonId;
	}

	public void setLessonId(String lessonId) {
		this.lessonId = lessonId;
	}

	public String getKehuId() {
		return kehuId;
	}

	public void setKehuId(String kehuId) {
		this.kehuId = kehuId;
	}

	public String getKehuNick() {
		return kehuNick;
	}

	public void setKehuNick(String kehuNick) {
		this.kehuNick = kehuNick;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public long getJoinNumber() {
		return joinNumber;
	}

	public void setJoinNumber(long joinNumber) {
		this.joinNumber = joinNumber;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String creatDate) {
		this.createDate = creatDate;
	}

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}

	public MyplanLesson() {

	}

	public MyplanLesson(java.sql.ResultSet rs) {
		try {
			this.setCreateDate(rs.getString("createDate"));
			this.setDiscription(rs.getString("discription"));
			this.setHeadimgurl(rs.getString("headimgurl"));
			this.setId(rs.getLong("id"));
			this.setJoinNumber(rs.getLong("joinNumber"));
			this.setKehuId(rs.getString("kehuId"));
			this.setKehuNick(rs.getString("kehuNick"));
			this.setLessonId(rs.getString("lessonId"));
			this.setPic(rs.getString("pic"));
			this.setPrice(rs.getLong("price"));
			this.setScore(rs.getLong("score"));
			this.setTitle(rs.getString("title"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}