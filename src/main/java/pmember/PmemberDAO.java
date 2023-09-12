package pmember;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.JDBCConnect;

public class PmemberDAO extends JDBCConnect{
	//회원 추가
	public int insertMember(PmemberDTO dto) {
		int result = 0;
		String query = "insert into pmember(idx, id, pass, name, email, postnumber, address, address1, birthday) "
				+ "values(seq_pmember_num.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPass());
			psmt.setString(3, dto.getName());
			psmt.setString(4, dto.getEmail());
			psmt.setString(5, dto.getPostnumber());
			psmt.setString(6, dto.getAddress());
			psmt.setString(7, dto.getAddress1());
			psmt.setString(8, dto.getBirthday());
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	//아이디와 비밀번호가 일치하는 회원정보 반환
	public PmemberDTO getMember(String id, String pass) {
		PmemberDTO dto = new PmemberDTO();
		String query = "select * from pmember where id=? and pass=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			psmt.setString(2, pass);
			rs = psmt.executeQuery();
			if(rs.next()) {
				dto.setIdx(rs.getString(1));
				dto.setId(rs.getString(2));
				dto.setPass(rs.getString(3));
				dto.setName(rs.getString(4));
				dto.setEmail(rs.getString(5));
				dto.setAddress(rs.getString(6));
				dto.setPurchase(rs.getString(7));
				dto.setAddress1(rs.getString(8));
				dto.setPostnumber(rs.getString(9));
				dto.setBirthday(rs.getString(10));
				dto.setMoney(rs.getString(11));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}
	//회원목록 반환
	public List<PmemberDTO> getMemberList() {
		List<PmemberDTO> memberlist = new ArrayList<>();
		String query = "select idx, id, name, email, birthday, postnumber, address, address1, purchase, "
				+ "nvl(money, '0') as money from pmember";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				PmemberDTO dto = new PmemberDTO();
				dto.setIdx(rs.getString("idx"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setBirthday(rs.getString("birthday"));
				dto.setPostnumber(rs.getString("postnumber"));
				dto.setAddress(rs.getString("address"));
				dto.setAddress1(rs.getString("address1"));
				dto.setPurchase(rs.getString("purchase"));
				dto.setMoney(rs.getString("money"));
				memberlist.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return memberlist;
	}
	//회원정보 보기
	public PmemberDTO selectMember(String id) {
		PmemberDTO dto = new PmemberDTO();
		String query = "select * from pmember where id=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if(rs.next()) {
				dto.setIdx(rs.getString("idx"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setBirthday(rs.getString("birthday"));
				dto.setPostnumber(rs.getString("postnumber"));
				dto.setAddress(rs.getString("address"));
				dto.setAddress1(rs.getString("address1"));
				dto.setPurchase(rs.getString("purchase"));
				dto.setMoney(rs.getString("money"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dto;
	}
	//입력한 비밀번호가 회원정보의 비밀번호와 동일한지 확인
	public boolean confirmPass(String pass, String idx) {
		boolean isCorr = true;
		String query = "select count(*) from pmember where pass=? and idx=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, pass);
			psmt.setString(2, idx);
			rs = psmt.executeQuery();
			rs.next();
			if(rs.getInt(1)==0) {
				isCorr = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			isCorr = false;
			e.printStackTrace();
		}
		
		return isCorr;
	}
	//회원탈퇴
	public int deleteMember(String idx) {
		int result = 0;
		String query = "delete from pmember where idx=?";
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
	//회원정보수정
	public int updateMember(PmemberDTO dto) {
		int result = 0;
		String query = "update pmember set pass=?, name=?, birthday=?, email=?, "
				+ "address=?, address1=?, postnumber=? where idx=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getPass());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getBirthday());
			psmt.setString(4, dto.getEmail());
			psmt.setString(5, dto.getAddress());
			psmt.setString(6, dto.getAddress1());
			psmt.setString(7, dto.getPostnumber());
			psmt.setString(8, dto.getIdx());
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
}
