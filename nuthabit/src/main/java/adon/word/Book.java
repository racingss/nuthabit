package adon.word;

import java.sql.SQLException;
import java.util.*;

public class Book {
	private long id = 0;
	private String bookName = null;
	private int wordcount = 0;
	private int publicFlag = 0;
	private String kehuId = null;

	public String getKehuId() {
		return kehuId;
	}

	public void setKehuId(String kehuId) {
		this.kehuId = kehuId;
	}

	public int getPublicFlag() {
		return publicFlag;
	}

	public void setPublicFlag(int publicFlag) {
		this.publicFlag = publicFlag;
	}

	public int getWordcount() {
		return wordcount;
	}

	public void setWordcount(int wordcount) {
		this.wordcount = wordcount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Book() {

	}

	private Collection bookkeColl = null;

	public Iterator getBookke() {

		if (bookkeColl == null) {
			bookkeColl = new BookDAO().getBookke(id);
		}
		return bookkeColl.iterator();
	}

	private static Collection bookWordColl = null;

	public Iterator getWordByKe(String ke) {
		if (bookWordColl == null) {
			bookWordColl = new ArrayList();
		}
		Iterator bookWordIt = bookWordColl.iterator();
		while (bookWordIt.hasNext()) {
			Bookke bk = (Bookke) bookWordIt.next();
			if (bk.getKe().equals(ke) && bk.bookwordColl == null) {
				bk.bookwordColl = new BookDAO().getWord(ke);
				return bk.bookwordColl.iterator();
			}
		}
		Bookke tbk = new Bookke();
		tbk.setBookId(id);
		tbk.setKe(ke);
		bookWordColl.add(tbk);
		return getWordByKe(ke);

	}

	public Book(java.sql.ResultSet rs) {
		try {
			this.setBookName(rs.getString("bookName"));
			this.setId(rs.getLong("id"));
			this.setWordcount(rs.getInt("wordcount"));
			this.setPublicFlag(rs.getInt("publicFlag"));
			this.setKehuId(rs.getString("kehuId"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
