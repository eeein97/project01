package music ;

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

@WebServlet("/musicpage/musicedit.do")
public class MusicEditController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idx = req.getParameter("idx");
		MusicDAO dao = new MusicDAO();
		MusicDTO dto = dao.selectMusic(idx);
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("./musicedit.jsp").forward(req, resp);
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
		String album = mr.getParameter("album");
		String year = mr.getParameter("year");
		String artist = mr.getParameter("artist");
		String playtime = mr.getParameter("playtime");
		String lyrics = mr.getParameter("lyrics");
		String price = mr.getParameter("price");
		String youtube = mr.getParameter("youtube");
		
		//DTO에 저장하기
		MusicDTO dto = new MusicDTO();
		dto.setTitle(title);
		dto.setAlbum(album);
		dto.setYear(year);
		dto.setArtist(artist);
		dto.setPlaytime(playtime);
		dto.setLyrics(lyrics);
		dto.setPrice(price);
		dto.setIdx(idx);
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
		MusicDAO dao = new MusicDAO();
		int result = dao.updateMusicPost(dto);
		dao.close();
		
		if(result == 1) {
			PrintWriter write = resp.getWriter();
			String script = "<script>alert('음악 정보 수정이 완료되었습니다.'); "
					+ "location.href='./hdmusicdetail.jsp'</script>";
			write.print(script);
			write.close();
		} else {
			PrintWriter write = resp.getWriter();
			String script = "<script>alert('음악 정보 수정을 실패했습니다.'); "
					+ "location.href='./musicedit.jsp'</script>";
			write.print(script);
			write.close();

		}
	}
	
	
}
