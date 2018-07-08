package com.babycard.dao;

import java.sql.*;

public class Fav {
	long favId = 0;
	long kehuId = 0;
	long cardId = 0;
	Timestamp favDate = null;

	public long getFavId() {
		return favId;
	}

	public void setFavId(long favId) {
		this.favId = favId;
	}

	public long getKehuId() {
		return kehuId;
	}

	public void setKehuId(long kehuId) {
		this.kehuId = kehuId;
	}

	public long getCardId() {
		return cardId;
	}

	public void setCardId(long cardId) {
		this.cardId = cardId;
	}

	public Timestamp getFavDate() {
		return favDate;
	}

	public void setFavDate(Timestamp favDate) {
		this.favDate = favDate;
	}

	public Fav() {

	}

	public Fav(ResultSet rs) {
		try {
			this.setCardId(rs.getLong("cardId"));
			this.setFavDate(rs.getTimestamp("favDate"));
			this.setFavId(rs.getLong("favId"));
			this.setKehuId(rs.getLong("kehuId"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
