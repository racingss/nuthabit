package adon.word;

import java.sql.SQLException;

public class ArticalWord {
	private long id = 0;
	private long articalId = 0;
	private String word = null;
	private long nums = 0;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getArticalId() {
		return articalId;
	}

	public void setArticalId(long articalId) {
		this.articalId = articalId;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public long getNums() {
		return nums;
	}

	public void setNums(long nums) {
		this.nums = nums;
	}
	
	public ArticalWord(){
		
	}
	
	public ArticalWord(java.sql.ResultSet rs){
		try {
			this.setId(rs.getLong("id"));
			this.setArticalId(rs.getLong("articalId"));
			this.setNums(rs.getLong("nums"));
			this.setWord(rs.getString("word"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
