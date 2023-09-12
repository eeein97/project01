package drama;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import movie.MovieDAO;
import movie.MovieDTO;
import movie.MoviePurchaseDAO;
import movie.MoviePurchaseDTO;

@WebServlet("/dramapage/dramapurchase.do")
public class DramaPurchaseController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DramaDAO dao = new DramaDAO();
		String idx = req.getParameter("idx");
		String title = req.getParameter("title");
		String episode = req.getParameter("episode");
		DramaDTO dto = dao.selectDrama(idx, title);
		dao.close();
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/dramapage/dramapurchase.jsp?idx"+idx+"&&title="+title+"&&episode="+episode).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		
		String idx = req.getParameter("idx");
		String title = req.getParameter("title");
		String episode = req.getParameter("episode");
		String id = (String) session.getAttribute("UserId");
		
		System.out.println(idx);
		
		DramaPurchaseDTO dto = new DramaPurchaseDTO();
		dto.setTitle(title);
		dto.setIdx(idx);
		dto.setEpisode(episode);
		dto.setId(id);
		
		DramaPurchaseDAO dao = new DramaPurchaseDAO();
		int result = dao.dramaPurchase(dto);
		
		if(result == 1) {
			PrintWriter write = resp.getWriter();
			String script = "<script>alert('구매가 완료되었습니다.'); "
					+ "location.href='./dramaview.do?idx="+idx+"&&title="+title+"&&episode="+episode+"'</script>";
			write.print(script);
			write.close();
		} else {
			PrintWriter write = resp.getWriter();
			String script = "<script>alert('구매하지 못하였습니다.'); "
					+ "location.href = document.referrer;</script>";
			write.print(script);
			write.close();

		}
	}
}
