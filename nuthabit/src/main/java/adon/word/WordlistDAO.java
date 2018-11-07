package adon.word;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.suyufuwu.dao.Caipu;
import com.suyufuwu.dao.SampleDAO;

public class WordlistDAO extends SampleDAO {

	public void add(Collection wColl) {
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

			Iterator it = wColl.iterator();
			int i = 0;
			while (it.hasNext()) {
				Wordlist w = w = (Wordlist) it.next();
				System.out.println("add:" + i++);
				try {
					ps = conn.prepareStatement("insert into wordlist(word,meaning)values(?,?)");
					ps.setString(1, w.getWord());
					ps.setString(2, w.getMeaning());
					ps.executeUpdate();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					System.out.println(e.toString());
				} finally {
					ps.close();
				}
				// ps.addBatch();
			}
			// ps.executeBatch();
			System.out.println("add all");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

	}

	public Collection getAll() {
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

			ps = conn.prepareStatement("select * from wordlist ");
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new Wordlist(rs));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Collection getAllByWorkId(long bookId) {
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

			ps = conn.prepareStatement(
					"select * from wordlist where word in (select * from wordbook where id=? order by id desc) ");
			ps.setLong(1, bookId);
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new Wordlist(rs));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Collection get(String word) {
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
			if (word.endsWith("ies"))
				word = word.substring(0, word.lastIndexOf("ies")) + "y";

			else if (word.endsWith("ied"))
				word = word.substring(0, word.lastIndexOf("ied")) + "y";

			ps = conn.prepareStatement("select * from wordlist where word = ?");
			ps.setString(1, word);
			rs = ps.executeQuery();
			if (rs.next()) {
				coll.add(new Wordlist(rs));
			}
			rs.close();
			ps.close();

			ps = conn.prepareStatement("select * from wordlist where word like '%" + word + "%' limit 0,50 ");
			rs = ps.executeQuery();
			while (rs.next()) {
				if (!rs.getString("word").equals(word))
					coll.add(new Wordlist(rs));
			}

			rs.close();
			ps.close();

			if (coll.size() == 0) {
				if (word.endsWith("s")) {
					word = word.substring(0, word.lastIndexOf("s"));

				} else if (word.endsWith("ed")) {
					word = word.substring(0, word.lastIndexOf("ed"));
				} else if (word.endsWith("ing")) {
					word = word.substring(0, word.lastIndexOf("ing"));
				} else if (word.endsWith("ly")) {
					word = word.substring(0, word.lastIndexOf("ly"));
				} else if (word.endsWith("'s")) {
					word = word.substring(0, word.lastIndexOf("'s"));
				} else if (word.endsWith("s")) {
					word = word.substring(0, word.lastIndexOf("s"));
				} else {
					return coll;
				}
				return get(word);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Wordlist getWord(String word) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection coll;
		conn = null;
		ps = null;
		rs = null;
		Wordlist m = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from wordlist where word = ?");
			ps.setString(1, word);
			rs = ps.executeQuery();
			if (rs.next()) {
				m = new Wordlist(rs);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		if (m == null) {
			return getWordFuzzy(word);
		}
		return m;
	}

	public Wordlist getWordFuzzy(String word) {
		word = FotmatUtil.getWordFuzzy(word);

		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection coll;
		conn = null;
		ps = null;
		rs = null;
		Wordlist m = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from wordlist where word like '%" + word + "%'");
			rs = ps.executeQuery();
			if (rs.next()) {
				m = new Wordlist(rs);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

		return m;
	}

	public Wordlist updateWord(Wordlist w) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection coll;
		conn = null;
		ps = null;
		rs = null;
		Wordlist m = null;
		String temp =null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(
					"update wordlist set enPh=?,amPh=?,enPhMp3=?,amPhmp3=?,pos1=?,pos2=?,pos3=?,pos4=?,acceptation1=?,acceptation2=?,acceptation3=?,acceptation4=? where word = ?");
			ps.setString(1, w.getEnPh());
			ps.setString(2, w.getAmPh());
			ps.setString(3, w.getEnPhMp3());
			ps.setString(4, w.getAmPhMp3());
			ps.setString(5, w.getPos1());
			ps.setString(6, w.getPos2());
			ps.setString(7, w.getPos3());
			ps.setString(8, w.getPos4());
			ps.setString(9, w.getAcceptation1());
			ps.setString(10, w.getAcceptation2());
			ps.setString(11, w.getAcceptation3());
			ps.setString(12, w.getAcceptation4());
			ps.setString(13, w.getWord());
			temp = ps.toString();
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ERROR:"+ps.toString());
		} finally {
			close(conn, ps, rs);
		}
		return m;
	}

}
