package movie ;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import fileupload.FileUtil;

@WebServlet("/moviepage/movieedit.do")
public class MovieEditController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idx = req.getParameter("idx");
		MovieDAO dao = new MovieDAO();
		MovieDTO dto = dao.selectMovie(idx);
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("./movieedit.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		//1.파일 업로드 처리
		//업로드 디렉토리의 물리적 경로확인
		String saveDirectory = req.getServletContext().getRealPath("/uploads");
		//파일 크기 지정
		int maxPostSize = 1024*1000;
		//파일 업로드
		MultipartRequest mr = FileUtil.uploadFile(req, saveDirectory, maxPostSize);
		if(mr == null) {
			//파일 업로드 실패
			return;
		}
		
		//파일 업로드 외 처리
		//수정내용을 매개변수에서 얻어옴
		String idx = mr.getParameter("idx");
		String title = mr.getParameter("title");
		String rate = mr.getParameter("rate");
		String year = mr.getParameter("year");
		String runtime = mr.getParameter("runtime");
		String age = mr.getParameter("age");
		String director = mr.getParameter("director");
		String content = mr.getParameter("content");
		String price = mr.getParameter("price");
		String youtube = mr.getParameter("youtube");
		String cast = mr.getParameter("cast");
		
		//DTO에 저장하기
		MovieDTO dto = new MovieDTO();
		dto.setTitle(title);
		dto.setRate(rate);
		dto.setYear(year);
		dto.setRuntime(runtime);
		dto.setAge(age);
		dto.setDirector(director);
		dto.setContent(content);
		dto.setPrice(price);
		dto.setIdx(idx);
		dto.setCast(cast);
		dto.setYoutube(youtube);
		
		String fileName = mr.getFilesystemName("ofile");
		if(fileName != null) {
			//새로운 파일명 생성
			String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
			String ext = fileName.substring(fileName.lastIndexOf("."));
			String newFileName = now + ext;
			
			//파일명 변경하기
			File oldFile = new File(saveDirectory+File.separator+fileName);
			File newFile = new File(saveDirectory+File.separator+newFileName);
			oldFile.renameTo(newFile);
			
			//DTO에 저장
			dto.setOfile(fileName);
			dto.setSfile(newFileName);
		}
		//데이터 베이스에 update 사용
		MovieDAO dao = new MovieDAO();
		int result = dao.updatePost(dto);
		dao.close();
		
		if(result == 1) {
			PrintWriter write = resp.getWriter();
			String script = "<script>alert('영화정보 수정이 완료되었습니다.'); "
					+ "location.href='./hdmoviedetail.jsp'</script>";
			write.print(script);
			write.close();
		} else {
			PrintWriter write = resp.getWriter();
			String script = "<script>alert('영화정보 수정을 실패했습니다.'); "
					+ "location.href='./movieedit.jsp'</script>";
			write.print(script);
			write.close();

		}
	}
	
	
}
