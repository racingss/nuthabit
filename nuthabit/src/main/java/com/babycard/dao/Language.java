package com.babycard.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

public class Language {
	private long languageId = 0;
	private String cname = null;
	private String sname = null;
	private String lname = null;

	public long getLanguageId() {
		return languageId;
	}

	public void setLanguageId(long languageId) {
		this.languageId = languageId;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public Language() {

	}

	public static Collection languageColl = new LanguageDAO().getAllLanguageColl();

	public static Language getLanguageByid(long languageId) {
		Iterator it = languageColl.iterator();
		while (it.hasNext()) {
			Language l = (Language) it.next();
			if (l.getLanguageId() == languageId)
				return l;
		}
		return null;
	}

	public Language(java.sql.ResultSet rs) {
		try {
			this.setCname(rs.getString("cname"));
			this.setLanguageId(rs.getLong("languageId"));
			this.setLname(rs.getString("lname"));
			this.setSname(rs.getString("sname"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Language [languageId=" + languageId + ", cname=" + cname + ", sname=" + sname + ", lname=" + lname
				+ "]";
	}

}
