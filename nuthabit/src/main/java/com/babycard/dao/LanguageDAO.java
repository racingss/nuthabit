package com.babycard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.baidu.translate.demo.Main;
import com.baidu.translate.demo.TransApi;
import com.nuthabit.dao.SampleDAO;

import net.sf.json.JSONObject;

public class LanguageDAO extends SampleDAO {

	public Collection getAllLanguageColl() {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Study s = null;
		Collection coll = new ArrayList();

		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from baby_language ");
			rs = ps.executeQuery();
			while (rs.next()) {
				coll.add(new Language(rs));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return coll;
	}
	
	public void updateLanguage(long languageId,String lname) {
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		conn = null;
		ps = null;
		rs = null;
		Study s = null;
		Collection coll = new ArrayList();

		try {
			conn = getConnection();
			ps = conn.prepareStatement("update baby_language set lname=? where languageId=?");
			ps.setString(1, lname);
			ps.setLong(2, languageId);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}
	
	public static void main(String arg[]){
		Collection coll = new LanguageDAO().getAllLanguageColl();
		Iterator it = coll.iterator();
		
		TransApi api = new TransApi(Main.APP_ID, Main.SECURITY_KEY);

		LanguageDAO dao = new LanguageDAO();
		
		while(it.hasNext()){
			Language l  = (Language)it.next();
			System.out.println(l.toString());
			
			String r = api.getTransResult(l.getCname(), "zh", l.getSname());
			JSONObject json = JSONObject.fromObject(r.toString());
			String str1 = json.get("trans_result").toString();
			// 去掉[]
			str1 = str1.replace("[", "");
			str1 = str1.replace("]", "");
			JSONObject json1 = JSONObject.fromObject(str1);

			System.out.println("源语言：" + json1.get("src"));
			System.out.println("目标语言：" + json1.get("dst"));
			dao.updateLanguage(l.getLanguageId(), json1.get("dst").toString());
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
