package com.babycard.dao;

import java.sql.*;

public class Daka {
	private long id = 0;
	private long kId = 0;
	private java.sql.Timestamp dakaTime = null;
	private long dakaNum = 0;

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

	public java.sql.Timestamp getDakaTime() {
		return dakaTime;
	}

	public void setDakaTime(java.sql.Timestamp dakaTime) {
		this.dakaTime = dakaTime;
	}

	public long getDakaNum() {
		return dakaNum;
	}

	public void setDakaNum(long dakaNum) {
		this.dakaNum = dakaNum;
	}

	public Daka() {

	}

	public Daka(ResultSet rs) {
		try {
			this.setDakaNum(rs.getLong("dakaNum"));
			this.setDakaTime(rs.getTimestamp("dakaTime"));
			this.setId(rs.getLong("id"));
			this.setkId(rs.getLong("kId"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
