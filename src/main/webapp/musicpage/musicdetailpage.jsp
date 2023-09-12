<%@page import="music.MusicPurchaseDAO"%>
<%@page import="music.MusicReviewDTO"%>
<%@page import="java.util.List"%>
<%@page import="music.MusicReviewDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>
<script type="text/javascript">
	function nonLogin() {
		alert("구매하시려면 로그인하세요")
		}
</script>
<div><iframe width='1360' height='600' src='${ dto.youtube }?
&autoplay=1&mute=1&cc_lang_pref=kr&cc_load_policy=1' frameborder='0' allowfullscreen>
</iframe></div>
<div id="title_h2">
	<h2>${ dto.title }</h2>
</div>
<div id="detail_div">
	<ul id="detail_ul_mu">
		<li>앨범 : ${ dto.album }</li>
		<li>${ dto.year }</li>
		<li>${ dto.playtime }</li>
	</ul>
	<!--  구매버튼 로그인시 구매함 구매안함 로그인안함 구분 -->
	<% 
		if(session.getAttribute("UserId") != null) {
		 	MusicPurchaseDAO dao = new MusicPurchaseDAO();
			String id = (String) session.getAttribute("UserId");
			String title = request.getParameter("title");
			
			int result = dao.musicPurchasecheck(id, title);
			dao.close();
		
			if(result==1) {			
	%>
	<button class="buy_btn">
	<span></span>
	<span></span>
	<span></span>
	<span></span>
	다운로드</button>
	<%
			} else if(result==0) {
	%>
	<form action="">
		<button type="button" onclick="location.href='musicpurchase.do?idx=${ dto.idx }&&title=${ dto.title }'"
		class="buy_btn">
		<span></span>
		<span></span>
		<span></span>
		<span></span>
		구매 ${ dto.price }
		</button>
	</form>
	<%
			}
		} else {
	%>
		<button type="button" onclick="nonLogin()" class="buy_btn">
		<span></span>
		<span></span>
		<span></span>
		<span></span>
		구매 ${ dto.price }
		</button>
	<%
		}	
	%>
	<!-- 구매버튼부분 끝 -->
</div>
<div id="music_info">
	<p>아티스트 <span>${ dto.artist }</span></p>
	<p>가사 <span>${ dto.lyrics }</span></p>
</div>
<!-- 리뷰영역 -->
<% 
	if(session.getAttribute("UserName") != null) {
%>
<form action="musicreviewwrite.do">
	<input type="hidden" name="title" value="${ param.title }" />
	<input type="hidden" name="idx" value="${ param.idx }" />
	<div id="reviewbox1_h2">
	<h2>감상평</h2>
	</div>
	<table id="reviewbox1">
		<tr>
			<td>${ UserId }</td>
		</tr>
		<tr>
			<td><textarea rows="2" cols="100" name="content"></textarea></td>
		</tr>
		<tr>
			<td><input type="submit" value="등록" id="review_btn1"></td>
		</tr>
	</table>
</form>
<div id="reviewbox2">
<%
	MusicReviewDAO dao = new MusicReviewDAO();
	String title = request.getParameter("title");	
	List<MusicReviewDTO> dto = dao.getMusicReview(title);
	dao.close();
%>
	<table>
	<%
		for (MusicReviewDTO mur : dto) {
	%>
		<tr>
			<td><%= mur.getId() %></td>
			<td><%= mur.getContent() %></td>
			<td><%= mur.getPostdate() %></td>
			<%
				if(session.getAttribute("UserId").equals(mur.getId())) {
			%>
			<form action="musicreviewdelete.do">
				<input type="hidden" name="idx" value="<%= mur.getIdx()%>"/>
				<input type="hidden" name="idx" value="<%= mur.getTitle()%>"/>
				<td><button type="submit" id="review_btn2">X</button></td>
			</form>
			<%
            	}
            %>			
		</tr>	
	<%
	}
	%>
	</table>
</div>
<%
    } else {
%>
<div id="reviewbox3_h3">
	<h3>감상평을 남기시려면 <a href="/project01/member/loginForm.jsp">로그인</a>을 하세요.</h3>
</div>
<div id="reviewbox3">
<%
	MusicReviewDAO dao = new MusicReviewDAO();
	String title = request.getParameter("title");	
	List<MusicReviewDTO> dto = dao.getMusicReview(title);
	dao.close();
%>
	<table>
	<%
		for (MusicReviewDTO mur : dto) {
	%>
		<tr>
			<td><%= mur.getId() %></td>
			<td><%= mur.getContent() %></td>
			<td><%= mur.getPostdate() %></td>
		</tr>	
	<%
	}
	%>
	</table>
</div>
<%
    }
%>
<!-- 리뷰영역 끝 -->
<%@ include file="/include/footer.jsp" %>