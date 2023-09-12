<%@page import="drama.DramaDTO"%>
<%@page import="drama.DramaDAO"%>
<%@page import="music.MusicDTO"%>
<%@page import="music.MusicDAO"%>
<%@page import="movie.MovieDAO"%>
<%@page import="java.util.List"%>
<%@page import="movie.MovieDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/include/header.jsp"%>
<%
	MovieDAO dao = new MovieDAO();
	List<MovieDTO> dto = dao.movielist();
	dao.close();
%>
<script type="text/javascript">
$(document).ready(function(){
	let title=$('title').val();
	$
})
</script>
<h2>영화</h2>
<div id="movieDivs" class="slider">
	<%
		for (MovieDTO m : dto) {
	%>
	<div>
		<a href="/project01/moviepage/movieview.do?idx=<%=m.getIdx()%>&&title=<%= m.getTitle()%>">
			<img src="/project01/uploads/<%=m.getSfile()%>">
			<p><%=m.getTitle()%></p>
		</a>
	</div>
	<%
		}
	%>
</div>
<div>
	<%
		DramaDAO dao1 = new DramaDAO();
		List<DramaDTO> dto1 = dao1.dramalist();
		dao.close();
	%>
	<h2>드라마</h2>
	<div id="dramaDivs" class="slider">
		<%
			for (DramaDTO d : dto1) {
		%>
		<div>
			<a href="/project01/dramapage/dramaview.do?idx=<%=d.getIdx()%>&&title=<%= d.getTitle()%>
			&&episode=<%= d.getEpisode()%>">
				<img src="/project01/uploads/<%=d.getSfile()%>">
				<p><%=d.getTitle()%></p>
			</a>
		</div>
		<%
			}
		%>
	</div>
</div>
<div>
	<%
		MusicDAO dao2 = new MusicDAO();
		List<MusicDTO> dto2 = dao2.musiclist();
		dao2.close();
	%>
	<h2>음악</h2>
	<div id="musicDivs" class="musicslider">
		<%
			for (MusicDTO u : dto2) {
		%>
		<div>
			<a href="/project01/musicpage/musicview.do?idx=<%=u.getIdx()%>&&title=<%= u.getTitle()%>">
				<img src="/project01/uploads/<%=u.getSfile()%>">
				<p><%=u.getTitle()%></p>
			</a>
		</div>
		<%
			}
		%>
	</div>
	<%@ include file="/include/footer.jsp"%>