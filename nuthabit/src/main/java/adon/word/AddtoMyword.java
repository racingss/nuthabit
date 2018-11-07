package adon.word;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddtoMyword
 */
@WebServlet("/yoyoword/addtomyword.html")
public class AddtoMyword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddtoMyword() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			String kehuId = "adon";

			String word = request.getParameter("word");
			word = word.replaceAll("_", " ");

			int level = 7;
			if (request.getParameter("level") != null)
				level = Integer.parseInt(request.getParameter("level"));

			MywordDAO dao = new MywordDAO();
			Myword m = new Myword();
			m.setKehuId(kehuId);
			m.setWord(word);
			m.setLevel(level);
			if (request.getParameter("level") != null) {
				try {
					dao.add(m);
					response.getWriter().append(Integer.toString(level));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.getWriter().append("-1");
				}
			} else {
				response.getWriter().append(Integer.toString(dao.getLevel(m)));
			}
		} catch (NumberFormatException e) {
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
