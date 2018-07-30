package com.babycard.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CardComm {

	private long id = 0;
	private long cardId = 0;
	private long kId = 0;
	private String comm = null;
	private Timestamp opTime = null;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCardId() {
		return cardId;
	}

	public void setCardId(long cardId) {
		this.cardId = cardId;
	}

	public long getkId() {
		return kId;
	}

	public void setkId(long kId) {
		this.kId = kId;
	}

	public String getComm() {
		return comm;
	}

	public void setComm(String comm) {
		this.comm = comm;
	}

	public Timestamp getOpTime() {
		return opTime;
	}

	public void setOpTime(Timestamp opTime) {
		this.opTime = opTime;
	}

	public CardComm() {

	}

	public CardComm(ResultSet rs) {
		try {
			this.setCardId(rs.getLong("cardId"));
			this.setId(rs.getLong("id"));
			this.setkId(rs.getLong("kId"));
			this.setOpTime(rs.getTimestamp("opTime"));
			this.setComm(rs.getString("comm"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
