package adon.word;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/yoyoword/book.html")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookServlet() {
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
			String kehuId = "adon";
			BookDAO dao = new BookDAO();

			if (request.getParameter("add") != null) {
				dao.addToMyBook(Long.parseLong(request.getParameter("bookId")), kehuId);
				request.setAttribute("msg", "已收藏");
			} else if (request.getParameter("delete") != null) {
				dao.deleteMyBook(Long.parseLong(request.getParameter("bookId")), kehuId);
				request.setAttribute("msg", "已取消收藏");
			}

			request.setAttribute("mybookColl", dao.getAllMybook(kehuId));
			request.setAttribute("keHistoryColl", new KeHistoryDAO().get(kehuId));
			request.setAttribute("bookColl", dao.getAll());

			request.getRequestDispatcher("book.jsp").forward(request, response);
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
