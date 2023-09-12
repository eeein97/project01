package music;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCConnect;

public class MusicReviewDAO extends JDBCConnect {
	//리뷰등록
	public int insertMusicReview(MusicReviewDTO dto) {
		int result = 0;
		String query = "insert into musicreview(idx, title, id, content, postdate) "
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
	//음악 리뷰 목록 반환
	public List<MusicReviewDTO> getMusicReview(String title) {
		List<MusicReviewDTO> dramareviewlist = new ArrayList<>();
		String query = "select * from musicreview where title=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, title);
			rs = psmt.executeQuery();
			while(rs.next()) {
				MusicReviewDTO dto = new MusicReviewDTO();
				dto.setIdx(rs.getString("idx"));
				dto.setTitle(rs.getString("title"));
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
	//음악 리뷰 삭제
	public int deleteMusicReview(String idx) {
		int result = 0;
		String query = "delete from musicreview where idx=?";
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
