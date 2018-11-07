package adon.word;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MywordListServlet
 */
@WebServlet("/yoyoword/mylist.html")
public class MywordListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MywordListServlet() {
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
		String kehuId = "adon";

		MywordDAO dao = new MywordDAO();
		if (request.getParameter("clean") != null && request.getParameter("clean").equalsIgnoreCase("t")) {
			dao.deleteAll(kehuId);
		}

		int level = 0;
		if (request.getParameter("level") != null)
			level = Integer.parseInt(request.getParameter("level"));
		request.setAttribute("mylist", new MywordDAO().getMywordColl(kehuId, level));
		request.getRequestDispatcher("mylist.jsp").forward(request, response);
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
