package movie;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCConnect;

public class MovieReviewDAO extends JDBCConnect {
	//리뷰 등록
	public int insertMovieReview(MovieReviewDTO dto) {
		int result = 0;
		String query = "insert into moviereview(idx, title, id, content, postdate) "
				+ "values(seq_table_num.nextval, ?, ?, ?, sysdate)";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getId());
			psmt.setString(3, dto.getContent());
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	//영화 리뷰 목록 반환
	public List<MovieReviewDTO> getMovieReview(String title) {
		List<MovieReviewDTO> moviereviewlist = new ArrayList<>();
		String query = "select * from moviereview where title=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, title);
			rs = psmt.executeQuery();
			while(rs.next()) {
				MovieReviewDTO dto = new MovieReviewDTO();
				dto.setIdx(rs.getString("idx"));
				dto.setTitle(rs.getString("title"));
				dto.setId(rs.getString("id"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getString("postdate"));
				moviereviewlist.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return moviereviewlist;
	}
	//영화 리뷰 삭제
	public int deleteMovieReview(String idx) {
		int result = 0;
		String query = "delete from moviereview where idx=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
