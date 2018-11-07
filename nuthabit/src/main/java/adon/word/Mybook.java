package adon.word;

import java.sql.SQLException;

public class Mybook {
	private long id=0;
	private long bookId=0;
	private String kehuId=null;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getBookId() {
		return bookId;
	}
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
	public String getKehuId() {
		return kehuId;
	}
	public void setKehuId(String kehuId) {
		this.kehuId = kehuId;
	}
	
	public Mybook(){
		
	}
	
	public Mybook(java.sql.ResultSet rs){
		try {
			this.setBookId(rs.getLong("bookId"));
			this.setId(rs.getLong("id"));
			this.setKehuId(rs.getString("kehuId"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
