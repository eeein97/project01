<%@page import="java.util.List"%>
<%@page import="movie.MovieDAO"%>
<%@page import="movie.MovieDTO"%>
<%@ include file="/include/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
MovieDAO dao = new MovieDAO();
List<MovieDTO> dto = dao.movielist();
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
		<td>감독</td>
		<td>가격</td>
	</tr>
	<%
	for (MovieDTO m : dto) {
	%>
	<tr id="hddetail">
		<td id="btn"><button type="button"
				onclick="location.href='/project01/moviepage/movieedit.do?idx=<%=m.getIdx()%>&title=<%=m.getTitle()%>'">수정하기</button></td>
		<td
			onclick="location.href='/project01/moviepage/movieview.do?idx=<%=m.getIdx()%>&&title=<%=m.getTitle()%>'"
			style="cursor: pointer;"><img src="../uploads/<%=m.getSfile()%>" id="hdmovieimg"></td>
		<td
			onclick="location.href='/project01/moviepage/movieview.do?idx=<%=m.getIdx()%>&&title=<%=m.getTitle()%>'"
			style="cursor: pointer;"><%=m.getTitle()%></a></td>
		<td
			onclick="location.href='/project01/moviepage/movieview.do?idx=<%=m.getIdx()%>&&title=<%=m.getTitle()%>'"
			style="cursor: pointer;"><%=m.getDirector()%></td>
		<td
			onclick="location.href='/project01/moviepage/movieview.do?idx=<%=m.getIdx()%>&&title=<%=m.getTitle()%>'"
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
		<td>개봉연도</td>
		<td>포스터</td>
		<td>제목</td>
		<td>감독</td>
		<td>가격</td>
	</tr>
	<%
	for (MovieDTO m : dto) {
	%>
	<tr
		onclick="location.href='/project01/moviepage/movieview.do?idx=<%=m.getIdx()%>&&title=<%=m.getTitle()%>'"
		style="cursor: pointer;">
		<td><%=m.getYear()%></td>
		<td><img src="../uploads/<%=m.getSfile()%>" id="hdmovieimg"></td>
		<td><%=m.getTitle()%></a></td>
		<td><%=m.getDirector()%></td>
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