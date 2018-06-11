package com.babycard.dao;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.*;

public class CardSound {
	private long soundId = 0;
	private long languageId = 0;
	private long cardId = 0;
	private String sound = null;
	private long kehuId = 0;
	private long status = 0;

	private long picId = 0;

	public final static long STATUS_FIRST = 0;
	public final static long STATUS_CONFIRMED = 1;
	public final static long STATUS_REJECT = -1;

	public long getPicId() {
		return picId;
	}

	public void setPicId(long picId) {
		this.picId = picId;
	}

	public long getSoundId() {
		return soundId;
	}

	public void setSoundId(long soundId) {
		this.soundId = soundId;
	}

	public long getLanguageId() {
		return languageId;
	}

	public void setLanguageId(long languageId) {
		this.languageId = languageId;
	}

	public long getCardId() {
		return cardId;
	}

	public void setCardId(long cardId) {
		this.cardId = cardId;
	}

	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}

	public long getKehuId() {
		return kehuId;
	}

	public void setKehuId(long kehuId) {
		this.kehuId = kehuId;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public CardSound() {

	}

	public static Collection cardSoundColl = new ArrayList();

	public static Collection getSoundCollByPicId(long picId) {
		Iterator it = cardSoundColl.iterator();
		while (it.hasNext()) {
			Object[] tempObj = (Object[]) it.next();
			if (picId == Long.parseLong(tempObj[0].toString()))
				return (Collection) tempObj[1];
		}

		Collection coll = new SoundDAO().getCardSoundCollByPicId(picId);
		Object[] tempObj = new Object[] { picId, coll };
		cardSoundColl.add(tempObj);
		return coll;
	}

	public CardSound(ResultSet rs) {
		try {
			this.setCardId(rs.getLong("cardId"));
			this.setKehuId(rs.getLong("kehuId"));
			this.setLanguageId(rs.getLong("languageId"));
			this.setSound(rs.getString("sound"));
			this.setSoundId(rs.getLong("soundId"));
			this.setStatus(rs.getLong("status"));
			this.setPicId(rs.getLong("picId"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public String toString() {
		return "CardSound [soundId=" + soundId + ", languageId=" + languageId + ", cardId=" + cardId + ", sound="
				+ sound + ", kehuId=" + kehuId + ", status=" + status + ", picId=" + picId + "]";
	}

}
