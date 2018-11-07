package adon.word;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.babycard.dao.SampleDAO;

public class MywordDAO extends SampleDAO {

	public void add(Myword m) {
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

			ps = conn.prepareStatement("select * from myword where word=? and kehuId=?");
			ps.setString(1, m.getWord());
			ps.setString(2, m.getKehuId());
			rs = ps.executeQuery();
			if (rs.next()) {
				rs.close();
				ps.close();

				ps = conn.prepareStatement(
						"update myword set level=?,reviewday=?,reviewIndex=? where word=? and kehuId=? ");
				ps.setInt(1, m.getLevel());
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				ps.setString(2, formatter.format(new Date()));
				ps.setLong(3, System.currentTimeMillis());
				ps.setString(4, m.getWord());
				ps.setString(5, m.getKehuId());
			} else {
				rs.close();
				ps.close();

				ps = conn.prepareStatement(
						"insert into myword(word,kehuId,level,reviewday,reviewIndex,kId,bId)values(?,?,?,?,?,?,?)");
				ps.setString(1, m.getWord());
				ps.setString(2, m.getKehuId());
				ps.setInt(3, m.getLevel());
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				ps.setString(4, formatter.format(new Date()));
				ps.setLong(5, System.currentTimeMillis());
				ps.setLong(6, m.getkId());
				ps.setLong(7, m.getbId());
			}
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

	}

	public void deleteAll(String kehuId) {
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

			ps = conn.prepareStatement("delete from myword where kehuId=? ");
			ps.setString(1, kehuId);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally

		{
			close(conn, ps, rs);
		}

	}

	public int getLevel(Myword m) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection coll;
		conn = null;
		ps = null;
		rs = null;
		int i = 0;
		try {
			conn = getConnection();

			ps = conn.prepareStatement("select * from myword where word=? and kehuId=? ");
			ps.setString(1, m.getWord());
			ps.setString(2, m.getKehuId());
			rs = ps.executeQuery();
			if (rs.next()) {
				i = rs.getInt("level");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return i;
	}

	public void reviewNotPass(Myword m) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection coll;
		conn = null;
		ps = null;
		rs = null;
		int i = 0;
		try {
			conn = getConnection();

			ps = conn.prepareStatement("update myword set reviewIndex=?, reviewFlag=1 where word=? and kehuId=? ");
			ps.setLong(1, System.currentTimeMillis());
			ps.setString(2, m.getWord());
			ps.setString(3, m.getKehuId());
			ps.executeUpdate();

			ps.close();
			ps = conn.prepareStatement("insert into mywordhistory(mwId,reviewDay,reviewProcess,level)values(?,?,?,?)");
			ps.setLong(1, m.getId());
			ps.setString(2, m.getReviewday());
			ps.setInt(3, 1);
			ps.setInt(4, m.getLevel());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public Myword getMyword(String kehuId, String word) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection coll;
		conn = null;
		ps = null;
		rs = null;
		Myword m = null;
		try {
			conn = getConnection();

			ps = conn.prepareStatement("select * from myword where word=? and kehuId=? ");
			ps.setString(1, word);
			ps.setString(2, kehuId);
			rs = ps.executeQuery();
			if (rs.next()) {
				m = new Myword(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return m;
	}

	public String getNewword(Plan p) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection coll;
		conn = null;
		ps = null;
		rs = null;
		String word = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from wordbook where bookId=? order by id limit ?,1");
			ps.setLong(1, p.getBookId());
			ps.setLong(2, p.getBookIndex());
			rs = ps.executeQuery();
			if (rs.next()) {
				word = rs.getString("word");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

		return word;
	}

	public Collection getWordByke(long kId, long bookId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection coll;
		conn = null;
		ps = null;
		rs = null;
		Collection result = new ArrayList();
		try {
			conn = getConnection();
			ps = conn.prepareStatement(
					"select * from wordbook where bookId=? and ke in (select ke from bookke where id=?)	");
			ps.setLong(1, bookId);
			ps.setLong(2, kId);
			rs = ps.executeQuery();
			while (rs.next()) {
				result.add(rs.getString("word"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

		return result;
	}

	public void addNewwordToMyList(String kehuId, String word, long kId, long bId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection coll;
		conn = null;
		ps = null;
		rs = null;
		Myword m = null;
		try {
			conn = getConnection();

			ps = conn.prepareStatement("select * from myword where word=? and kehuId=?");
			ps.setString(1, word);
			ps.setString(2, kehuId);
			rs = ps.executeQuery();
			if (rs.next()) {
				rs.close();
				ps.close();

				ps = conn.prepareStatement(
						"update myword set level=?,reviewday=?,reviewIndex=? where word=? and kehuId=? ");
				ps.setInt(1, 7);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				ps.setString(2, formatter.format(new Date()));
				ps.setLong(3, System.currentTimeMillis());
				ps.setString(4, word);
				ps.setString(5, kehuId);
			} else {
				rs.close();
				ps.close();

				ps = conn.prepareStatement(
						"insert into myword(word,kehuId,level,reviewday,reviewIndex,kId,bId)values(?,?,?,?,?,?,?)");
				ps.setString(1, word);
				ps.setString(2, kehuId);
				ps.setInt(3, 7);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				ps.setString(4, formatter.format(new Date()));
				ps.setLong(5, System.currentTimeMillis());
				ps.setLong(6, kId);
				ps.setLong(7, bId);
			}
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public Collection getMywordColl(String kehuId, int level) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection coll = new ArrayList();
		conn = null;
		ps = null;
		rs = null;

		try {
			conn = getConnection();

			if (level == -1) {
				ps = conn.prepareStatement("select * from myword where kehuId=? order by word");
				ps.setString(1, kehuId);
			} else if (level == 0) {
				ps = conn.prepareStatement("select * from myword where kehuId=? and level!=0 order by word");
				ps.setString(1, kehuId);
			} else {
				ps = conn.prepareStatement("select * from myword where kehuId=? and level=?  order by word");
				ps.setString(1, kehuId);
				ps.setInt(2, level);
			}

			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new Myword(rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Collection getMywordCollByKehuId(String kehuId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection coll = new ArrayList();
		conn = null;
		ps = null;
		rs = null;

		try {
			conn = getConnection();

			ps = conn.prepareStatement("select * from myword where kehuId=? order by word");
			ps.setString(1, kehuId);

			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new Myword(rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Collection getMywordColl(String kehuId) {
		return getMywordColl(kehuId, 0);
	}

	public int getReviewNums(String kehuId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection coll;
		conn = null;
		ps = null;
		rs = null;
		int i = 0;
		try {
			conn = getConnection();

			ps = conn.prepareStatement("select count(*) from myword where kehuId=? and reviewday<=? and level>0 ");
			ps.setString(1, kehuId);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			ps.setString(2, formatter.format(new Date()));
			rs = ps.executeQuery();
			if (rs.next()) {
				i = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return i;
	}

	public Myword getReview(String kehuId, int reviewFlag) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection coll;
		conn = null;
		ps = null;
		rs = null;
		Myword m = null;
		try {
			conn = getConnection();

			ps = conn.prepareStatement(
					"select * from myword where kehuId=? and reviewday<=? and reviewFlag=? and level>0 order by reviewIndex ");
			ps.setString(1, kehuId);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			ps.setString(2, formatter.format(new Date()));
			ps.setInt(3, reviewFlag);
			rs = ps.executeQuery();
			if (rs.next()) {
				m = new Myword(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		if (m != null) {
			try {
				Wordlist w = new WordlistDAO().getWord(m.getWord());
				ICBA.check(w);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return m;
	}

	public Map getReview(String kehuId, long bId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Map map = new HashMap();
		try {
			conn = getConnection();

			ps = conn.prepareStatement(
					"select * from myword where kehuId=? and reviewday<=? and bId=? order by reviewIndex ");
			ps.setString(1, kehuId);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			ps.setString(2, formatter.format(new Date()));
			ps.setLong(3, bId);
			rs = ps.executeQuery();
			while (rs.next()) {
				map.put(rs.getString("word"), new Myword(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

		return map;
	}

	public void reviewPassed(Myword m, boolean skip) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection coll;
		conn = null;
		ps = null;
		rs = null;

		try {
			int level = m.getLevel();
			int addDay = 0;
			String reviewDay = m.getReviewday();

			if (level == 7)
				addDay = 1;
			else if (level == 6)
				addDay = 2;
			else if (level == 5)
				addDay = 3;
			else if (level == 4)
				addDay = 7;
			else if (level == 3)
				addDay = 14;
			else if (level == 2)
				addDay = 32;

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(reviewDay);
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(calendar.DATE, addDay);// 把日期往后增加一天.整数往后推,负数往前移动
			date = calendar.getTime();

			conn = getConnection();
			if (m.getReviewFlag() == 0) {
				if (level > 1) {
					ps = conn.prepareStatement(
							"update myword set level=?,reviewday=?,reviewIndex=?,reviewFlag=0 where kehuId=? and word=? ");
					ps.setInt(1, m.getLevel() - 1);
					ps.setString(2, sdf.format(date));
					ps.setLong(3, System.currentTimeMillis());
					ps.setString(4, m.getKehuId());
					ps.setString(5, m.getWord());
				} else {
					ps = conn.prepareStatement("delete from myword where kehuId=? and word=? ");
					ps.setString(1, m.getKehuId());
					ps.setString(2, m.getWord());
				}
			} else {
				ps = conn.prepareStatement(
						"update myword set level=?,reviewday=?,reviewIndex=?,reviewFlag=0 where kehuId=? and word=? ");
				ps.setInt(1, m.getLevel());
				ps.setString(2, sdf.format(date));
				ps.setLong(3, System.currentTimeMillis());
				ps.setString(4, m.getKehuId());
				ps.setString(5, m.getWord());
			}
			ps.executeUpdate();

			ps.close();
			ps = conn.prepareStatement("insert into mywordhistory(mwId,reviewDay,reviewProcess,level)values(?,?,?,?)");
			ps.setLong(1, m.getId());
			ps.setString(2, m.getReviewday());
			if (skip)
				ps.setInt(3, 2);
			else
				ps.setInt(3, 0);
			ps.setInt(4, m.getLevel());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public static void main(String arg[]) {
		String a = "bill of fare";
		System.out.println(a);
		String b = a.replace(" ", "_");
		System.out.println(b);
		// System.out.println(new MywordDAO().getReviewNums("adon"));

		int level = 1;
		String reviewDay = "2017-04-24";
		int addDay = 0;

		if (level == 7)
			addDay = 1;
		else if (level == 6)
			addDay = 2;
		else if (level == 5)
			addDay = 3;
		else if (level == 4)
			addDay = 7;
		else if (level == 3)
			addDay = 14;
		else if (level == 2)
			addDay = 32;

		System.out.println(reviewDay);
		System.out.println(addDay);
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(reviewDay);
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(calendar.DATE, addDay);// 把日期往后增加一天.整数往后推,负数往前移动
			date = calendar.getTime();
			System.out.println(sdf.format(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
