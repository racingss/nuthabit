package com.babycard.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

public class CardTagLanguage {
	private long id = 0;
	private long tagId = 0;
	private String tag = null;
	private long languageId = 0;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTagId() {
		return tagId;
	}

	public void setTagId(long tagId) {
		this.tagId = tagId;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public long getLanguageId() {
		return languageId;
	}

	public void setLanguageId(long languageId) {
		this.languageId = languageId;
	}

	public CardTagLanguage() {

	}

	public static Collection languageColl = null;

	public static String getTagLanguage(long tagId, long languageId) {
		if (languageColl == null)
			languageColl = new TagDAO().getAllTagLanguage();
		Iterator it = languageColl.iterator();
		while (it.hasNext()) {
			CardTagLanguage ctl = (CardTagLanguage) it.next();
			if (ctl.getLanguageId() == languageId && ctl.getTagId() == tagId)
				return ctl.getTag();
		}

		CardTagLanguage temp = new TagDAO().getTagLanguage(tagId, languageId);
		if (temp != null) {
			languageColl.add(temp);
			return temp.getTag();
		} else {
			return CardTag.getCartTagByTagId(tagId).getTag();
		}
	}

	public CardTagLanguage(ResultSet rs) {
		try {
			this.setId(rs.getLong("id"));
			this.setLanguageId(rs.getLong("languageId"));
			this.setTag(rs.getString("tag"));
			this.setLanguageId(rs.getLong("languageId"));
			this.setTagId(rs.getLong("tagId"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "CardTagLanguage [id=" + id + ", tagId=" + tagId + ", tag=" + tag + ", languageId=" + languageId + "]";
	}
	
	

}
