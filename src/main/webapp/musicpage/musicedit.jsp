<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<div id="music_up1">
	<h2>음악 수정</h2>
</div>
<div id="musicuploadsection" class="sectiondiv">
<form name="musicEditForm" method="post" action="musicedit.do"
	enctype="multipart/form-data" onsubmit="return validateForm(this)">
	<input type="hidden" name="title" value="${param.title}" /> 
	<input type="hidden" name="idx" value="${param.idx}" />
	<table id="music_up2">
		<tr>
			<td>앨범</td>
			<td><input type="text" name="album" value="${dto.album}"></td>
		</tr>
		<tr>
			<td>제목</td>
			<td><input type="text" name="title" value="${dto.title}"></td>
		</tr>
		<tr>
			<td>아티스트</td>
			<td><input type="text" name="artist" value="${dto.artist}"></td>
		</tr>
		<tr>
			<td>발매연도</td>
			<td><input type="text" name="year" value="${dto.year}"></td>
		</tr>
		<tr>
			<td>재생시간</td>
			<td><input type="text" name="playtime" value="${dto.playtime}"></td>
		</tr>
		<tr>
			<td>가사</td>
			<td><textarea name="lyrics">${dto.lyrics}</textarea></td>
		</tr>
		<tr>
			<td>가격</td>
			<td><input type="text" name="price" value="${dto.price}"></td>
		</tr>
		<tr>
			<td>유튜브 뮤직비디오 url</td>
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