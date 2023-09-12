package drama;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCConnect;

public class DramaReviewDAO extends JDBCConnect {
	//드라마 리뷰 등록
	public int insertDramaReview(DramaReviewDTO dto) {
		int result = 0;
		String query = "insert into dramareview(idx, title, id, episode, content, postdate) "
				+ "values(seq_table_num.nextval, ?, ?, ?, ?, sysdate)";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getId());
			psmt.setString(3, dto.getEpisode());
			psmt.setString(4, dto.getContent());
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	//드라마 리뷰 목록 반환
	public List<DramaReviewDTO> getDramaReview(String title, String episode) {
		List<DramaReviewDTO> dramareviewlist = new ArrayList<>();
		String query = "select * from dramareview where title=? and episode=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, title);
			psmt.setString(2, episode);
			rs = psmt.executeQuery();
			while(rs.next()) {
				DramaReviewDTO dto = new DramaReviewDTO();
				dto.setIdx(rs.getString("idx"));
				dto.setTitle(rs.getString("title"));
				dto.setEpisode(rs.getString("episode"));
				dto.setId(rs.getString("id"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getString("postdate"));
				dramareviewlist.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dramareviewlist;
	}
	//드라마 리뷰 삭제
	public int deleteDramaReview(String idx) {
		int result = 0;
		String query = "delete from dramareview where idx=?";
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
