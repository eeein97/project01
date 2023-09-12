package pmember;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/member/pass.do")
public class PassController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("mode", req.getAttribute("mode"));
		req.setAttribute("idx", req.getAttribute("idx"));
		req.getRequestDispatcher("/member/pass.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idx = req.getParameter("idx");
		String mode = req.getParameter("mode");
		String pass = req.getParameter("pass");
		HttpSession session = req.getSession();
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		PmemberDAO dao = new PmemberDAO();
		boolean confirmed = dao.confirmPass(pass, idx);
		dao.close();
		if(confirmed) {
			if(mode.equals("memberdelete")) {
				dao = new PmemberDAO();
				int result = dao.deleteMember(idx);
				if(result==1) {
					session.removeAttribute("UserName");
					session.removeAttribute("UserId");
					PrintWriter write = resp.getWriter();
					String script = "<script>alert('탈퇴하였습니다. 그동안 감사드립니다.'); "
							+ "location.href='../index.jsp'</script>";
					write.print(script);
					write.close();
				} else {
					PrintWriter write = resp.getWriter();
					String script = "<script>alert('탈퇴되지 않았습니다.'); "
							+ "location.href='../index.jsp'</script>";
					write.print(script);
					write.close();
				}
			} else if(mode.equals("editmember")) {
				session.setAttribute("pass", pass);
				resp.sendRedirect("./editmember.do?idx="+idx);
			} 
		} else {
			PrintWriter write = resp.getWriter();
			String script = "<script>alert('비밀번호가 틀렸습니다.'); "
					+ "history.back();</script>";
			write.print(script);
			write.close();
		}
	}
}
