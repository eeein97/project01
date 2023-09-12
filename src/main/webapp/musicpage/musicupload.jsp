<%@page import="music.MusicDTO"%>
<%@page import="music.MusicDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<script>
    function parseLine() {
      $('#lyrics').val($('#lyrics').val().replace(/\r?\n/g, '<br />'));
    }
</script>
<div id="music_up1">
	<h2>음악 등록</h2>
</div>
<form name="musicForm" method="post" enctype="multipart/form-data"
action="musicForm.do" onsubmit="return parseLine()">
<div id="musicuploadsection" class="sectiondiv">
	<table id="music_up2">
		<tr>
			<td>앨범</td>
			<td><input type="text" name="album"></td>
		</tr>
		<tr>
			<td>제목</td>
			<td><input type="text" name="title"></td>
		</tr>
		<tr>
			<td>아티스트</td>
			<td><input type="text" name="artist"></td>
		</tr>
		<tr>
			<td>발매연도</td>
			<td><input type="text" name="year"></td>
		</tr>
		<tr>
			<td>재생시간</td>
			<td><input type="text" name="playtime"></td>
		</tr>
		<tr>
			<td>가사</td>
			<td><textarea type="text" name="lyrics" ></textarea></td>
		</tr>
		<tr>
			<td>가격</td>
			<td><input type="text" name="price"></td>
		</tr>
		<tr>
			<td>유튜브 뮤직비디오 url</td>
			<td><input type="text" name="youtube"></td>
		</tr>
		<tr>
			<td>첨부파일</td>
			<td><input type="file" name="ofile" /></td>
		</tr>
		<tr id="btn_row">
			<td colspan="2" id="btn">
				<button type="submit">작성완료</button>
				<button type="reset">취소</button>
				<button type="button" onclick="location.href='/project01/index.jsp'">메인화면</button>
			</td>
		</tr>
	</table>
</div>
</form>

<%@ include file="/include/footer.jsp"%>