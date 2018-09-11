package com.babycard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import com.nuthabit.dao.SampleDAO;

public class BookDAO extends SampleDAO {

	public void addBook(Book b) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("insert into baby_book(bookName,kId)values(?,?) ");
			ps.setString(1, b.getBookName());
			ps.setLong(2, b.getkId());
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public Book getBookById(long bookId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from baby_book where bookId=?");
			ps.setLong(1, bookId);
			rs = ps.executeQuery();
			if (rs.next()) {
				return new Book(rs);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return null;
	}
	
	public Book getBookByFirstCardId(long firstCardId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from baby_book where firstCardId=?");
			ps.setLong(1, firstCardId);
			rs = ps.executeQuery();
			if (rs.next()) {
				return new Book(rs);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return null;
	}

	public Collection getBookCollByKId(long kId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection coll = new ArrayList();
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from baby_book where kId=?");
			ps.setLong(1, kId);
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new Book(rs));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public static void main(String arg[]) {
		BookDAO dao = new BookDAO();
		System.out.println(dao.getBookByFirstCardId(2281));
	}

}
