<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>
<div id="movie_up1">
	<h2>영화 등록</h2>
</div>
	<form name="movieForm" method="post" action="movieForm.do"
		enctype="multipart/form-data" onsubmit="return validateForm(this)">
	<div id="movieuploadsection" class="sectiondiv">
		<table id="movie_up2">
			<tr>
				<td>제목</td>
				<td><input type="text" name="title"></td>
			</tr>
			<tr>
				<td>평점</td>
				<td><input type="text" name="rate"></td>
			</tr>
			<tr>
				<td>개봉연도</td>
				<td><input type="text" name="year"></td>
			</tr>
			<tr>
				<td>상영시간</td>
				<td><input type="text" name="runtime"></td>
			</tr>
			<tr>
				<td>시청연령</td>
				<td><input type="text" name="age"></td>
			</tr>
			<tr>
				<td>감독</td>
				<td><input type="text" name="director"></td>
			</tr>
			<tr>
				<td>출연진</td>
				<td><input type="text" name="cast"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea type="text" name="content"></textarea></td>
			</tr>
			<tr>
				<td>가격</td>
				<td><input type="text" name="price"></td>
			</tr>
			<tr>
				<td>유튜브 예고편 url</td>
				<td><input type="text" name="youtube"></td>
			</tr>
			<tr>
				<td>이미지</td>
				<td><input type="file" name="ofile"></td>
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
	
<%@ include file="/include/footer.jsp" %>