package com.babycard.dao;

import java.sql.*;

public class Voice {
	private long id = 0;
	private long cardId = 0;
	private long picId = 0;
	private long kId = 0;
	private long status = 0;
	private String voiceUrl = null;

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

	public long getPicId() {
		return picId;
	}

	public void setPicId(long picId) {
		this.picId = picId;
	}

	public long getkId() {
		return kId;
	}

	public void setkId(long kId) {
		this.kId = kId;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public String getVoiceUrl() {
		return voiceUrl;
	}

	public void setVoiceUrl(String voiceUrl) {
		this.voiceUrl = voiceUrl;
	}

	public Voice() {

	}

	public Voice(ResultSet rs) {
		try {
			this.setCardId(rs.getLong("cardId"));
			this.setId(rs.getLong("id"));
			this.setkId(rs.getLong("kId"));
			this.setPicId(rs.getLong("picId"));
			this.setStatus(rs.getLong("status"));
			this.setVoiceUrl(rs.getString("voiceUrl"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Voice [id=" + id + ", cardId=" + cardId + ", picId=" + picId + ", kId=" + kId + ", status=" + status
				+ ", voiceUrl=" + voiceUrl + "]";
	}
	
	
}
