package movie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/moviepage/moviereviewwrite.do")
public class MovieReviewWrite extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MovieReviewDTO dto = new MovieReviewDTO();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		HttpSession session = req.getSession();
		
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String id = (String) session.getAttribute("UserId");
		String idx = req.getParameter("idx");
		
		MovieReviewDAO dao = new MovieReviewDAO();
		dto.setTitle(title);
		dto.setContent(content);
		dto.setId(id);
		
		int result = dao.insertMovieReview(dto);
		dao.close();
		
		
		if(result == 1) {
			PrintWriter write = resp.getWriter();
			String script = "<script>alert('감상평이 등록되었습니다.'); "
					+ "location.href='/project01/moviepage/movieview.do?idx="+idx+"&&title="+title+"'</script>";
			write.print(script);
			write.close();
		} else {
			PrintWriter write = resp.getWriter();
			String script = "<script>alert('감상평 등록이 실패했습니다'); "
					+ "location.href='/project01/moviepage/movieview.do?idx="+idx+"&&title="+title+"'</script>";
			write.print(script);
			write.close();
		}
	}
	
}
