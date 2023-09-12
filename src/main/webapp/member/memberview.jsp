<%@page import="java.util.List"%>
<%@page import="pmember.PurchaseDTO"%>
<%@page import="pmember.PurchaseDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<div id="me_view1">
	<h2>My Info</h2>
</div>
<div id="memberviewsection" class="sectiondiv">
	<table id="me_view2">
		<tr>
			<td>회원번호</td>
			<td>${ dto.idx }</td>
		</tr>
		<tr>
			<td>아이디</td>
			<td>${ dto.id }</td>
		</tr>
		<tr>
			<td>이름</td>
			<td>${ dto.name }</td>
		</tr>
		<tr>
			<td>생년월일</td>
			<td>${ dto.birthday }</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td>${ dto.email }</td>
		</tr>
		<tr>
			<td>우편번호</td>
			<td>${ dto.postnumber }</td>
		</tr>
		<tr>
			<td>주소</td>
			<td>${ dto.address }</td>
		</tr>
		<tr>
			<td>상세주소</td>
			<td>${ dto.address1 }</td>
		</tr>
		<tr>
			<td>예치금</td>
			<td>${ dto.money }</td>
		</tr>
		<tr>
			<td>구매목록</td>
			<td><button type="button" onclick="location.href='purchaselist.do?id=${dto.id}'" id="mem_view_btn" style="cursor: pointer;">구매목록을 보려면 "클릭"하세요</button></td>
		</tr>
		<tr>
			<td colspan="2" id="btn">
				<button
					onclick="location.href='pass.do?mode=editmember&idx=${ dto.idx }'">회원정보수정</button>
				<button
					onclick="location.href='pass.do?mode=memberdelete&idx=${ dto.idx }'">회원탈퇴</button>
			</td>
		</tr>
	</table>
</div>

<%@ include file="/include/footer.jsp"%>