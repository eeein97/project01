package drama;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.JDBCConnect;
import movie.MovieDTO;
import music.MusicDTO;

public class DramaDAO extends JDBCConnect{
	//드라마 상세 페이지
	public DramaDTO selectDrama(String idx, String title) {
		DramaDTO dto = new DramaDTO();
		String query = "select * from drama where idx=? and title=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			psmt.setString(2, title);
			rs = psmt.executeQuery();
			if(rs.next()) {
				dto.setIdx(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setOfile(rs.getString(3));
				dto.setSfile(rs.getString(4));
				dto.setYear(rs.getString(5));
				dto.setEpisode(rs.getString(6));
				dto.setAge(rs.getString(7));
				dto.setCast(rs.getString(8));
				dto.setContent(rs.getString(9));
				dto.setInfo(rs.getString(10));
				dto.setPrice(rs.getString(11));
				dto.setYoutube(rs.getString(12));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dto;
	}

	//게시물 등록
	public int insertdrama(DramaDTO dto) {
		int result = 0;
		String qurey = "insert into drama(idx, title, ofile, sfile, episode, year, age, cast, content, info, price, youtube)" 
				+ " values(seq_drama_num.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			psmt = con.prepareStatement(qurey);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getOfile());
			psmt.setString(3, dto.getSfile());
			psmt.setString(4, dto.getEpisode());
			psmt.setString(5, dto.getYear());
			psmt.setString(6, dto.getAge());
			psmt.setString(7, dto.getCast());
			psmt.setString(8, dto.getContent());
			psmt.setString(9, dto.getInfo());
			psmt.setString(10, dto.getPrice());
			psmt.setString(11, dto.getYoutube());
			result = psmt.executeUpdate();
			System.out.println("성공");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	//드라마 목록 가져오기
	public List<DramaDTO> dramalist() {
		List<DramaDTO> mlist = new ArrayList<>();
		String query = "select * from drama where episode=1";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				DramaDTO dto = new DramaDTO();
				dto.setIdx(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setOfile(rs.getString(3));
				dto.setSfile(rs.getString(4));
				dto.setYear(rs.getString(5));
				dto.setEpisode(rs.getString(6));
				dto.setAge(rs.getString(7));
				dto.setCast(rs.getString(8));
				dto.setContent(rs.getString(9));
				dto.setInfo(rs.getString(10));
				dto.setPrice(rs.getString(11));
				dto.setYoutube(rs.getString(12));
				mlist.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mlist;
	}
	public List<DramaDTO> beDramalist(String title) {
		List<DramaDTO> mlist = new ArrayList<>();
		
		String query = "select * from drama where title='"+title+"' order by title , LPAD(episode, 2, '0')";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			//게시글 목록을 list컬렉션에 추가
			while(rs.next()) {
				DramaDTO dto = new DramaDTO();
				dto.setIdx(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setOfile(rs.getString(3));
				dto.setSfile(rs.getString(4));
				dto.setYear(rs.getString(5));
				dto.setEpisode(rs.getString(6));
				dto.setAge(rs.getString(7));
				dto.setCast(rs.getString(8));
				dto.setContent(rs.getString(9));
				dto.setInfo(rs.getString(10));
				dto.setPrice(rs.getString(11));
				dto.setYoutube(rs.getString(12));
				mlist.add(dto);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return mlist;
	}
	//에피소드 불러오기
	public List<DramaDTO> episodelist(String title) {
		List<DramaDTO> mlist = new ArrayList<>();
		String query = "select * from drama where title=? order by LPAD(episode, 2, '0')";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, title);
			rs = psmt.executeQuery();
			while(rs.next()) {
				DramaDTO dto = new DramaDTO();
				dto.setIdx(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setOfile(rs.getString(3));
				dto.setSfile(rs.getString(4));
				dto.setYear(rs.getString(5));
				dto.setEpisode(rs.getString(6));
				dto.setAge(rs.getString(7));
				dto.setCast(rs.getString(8));
				dto.setContent(rs.getString(9));
				dto.setInfo(rs.getString(10));
				dto.setPrice(rs.getString(11));
				dto.setYoutube(rs.getString(12));
				mlist.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mlist;
	}
	//검색 목록 반환
	public List<DramaDTO> getDramaSearch(Map<String, Object> daramamap) {
		List<DramaDTO> dramasearchlist = new ArrayList<DramaDTO>();
		String query = "select * from drama where " + daramamap.get("searchField1") + " like '%" 
				 + daramamap.get("searchWord1") + "%' order by title, LPAD(episode, 2, '0')";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				DramaDTO dto = new DramaDTO();
				dto.setIdx(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setOfile(rs.getString(3));
				dto.setSfile(rs.getString(4));
				dto.setYear(rs.getString(5));
				dto.setEpisode(rs.getString(6));
				dto.setAge(rs.getString(7));
				dto.setCast(rs.getString(8));
				dto.setContent(rs.getString(9));
				dto.setInfo(rs.getString(10));
				dto.setPrice(rs.getString(11));
				dto.setYoutube(rs.getString(12));
				dramasearchlist.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dramasearchlist;
	}
	//게시글 업데이트 하기 (게시글 수정하기)
	public int updatePost(DramaDTO dto) {
		int result = 0;
		String query = "update drama set title=?, ofile=?, sfile=?, year=?, episode=?, "
				+ "age=?, cast=?, content=?, price=?, youtube=? where idx=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getOfile());
			psmt.setString(3, dto.getSfile());
			psmt.setString(4, dto.getYear());
			psmt.setString(5, dto.getEpisode());
			psmt.setString(6, dto.getAge());
			psmt.setString(7, dto.getCast());
			psmt.setString(8, dto.getContent());
			psmt.setString(9, dto.getPrice());
			psmt.setString(10, dto.getYoutube());
			psmt.setString(11, dto.getIdx());
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}

