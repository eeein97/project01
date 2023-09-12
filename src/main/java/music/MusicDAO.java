package music;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.JDBCConnect;
import movie.MovieDTO;

public class MusicDAO extends JDBCConnect {

	// 게시물 등록
	public int insertMusic(MusicDTO dto) {
		int result = 0;
		String qurey = "insert into music(idx, album, title, ofile, sfile, artist, year, playtime, lyrics, price, youtube)"
				+ " values(seq_music_num.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			psmt = con.prepareStatement(qurey);
			psmt.setString(1, dto.getAlbum());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getOfile());
			psmt.setString(4, dto.getSfile());
			psmt.setString(5, dto.getArtist());
			psmt.setString(6, dto.getYear());
			psmt.setString(7, dto.getPlaytime());
			psmt.setString(8, dto.getLyrics());
			psmt.setString(9, dto.getPrice());
			psmt.setString(10, dto.getYoutube());
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	// 게시물 삭제하기
	public int deleteMusic(String idx) {
		int result = 0;
		String query = "delete from music where idx=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 게시글 업데이트 하기(게시글 수정하기)
	public int updateMusic(MusicDTO dto) {
		int result = 0;
		String query = "update music set title=?, album=?, ofile=?, sfile=?, year=?, playtime=?, artist=?, lyrics=?, price=? where idx=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getAlbum());
			psmt.setString(3, dto.getOfile());
			psmt.setString(4, dto.getSfile());
			psmt.setString(5, dto.getYear());
			psmt.setString(6, dto.getPlaytime());
			psmt.setString(7, dto.getArtist());
			psmt.setString(8, dto.getLyrics());
			psmt.setString(9, dto.getPrice());
			psmt.setString(10, dto.getIdx());

			// 쿼리문 실행
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	//음악목록조회
	public List<MusicDTO> musiclist() {
		List<MusicDTO> mlist = new ArrayList<>();
		String query = "select * from music";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				MusicDTO dto = new MusicDTO();
				dto.setIdx(rs.getString(1));
				dto.setAlbum(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setOfile(rs.getString(4));
				dto.setSfile(rs.getString(5));
				dto.setArtist(rs.getString(6));
				dto.setYear(rs.getString(7));
				dto.setPlaytime(rs.getString(8));
				dto.setLyrics(rs.getString(9));
				dto.setPrice(rs.getString(10));
				dto.setYoutube(rs.getString(11));
				mlist.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mlist;
	}
	//상세조회
	public MusicDTO selectMusic(String idx) {
		MusicDTO dto = new MusicDTO();
		String query = "select * from music where idx=?";
			try {
				psmt = con.prepareStatement(query);
				psmt.setString(1, idx);
				rs = psmt.executeQuery();
				if(rs.next()) {
					dto.setIdx(rs.getString(1));
					dto.setAlbum(rs.getString(2));
					dto.setTitle(rs.getString(3));
					dto.setOfile(rs.getString(4));
					dto.setSfile(rs.getString(5));
					dto.setArtist(rs.getString(6));
					dto.setYear(rs.getString(7));
					dto.setPlaytime(rs.getString(8));
					dto.setLyrics(rs.getString(9));
					dto.setPrice(rs.getString(10));
					dto.setYoutube(rs.getString(11));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return dto;
	}
	//검색 목록 반환
	public List<MusicDTO> getMusicSearch(Map<String, Object> musicmap) {
		List<MusicDTO> musicsearchlist = new ArrayList<MusicDTO>();
		String query = "select * from music where " + musicmap.get("searchField2") + " like '%" 
				 + musicmap.get("searchWord2") + "%'";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				MusicDTO dto = new MusicDTO();
				dto.setIdx(rs.getString(1));
				dto.setAlbum(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setOfile(rs.getString(4));
				dto.setSfile(rs.getString(5));
				dto.setArtist(rs.getString(6));
				dto.setYear(rs.getString(7));
				dto.setPlaytime(rs.getString(8));
				dto.setLyrics(rs.getString(9));
				dto.setPrice(rs.getString(10));
				dto.setYoutube(rs.getString(11));
				musicsearchlist.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return musicsearchlist;
	}
	//게시글 업데이트 하기 (게시글 수정하기)
		public int updateMusicPost(MusicDTO dto) {
			int result = 0;
			String query = "update music set album=?, title=?, ofile=?, sfile=?, artist=?, year=?, "
					+ "playtime=?, lyrics=?, price=?, youtube=? where idx=?";
			try {
				psmt = con.prepareStatement(query);
				psmt.setString(1, dto.getAlbum());
				psmt.setString(2, dto.getTitle());
				psmt.setString(3, dto.getOfile());
				psmt.setString(4, dto.getSfile());
				psmt.setString(5, dto.getArtist());
				psmt.setString(6, dto.getYear());
				psmt.setString(7, dto.getPlaytime());
				psmt.setString(8, dto.getLyrics());
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
