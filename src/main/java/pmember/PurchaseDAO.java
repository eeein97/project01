package pmember;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCConnect;

public class PurchaseDAO extends JDBCConnect{
	//구매목록 반환
	public List<PurchaseDTO> purchaseList(String id) {
		List<PurchaseDTO> plist = new ArrayList<>();
		String query = "select * from purchase where id=? order by postdate";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			while(rs.next()) {
				PurchaseDTO dto = new PurchaseDTO();
				dto.setIdx(rs.getString("idx"));
				dto.setTitle(rs.getString("title"));
				dto.setEpisode(rs.getString("episode"));
				dto.setId(rs.getString("id"));
				dto.setPostdate(rs.getString("postdate"));
				plist.add(dto);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return plist;
	}
}
