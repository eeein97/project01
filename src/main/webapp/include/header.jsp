<%@page import="pmember.PmemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie Night Club by DragonJJ</title>
<style><%@include file="../css/style.css"%></style>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick-theme.min.css">
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
          $(function(){
        	  $('.slider').slick({
        		  slide: 'div',
        		  arrows : true,
        		  autoplay : false,
        		  centerMode: true,
        		  centerPadding: '0px',
        		  slidesToShow: 4  
        		});
        	  $('.musicslider').slick({
        		  slide: 'div',
        		  arrows : true,
        		  autoplay : false,
        		  centerMode: true,
        		  centerPadding: '0px',
        		  slidesToShow: 6  
        		});
          })  
</script>
</head>
<body>
	<header class="inner">
        <div id="head1">
            <div id="logo1"><a href="/project01/index.jsp"><img src="/project01/images/logo2 nobc.png" alt=""></a></div>
            <form>
            <ul id="login">
            <% 
            	if(session.getAttribute("UserName") != null) {
			 %>
                <li><a href="/project01/process/logout_process.jsp">로그아웃</a></li>
                <li><a href="/project01/member/memberview.do?id=${ UserId }">마이페이지</a></li>
            <%
					if(session.getAttribute("UserName").equals("관리자")) {            
            %>
            			<li><a href="/project01/member/memberlist.jsp">회원목록</a></li>
            			<li><a href="/project01/moviepage/movieupload.jsp">영화 관리</a></li>
            			<li><a href="/project01/dramapage/dramaupload.jsp">드라마 관리</a></li>
            			<li><a href="/project01/musicpage/musicupload.jsp">음악 관리</a></li>
            <%
					}
            	} else {
            %>
            	<li><a href="/project01/member/loginForm.jsp">로그인</a></li>
                <li>
                	<a href="/project01/member/joinForm.jsp">회원가입</a>
                </li>
            <%
            	}
            %>
            </ul>
            </form>
        </div>
        <div id="head2">
            <ul id="hmenu">
                <li><a href="/project01/moviepage/hdmoviedetail.jsp">영화</a></li>
                <li><a href="/project01/dramapage/hddramadetail.jsp">드라마</a></li>
                <li><a href="/project01/musicpage/hdmusicdetail.jsp">음악</a></li>
            </ul>
            <!--  검색 -->
			<form method="get">
					<select onchange="if(this.value) location.href=(this.value);" id="searchbox">
						<option value="">검색</option>
						<option value="/project01/search/moviesearch.jsp">영화</option>
						<option value="/project01/search/dramasearch.jsp">드라마</option>
						<option value="/project01/search/musicsearch.jsp">음악</option>
					</select> 
			</form>
		</div>
    </header>
    <section class="inner">
        <div>