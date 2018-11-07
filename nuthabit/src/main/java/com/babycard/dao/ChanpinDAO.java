package com.babycard.dao;

import java.sql.*;

import java.util.ArrayList;
import java.util.Collection;

public class ChanpinDAO extends SampleDAO {

	public ChanpinDAO() {
	}

	public Collection getChanpinListByTuijianId(long tuijianId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection coll;
		conn = null;
		ps = null;
		rs = null;
		coll = new ArrayList();
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from chanpin where tuijianId=? order by shunxu desc ");
			ps.setLong(1, tuijianId);
			for (rs = ps.executeQuery(); rs.next(); coll.add(new Chanpin(rs)))
				;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

		return coll;
	}

	public Collection getChanpinListByMingcheng(String mingcheng) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection coll;
		conn = null;
		ps = null;
		rs = null;
		coll = new ArrayList();
		try {
			conn = getConnection();
			StringBuffer sb = new StringBuffer("select * from chanpin where mingcheng like '%");
			sb.append(mingcheng);
			sb.append("%' order by shunxu desc ");
			ps = conn.prepareStatement(sb.toString());
			System.out.println(ps.toString());
			for (rs = ps.executeQuery(); rs.next(); coll.add(new Chanpin(rs)))
				;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

		return coll;
	}

	public Collection getChanpinListByLeibieId(long leibieId, int pages) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection coll;
		conn = null;
		ps = null;
		rs = null;
		coll = new ArrayList();
		try {
			conn = getConnection();

			StringBuffer sb = new StringBuffer("select * from chanpin where status=0 and mainId=0");
			if (leibieId != -1) {
				sb.append(" and leibieId=");
				sb.append(leibieId);
			}
			sb.append("  order by shunxu desc limit ");
			sb.append((pages - 1) * 20);
			sb.append(",20 ");

			ps = conn.prepareStatement(sb.toString());

			for (rs = ps.executeQuery(); rs.next(); coll.add(new Chanpin(rs)))
				;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

		return coll;
	}

	public Collection getChanpinListByLeibieIdAll(long leibieId, int pages) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection coll;
		conn = null;
		ps = null;
		rs = null;
		coll = new ArrayList();
		try {
			conn = getConnection();

			StringBuffer sb = new StringBuffer("select * from chanpin where mainId=0");
			if (leibieId != -1) {
				sb.append(" and leibieId=");
				sb.append(leibieId);
			}
			sb.append("  order by shunxu desc limit ");
			sb.append((pages - 1) * 20);
			sb.append(",20 ");

			ps = conn.prepareStatement(sb.toString());

			for (rs = ps.executeQuery(); rs.next(); coll.add(new Chanpin(rs)))
				;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

		return coll;
	}

	public Collection getMailChanpinList(long leibieId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection coll;
		conn = null;
		ps = null;
		rs = null;
		coll = new ArrayList();
		try {
			conn = getConnection();

			StringBuffer sb = new StringBuffer("select * from chanpin where status=0 and mainId=1");
			if (leibieId != -1) {
				sb.append(" and leibieId=");
				sb.append(leibieId);
			}

			ps = conn.prepareStatement(sb.toString());

			for (rs = ps.executeQuery(); rs.next(); coll.add(new Chanpin(rs)))
				;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

		return coll;
	}

	public Collection getAllChanpin() {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection coll;
		conn = null;
		ps = null;
		rs = null;
		coll = new ArrayList();
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from chanpin order by leibieId,shunxu desc");
			for (rs = ps.executeQuery(); rs.next(); coll.add(new Chanpin(rs)))
				;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Collection getAllQuickChanpin() {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection coll;
		conn = null;
		ps = null;
		rs = null;
		coll = new ArrayList();
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from chanpin where leibieId=0 and status=0");
			for (rs = ps.executeQuery(); rs.next(); coll.add(new Chanpin(rs)))
				;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Chanpin getChanpinById(long id) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Chanpin result;
		conn = null;
		ps = null;
		rs = null;
		result = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from chanpin where id=? ");
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next())
				result = new Chanpin(rs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return result;
	}

	public void updateChanpin(Chanpin c) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Chanpin result;
		conn = null;
		ps = null;
		rs = null;
		result = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(
					"update chanpin set mingcheng=?, jiage=?,yuanjiage=?,tuangoujia=?,status=?,chengbenjia=?,jiancheng=?,jianshu=?,guige=? where id=? ");
			ps.setString(1, c.getMingcheng());
			ps.setLong(2, c.getJiage());
			ps.setLong(3, c.getYuanjiage());
			ps.setLong(4, c.getTuangoujia());
			ps.setLong(5, c.getStatus());
			ps.setLong(6, c.getChengbenjia());
			ps.setString(7, c.getJiancheng());
			ps.setString(8, c.getJianshu());
			ps.setString(9, c.getGuige());
			ps.setLong(10, c.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public void updateChanpinMainid(Chanpin c) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Chanpin result;
		conn = null;
		ps = null;
		rs = null;
		result = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("update chanpin set mainid=1 where id=? ");
			ps.setLong(1, c.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public void xiajiaChanpin(long id) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Chanpin result;
		conn = null;
		ps = null;
		rs = null;
		result = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("update chanpin set status=-1 where id=? ");
			ps.setLong(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public void xiajiaQuickChanpinAll() {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Chanpin result;
		conn = null;
		ps = null;
		rs = null;
		result = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("update chanpin set status=-1 where leibieId=0 ");
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public void addPingjiaChanpin(Chanpinpingjia p) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Chanpin result;
		conn = null;
		ps = null;
		rs = null;
		result = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(
					"insert into chanpinpingjia(chanpinId,kehuId,dingdanId,pingfen,pingjia,tupian1,tupian2,tupian3,tupian4,tupian5,tupian6)values(?,?,?,?,?,?,?,?,?,?,?)");
			ps.setLong(1, p.getChanpinId());
			ps.setString(2, p.getKehuId());
			ps.setLong(3, p.getDingdanId());
			ps.setLong(4, p.getPingfen());
			ps.setString(5, p.getPingjia());
			ps.setString(6, p.getTupian1());
			ps.setString(7, p.getTupian2());
			ps.setString(8, p.getTupian3());
			ps.setString(9, p.getTupian4());
			ps.setString(10, p.getTupian5());
			ps.setString(11, p.getTupian6());
			ps.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public Collection<Chanpinpingjia> getPingjiaChanpin(long chanpinId, long pages, long nums) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Chanpin result;
		conn = null;
		ps = null;
		rs = null;
		result = null;
		Collection<Chanpinpingjia> coll = new ArrayList();
		try {
			conn = getConnection();

			StringBuffer sb = new StringBuffer("select * from chanpinpingjia where chanpinId=");
			sb.append(chanpinId);
			sb.append("  and status=0 order by status desc,id desc limit ");
			sb.append((pages - 1) * nums);
			sb.append(",");
			sb.append(nums);

			ps = conn.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new Chanpinpingjia(rs));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public void addChanpinQuick(Chanpin c) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Chanpin result;
		conn = null;
		ps = null;
		rs = null;
		result = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("insert into chanpin(jiancheng,mingcheng,jiage,kucun,baoyou)values(?,?,?,?,?)");
			ps.setString(1, c.getJiancheng());
			ps.setString(2, c.getMingcheng());
			ps.setLong(3, c.getJiage());
			ps.setLong(4, c.getKucun());
			ps.setLong(5, c.getBaoyou());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

}