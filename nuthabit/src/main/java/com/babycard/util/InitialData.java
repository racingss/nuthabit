package com.babycard.util;

import java.util.ArrayList;

import com.babycard.dao.Card;
import com.babycard.dao.CardMeaning;
import com.babycard.dao.CardSound;
import com.babycard.dao.CardTag;
import com.babycard.dao.CardTagLanguage;
import com.babycard.dao.Menu;

public class InitialData {

	public static void initAlldate() {
		Card.cardPicColl = null;
		Card.cardprivateColl = new ArrayList();
		CardSound.cardSoundColl = new ArrayList();
		CardMeaning.meaningColl=new ArrayList();
	}

}
