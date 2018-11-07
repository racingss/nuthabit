package adon.word;

import java.sql.SQLException;

public class ArticalLineWord {
	private long id = 0;
	private long articalId = 0;
	private long line = 0;
	private String word = null;

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

	public long getLine() {
		return line;
	}

	public void setLine(long line) {
		this.line = line;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public ArticalLineWord() {

	}

	public ArticalLineWord(java.sql.ResultSet rs) {
		try {
			this.setId(rs.getLong("id"));
			this.setArticalId(rs.getLong("articalId"));
			this.setLine(rs.getLong("line"));
			this.setWord(rs.getString("word"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
