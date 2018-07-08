package com.babycard.dao;

import java.sql.*;

public class Search {
	private long id = 0;
	private String qString = null;
	private long kId = 0;
	private long rCount = 0;
	private Timestamp qDate = null;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getqString() {
		return qString;
	}

	public void setqString(String qString) {
		this.qString = qString;
	}

	public long getkId() {
		return kId;
	}

	public void setkId(long kId) {
		this.kId = kId;
	}

	public long getrCount() {
		return rCount;
	}

	public void setrCount(long rCount) {
		this.rCount = rCount;
	}

	public Timestamp getqDate() {
		return qDate;
	}

	public void setqDate(Timestamp qDate) {
		this.qDate = qDate;
	}

	public Search() {

	}

	public Search(java.sql.ResultSet rs) {
		try {
			this.setId(rs.getLong("id"));
			this.setkId(rs.getLong("kId"));
			this.setqDate(rs.getTimestamp("qDate"));
			this.setqString(rs.getString("qString"));
			this.setrCount(rs.getLong("rCount"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
