<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<h2 id="ph_h2">구매 페이지</h2>
<div id="purchase_box">
	<div>
		<img src="/project01/uploads/${ dto.sfile }">
	</div>
	<div id="ph_view">
		<ul>
			<li>제목</li>
			<li>${ dto.title } ${ dto.episode }회</li>
		</ul>
		<ul>
			<li>가격</li>
			<li>${ dto.price }</li>
		</ul>
		<h3>상품정보</h3>
		<ul>
			<li><span>구매 이용기간</span> 제한 없음 | <span>이용기기</span> PC, 모바일, TV 총 5대</li>
			<li><span>제공화질,음향</span> 최대 4K 해상도 및 HDR, Dolby Vision, Dolby Asmos 지원 | <span>DRM</span> (저작권 보호장치 적용)</li>
			<li><span>자막</span> 노출 설정가능, 영자막 지원 | <span>이용</span> 해외에서 스트리밍, 다운로드 불가</li>
			<li><span>이용안내</span> 결제 후 7일 이내 이용을 시작하지 않을경우, 자동으로 이용기간이 시작됩니다.</li>
			<li><span>소장</span> 상품은 구매한 계정에서 이용 계약이 유지되는 동안 이용 가능합니다.</li>
			<li>일부 작품은 다운로드 기간 제한이 있으므로 이용기간을 확인해 주세요. <br/>다운로드는 모바일/태블릿 앱에서 가능하며, PC 및 TV에서는 스트리밍 방식으로 감상하실 수 있습니다.</li>
			<li><span>환불안내</span> 사용하지 않은 구매,대여 상품은 7일 이내 구매목록에서 확인하실 수 있습니다.</li>
		</ul>
	</div>
</div>
<div id="ph_sale">
	<p>구매하시겠습니까?</p>
	<form id="purchase_from" action="dramapurchase.do" method="post">
		<input type="hidden" name="title" value="${ dto.title }" /> 
		<input type="hidden" name="idx" value="${ dto.idx }" />
		<input type="hidden" name="episode" value="${ dto.episode }" />
		<button class="purchase_from_btn" type="submit">구매</button>
		<button class="purchase_from_btn" type="button" onclick="location.href = document.referrer;">취소</button>
	</form>
</div>

<%@ include file="/include/footer.jsp"%>