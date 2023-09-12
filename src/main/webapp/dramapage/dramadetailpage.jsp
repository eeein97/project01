<%@page import="drama.DramaPurchaseDAO"%>
<%@page import="drama.DramaReviewDTO"%>
<%@page import="drama.DramaReviewDAO"%>
<%@page import="drama.DramaDTO"%>
<%@page import="java.util.List"%>
<%@page import="drama.DramaDAO"%>
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
<p> : 에피소드 ${ dto.episode }</p>
</div>
<div id="detail_div">
	<ul id="detail_ul_mu">
		<li>${ dto.year }</li>
		<li>${ dto.age }</li>
		<li>${ dto.cast }</li>
	</ul>
	<!--  구매버튼 로그인시 구매함 구매안함 로그인안함 구분 -->
	<% 
		if(session.getAttribute("UserId") != null) {
		 	DramaPurchaseDAO dao = new DramaPurchaseDAO();
			String id = (String) session.getAttribute("UserId");
			String title = request.getParameter("title");
			String episode = request.getParameter("episode");
			
			int result = dao.dramaPurchasecheck(id, title, episode);
			dao.close();
		
			if(result>=1) {		
	%>
	<button class="buy_btn">
	<span></span>
	<span></span>
	<span></span>
	<span></span>
	시청하기</button>
	<%
			} else if(result==0) {
	%>
	<form action="">
		<button type="button" onclick="location.href='dramapurchase.do?idx=${ dto.idx }&&title=${ dto.title }&&episode=${ dto.episode }'"
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
<div id="drama_info">
	<p><span>${ dto.content }</span></p>
</div>
<!-- 리뷰영역 -->
<% 
	if(session.getAttribute("UserName") != null) {
%>
<form action="dramareviewwrite.do">
	<input type="hidden" name="title" value="${ param.title }" />
	<input type="hidden" name="idx" value="${ param.idx }" />
	<input type="hidden" name="episode" value="${ param.episode }" />
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
	DramaReviewDAO dao = new DramaReviewDAO();
	String title1 = request.getParameter("title");
	String episode1 = request.getParameter("episode");	
	List<DramaReviewDTO> dto = dao.getDramaReview(title1, episode1);
	dao.close();
%>
	<table>
	<%
		for (DramaReviewDTO dr : dto) {
	%>
		<tr>
			<td><%= dr.getId() %></td>
			<td><%= dr.getContent() %></td>
			<td><%= dr.getPostdate() %></td>
			<%
				if(session.getAttribute("UserId").equals(dr.getId())) {
			%>
			<form action="dramareviewdelete.do">
				<input type="hidden" name="idx" value="<%= dr.getIdx()%>"/>
				<input type="hidden" name="idx" value="<%= dr.getEpisode()%>"/>
				<input type="hidden" name="idx" value="<%= dr.getTitle()%>"/>
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
	DramaReviewDAO dao = new DramaReviewDAO();
	String title1 = request.getParameter("title");
	String episode1 = request.getParameter("episode");	
	List<DramaReviewDTO> dto = dao.getDramaReview(title1, episode1);
	dao.close();
%>
	<table>
	<%
		for (DramaReviewDTO dr : dto) {
	%>
		<tr>
			<td><%= dr.getId() %></td>
			<td><%= dr.getContent() %></td>
			<td><%= dr.getPostdate() %></td>
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
<div id="episodebox1">
	<%
		DramaDAO elist = new DramaDAO();
		String title = request.getParameter("title");
		List<DramaDTO> episodelist = elist.episodelist(title);
		elist.close();
	%>
	<%
		for (DramaDTO e : episodelist) {
	%>
	<div id="episodebox2">
		<a href="/project01/dramapage/dramaview.do?idx=<%=e.getIdx()%>&&title=<%= e.getTitle()%>&&episode=<%= e.getEpisode()%>">
			<div><img src="/project01/uploads/<%= e.getSfile() %>"></div>
			<div><%= e.getTitle() %> 에피소드 <%= e.getEpisode() %></div>
			<div><%= e.getContent() %></div>
		</a>
	</div>
	<%
		}
	%>
</div>
<%@ include file="/include/footer.jsp" %>