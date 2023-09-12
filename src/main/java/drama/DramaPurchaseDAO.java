package drama;

import java.sql.SQLException;

import common.JDBCConnect;
import movie.MoviePurchaseDTO;

public class DramaPurchaseDAO extends JDBCConnect {
	// 구매
	public int dramaPurchase(DramaPurchaseDTO dto) {
		int result = 0;
		String query = "insert into purchase(idx, title, id, episode, postdate) "
				+ "values(?, ?, ?, ?, sysdate)";
		try {
			psmt = con.prepareStatement(query);			
			psmt.setString(1, dto.getIdx());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getId());
			psmt.setString(4, dto.getEpisode());
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	// 구매 여부 반환
	public int dramaPurchasecheck(String id, String title, String episode) {
		int result = 0;
		String query = "select count(*) from purchase where id=? and title=? and episode=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			psmt.setString(2, title);
			psmt.setString(3, episode);
			rs = psmt.executeQuery();
			rs.next();
			result = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = 0;
			e.printStackTrace();
		}
			
		return result;
	}
}
