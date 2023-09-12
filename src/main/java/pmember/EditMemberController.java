package pmember;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/editmember.do")
public class EditMemberController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idx = req.getParameter("idx");
		PmemberDAO dao = new PmemberDAO();
		PmemberDTO dto = dao.selectMember(idx);
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/member/editmemberForm.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PmemberDAO dao = new PmemberDAO();
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String idx = req.getParameter("idx");
		String pass = req.getParameter("pass");
		String name = req.getParameter("name");
		String birthday = req.getParameter("birthday");
		String email = req.getParameter("email");
		String domain = req.getParameter("domain");
		String address = req.getParameter("address");
		String address1 = req.getParameter("address1");
		String postnumber = req.getParameter("postnumber");
		
		PmemberDTO dto = new PmemberDTO();
		dto.setIdx(idx);
		dto.setPass(pass);
		dto.setName(name);
		dto.setBirthday(birthday);
		dto.setEmail(email+"@"+domain);
		dto.setAddress(address);
		dto.setAddress1(address1);
		dto.setPostnumber(postnumber);
		
		int result = dao.updateMember(dto);
		dao.close();
		
		req.setAttribute("dto", dto);
		
		if(result == 1) {
			PrintWriter write = resp.getWriter();
			String script = "<script>alert('회원정보가 수정 되었습니다..'); "
					+ "location.href='/project01/index.jsp'</script>";
			write.print(script);
			write.close();

		} else {
			PrintWriter write = resp.getWriter();
			String script = "<script>alert('회원정보가 수정되지 않았습니다.'); "
					+ "history.back();</script>";
			write.print(script);
			write.close();
		}
	
	}
}
