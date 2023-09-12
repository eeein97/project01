<%@page import="movie.MovieDTO"%>
<%@page import="java.util.List"%>
<%@page import="movie.MovieDAO"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<%
	Map<String, Object> param = new HashMap<>();
	String searchField = request.getParameter("searchField");
	String searchWord = request.getParameter("searchWord");
	if(searchWord != null) {
		param.put("searchField", searchField);
		param.put("searchWord", searchWord);
	}
	MovieDAO dao = new MovieDAO();
	List<MovieDTO> mlist = dao.getMovieSearch(param);
	dao.close();

%>
<div id="movie_se1">
	<h2>영화 검색</h2>
</div>
<div id="moviesearchsection">
	<form method="get">
		<select name="searchField" id="movie_se2">
			<!-- value값은 테이블의 열이름과 동일하게  -->
			<option value="title">제목</option>
			<option value="cast">배우</option>
		</select>
	<input type="text" name="searchWord" id="mo_input1"/>
	<input type="submit" value="검색" id="mo_input2"/>
	</form>
</div>
<div id="movie_se3" class="sectiondiv2">
	<h2>검색 결과</h2>
	<table border="1" id="movie_se4">
	<tr>
		<td>개봉년도</td>
		<td>포스터</td>
		<td>제목</td>
		<td>출연</td>
		<td>가격</td>
	</tr>
	<%
		if(mlist.isEmpty()) {
	%>
		<tr>
			<td colspan="5">검색 결과가 없습니다.</td>
		</tr>
	<%
		} else {
			for(MovieDTO dto: mlist) {
	%>
		
				<tr onclick="location.href='/project01/moviepage/movieview.do?idx=<%= dto.getIdx()%>&&title=<%= dto.getTitle()%>'" style="cursor:pointer;">
					<td><%= dto.getYear()%></td>
					<td><img src="/project01/uploads/<%= dto.getSfile()%>" id="movie_se4img"></td>
					<td><%= dto.getTitle()%></td>
					<td><%= dto.getCast()%></td>
					<td><%= dto.getPrice()%></td>
				</tr>
	<%
			}
		}
	%>
	</table>
</div>
<%@ include file="/include/footer.jsp"%>