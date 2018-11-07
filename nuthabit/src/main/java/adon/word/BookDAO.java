package adon.word;

import java.sql.Connection;
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.StringTokenizer;

import com.suyufuwu.dao.SampleDAO;

public class BookDAO extends SampleDAO {

	public void addBook(String bookName, int publicFlag, String kehuId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("insert into book(bookName,publicFlag,kehuId)values(?,?,?,?)");
			ps.setString(1, bookName);
			ps.setInt(2, publicFlag);
			ps.setString(3, kehuId);
			ps.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public void add(Collection wColl, long bookId) {
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
			ps = conn.prepareStatement("insert into wordbook(bookId,word)values(?,?)");

			Iterator it = wColl.iterator();
			while (it.hasNext()) {
				ps.setLong(1, bookId);
				ps.setString(2, it.next().toString());
				ps.addBatch();
			}
			ps.executeBatch();
			System.out.println("add all");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public void add(Collection wColl, long bookId, String ke) {
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
			ps = conn.prepareStatement("insert into bookke(bookId,ke)values(?,?)");
			ps.setLong(1, bookId);
			ps.setString(2, ke);
			ps.executeUpdate();
			ps.close();

			Iterator it = wColl.iterator();
			while (it.hasNext()) {
				String temp = it.next().toString();

				ps = conn.prepareStatement("select * from wordbook where bookId=? and word=? and ke=?");
				ps.setLong(1, bookId);
				ps.setString(2, temp);
				ps.setString(3, ke);
				rs = ps.executeQuery();
				if (rs.next()) {
					rs.close();
					ps.close();
					System.out.println(temp);
				} else {
					rs.close();
					ps.close();

					ps = conn.prepareStatement("insert into wordbook(bookId,word,ke)values(?,?,?)");
					ps.setLong(1, bookId);
					ps.setString(2, temp);
					ps.setString(3, ke);
					ps.executeUpdate();
					ps.close();
				}
			}
			System.out.println("add all");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public void count(long bookId) {
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
			ps = conn.prepareStatement("select count(*) from wordbook where bookId=?");
			ps.setLong(1, bookId);
			rs = ps.executeQuery();
			int count = 0;
			if (rs.next())
				count = rs.getInt(1);

			rs.close();
			ps.close();

			ps = conn.prepareStatement("update book set wordcount=? where id=?");
			ps.setInt(1, count);
			ps.setLong(2, bookId);
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
			ps = conn.prepareStatement("select * from book");
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

	public Collection getBookke(long bookId) {
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
			ps = conn.prepareStatement("select * from bookke where bookId=?");
			ps.setLong(1, bookId);
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new Bookke(rs));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Collection getWord(String ke) {
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
			ps = conn.prepareStatement("select word from wordbook where ke=?");
			ps.setString(1, ke);
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(rs.getString("word"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}

	public Collection getAllMybook(String kehuId) {
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
			ps = conn.prepareStatement("select * from book where id in (select bookId from mybook where kehuId=?) ");
			ps.setString(1, kehuId);
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

	public void addToMyBook(long bookId, String kehuId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection coll;
		conn = null;
		ps = null;
		rs = null;
		Book b = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("delete from mybook where bookId=? and kehuId=?");
			ps.setLong(1, bookId);
			ps.setString(2, kehuId);
			ps.executeUpdate();
			ps.close();

			ps = conn.prepareStatement("insert into mybook(bookId,kehuId)values(?,?)");
			ps.setLong(1, bookId);
			ps.setString(2, kehuId);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public void deleteMyBook(long bookId, String kehuId) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection coll;
		conn = null;
		ps = null;
		rs = null;
		Book b = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("delete from mybook where bookId=? and kehuId=?");
			ps.setLong(1, bookId);
			ps.setString(2, kehuId);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}

	public Book get(long id) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		Collection coll;
		conn = null;
		ps = null;
		rs = null;
		Book b = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from book where id=?");
			ps.setLong(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				b = new Book(rs);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return b;
	}

	public static void main(String args[]) {

		/*
		 * String a =
		 * "boy，girl，teacher，student，this，my，friend，nice，good，morning，goodafternoon，meet，goodbye，too，I‘m=Iam，father，dad，mother，mom，man，woman，grandmother，grandma，grandfather，grandpa，sister，brother，let’s=let，us，really，great，and，howeleven，twelve，thirteen，fourteen，fifteen，sixteen，seventeen，eighteen，nineteen，twenty，how，many，can，look，at，peach，pear，orange，watermelon，apple，banana，grape，strawberry，like，some，thanks，bus，bike，taxi，jeep，desk，chair，walkman，lamp，your，zoo，small，big，long，short，tall，giraffe，deer";
		 * StringTokenizer st = new StringTokenizer(a, "，"); Collection coll =
		 * new ArrayList(); while (st.hasMoreElements()) {
		 * coll.add(st.nextToken()); } // new BookDAO().add(coll, 1); //new
		 * BookDAO().count(1);
		 * 
		 */
		new BookDAO().count(2);
		if (true)
			return;

		try {
			File f = new File("/Users/wangjunwu/百度云同步盘/yoyogame/yoyogame/chinese3.txt");
			FileReader r = new FileReader(f);
			BufferedReader b = new BufferedReader(r);
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			int i = 0;
			String a = null;
			while ((tempString = b.readLine()) != null) {
				if (i++ == 0) {
					a = tempString;
				} else {
					i = 0;
					System.out.println(a + ":" + tempString);
					StringTokenizer st = new StringTokenizer(tempString, "　");
					Collection coll = new ArrayList();
					while (st.hasMoreElements()) {
						coll.add(st.nextToken());
					}
					new BookDAO().add(coll, 2, a);
				}
			}
			b.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
