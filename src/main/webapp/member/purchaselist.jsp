<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<div id="purchaseh2">
	<h2>구매목록</h2>
</div>
<div class="sectiondiv2">
	<table id="purchasetable">
		<tr>
			<td>컨텐츠명</td>
			<td>에피소드</td>
			<td>구매일</td>
		</tr>
		<c:choose>
			<c:when test="${ empty plist }">
			<tr>
				<td colspan="6">구매한 컨텐츠가 없습니다.</td>
			</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${ plist }" var="row" varStatus="loop">
				<tr>
					<td>${ row.title }</td>
					<td>${ row.episode }</td>
					<td>${ row.postdate }</td>
				</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>	
		<tr>
			<td colspan="3" id="btn"><button type="button" onclick="location.href = document.referrer;">확인</button></td>
		</tr>
	</table>
</div>
<div>
	${ map.pagingStr }
</div>

<%@ include file="/include/footer.jsp"%>