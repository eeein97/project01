package pmember;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletRequest;

@WebServlet("/member/login.do")
public class LoginController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//선연결
		PmemberDAO dao = new PmemberDAO();
		//세션생성
		HttpSession session = req.getSession();
		//인코딩
		resp.setContentType("text/html;charset=UTF-8");
		
		PmemberDTO dto = dao.getMember(req.getParameter("id"),
					req.getParameter("pass"));
		if(dto.getId() != null) {
			session.setAttribute("UserId", dto.getId());
			session.setAttribute("UserName", dto.getName());
			PrintWriter write = resp.getWriter();
			String script = "<script>alert('로그인 되었습니다.'); "
					+ "location.href='../index.jsp'</script>";
			write.print(script);
			write.close();
		} else {
			PrintWriter write = resp.getWriter();
			String script = "<script>alert('로그인 실패. 아이디 / 비밀번호를 확인하세요.'); "
					+ "location.href='../index.jsp'</script>";
			write.print(script);
			write.close();
		}
	}
	
	
}
