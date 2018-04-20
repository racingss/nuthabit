package com.babycard.dao;

import java.sql.SQLException;

public class BindMeaningPic {
	private long meaningId = 0;
	private long picId = 0;

	public long getMeaningId() {
		return meaningId;
	}

	public void setMeaningId(long meaningId) {
		this.meaningId = meaningId;
	}

	public long getPicId() {
		return picId;
	}

	public void setPicId(long picId) {
		this.picId = picId;
	}

	public BindMeaningPic() {

	}

	public BindMeaningPic(java.sql.ResultSet rs) {
		try {
			this.setMeaningId(rs.getLong("meaningId"));
			this.setPicId(rs.getLong("picId"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
