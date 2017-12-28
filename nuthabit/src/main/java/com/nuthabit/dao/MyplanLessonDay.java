package com.nuthabit.dao;

import java.sql.SQLException;

public class MyplanLessonDay {
	private long id = 0;
	private String lessonId = null;
	private String title = null;
	private String discription = null;
	private long status = 0;
	private long dayIndex = 0;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLessonId() {
		return lessonId;
	}

	public void setLessonId(String lessonId) {
		this.lessonId = lessonId;
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

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public long getDayIndex() {
		return dayIndex;
	}

	public void setDayIndex(long dayIndex) {
		this.dayIndex = dayIndex;
	}

	public MyplanLessonDay() {

	}

	public MyplanLessonDay(java.sql.ResultSet rs) {
		try {
			this.setDayIndex(rs.getLong("dayIndex"));
			this.setDiscription(rs.getString("discription"));
			this.setId(rs.getLong("id"));
			this.setLessonId(rs.getString("lessonId"));
			this.setStatus(rs.getLong("status"));
			this.setTitle(rs.getString("title"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
