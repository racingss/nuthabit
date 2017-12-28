package com.nuthabit.dao;

import java.io.PrintStream;


import java.sql.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SampleDAO {

	DataSource dataSource;

	public SampleDAO() {
		dataSource = null;
	}

	private Connection getDateSourceConnection(String sourcename) throws DaoException {
		try {
			if (false) {
				System.out.println("return drivermanage");
				return getDriverManageConnection();
			}
			InitialContext ic = new InitialContext();
			dataSource = (DataSource) ic.lookup(sourcename);
			return dataSource.getConnection();
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println(e.toString());
			// throw new DaoException(e);
			try {
				return getDriverManageConnection();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				throw new DaoException(e1);
			}
		}

	}

	protected Connection getDriverManageConnection() throws DaoException {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			return DriverManager.getConnection(
					"jdbc:mysql://114.55.224.235/suyu?user=java&password=Adon2009YOYO&useUnicode=true&characterEncoding=utf-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			throw new DaoException(e);
		}

	}

	public int getPageCount(int nums, int count) {
		if (nums > count)
			return 1;
		if ((double) (count / nums) < (double) count / (double) nums)
			return count / nums + 1;
		else
			return count / nums;
	}

	public Connection getConnection() throws DaoException {
		return getDriverManageConnection();
		//return getDateSourceConnection("java:comp/env/jdbc/suyu");
	}

	public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		try {
			if (ps != null)
				ps.close();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		try {
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public static void main(String arg[]) {
		SampleDAO dao = new SampleDAO();
		try {
			dao.getConnection();
			System.out.println("conn");
			dao.close(dao.getConnection(), null, null);
			System.out.println("close");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}