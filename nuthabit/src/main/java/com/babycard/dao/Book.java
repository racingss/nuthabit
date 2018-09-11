package com.babycard.dao;

import java.sql.*;

public class Book {
	private long bookId = 0;
	private String bookName = null;
	private long kId = 0;
	private long cardNums = 0;
	private long status = 0;
	private long firstCardId = 0;
	private String defaultPic = null;

	public String getDefaultPic() {
		return defaultPic;
	}

	public void setDefaultPic(String defaultPic) {
		this.defaultPic = defaultPic;
	}

	public long getFirstCardId() {
		return firstCardId;
	}

	public void setFirstCardId(long firstCardId) {
		this.firstCardId = firstCardId;
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public long getkId() {
		return kId;
	}

	public void setkId(long kId) {
		this.kId = kId;
	}

	public long getCardNums() {
		return cardNums;
	}

	public void setCardNums(long cardNums) {
		this.cardNums = cardNums;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public Book() {

	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + ", kId=" + kId + ", cardNums=" + cardNums
				+ ", status=" + status + "]";
	}

	public Book(ResultSet rs) {
		try {
			this.setBookId(rs.getLong("bookId"));
			this.setBookName(rs.getString("bookName"));
			this.setCardNums(rs.getLong("cardNums"));
			this.setkId(rs.getLong("kId"));
			this.setStatus(rs.getLong("status"));
			this.setFirstCardId(rs.getLong("firstCardId"));
			this.setDefaultPic(rs.getString("defaultPic"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
