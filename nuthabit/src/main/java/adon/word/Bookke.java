package adon.word;

import java.sql.SQLException;
import java.util.*;

public class Bookke {
	private long id = 0;
	private long bookId = 0;
	private String ke = null;

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

	public String getKe() {
		return ke;
	}

	public void setKe(String ke) {
		this.ke = ke;
	}

	public Bookke() {

	}

	public boolean isStuay(Collection hColl) {
		Iterator it = hColl.iterator();
		while (it.hasNext()) {
			KeHistory kh = (KeHistory) it.next();
			if (kh.getKeId() == id) {
				return true;
			}
		}
		return false;
	}

	public Collection bookwordColl = null;

	public Bookke(java.sql.ResultSet rs) {
		try {
			this.setBookId(rs.getLong("bookId"));
			this.setId(rs.getLong("id"));
			this.setKe(rs.getString("ke"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
