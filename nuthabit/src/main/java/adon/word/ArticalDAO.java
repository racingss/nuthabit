package adon.word;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.suyufuwu.dao.SampleDAO;

public class ArticalDAO extends SampleDAO {

	public Artical add(Artical a) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("insert into artical(title,url,wordNums,totalNums)values(?,?,?,?)");
			ps.setString(1, a.getTitle());
			ps.setString(2, a.getUrl());
			ps.setLong(3, a.getWordNums());
			ps.setLong(4, a.getTotalNums());
			ps.executeUpdate();
			ps.close();
			ps = conn.prepareStatement("select * from artical order by id desc");
			rs = ps.executeQuery();
			if (rs.next()) {
				return new Artical(rs);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return null;
	}

	public void delete(long id) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("delete from artical where id=?");
			ps.setLong(1, id);
			ps.executeUpdate();
			ps.close();
			ps = conn.prepareStatement("delete from artical_line where articalId=?");
			ps.setLong(1, id);
			ps.executeUpdate();
			ps.close();
			ps = conn.prepareStatement("delete from artical_line_word where articalId=?");
			ps.setLong(1, id);
			ps.executeUpdate();
			ps.close();
			ps = conn.prepareStatement("delete from artical_word where articalId=?");
			ps.setLong(1, id);
			ps.executeUpdate();
			ps.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

	}

	public void delete(long articalId, String word) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("delete from artical_line_word where articalId=? and word=?");
			ps.setLong(1, articalId);
			ps.setString(2, word);
			ps.executeUpdate();
			ps.close();
			ps = conn.prepareStatement("delete from artical_word where articalId=? and word=?");
			ps.setLong(1, articalId);
			ps.setString(2, word);
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

	}

	public void update(Artical a) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("update artical set wordNums=?,totalNums=? where id=?");
			ps.setLong(1, a.getWordNums());
			ps.setLong(2, a.getTotalNums());
			ps.setLong(3, a.getId());
			ps.executeUpdate();
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
			ps = conn.prepareStatement("select * from artical order by id desc");
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new Artical(rs));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Artical getArtical(long id) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection coll;
		conn = null;
		ps = null;
		rs = null;
		Artical a = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from artical  where id=?");
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				a = new Artical(rs);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

		if (a != null)
			a.articalLineColl = getAllArticalLines(a.getId());
		return a;
	}

	public Collection getAllArticalLines(long articalId) {
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
			ps = conn.prepareStatement("select * from artical_line where articalId=? order by line");
			ps.setLong(1, articalId);
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new ArticalLine(rs));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Collection getAllArticalLinesByWord(long articalId, String word) {
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
					"select * from artical_line where articalId=? and line in( select line from artical_line_word where articalId=? and word=?)");
			ps.setLong(1, articalId);
			ps.setLong(2, articalId);
			ps.setString(3, word);
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new ArticalLine(rs));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Collection getAllArticalLinesByWord(String word) {
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
			ps = conn.prepareStatement("select * from artical_line where detail like '%" + word + "%' limit 0,10");
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new ArticalLine(rs));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Collection getAllArticalWords(long articalId) {
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
			ps = conn.prepareStatement("select * from artical_word where articalId=? order by nums desc");
			ps.setLong(1, articalId);
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new ArticalWord(rs));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Collection getAllArticalLineWord(long articalId, String word) {
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
			ps = conn.prepareStatement("select * from artical_line_word where articalId=? and word=? order by line");
			ps.setLong(1, articalId);
			ps.setString(2, word);
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new ArticalLineWord(rs));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public void addArticalLine(ArticalLine l) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("insert into artical_line(articalId,line,detail)values(?,?,?)");
			ps.setLong(1, l.getArticalId());
			ps.setLong(2, l.getLine());
			ps.setString(3, l.getDetail());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public void addArticalWords(ArticalWord aw) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("insert into artical_word(articalId,word,nums)values(?,?,?)");

			ps.setLong(1, aw.getArticalId());
			ps.setString(2, aw.getWord());
			ps.setLong(3, aw.getNums());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public void addArticalLineWord(long articalId, long line, String word) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("insert into artical_line_word(articalId,line,word)values(?,?,?)");
			ps.setLong(1, articalId);
			ps.setLong(2, line);
			ps.setString(3, word);
			ps.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

}
