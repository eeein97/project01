<%@page import="java.util.List"%>
<%@page import="pmember.PmemberDAO"%>
<%@page import="pmember.PmemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<%
PmemberDAO dao = new PmemberDAO();
List<PmemberDTO> mlist = dao.getMemberList();
dao.close();
%>
<div id="me_list1">
	<h2>Member List</h2>
</div>
<div id="memberlistsection" class="sectiondiv2">
	<table id="me_view8">
		<tr id="first_row">
			<td>번호</td>
			<td>아이디</td>
			<td>이름</td>
			<td>이메일</td>
			<td>생년월일</td>
			<td>주소</td>
			<td>예치금</td>
			<td>구매목록</td>

			<%
			//리스트 항목이 비어있는지 확인
			if (mlist.isEmpty()) {
			%>
		
		<tr>
			<td colspan="5">회원이 없습니다.</td>
		</tr>
		<%
		} else {
		for (PmemberDTO dto : mlist) {
		%>
		<tr>
			<td><%=dto.getIdx()%></td>
			<td><%=dto.getId()%></td>
			<td><%=dto.getName()%></td>
			<td><%=dto.getEmail()%></td>
			<td><%=dto.getBirthday()%></td>
			<td><%=dto.getPostnumber()%> <%=dto.getAddress()%> <%=dto.getAddress1()%></td>
			<td><%=dto.getMoney()%></td>
			<td><button type="button" onclick="location.href='purchaselist.do?id=${dto.id}'" id="mem_view_btn" style="cursor: pointer;">구매목록확인</button></td>
		</tr>
		<%
			}
		}
		%>
	</table>
</div>
<%@ include file="/include/footer.jsp"%>