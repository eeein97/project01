<%@page import="drama.DramaDTO"%>
<%@page import="drama.DramaDAO"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<%
	String title = request.getParameter("title");
	DramaDAO dao = new DramaDAO();
	List<DramaDTO> lists = dao.beDramalist(title);
	dao.close();
%>
<h2>드라마 수정 목록</h2>
<form name="dramaEditForm" method="post" action="dramaedit.do"
	enctype="multipart/form-data" onsubmit="return validateForm(this)">
	<input type="hidden" name="title" value="${param.title}" /> <input
		type="hidden" name="idx" value="${param.idx}" />
<!--  수정버튼 (관리자만 보이도록) -->
<%
if (session.getAttribute("UserName") != null && session.getAttribute("UserName").equals("관리자")) {
%>
<table id="hdmovietable">
	<tr>
		<td>방영년도</td>
		<td>이미지</td>
		<td>제목</td>
		<td>에피소드</td>
		<td>수정</td>
	</tr>
	<%
	for (DramaDTO m : lists) {
	%>
	<tr>
		<td
			onclick="location.href='/project01/dramapage/dramaview.do?idx=<%=m.getIdx()%>&&title=<%=m.getTitle()%>'"
			style="cursor: pointer;"><%=m.getYear()%></td>
		<td
			onclick="location.href='/project01/dramapage/dramaview.do?idx=<%=m.getIdx()%>&&title=<%=m.getTitle()%>'"
			style="cursor: pointer;"><img src="../uploads/<%=m.getSfile()%>"
			id="hdmovieimg"></td>
		<td
			onclick="location.href='/project01/dramapage/dramaview.do?idx=<%=m.getIdx()%>&&title=<%=m.getTitle()%>'"
			style="cursor: pointer;"><%=m.getTitle()%></td>
		<td
			onclick="location.href='/project01/dramapage/dramaview.do?idx=<%=m.getIdx()%>&&title=<%=m.getTitle()%>'"
			style="cursor: pointer;"><%=m.getEpisode()%></td>

		<td id="btn"><button type="button"
				onclick="location.href='/project01/dramapage/dramaedit.do?idx=<%=m.getIdx()%>&title=<%=m.getTitle()%>'">수정하기</button></td>
	</tr>
	<%
	}
	%>
</table>
<!-- 관리자가 아니거나 로그인이 아닌경우 -->
<%
} else {
%>
<h2>관리자 모드로 로그인하세요.</h2>
<%
}
%>
</form>
<%@ include file="/include/footer.jsp"%>