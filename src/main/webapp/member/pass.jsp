<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<div id="pass_ti">
	<h2>비밀번호 확인</h2>
</div>
<div id="passformsection" class="sectiondiv">
	<form action="./pass.do" method="post" id="pass_form">
		<input type="hidden" name="idx" value=${ param.idx } /> <input
			type="hidden" name="mode" value=${ param.mode } />
		<p>
			비밀번호 : <input type="password" name="pass" />
		</p>
		<p id="btn">
			<button type="submit">확인</button>
			<button type="button" onclick="history.back();">취소</button>
		</p>
	</form>
</div>
<%@ include file="/include/footer.jsp"%>