<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<div id="log1">
	<h2>LOGIN</h2>
</div>
<div id="loginsection" class="sectiondiv">
	<form name="loginForm" method="post" action="login.do">
		<table id="log2">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="id" /></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pass" /></td>
			</tr>
			<tr>
				<td colspan="2" id="btn">
					<button type="submit">로그인</button>
					<button type="submit">
						<a href="../index.jsp">취소</a>
					</button>
				</td>
			</tr>
		</table>
	</form>
</div>
<%@ include file="/include/footer.jsp"%>