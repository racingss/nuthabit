package adon.word;

import java.sql.SQLException;

public class MywordHistory {
	private long id = 0;
	private long mwId = 0;
	private String reviewDay = null;
	private int reviewProcess = 0;
	private int level = 0;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getMwId() {
		return mwId;
	}

	public void setMwId(long mwId) {
		this.mwId = mwId;
	}

	public String getReviewDay() {
		return reviewDay;
	}

	public void setReviewDay(String reviewDay) {
		this.reviewDay = reviewDay;
	}

	public int getReviewProcess() {
		return reviewProcess;
	}

	public void setReviewProcess(int reviewProcess) {
		this.reviewProcess = reviewProcess;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public MywordHistory() {

	}

	public MywordHistory(java.sql.ResultSet rs) {
		try {
			this.setId(rs.getLong("id"));
			this.setLevel(rs.getInt("level"));
			this.setMwId(rs.getLong("mwId"));
			this.setReviewDay(rs.getString("reviewDay"));
			this.setReviewProcess(rs.getInt("reviewProcess"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
