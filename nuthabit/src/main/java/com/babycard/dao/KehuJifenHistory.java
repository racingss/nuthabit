package com.babycard.dao;

import java.sql.*;

public class KehuJifenHistory {
	private long id=0;
	private long kId=0;
	private long point=0;
	private Timestamp jifeDate=null;
	private String description=null;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getkId() {
		return kId;
	}
	public void setkId(long kId) {
		this.kId = kId;
	}
	public long getPoint() {
		return point;
	}
	public void setPoint(long point) {
		this.point = point;
	}
	public Timestamp getJifeDate() {
		return jifeDate;
	}
	public void setJifeDate(Timestamp jifeDate) {
		this.jifeDate = jifeDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public KehuJifenHistory(){
		
	}
	
	public KehuJifenHistory(ResultSet rs){
		try {
			this.setDescription(rs.getString("description"));
			this.setId(rs.getLong("id"));
			this.setJifeDate(rs.getTimestamp("jifeDate"));
			this.setkId(rs.getLong("kId"));
			this.setPoint(rs.getLong("point"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
