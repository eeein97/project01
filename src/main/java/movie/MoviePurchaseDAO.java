package movie;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCConnect;

public class MoviePurchaseDAO extends JDBCConnect {
	// 구매
	public int moviePurchase(MoviePurchaseDTO dto) {
		int result = 0;
		String query = "insert into purchase(idx, title, id, postdate) "
				+ "values(?, ?, ?, sysdate)";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getIdx());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getId());
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	// 구매 여부 반환
	public int moviePurchasecheck(String id, String title) {
		int result = 0;
		String query = "select count(m.title) from movie m join purchase p on (m.title = p.title) where p.id= ? and m.title=?";;
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			psmt.setString(2, title);
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
