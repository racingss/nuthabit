package adon.word;

import java.sql.SQLException;

public class Myword {
	private long id = 0;
	private String word = null;
	private String kehuId = null;
	private int level = 0;
	private String reviewday = null;
	private long reviewIndex = 0;
	private int reviewFlag = 0;
	private long kId = 0;
	private long bId = 0;

	public long getbId() {
		return bId;
	}

	public void setbId(long bId) {
		this.bId = bId;
	}

	public long getkId() {
		return kId;
	}

	public void setkId(long kId) {
		this.kId = kId;
	}

	public int getReviewFlag() {
		return reviewFlag;
	}

	public void setReviewFlag(int reviewFlag) {
		this.reviewFlag = reviewFlag;
	}

	public long getReviewIndex() {
		return reviewIndex;
	}

	public void setReviewIndex(long reviewIndex) {
		this.reviewIndex = reviewIndex;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getKehuId() {
		return kehuId;
	}

	public void setKehuId(String kehuId) {
		this.kehuId = kehuId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getReviewday() {
		return reviewday;
	}

	public void setReviewday(String reviewday) {
		this.reviewday = reviewday;
	}

	public Myword() {

	}

	public Myword(String kehuId, String word) {
		this.setKehuId(kehuId);
		this.setWord(word);
	}

	public Myword(java.sql.ResultSet rs) {
		try {
			this.setId(rs.getLong("id"));
			this.setKehuId(rs.getString("kehuId"));
			this.setLevel(rs.getInt("level"));
			this.setReviewday(rs.getString("reviewday"));
			this.setWord(rs.getString("word"));
			this.setReviewIndex(rs.getLong("reviewIndex"));
			this.setReviewFlag(rs.getInt("reviewFlag"));
			this.setkId(rs.getLong("kId"));
			this.setbId(rs.getLong("bId"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Myword [id=" + id + ", word=" + word + ", kehuId=" + kehuId + ", level=" + level + ", reviewday="
				+ reviewday + ", reviewIndex=" + reviewIndex + ", reviewFlag=" + reviewFlag + ", kId=" + kId + ", bId="
				+ bId + "]";
	}

}
