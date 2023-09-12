package music;

import java.sql.SQLException;

import common.JDBCConnect;
import movie.MoviePurchaseDTO;

public class MusicPurchaseDAO extends JDBCConnect {
	// 구매
		public int musicPurchase(MusicPurchaseDTO dto) {
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
		public int musicPurchasecheck(String id, String title) {
			int result = 0;
			String query = "select count(m.title) from music m join purchase p on (m.title = p.title) where p.id= ? and m.title=?";;
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
