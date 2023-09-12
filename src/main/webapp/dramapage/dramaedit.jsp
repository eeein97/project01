<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<div id="drama_up1">
	<h2>드라마 수정</h2>
</div>
<div class="sectiondiv">
<form name="dramaEditForm" method="post" action="dramaedit.do"
	enctype="multipart/form-data" onsubmit="return validateForm(this)">
	<input type="hidden" name="title" value="${param.title}" /> <input
		type="hidden" name="idx" value="${param.idx}" />
	<table id="drama_up2">
		<tr>
			<td>제목</td>
			<td><input type="text" name="title" value="${dto.title}"></td>
		</tr>
		<tr>
			<td>방영연도</td>
			<td><input type="text" name="year" value="${dto.year}"></td>
		</tr>
		<tr>
			<td>에피소드</td>
			<td><input type="text" name="episode" value="${dto.episode}"></td>
		</tr>
		<tr>
			<td>시청연령</td>
			<td><input type="text" name="age" value="${dto.age}"></td>
		</tr>
		<tr>
			<td>출연진</td>
			<td><input type="text" name="cast" value="${dto.cast}"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea name="content">${dto.content}</textarea></td>
		</tr>
		<tr>
			<td>가격</td>
			<td><input type="text" name="price" value="${dto.price}"></td>
		</tr>
		<tr>
			<td>유튜브 예고편 url</td>
			<td><input type="text" name="youtube" value="${dto.youtube}"></td>
		</tr>
		<tr>
			<td>이미지</td>
			<td><input type="file" name="ofile"></td>
		</tr>
		<tr id="btn_row">
			<td colspan="2" id="btn">
				<button type="submit">작성완료</button>
				<button type="reset" onclick="location.href = document.referrer;">취소</button>
			</td>
		</tr>
	</table>
</form>
</div>
<%@ include file="/include/footer.jsp"%>