package adon.word;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/yoyoword/articalword.html")
public class ArticalWordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ArticalWordServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// TODO Auto-generated method stub
			String kehuId = "adon";

			MywordDAO dao = new MywordDAO();

			if (request.getParameter("process") != null) {
				Myword m = new Myword();
				m.setKehuId(kehuId);
				m.setLevel(Integer.parseInt(request.getParameter("process")));
				m.setWord(request.getParameter("word"));
				dao.add(m);
			}

			Collection coll = new ArticalDAO().getAllArticalWords(Long.parseLong(request.getParameter("id")));
			Collection mycoll = new MywordDAO().getMywordColl(kehuId, -1);
			Iterator it = coll.iterator();
			while (it.hasNext()) {
				ArticalWord aw = (ArticalWord) it.next();
				if (exist(mycoll, aw.getWord()))
					continue;
				else {
					Wordlist wl = new WordlistDAO().getWord(aw.getWord());
					if (wl != null) {
						request.setAttribute("w", wl);
						break;
					}
				}
			}

			request.getRequestDispatcher("articalword.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean exist(Collection mycoll, String word) {
		Iterator myIt = mycoll.iterator();
		while (myIt.hasNext()) {
			Myword m = (Myword) myIt.next();
			if (m.getWord().equalsIgnoreCase(word))
				return true;
			else if (m.getWord().indexOf(FotmatUtil.getWordFuzzy(word)) != -1)
				return true;
		}
		return false;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
