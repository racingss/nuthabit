package com.babycard.dao;

import java.sql.*;

public class History {
	private long historyId=0;
	private long kId=0;
	private long cardId=0;
	private Timestamp readDate=null;
	private long finishFlag=0;
	public long getHistoryId() {
		return historyId;
	}
	public void setHistoryId(long historyId) {
		this.historyId = historyId;
	}
	public long getKId() {
		return kId;
	}
	public void setKId(long kId) {
		this.kId = kId;
	}
	public long getCardId() {
		return cardId;
	}
	public void setCardId(long cardId) {
		this.cardId = cardId;
	}
	public Timestamp getReadDate() {
		return readDate;
	}
	public void setReadDate(Timestamp readDate) {
		this.readDate = readDate;
	}
	public long getFinishFlag() {
		return finishFlag;
	}
	public void setFinishFlag(long finishFlag) {
		this.finishFlag = finishFlag;
	}
	
	public History(){
		
	}
	
	public History(java.sql.ResultSet rs){
		try {
			this.setCardId(rs.getLong("cardId"));
			this.setFinishFlag(rs.getLong("finishFlag"));
			this.setHistoryId(rs.getLong("historyId"));
			this.setKId(rs.getLong("kId"));
			this.setReadDate(rs.getTimestamp("readDate"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
