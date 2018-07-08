package com.babycard.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.*;

import com.nuthabit.dao.SampleDAO;

public class SearchDAO extends SampleDAO {

	public void addSearch(Search s) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("delete from baby_search where kId=? and qString=?");
			ps.setLong(1, s.getkId());
			ps.setString(2, s.getqString());
			ps.executeUpdate();
			ps.close();
			
			ps = conn.prepareStatement("insert into baby_search(kId,qString,rCount,qDate)values(?,?,?,?) ");
			ps.setLong(1, s.getkId());
			ps.setString(2, s.getqString());
			ps.setLong(3, s.getrCount());
			ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}
	
	public Collection getSearchBykId(long kId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Collection coll  = new ArrayList();
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from baby_search where kId=? order by id desc limit 0,10");
			ps.setLong(1, kId);
			rs = ps.executeQuery();
			while(rs.next()){
				coll.add(new Search(rs));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}
	
	public static void main(String arg[]){
		Search s = new Search();
		s.setkId(18);
		s.setqString("宝宝");
		s.setrCount(19);
		new SearchDAO().addSearch(s);
	}

}
