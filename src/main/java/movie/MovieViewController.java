package movie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/moviepage/movieview.do")
public class MovieViewController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MovieDAO dao = new MovieDAO();
		String idx = req.getParameter("idx");
		MovieDTO dto = dao.selectMovie(idx);
		dao.close();
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/moviepage/moviedetailpage.jsp").forward(req, resp);
	}
	
}
