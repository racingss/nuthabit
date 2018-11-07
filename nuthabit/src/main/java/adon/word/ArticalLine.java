package adon.word;

import java.sql.SQLException;

public class ArticalLine {
	private long id = 0;
	private long articalId = 0;
	private long line = 0;
	private String detail = null;

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

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public ArticalLine() {

	}

	public ArticalLine(java.sql.ResultSet rs) {
		try {
			this.setArticalId(rs.getLong("articalId"));
			this.setDetail(rs.getString("detail"));
			this.setId(rs.getLong("id"));
			this.setLine(rs.getLong("line"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
