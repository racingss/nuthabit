package com.babycard.dao;

import java.sql.SQLException;

public class BindCardTag {

	private long cardId = 0;
	private long tagId = 0;

	public long getCardId() {
		return cardId;
	}

	public void setCardId(long cardId) {
		this.cardId = cardId;
	}

	public long getTagId() {
		return tagId;
	}

	public void setTagId(long tagId) {
		this.tagId = tagId;
	}

	public BindCardTag() {

	}

	public BindCardTag(java.sql.ResultSet rs) {
		try {
			this.setCardId(rs.getLong("cardId"));
			this.setTagId(rs.getLong("tagId"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
