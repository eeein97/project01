package music;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.MovieDTO;

@WebServlet("/musicpage/musicview.do")
public class MusicViewController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MusicDAO dao = new MusicDAO();
		String idx = req.getParameter("idx");
		MusicDTO dto = dao.selectMusic(idx);
		dao.close();
		
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/musicpage/musicdetailpage.jsp").forward(req, resp);
	}

	
	
}
