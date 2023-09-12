package music;

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

@WebServlet("/musicpage/musicpurchase.do")
public class MusicPurchaseController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MusicDAO dao = new MusicDAO();
		String idx = req.getParameter("idx");
		String title = req.getParameter("title");
		MusicDTO dto = dao.selectMusic(idx);
		dao.close();
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/musicpage/musicpurchase.jsp?idx"+idx+"&&title="+title).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		
		String title = req.getParameter("title");
		String idx = req.getParameter("idx");
		String id = (String) session.getAttribute("UserId");
		
		MusicPurchaseDTO dto = new MusicPurchaseDTO();
		dto.setTitle(title);
		dto.setIdx(idx);
		dto.setId(id);
		
		MusicPurchaseDAO dao = new MusicPurchaseDAO();
		int result = dao.musicPurchase(dto);
		
		if(result == 1) {
			PrintWriter write = resp.getWriter();
			String script = "<script>alert('구매가 완료되었습니다.'); "
					+ "location.href='./musicview.do?idx="+idx+"&&title="+title+"'</script>";
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
