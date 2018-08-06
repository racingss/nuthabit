package com.babycard.dao;

import java.sql.*;

public class Tutorial {
	public static final long CARDPLAY = 0;
	public static final long MAIN = 1;

	private long id=0;
	private long kId=0;
	private Timestamp tutorialTime=null;
	private long tutorialType=0;
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
	public Timestamp getTutorialTime() {
		return tutorialTime;
	}
	public void setTutorialTime(Timestamp tutorialTime) {
		this.tutorialTime = tutorialTime;
	}
	public long getTutorialType() {
		return tutorialType;
	}
	public void setTutorialType(long tutorialType) {
		this.tutorialType = tutorialType;
	}
	
	public Tutorial(){
		
	}
	
	public Tutorial(java.sql.ResultSet rs){
		try {
			this.setId(rs.getLong("id"));
			this.setkId(rs.getLong("kId"));
			this.setTutorialTime(rs.getTimestamp("tutorialTime"));
			this.setTutorialType(rs.getLong("tutorialType"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
