package adon.word;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/yoyoword/index.html")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("search:" + request.getParameter("word"));
		// TODO Auto-generated method stub
		try {
			if (request.getParameter("word") != null) {
				try {
					Wordlist w = new WordlistDAO().getWord(request.getParameter("word"));
					ICBA.check(w);

					request.setAttribute("articalLineWordColl",
							new ArticalDAO().getAllArticalLinesByWord(request.getParameter("word")));

				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.err.println(e.toString());
				}
				request.setAttribute("wordColl", new WordlistDAO().get(request.getParameter("word")));
				request.setAttribute("word", request.getParameter("word"));
			}

			request.getRequestDispatcher("index.jsp").forward(request, response);
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
