package com.babycard.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Study {
	private long id = 0;
	private long customerId = 0;
	private long cardId = 0;
	private long picId = 0;
	private Timestamp createDate;
	private Timestamp reviewDate;
	private long reviewFlag = 0;
	private long reviewLevel = 0;

	public long getPicId() {
		return picId;
	}

	public void setPicId(long picId) {
		this.picId = picId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public long getCardId() {
		return cardId;
	}

	public void setCardId(long cardId) {
		this.cardId = cardId;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	long INIT_TIME = 1000 * 60;

	public Timestamp getReviewDate() {
		if (reviewDate != null)
			return reviewDate;
		long reviewtime = 0;
		if (reviewLevel == 0) {
			reviewtime = INIT_TIME * 60;
		} else if (reviewLevel == 1) {
			reviewtime = INIT_TIME * 60 * 24;
		} else if (reviewLevel == 2) {
			reviewtime = INIT_TIME * 60 * 24 * 2;
		} else if (reviewLevel == 3) {
			reviewtime = INIT_TIME * 60 * 24 * 4;
		} else if (reviewLevel == 4) {
			reviewtime = INIT_TIME * 60 * 24 * 6;
		} else if (reviewLevel == 5) {
			reviewtime = INIT_TIME * 60 * 24 * 13;
		} else if (reviewLevel == 6) {
			reviewtime = INIT_TIME * 60 * 24 * 29;
		}
		reviewDate = new Timestamp(System.currentTimeMillis() + reviewtime);
		return reviewDate;
	}

	public void setReviewDate(Timestamp reviewDate) {
		this.reviewDate = reviewDate;
	}

	public long getReviewFlag() {
		return reviewFlag;
	}

	public void setReviewFlag(long reviewFlag) {
		this.reviewFlag = reviewFlag;
	}

	public long getReviewLevel() {
		return reviewLevel;
	}

	public void setReviewLevel(long reviewLevel) {
		this.reviewLevel = reviewLevel;
	}

	public Study() {

	}

	public Study(ResultSet rs) {
		try {
			this.setCardId(rs.getLong("cardId"));
			this.setCreateDate(rs.getTimestamp("createDate"));
			this.setCustomerId(rs.getLong("customerId"));
			this.setId(rs.getLong("id"));
			this.setReviewDate(rs.getTimestamp("reviewDate"));
			this.setReviewFlag(rs.getLong("reviewFlag"));
			this.setReviewLevel(rs.getLong("reviewLevel"));
			this.setPicId(rs.getLong("picId"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Study [id=" + id + ", customerId=" + customerId + ", cardId=" + cardId + ", createDate=" + createDate
				+ ", reviewDate=" + this.getReviewDate() + ", reviewFlag=" + reviewFlag + ", reviewLevel=" + reviewLevel
				+ ", INIT_TIME=" + INIT_TIME + "]";
	}

	public static void main(String arg[]) {
		for (int i = 0; i < 10; i++) {
			Study s = new Study();
			s.setCreateDate(new Timestamp(System.currentTimeMillis()));
			s.setReviewLevel(i);
			System.out.println(s.toString());
		}

	}

}
