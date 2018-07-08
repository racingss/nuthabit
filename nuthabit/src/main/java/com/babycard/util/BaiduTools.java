package com.babycard.util;

import java.util.ArrayList;
import java.util.Collection;

import com.babycard.dao.CardMeaning;
import com.babycard.dao.CardMeaningDAO;
import com.babycard.dao.CardSound;
import com.babycard.dao.Language;
import com.babycard.dao.SoundDAO;
import com.baidu.speech.restapi.ttsdemo.TtsMain;
import com.baidu.translate.demo.Main;
import com.baidu.translate.demo.TransApi;

import net.sf.json.JSONObject;

public class BaiduTools implements Runnable {

	long picId = 0;
	long languageId = 0;
	long cardId = 0;
	String meaning = null;
	String server = null;

	public BaiduTools(long picId, long languageId, long cardId, String meaning, String server) {
		this.picId = picId;
		this.languageId = languageId;
		this.cardId = cardId;
		this.meaning = meaning;
		this.server = server;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		buildMeaning();
	}

	public void buildMeaning() {
		System.out.println("ready:");
		CardSound cs = new CardSound();
		cs.setCardId(cardId);
		cs.setLanguageId(languageId);
		cs.setPicId(picId);
		try {
			String weburl = "card/sound/";
			String path = server + weburl;// request.getSession().getServletContext().getRealPath("/")
											// + weburl;
			cs.setSound(TtsMain.build(meaning, path));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("something wrong:");
			e.printStackTrace();
		}
		cs.setStatus(1);
		System.out.println(cs.toString());
		new SoundDAO().addCardSound(cs, true);
		CardSound.cardSoundColl = new ArrayList();
	}

}
