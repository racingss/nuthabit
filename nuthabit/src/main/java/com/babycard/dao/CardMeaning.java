package com.babycard.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CardMeaning {
	private long meaningId = 0;
	// 0 中文 ，1 英 ，2 上海话
	private long languageId = 0;
	private String meaning = "";
	private long cardId = 0;
	private String sound = null;
	private String soundQue = null;
	private long picId = 0;

	public long getPicId() {
		return picId;
	}

	public void setPicId(long picId) {
		this.picId = picId;
	}

	public String getSoundQue() {
		return soundQue;
	}

	public void setSoundQue(String soundQue) {
		this.soundQue = soundQue;
	}

	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}

	public String getLanguage() {
		if (languageId == 0)
			return "中文";
		if (languageId == 1)
			return "English";
		return "还没注册这个语言";
	}

	public String getLanguageInput() {
		if (languageId == 0)
			return "chinese";
		if (languageId == 1)
			return "english";
		return "还没注册这个语言";
	}

	public long getMeaningId() {
		return meaningId;
	}

	public void setMeaningId(long meaningId) {
		this.meaningId = meaningId;
	}

	public long getLanguageId() {
		return languageId;
	}

	public void setLanguageId(long languageId) {
		this.languageId = languageId;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public CardMeaning() {

	}

	public long getCardId() {
		return cardId;
	}

	public void setCardId(long cardId) {
		this.cardId = cardId;
	}

	public CardMeaning(java.sql.ResultSet rs) {
		try {
			this.setLanguageId(rs.getLong("languageId"));
			this.setMeaning(rs.getString("meaning"));
			this.setMeaningId(rs.getLong("meaningId"));
			this.setCardId(rs.getLong("cardId"));
			// this.setSound(rs.getString("sound"));
			this.setSoundQue(rs.getString("soundQue"));
			this.setPicId(rs.getLong("picId"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Collection meaningColl = null;

	public static CardMeaning getStaticCard(long picId, long languageId) {
		try {
			CardMeaningDAO dao = new CardMeaningDAO();
			if (meaningColl == null)
				meaningColl = dao.getAllCardMeaning();
			Iterator it = meaningColl.iterator();
			while (it.hasNext()) {
				CardMeaning cm = (CardMeaning) it.next();
				if (cm == null)
					continue;
				if (cm.getPicId() == picId && cm.getLanguageId() == languageId)
					return cm;
			}

			// 重新翻译
			dao.buildMeaning(picId, languageId);
			CardMeaning temp = new CardMeaningDAO().getCardMeaningByPicId(picId, languageId);
			meaningColl.add(temp);
			return temp;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public String toString() {
		return "CardMeaning [meaningId=" + meaningId + ", languageId=" + languageId + ", meaning=" + meaning
				+ ", cardId=" + cardId + ", sound=" + sound + ", soundQue=" + soundQue + ", picId=" + picId + "]";
	}

}
