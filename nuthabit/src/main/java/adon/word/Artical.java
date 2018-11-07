package adon.word;

import java.sql.SQLException;
import java.util.Collection;

public class Artical {

	private long id = 0;
	private String title = null;
	private String url = null;
	private long wordNums = 0;
	private long totalNums = 0;
	public Collection articalLineColl=null;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getWordNums() {
		return wordNums;
	}

	public void setWordNums(long wordNums) {
		this.wordNums = wordNums;
	}

	public long getTotalNums() {
		return totalNums;
	}

	public void setTotalNums(long totalNums) {
		this.totalNums = totalNums;
	}

	public Artical(){
		
	}
	
	public Artical(java.sql.ResultSet rs){
		try {
			this.setId(rs.getLong("id"));
			this.setTitle(rs.getString("title"));
			this.setTotalNums(rs.getLong("totalNums"));
			this.setUrl(rs.getString("url"));
			this.setWordNums(rs.getLong("wordNums"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
