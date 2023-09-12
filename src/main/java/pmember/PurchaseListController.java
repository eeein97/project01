package pmember;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

@WebServlet("/member/purchaselist.do")
public class PurchaseListController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//DAO클래스와 연결할 dao객체 생성 (DAO클래스는 JDBCConnect클래스를 상속받았으므로,DB와 연결가능)
		PurchaseDAO dao = new PurchaseDAO();
		//세션값을 가져올 세션 객체 생성
		HttpSession session = req.getSession();
		
		//DAO의 메소드에 넣어줄 id값을 세션으로부터 받아와서 id객체를 생성
		String id = (String) session.getAttribute("UserId");
		//DTO List객체(복수의 값을 받아와야 하므로, List 사용)에 dao의 purchaseList메소드에 아이디값을 담아 전달
		List<PurchaseDTO> plist = dao.purchaseList(id);
		//DB와 연결 종료 
		dao.close();
		
		//Request객체에 dto의 데이터들을 담는다.
		req.setAttribute("plist", plist);
		//Request객체와 Response객체를 해당주소로 전달
		req.getRequestDispatcher("/member/purchaselist.jsp").forward(req, resp);	
	}
}
