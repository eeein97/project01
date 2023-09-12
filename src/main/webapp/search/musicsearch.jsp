<%@page import="music.MusicDTO"%>
<%@page import="java.util.List"%>
<%@page import="music.MusicDAO"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<%
	Map<String, Object> musicparam = new HashMap<>();
	String searchField2 = request.getParameter("searchField2");
	String searchWord2 = request.getParameter("searchWord2");
	if(searchWord2 != null) {
		musicparam.put("searchField2", searchField2);
		musicparam.put("searchWord2", searchWord2);
	}
	MusicDAO dao = new MusicDAO();
	List<MusicDTO> mulist = dao.getMusicSearch(musicparam);
	dao.close();
%>
<div id="music_se1">
	<h2>음악 검색</h2>
</div>
<div id="musicsearchsection">
	<form method="get">
		<select name="searchField2" id="music_se2">
			<!-- value값은 테이블의 열이름과 동일하게  -->
			<option value="title">제목</option>
			<option value="lyrics">가사</option>
			<option value="artist">가수</option>
		</select>
	<input type="text" name="searchWord2" id="mu_input1"/>
	<input type="submit" value="검색" id="mu_input2"/>
	</form>
</div>
<div id="music_se3" class="sectiondiv2">
	<h2>검색 결과</h2>
	<table border="1" id="music_se4">
	<tr>
		<td>발매연도</td>
		<td>엘범</td>
		<td>제목</td>
		<td>아티스트</td>
		<td>가격</td>
	</tr>
	<%
		if(mulist.isEmpty()) {
	%>
		<tr>
			<td colspan="5">검색 결과가 없습니다.</td>
		</tr>
	<%
		} else {
			for(MusicDTO dto: mulist) {
	%>
		
				<tr onclick="location.href='/project01/musicpage/musicview.do?idx=<%= dto.getIdx()%>&&title=<%= dto.getTitle()%>'" style="cursor:pointer;">
					<td><%= dto.getYear()%></td>
					<td><img src="/project01/uploads/<%= dto.getSfile()%>"></td>
					<td><%= dto.getTitle()%></td>
					<td><%= dto.getArtist()%></td>
					<td><%= dto.getPrice()%></td>
				</tr>
	<%
			}
		}
	%>
	</table>
</div>
<%@ include file="/include/footer.jsp"%>
