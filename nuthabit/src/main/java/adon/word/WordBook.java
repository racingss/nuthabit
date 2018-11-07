package adon.word;

import java.sql.SQLException;

public class WordBook {
	private long id = 0;
	private long bookId = 0;
	private String word = null;
	private String ke = null;

	public String getKe() {
		return ke;
	}

	public void setKe(String ke) {
		this.ke = ke;
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

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public WordBook() {

	}

	public WordBook(java.sql.ResultSet rs) {
		try {
			this.setBookId(rs.getLong("bookId"));
			this.setId(rs.getLong("id"));
			this.setWord(rs.getString("word"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
