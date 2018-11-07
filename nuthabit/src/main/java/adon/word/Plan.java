package adon.word;

import java.sql.SQLException;

public class Plan {
	private long id = 0;
	private String kehuId = null;
	private long bookId = 0;
	private int planCount = 0;
	private int studyFlag = 0;
	private int bookIndex = 0;
	private int todayIndex = 0;
	private String currentDay = null;

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

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public int getPlanCount() {
		return planCount;
	}

	public void setPlanCount(int planCount) {
		this.planCount = planCount;
	}

	public int getStudyFlag() {
		return studyFlag;
	}

	public void setStudyFlag(int studyFlag) {
		this.studyFlag = studyFlag;
	}

	public int getBookIndex() {
		return bookIndex;
	}

	public void setBookIndex(int bookIndex) {
		this.bookIndex = bookIndex;
	}

	public int getTodayIndex() {
		return todayIndex;
	}

	public void setTodayIndex(int todayIndex) {
		this.todayIndex = todayIndex;
	}

	public String getCurrentDay() {
		return currentDay;
	}

	public void setCurrentDay(String currentDay) {
		this.currentDay = currentDay;
	}

	public Plan() {

	}

	public Plan(java.sql.ResultSet rs) {
		try {
			this.setBookId(rs.getLong("bookId"));
			this.setBookIndex(rs.getInt("bookIndex"));
			this.setCurrentDay(rs.getString("currentDay"));
			this.setId(rs.getLong("id"));
			this.setKehuId(rs.getString("kehuId"));
			this.setPlanCount(rs.getInt("planCount"));
			this.setStudyFlag(rs.getInt("studyFlag"));
			this.setTodayIndex(rs.getInt("todayIndex"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
