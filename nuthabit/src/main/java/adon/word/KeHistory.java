package adon.word;

import java.sql.SQLException;

public class KeHistory {
	private long id=0;
	private long keId=0;
	private String kehuId=null;
	private String reviewDay = null;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getKeId() {
		return keId;
	}
	public void setKeId(long keId) {
		this.keId = keId;
	}
	public String getKehuId() {
		return kehuId;
	}
	public void setKehuId(String kehuId) {
		this.kehuId = kehuId;
	}
	public String getReviewDay() {
		return reviewDay;
	}
	public void setReviewDay(String reviewDay) {
		this.reviewDay = reviewDay;
	}
	
	public KeHistory(){
		
	}
	
	public KeHistory(java.sql.ResultSet rs){
		try {
			this.setId(rs.getLong("id"));
			this.setKehuId(rs.getString("kehuId"));
			this.setKeId(rs.getLong("keId"));
			this.setReviewDay(rs.getString("reviewDay"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
