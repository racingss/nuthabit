package com.babycard.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Sound {
	private long id=0;
	private String soundType=null;
	private String soundUrl=null;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSoundType() {
		return soundType;
	}
	public void setSoundType(String soundType) {
		this.soundType = soundType;
	}
	public String getSoundUrl() {
		return soundUrl;
	}
	public void setSoundUrl(String soundUrl) {
		this.soundUrl = soundUrl;
	}
	
	public Sound(){
		
	}
	
	public Sound(ResultSet rs){
		try {
			this.setId(rs.getLong("id"));
			this.setSoundUrl(rs.getString("soundUrl"));
			this.setSoundType(rs.getString("soundType"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
