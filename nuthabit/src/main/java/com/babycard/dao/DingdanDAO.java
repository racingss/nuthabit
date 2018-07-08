package com.babycard.dao;

import java.sql.*;
import java.util.*;

public class DingdanDAO extends SampleDAO {

	public DingdanDAO() {
	}

	public Dingdan getGouwuche(Dingdan d) throws Exception {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from dingdan where kehuId=? and status=?");
			ps.setString(1, d.getKehuId());
			ps.setLong(2, d.STATUS_GOUWUCHE);
			rs = ps.executeQuery();
			if (rs.next()) {
				return new Dingdan(rs);
			}

			rs.close();
			ps.close();
			ps = conn.prepareStatement(
					"insert into dingdan(kehuId,dizhiId,zongjine,status,beizhu,riqi,tuijianyouhui)values(?,?,?,?,?,?,?) ");
			ps.setString(1, d.getKehuId());
			ps.setLong(2, d.getDizhiId());
			ps.setLong(3, d.getZongjine());
			ps.setLong(4, 0L);
			ps.setString(5, d.getBeizhu());
			ps.setString(6, d.getRiqi());
			ps.setLong(7, d.getTuijianyouhui());
			ps.executeUpdate();
			rs.close();
			ps.close();
			ps = conn.prepareStatement("select * from dingdan where kehuId=? and status=?");
			ps.setString(1, d.getKehuId());
			ps.setLong(2, 0L);
			rs = ps.executeQuery();
			if (rs.next()) {
				return new Dingdan(rs);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return null;
	}

	public Dingdan getDingdan(long id) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();

			ps = conn.prepareStatement("select * from dingdan where id=?");
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				return new Dingdan(rs);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return null;
	}

	public long[] tongjiDailihuizong(String songhuori, String shouji) throws Exception {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();

			ps = conn.prepareStatement(
					"select sum(zhifujine),count(*) from suyu.dingdan where left(songhuori,7)=? and status>=? and kehuId in (select kehuId from suyu.kehu where tuijianren=?) ");
			ps.setString(1, songhuori);
			ps.setLong(2, Dingdan.STATUS_YIZHIFU);
			ps.setString(3, shouji);
			rs = ps.executeQuery();
			if (rs.next()) {
				return new long[] { rs.getLong(1), rs.getLong(2) };
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return null;
	}

	public Dingdan getDingdanByDingdanId(String dingdanId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Dingdan d = null;
		try {
			conn = getConnection();

			ps = conn.prepareStatement("select * from dingdan where dingdanId=?");
			ps.setString(1, dingdanId);
			rs = ps.executeQuery();
			if (rs.next()) {
				d= new Dingdan(rs);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return d;
	}

	public long getDingdanCount(String kehuId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();

			ps = conn.prepareStatement("select count(*) from dingdan where kehuId=? and status>=2 ");
			ps.setString(1, kehuId);
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getLong(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return 0;
	}

	public Dingdan getDingdanByTuangouId(long id) throws Exception {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();

			ps = conn.prepareStatement("select * from dingdan where tuangoubiaoji=? and status !=?");
			ps.setLong(1, id);
			ps.setLong(2, Dingdan.STATUS_QUXIAO);
			rs = ps.executeQuery();
			if (rs.next()) {
				return new Dingdan(rs);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return null;
	}

	public Collection getDingdanXiangxi(long dingdanId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection result;
		conn = null;
		ps = null;
		rs = null;
		result = new ArrayList();
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from dingdanxiangxi where dingdanId=? ");
			ps.setLong(1, dingdanId);
			for (rs = ps.executeQuery(); rs.next(); result.add(new Dingdanxiangxi(rs)))
				;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return result;
	}

	public Collection getDingdanByKehuId(String kehuId, long status, int pages) throws Exception {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection result;
		conn = null;
		ps = null;
		rs = null;
		result = new ArrayList();
		try {
			conn = getConnection();
			StringBuffer sb = new StringBuffer("select * from dingdan where kehuId=? ");
			if (status != -1) {
				sb.append(" and status=");
				sb.append(status);
			} else {
				sb.append(" and status>");
				sb.append(Dingdan.STATUS_GOUWUCHE);
			}
			sb.append("  order by id desc limit ");
			sb.append((pages - 1) * 5);
			sb.append(",5 ");

			System.out.println("订单列表查询：" + sb.toString());

			ps = conn.prepareStatement(sb.toString());
			ps.setString(1, kehuId);
			for (rs = ps.executeQuery(); rs.next(); result.add(new Dingdan(rs)))
				;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return result;
	}

	public void addDingdan(Dingdan d, Collection coll) throws Exception {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(
					"insert into dingdan(kehuId,dizhiId,zongjine,status,beizhu,riqi,tuijianyouhui)values(?,?,?,?,?,?,?) ");
			ps.setString(1, d.getKehuId());
			ps.setLong(2, d.getDizhiId());
			ps.setLong(3, d.getZongjine());
			ps.setLong(4, d.getStatus());
			ps.setString(5, d.getBeizhu());
			ps.setString(6, d.getRiqi());
			ps.setLong(7, d.getTuijianyouhui());
			ps.executeUpdate();
			ps.close();
			ps = conn.prepareStatement("select * from dingdan where kehuId=? order by id desc");
			ps.setString(1, d.getKehuId());
			rs = ps.executeQuery();
			if (rs.next())
				d = new Dingdan(rs);
			rs.close();
			ps.close();
			Iterator it = coll.iterator();
			ps = conn.prepareStatement("insert into dingdanxiangxi (dingdanId,chanpinId,jine,jianshu,fuwu,mingcheng)");
			for (; it.hasNext(); ps.addBatch()) {
				Dingdanxiangxi x = (Dingdanxiangxi) it.next();
				ps.setLong(1, d.getId());
				ps.setLong(2, x.getChanpinId());
				ps.setLong(3, x.getJine());
				ps.setLong(4, x.getJianshu());
				ps.setString(5, x.getFuwu());
				ps.setString(6, x.getMingcheng());
				ps.addBatch();
			}

			ps.executeBatch();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

	}
	
	public void newDingdan(Dingdan d) throws Exception {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(
					"insert into dingdan(kehuId,dizhiId,zongjine,status,beizhu,riqi,tuijianyouhui,dingdanId,zhifujine,fuwu1)values(?,?,?,?,?,?,?,?,?,?) ");
			ps.setString(1, d.getKehuId());
			ps.setLong(2, d.getDizhiId());
			ps.setLong(3, d.getZongjine());
			ps.setLong(4, d.getStatus());
			ps.setString(5, d.getBeizhu());
			ps.setString(6, d.getRiqi());
			ps.setLong(7, d.getTuijianyouhui());
			ps.setString(8, d.getDingdanId());
			ps.setLong(9, d.getZhifujine());
			ps.setString(10, d.getFuwu1());
			ps.executeUpdate();
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

	}

	public void updateDingdan(Dingdan d) throws Exception {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(
					"update dingdan set status=?, beizhu=?,kuaidi=?,kuaididan=?,fankui=? ,songhuori=?,dizhiId=?,yunfei=?,riqi=?,dingdanId=?,zhifujine=?,tuijianyouhui=?,hongbao=?,fuwu1=?,fuwu2=?,fuwu3=?,lianxishijian=?,youhuima=?,liquanId=? where id=?");
			ps.setLong(1, d.getStatus());
			ps.setString(2, d.getBeizhu());
			ps.setString(3, d.getKuaidi());
			ps.setString(4, d.getKuaididan());
			ps.setString(5, d.getFankui());
			ps.setString(6, d.getSonghuori());
			ps.setLong(7, d.getDizhiId());
			ps.setLong(8, d.getYunfei());
			ps.setString(9, d.getRiqi());
			ps.setString(10, d.getDingdanId());
			ps.setLong(11, d.getZhifujine());
			ps.setLong(12, d.getTuijianyouhui());
			ps.setLong(13, d.getHongbao());
			ps.setString(14, d.getFuwu1());
			ps.setString(15, d.getFuwu2());
			ps.setString(16, d.getFuwu3());
			ps.setString(17, d.getLianxishijian());
			ps.setLong(18, d.getYouhuima());
			ps.setString(19, d.getLiquanId());
			ps.setLong(20, d.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public void dingdanxiangxiYiping(long dingdanId, long chanpinId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("update dingdanxiangxi set pingjia=1 where dingdanId=? and chanpinId=?");
			ps.setLong(1, dingdanId);
			ps.setLong(2, chanpinId);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public void dingdanFahuo(long id, String kuaidi, String kuaididan) throws Exception {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("update dingdan set status=?,kuaidi=?,kuaididan=? where id=?");
			ps.setLong(1, Dingdan.STATUS_YIFAHUO);
			ps.setString(2, kuaidi);
			ps.setString(3, kuaididan);
			ps.setLong(4, id);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public void dingdanQuxiao(long id, String kehuId) throws Exception {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("update dingdan set status=? where id=? and kehuId=? and status=?");
			ps.setLong(1, Dingdan.STATUS_QUXIAO);
			ps.setLong(2, id);
			ps.setString(3, kehuId);
			ps.setLong(4, Dingdan.STATUS_DAIZHIFU);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public Collection getDingdanHuizong(String songhuori, long status) {

		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection result;
		conn = null;
		ps = null;
		rs = null;
		result = new ArrayList();
		try {
			conn = getConnection();
			ps = conn.prepareStatement(
					"SELECT chanpinId,sum(jianshu) FROM suyu.dingdanxiangxi where dingdanId in (select id from suyu.dingdan where songhuori=? and status=?) group by chanpinId ");

			ps.setString(1, songhuori);
			ps.setLong(2, status);
			for (rs = ps.executeQuery(); rs.next(); result.add(new Long[] { rs.getLong(1), rs.getLong(2) }))
				;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return result;
	}

	public Collection getLiquanHuizong(String songhuori, long status) {

		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection result;
		conn = null;
		ps = null;
		rs = null;
		result = new ArrayList();
		try {
			conn = getConnection();
			if (songhuori != null) {
				ps = conn.prepareStatement(
						"SELECT mingcheng,count(*) FROM suyu.liquan where dingdanId in (select dingdanId from suyu.dingdan where songhuori=? and status=?) group by mingcheng ");
				ps.setString(1, songhuori);
				ps.setLong(2, status);
			} else {
				ps = conn.prepareStatement(
						"SELECT mingcheng,count(*) FROM suyu.liquan where dingdanId in (select dingdanId from suyu.dingdan where status=?) group by mingcheng ");
				ps.setLong(1, status);
			}
			for (rs = ps.executeQuery(); rs.next(); result.add(new Object[] { rs.getString(1), rs.getLong(2) }))
				;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return result;
	}

	public Collection getDingdanHuizong(String begin, String end, long status) {

		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection result;
		conn = null;
		ps = null;
		rs = null;
		result = new ArrayList();
		try {
			conn = getConnection();
			ps = conn.prepareStatement(
					"SELECT chanpinId,sum(jianshu) FROM suyu.dingdanxiangxi where dingdanId in (select id from suyu.dingdan where substr(songhuori,1,10)>=? and substr(songhuori,1,10)<=? and status=?) group by chanpinId ");

			ps.setString(1, begin);
			ps.setString(2, end);
			ps.setLong(3, status);
			for (rs = ps.executeQuery(); rs.next(); result.add(new Long[] { rs.getLong(1), rs.getLong(2) }))
				;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return result;
	}

	public Collection getDingdanHuizong() {

		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection result;
		conn = null;
		ps = null;
		rs = null;
		result = new ArrayList();
		try {
			conn = getConnection();
			ps = conn.prepareStatement(
					"SELECT chanpinId,sum(jianshu) FROM suyu.dingdanxiangxi where dingdanId in (select id from suyu.dingdan where status=?) group by chanpinId ");
			ps.setLong(1, Dingdan.STATUS_YIZHIFU);
			for (rs = ps.executeQuery(); rs.next(); result.add(new Long[] { rs.getLong(1), rs.getLong(2) }))
				;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return result;
	}

	public Collection getDingdanmingxi(String songhuori, long status) {

		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection result;
		conn = null;
		ps = null;
		rs = null;
		result = new ArrayList();
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from suyu.dingdan where songhuori=? and status=?");
			ps.setString(1, songhuori);
			ps.setLong(2, status);
			for (rs = ps.executeQuery(); rs.next(); result.add(new Dingdan(rs)))
				;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return result;
	}

	public Collection getDingdanmingxi(String begin, String end, long status, long pages) {

		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection result;
		conn = null;
		ps = null;
		rs = null;
		result = new ArrayList();
		try {
			conn = getConnection();
			ps = conn.prepareStatement(
					"select * from suyu.dingdan where substr(songhuori,1,10)>=? and substr(songhuori,1,10)<=? and status=? limit "
							+ (pages - 1) * 20 + ",20 ");
			ps.setString(1, begin);
			ps.setString(2, end);
			ps.setLong(3, status);
			for (rs = ps.executeQuery(); rs.next(); result.add(new Dingdan(rs)))
				;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return result;
	}

	public Collection getDingdanmingxiByNeirong(String neirong) {

		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection result;
		conn = null;
		ps = null;
		rs = null;
		result = new ArrayList();
		try {
			conn = getConnection();
			ps = conn.prepareStatement(
					"select * from suyu.dingdan where dizhiId in (select id from dizhi where xiangxi like '%" + neirong
							+ "%' or dianhua like '%" + neirong + "%' or shoujianren like '%" + neirong + "%'  )");
			for (rs = ps.executeQuery(); rs.next(); result.add(new Dingdan(rs)))
				;
			System.out.println("查询订单：" + ps.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return result;
	}

	public Collection getDingdanmingxi() {

		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection result;
		conn = null;
		ps = null;
		rs = null;
		result = new ArrayList();
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from suyu.dingdan where status=? order by songhuori");
			ps.setLong(1, Dingdan.STATUS_YIZHIFU);
			for (rs = ps.executeQuery(); rs.next(); result.add(new Dingdan(rs)))
				;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return result;
	}

	public boolean yanzhengMiaosha(String kehuId, long chanpinId) {

		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		boolean result = false;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(
					"select * from suyu.dingdanxiangxi where chanpinId=? and dingdanId in (select id from suyu.dingdan where kehuId=?)");
			ps.setLong(1, chanpinId);
			ps.setString(2, kehuId);
			rs = ps.executeQuery();
			if (rs.next()) {
				result = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return result;
	}

}