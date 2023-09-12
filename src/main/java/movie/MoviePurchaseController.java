package movie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/moviepage/moviepurchase.do")
public class MoviePurchaseController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MovieDAO dao = new MovieDAO();
		String idx = req.getParameter("idx");
		String title = req.getParameter("title");
		MovieDTO dto = dao.selectMovie(idx);
		dao.close();
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/moviepage/moviepurchase.jsp?idx"+idx+"&&title="+title).forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		
		String title = req.getParameter("title");
		String idx = req.getParameter("idx");
		String id = (String) session.getAttribute("UserId");
		
		MoviePurchaseDTO dto = new MoviePurchaseDTO();
		dto.setTitle(title);
		dto.setIdx(idx);
		dto.setId(id);
		
		MoviePurchaseDAO dao = new MoviePurchaseDAO();
		int result = dao.moviePurchase(dto);
		
		if(result == 1) {
			PrintWriter write = resp.getWriter();
			String script = "<script>alert('구매가 완료되었습니다.'); "
					+ "location.href='./movieview.do?idx="+idx+"&&title="+title+"'</script>";
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
