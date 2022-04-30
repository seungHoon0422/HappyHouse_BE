<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
    
<!-- 메시지 존재 시, alert -->
<c:if test="${not empty msg}">
<script>
	alert("${msg}");
</script>
</c:if>

<!-- 아파트 매매 검색 -->
	<div class="container text-center mt-3">
        <div class="col-lg-8 mx-auto">
			<form action="${root}/house" method="post">
				<input type="hidden" name="act" value="dealList"/>
				<div class="form-group row">
					<label for="type"><strong>검색 타입</strong></label>
					<select class="form-control" name="type" id="type">
						<option value="ym">거래년도</option>
						<option value="name">아파트명</option>
					</select>
				</div>
				<div class="form-group row">
					<label for="sidoName"><strong>시도명</strong></label>
					<input class="form-control" type="text" id="sidoName" name="sidoName" placeholder="시/도"/>
				</div>
				<div class="form-group row">
					<label for="gugunName"><strong>구군명</strong></label>
					<input class="form-control" type="text" id="gugunName" name="gugunName" placeholder="구/군"/>
				</div>
				<div class="form-group row">
					<label for="dongName"><strong>동명</strong></label>
					<input class="form-control" type="text" id="dongName" name="dongName" placeholder="읍/면/동"/>
				</div>
				<div class="form-group row name">
					<label for="aptName"><strong>아파트명</strong></label>
					<input class="form-control" type="text" id="aptName" name="aptName" placeholder="아파트명"/>
				</div>
				<input type="submit" id="dealListBtn" value="검색"/>
			</form>
		</div>
	</div>
	<div class="container text-center mt-3">
		<a href="${root}/house?act=mvDealRegister" class="btn btn-dark">매매 정보 등록</a>
		<a href="${root}/house?act=mvInfo" class="btn btn-dark">주택 정보 검색</a>
	</div>
	
	<div class="container text-center mt-3">
		<a href="${root}/house?act=mvDealRegister" class="btn btn-dark">매매 정보 등록</a>
		<a href="${root}/house?act=mvInfo" class="btn btn-dark">주택 정보 검색</a>
	</div>
	<!-- type에 따른, input 태그 toggle -->
	<script>
	$(function() {
		$("div.name").toggle();
		$("#type").on("change", function() {
			$("div.ym").toggle();
			$("div.name").toggle();
		});
	});
	</script>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>