package adon.word;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import adon.fangchan.BankuaiData;
import adon.fangchan.BankuaiDataDAO;

public class ICBA {
	public static Wordlist getUrl(String url) {
		URL tempUrl = null;
		BufferedReader reader = null;

		try {
			tempUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) tempUrl.openConnection();
			connection.connect();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String temp = null;

			InputStream is = connection.getInputStream();
			StringBuffer content = new StringBuffer();
			int a = 0;
			int d = 0;
			int i = 0;
			while ((is.read()) != -1) {
				int all = is.available();
				byte[] b = new byte[all];
				is.read(b);
				content.append(new String(b, "UTF-8"));
			}

			System.out.println(content.toString());

			Wordlist w = new Wordlist();
			// w.setEnPh(enPh);
			a = content.indexOf("key>") + 4;
			d = content.indexOf("</key", a);
			w.setWord(content.substring(a, d));

			a = content.indexOf("ps>", d) + 3;
			d = content.indexOf("</ps", a);
			w.setEnPh(content.substring(a, d));

			a = content.indexOf("pron>", d) + 5;
			d = content.indexOf("</pron", a);
			w.setEnPhMp3(content.substring(a, d));

			a = content.indexOf("ps>", d) + 3;
			d = content.indexOf("</ps", a);
			w.setAmPh(content.substring(a, d));

			a = content.indexOf("pron>", d) + 5;
			d = content.indexOf("</pron", a);
			w.setAmPhMp3(content.substring(a, d));

			if (content.indexOf("pos>", d) != -1) {
				a = content.indexOf("pos>", d) + 4;
				d = content.indexOf("</pos", a);
				w.setPos1(content.substring(a, d));

				a = content.indexOf("acceptation>", d) + 12;
				d = content.indexOf("</acceptation", a);
				w.setAcceptation1(content.substring(a, d));
			}

			if (content.indexOf("pos>", d) != -1) {
				a = content.indexOf("pos>", d) + 4;
				d = content.indexOf("</pos", a);
				w.setPos2(content.substring(a, d));

				a = content.indexOf("acceptation>", d) + 12;
				d = content.indexOf("</acceptation", a);
				w.setAcceptation2(content.substring(a, d));
			}

			if (content.indexOf("pos>", d) != -1) {
				a = content.indexOf("pos>", d) + 4;
				d = content.indexOf("</pos", a);
				w.setPos3(content.substring(a, d));

				a = content.indexOf("acceptation>", d) + 12;
				d = content.indexOf("</acceptation", a);
				w.setAcceptation3(content.substring(a, d));
			}

			if (content.indexOf("pos>", d) != -1) {
				a = content.indexOf("pos>", d) + 4;
				d = content.indexOf("</pos", a);
				w.setPos4(content.substring(a, d));

				a = content.indexOf("acceptation>", d) + 12;
				d = content.indexOf("</acceptation", a);
				w.setAcceptation4(content.substring(a, d));
			}

			System.out.println(w.toString());
			new WordlistDAO().updateWord(w);

		} catch (Exception me) {
			System.err.println(me);
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}

	public static void check(Wordlist w) {
		if (w == null)
			return;
		if (w.getPos1() == null) {
			ICBA.getUrl("http://dict-co.iciba.com/api/dictionary.php?w=" + w.getWord()
					+ "&key=D930B9D4E2657C394828BCBF7E421AA1");
			w = new WordlistDAO().getWord(w.getWord());
		}
	}

	public static void main(String[] args) {
		System.out.println(
				ICBA.getUrl("http://dict-co.iciba.com/api/dictionary.php?w=long&key=D930B9D4E2657C394828BCBF7E421AA1"));
		;
	}

}
