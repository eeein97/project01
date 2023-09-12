package drama;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.MovieDTO;

@WebServlet("/dramapage/dramaview.do")
public class DramaViewController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DramaDAO dao = new DramaDAO();
		String idx = req.getParameter("idx");
		String title = req.getParameter("title");
		DramaDTO dto = dao.selectDrama(idx, title);
		dao.close();
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/dramapage/dramadetailpage.jsp").forward(req, resp);
	}
	
}
