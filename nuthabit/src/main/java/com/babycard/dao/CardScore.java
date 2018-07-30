package com.babycard.dao;

import java.sql.*;

public class CardScore {

	private long id = 0;
	private long cardId = 0;
	private long kId = 0;
	private long score = 0;
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

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}

	public Timestamp getOpTime() {
		return opTime;
	}

	public void setOpTime(Timestamp opTime) {
		this.opTime = opTime;
	}

	public CardScore() {

	}

	public CardScore(ResultSet rs) {
		try {
			this.setCardId(rs.getLong("cardId"));
			this.setId(rs.getLong("id"));
			this.setkId(rs.getLong("kId"));
			this.setOpTime(rs.getTimestamp("opTime"));
			this.setScore(rs.getLong("score"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "CardScore [id=" + id + ", cardId=" + cardId + ", kId=" + kId + ", score=" + score + ", opTime=" + opTime
				+ "]";
	}

}
