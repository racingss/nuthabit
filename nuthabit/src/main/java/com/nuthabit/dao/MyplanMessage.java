package com.nuthabit.dao;

import java.sql.SQLException;

public class MyplanMessage {
	private long id = 0;
	private String kehuId = null;
	private String sourceId = null;
	private String sourceName = null;
	private String content = null;
	private long mainId = 0;
	private java.sql.Timestamp msgTime = null;
	private long status = 0;
	private String sourcehead = null;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getKehuId() {
		return kehuId;
	}

	public void setKehuId(String kehuId) {
		this.kehuId = kehuId;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getMainId() {
		return mainId;
	}

	public void setMainId(long mainId) {
		this.mainId = mainId;
	}

	public java.sql.Timestamp getMsgTime() {
		return msgTime;
	}

	public void setMsgTime(java.sql.Timestamp msgTime) {
		this.msgTime = msgTime;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public String getSourcehead() {
		return sourcehead;
	}

	public void setSourcehead(String sourcehead) {
		this.sourcehead = sourcehead;
	}

	public MyplanMessage (){
		
	}
	
	public MyplanMessage(java.sql.ResultSet rs){
		try {
			this.setContent(rs.getString("content"));
			this.setId(rs.getLong("id"));
			this.setKehuId(rs.getString("kehuId"));
			this.setMainId(rs.getLong("mainId"));
			this.setMsgTime(rs.getTimestamp("msgTime"));
			this.setSourcehead(rs.getString("sourcehead"));
			this.setSourceId(rs.getString("sourceId"));
			this.setSourceName(rs.getString("sourceName"));
			this.setStatus(rs.getLong("status"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
