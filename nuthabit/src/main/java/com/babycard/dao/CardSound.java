package com.babycard.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CardSound {
	private long soundId = 0;
	private long languageId = 0;
	private long cardId = 0;
	private String sound = null;
	private long kehuId = 0;
	private long status = 0;

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

	public CardSound(ResultSet rs) {
		try {
			this.setCardId(rs.getLong("cardId"));
			this.setKehuId(rs.getLong("kehuId"));
			this.setLanguageId(rs.getLong("languageId"));
			this.setSound(rs.getString("sound"));
			this.setSoundId(rs.getLong("soundId"));
			this.setStatus(rs.getLong("status"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
