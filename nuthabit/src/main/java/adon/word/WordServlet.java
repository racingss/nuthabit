package adon.word;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/yoyoword/wordajax.html")
public class WordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WordServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// TODO Auto-generated method stub
		try {
			if (request.getParameter("word") != null) {
				try {
					Wordlist w = new WordlistDAO().getWord(request.getParameter("word"));
					if (w == null) {
						return;
					}
					ICBA.check(w);
					StringBuffer s = new StringBuffer();
					if (w.getAmPhMp3() != null && w.getAmPh() != null && w.getEnPhMp3() != null
							&& w.getEnPhMp3() != null) {
						s.append("English phonetics:");
						s.append(w.getEnPh());
						s.append("<audio controls=\"controls\">");
						s.append("\"");
						s.append(w.getEnPhMp3());
						s.append("\"/></audio>");
						s.append(w.getAmPh());
						s.append("American phonetics:");
						s.append("<audio controls=\"controls\">");
						s.append("<source src=\"");
						s.append(w.getAmPhMp3());
						s.append("\"/></audio>");
					}
					response.setCharacterEncoding("UTF-8");
					response.getWriter().append(s.toString());

				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.err.println(e.toString());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
