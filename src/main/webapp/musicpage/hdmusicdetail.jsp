<%@page import="music.MusicDTO"%>
<%@page import="music.MusicDAO"%>
<%@page import="java.util.List"%>

<%@ include file="/include/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
MusicDAO dao = new MusicDAO();
List<MusicDTO> dto = dao.musiclist();
dao.close();
%>
<h2>HOT&NEW</h2>
<!--  수정버튼 (관리자만 보이도록) -->
<%
if (session.getAttribute("UserName") != null && session.getAttribute("UserName").equals("관리자")) {
%>
<table id="hdmovietable">
	<tr>
		<td>수정</td>
		<td>포스터</td>
		<td>제목</td>
		<td>엘범</td>
		<td>아티스트</td>
		<td>가격</td>
	</tr>

	<%
	for (MusicDTO m : dto) {
	%>
	<tr id="hddetail">
		<td id="btn"><button type="button"
				onclick="location.href='/project01/musicpage/musicedit.do?idx=<%=m.getIdx()%>&title=<%=m.getTitle()%>'">수정하기</button></td>
		<td
			onclick="location.href='/project01/musicpage/musicview.do?idx=<%=m.getIdx()%>&&title=<%=m.getTitle()%>'"
			style="cursor: pointer;"><img src="../uploads/<%=m.getSfile()%>" id="hdmovieimg"></td>
		<td
			onclick="location.href='/project01/musicpage/musicview.do?idx=<%=m.getIdx()%>&&title=<%=m.getTitle()%>'"
			style="cursor: pointer;"><%=m.getTitle()%></td>
		<td
			onclick="location.href='/project01/musicpage/musicview.do?idx=<%=m.getIdx()%>&&title=<%=m.getTitle()%>'"
			style="cursor: pointer;"><%=m.getAlbum()%></td>
		<td
			onclick="location.href='/project01/musicpage/musicview.do?idx=<%=m.getIdx()%>&&title=<%=m.getTitle()%>'"
			style="cursor: pointer;"><%=m.getArtist()%></td>
		<td
			onclick="location.href='/project01/musicpage/musicview.do?idx=<%=m.getIdx()%>&&title=<%=m.getTitle()%>'"
			style="cursor: pointer;"><%=m.getPrice()%></td>
	</tr>
	<%
	}
	%>
</table>
<!-- 관리자가 아니거나 로그인이 아닌경우 -->
<%
} else {
%>
<table id="hdmovietable">
	<tr>
		<td>발매일</td>
		<td>포스터</td>
		<td>제목</td>
		<td>엘범</td>
		<td>아티스트</td>
		<td>가격</td>
	</tr>
	<%
	for (MusicDTO m : dto) {
	%>
	<tr
		onclick="location.href='/project01/musicpage/musicview.do?idx=<%=m.getIdx()%>&&title=<%=m.getTitle()%>'"
		style="cursor: pointer;">
		<td><%=m.getYear()%></td>
		<td><img src="../uploads/<%=m.getSfile()%>" id="hdmovieimg"></td>
		<td><%=m.getTitle()%></td>
		<td><%=m.getAlbum()%></td>
		<td><%=m.getArtist()%></td>
		<td><%=m.getPrice()%></td>
	</tr>
	<%
	}
	%>
</table>
<%
}
%>
<%@ include file="/include/footer.jsp"%>