package movie;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.JDBCConnect;

public class MovieDAO extends JDBCConnect {
	
	//해당하는 영화 정보 반환
	public MovieDTO selectMovie(String idx) {
		MovieDTO dto = new MovieDTO();
		String query = "select * from movie where idx=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			rs = psmt.executeQuery();
			if(rs.next()) {
				dto.setIdx(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setOfile(rs.getString(3));
				dto.setSfile(rs.getString(4));
				dto.setRate(rs.getString(5));
				dto.setYear(rs.getString(6));
				dto.setRuntime(rs.getString(7));
				dto.setAge(rs.getString(8));
				dto.setDirector(rs.getString(9));
				dto.setCast(rs.getString(10));
				dto.setContent(rs.getString(11));
				dto.setPrice(rs.getString(12));
				dto.setYoutube(rs.getString(13));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dto;
	}
	//영화 목록 가져오기
	public List<MovieDTO> movielist() {
		List<MovieDTO> mlist = new ArrayList<>();
		String query = "select * from movie";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				MovieDTO dto = new MovieDTO();
				dto.setIdx(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setOfile(rs.getString(3));
				dto.setSfile(rs.getString(4));
				dto.setRate(rs.getString(5));
				dto.setYear(rs.getString(6));
				dto.setRuntime(rs.getString(7));
				dto.setAge(rs.getString(8));
				dto.setDirector(rs.getString(9));
				dto.setCast(rs.getString(10));
				dto.setContent(rs.getString(11));
				dto.setPrice(rs.getString(12));
				dto.setYoutube(rs.getString(13));
				mlist.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mlist;
	}
	//게시물 등록
	public int insertMovie(MovieDTO dto) {
		int result = 0;
		String qurey = "insert into movie(idx, title, ofile, sfile, rate, year, runtime, age, director, cast, content, price, youtube)" 
				+ " values(seq_table_num.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			psmt = con.prepareStatement(qurey);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getOfile());
			psmt.setString(3, dto.getSfile());
			psmt.setString(4, dto.getRate());
			psmt.setString(5, dto.getYear());
			psmt.setString(6, dto.getRuntime());
			psmt.setString(7, dto.getAge());
			psmt.setString(8, dto.getDirector());
			psmt.setString(9, dto.getCast());
			psmt.setString(10, dto.getContent());
			psmt.setString(11, dto.getPrice());
			psmt.setString(12, dto.getYoutube());
			result = psmt.executeUpdate();
			System.out.println("성공");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	//검색목록반환
	public List<MovieDTO> getMovieSearch(Map<String, Object> moviemap) {
		List<MovieDTO> moviesearchlist = new ArrayList<MovieDTO>();
		String query = "select * from movie where " + moviemap.get("searchField") + " like '%" 
				 + moviemap.get("searchWord") + "%'";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				MovieDTO dto = new MovieDTO();
				dto.setIdx(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setOfile(rs.getString(3));
				dto.setSfile(rs.getString(4));
				dto.setRate(rs.getString(5));
				dto.setYear(rs.getString(6));
				dto.setRuntime(rs.getString(7));
				dto.setAge(rs.getString(8));
				dto.setDirector(rs.getString(9));
				dto.setCast(rs.getString(10));
				dto.setContent(rs.getString(11));
				dto.setPrice(rs.getString(12));
				dto.setYoutube(rs.getString(13));
				moviesearchlist.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return moviesearchlist;
	}
	//게시글 업데이트 하기 (게시글 수정하기)
	public int updatePost(MovieDTO dto) {
		int result = 0;
		String query = "update movie set title=?, ofile=?, sfile=?, rate=?, year=?, runtime=?, "
				+ "age=?, director=?, cast=?, content=?, price=?, youtube=? where idx=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getOfile());
			psmt.setString(3, dto.getSfile());
			psmt.setString(4, dto.getRate());
			psmt.setString(5, dto.getYear());
			psmt.setString(6, dto.getRuntime());
			psmt.setString(7, dto.getAge());
			psmt.setString(8, dto.getDirector());
			psmt.setString(9, dto.getCast());
			psmt.setString(10, dto.getContent());
			psmt.setString(11, dto.getPrice());
			psmt.setString(12, dto.getYoutube());
			psmt.setString(13, dto.getIdx());
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
}
