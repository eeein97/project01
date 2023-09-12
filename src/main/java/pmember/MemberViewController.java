package pmember;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/memberview.do")
public class MemberViewController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PmemberDAO dao = new PmemberDAO();
		String id = req.getParameter("id");
		PmemberDTO dto = dao.selectMember(id);
		dao.close();
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/member/memberview.jsp").forward(req, resp);
	}
	
}
