package drama;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dramapage/dramareviewdelete.do")
public class DramaReviewDelete extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		String idx = req.getParameter("idx");
		String title = req.getParameter("title");
		String episode = req.getParameter("episode");
		DramaReviewDAO dao = new DramaReviewDAO();
		int result = dao.deleteDramaReview(idx);
		dao.close();
		
		if(result == 1) {
			PrintWriter write = resp.getWriter();
			String script = "<script>alert('삭제 되었습니다.'); "
					+ "location.href = document.referrer;</script>";
			write.print(script);
			write.close();
		} else {
			PrintWriter write = resp.getWriter();
			String script = "<script>alert('삭제 하지 못하였습니다.'); "
					+ "history.back()</script>";
			write.print(script);
			write.close();
		}
		
		
		
	}
	
}
