package pmember;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/join.do")
public class JoinController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PmemberDAO dao = new PmemberDAO();
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String id = req.getParameter("id");
		String pass = req.getParameter("pass");
		String name = req.getParameter("name");
		String date = req.getParameter("date");
		String email = req.getParameter("email");
		String domain = req.getParameter("domain");
		String address = req.getParameter("address");
		String address1 = req.getParameter("address1");
		String postnumber = req.getParameter("postnumber");
		
		PmemberDTO dto = new PmemberDTO();
		dto.setId(id);
		dto.setPass(pass);
		dto.setName(name);
		dto.setBirthday(date);
		dto.setEmail(email+"@"+domain);
		dto.setAddress(address);
		dto.setAddress1(address1);
		dto.setPostnumber(postnumber);
		
		int result = dao.insertMember(dto);
		dao.close();
		if(result == 1) {
			PrintWriter write = resp.getWriter();
			String script = "<script>alert('회원가입이 완료되었습니다.'); "
					+ "location.href='../index.jsp'</script>";
			write.print(script);
			write.close();

		} else {
			PrintWriter write = resp.getWriter();
			String script = "<script>alert('회원가입이 되지 않았습니다. 아이디 또는 메일이 중복되었습니다.'); "
					+ "location.href='./joinForm.jsp'</script>";
			write.print(script);
			write.close();

		}
		
	}
	
}
