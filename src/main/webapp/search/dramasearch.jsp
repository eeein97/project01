<%@page import="drama.DramaDTO"%>
<%@page import="java.util.List"%>
<%@page import="drama.DramaDAO"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<%
	Map<String, Object> dramaparam = new HashMap<>();
	String searchField1 = request.getParameter("searchField1");
	String searchWord1 = request.getParameter("searchWord1");
	if(searchWord1 != null) {
		dramaparam.put("searchField1", searchField1);
		dramaparam.put("searchWord1", searchWord1);
	}
	DramaDAO dao = new DramaDAO();
	List<DramaDTO> dlist = dao.getDramaSearch(dramaparam);
	dao.close();
%>
<div id="drama_se1">
	<h2>드라마 검색</h2>
</div>
<div id="dramasearchsection">
	<form method="get">
		<select name="searchField1" id="drama_se2">
			<!-- value값은 테이블의 열이름과 동일하게  -->
			<option value="title">제목</option>
			<option value="cast">배우</option>
		</select>
	<input type="text" name="searchWord1" id="dr_input1" />
	<input type="submit" value="검색" id="dr_input2"/>
	</form>
</div>
<div id="drama_se3" class="sectiondiv2">
	<h2>검색 결과</h2>
	<table border="1" id="drama_se4">
	<tr>
		<td>방영연도</td>
		<td>포스터</td>
		<td>제목</td>
		<td>에피소드</td>
		<td>출연</td>
		<td>가격</td>
	</tr>
	<%
		if(dlist.isEmpty()) {
	%>
		<tr>
			<td colspan="6">검색 결과가 없습니다.</td>
		</tr>
	<%
		} else {
			for(DramaDTO dto: dlist) {
	%>
		
				<tr onclick="location.href='/project01/dramapage/dramaview.do?idx=<%=dto.getIdx()%>&&title=<%= dto.getTitle()%>&&episode=<%= dto.getEpisode()%>'" style="cursor:pointer;">
					<td><%= dto.getYear()%></td>
					<td><img src="/project01/uploads/<%= dto.getSfile()%>" id="drama_se4img"></td>
					<td><%= dto.getTitle()%></td>
					<td>에피소드 <%= dto.getEpisode()%></td>
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