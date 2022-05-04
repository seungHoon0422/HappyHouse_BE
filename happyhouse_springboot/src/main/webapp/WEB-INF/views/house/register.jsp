<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
    
<script type="text/javascript">



$(document).ready(function () {
    $("#dealRegisterBtn").click(function () {
//         if (!$("#dealAmount").val()) {
//         	alert("");
//             return;
//         } else if (!$("#dealYear").val()) {
//         	alert("");
//             return;
//         } else if (!$("#dealMonth").val()) {
//         	alert("");
//             return;
//         } else if (!$("#dealDay").val()) {
//         	alert("");
//             return;
//         } else if (!$("#aptName").val()) {
//         	alert("");
//             return;
//         } else if (!$("#buildYear").val()) {
//         	alert("");
//             return;
//         } else if (!$("#sidoName").val()) {
//         	alert("");
//             return;
//         } else if (!$("#gugunName").val()) {
//         	alert("");
//             return;
//         } else if (!$("#dongName").val()) {
//         	alert("");
//             return;
//         } else if (!$("#jibun").val()) {
//         	alert("");
//             return;
//         } else if (!$("#area").val()) {
//         	alert("");
//             return;
//         } else if (!$("#floor").val()) {
//         	alert("");
//             return;
//         } else {
			alert('매매정보 등록 완료')
            $("#dealform").attr("action", "${root}/house/register").submit();
//         }
    });
	
});
    
</script>

<div class="container text-center mt-3">
	<div class="col-lg-10 mx-auto ">
		<h4><strong>아파트 매매 정보 입력</strong></h4>
		<div class="col-8 mx-auto">
		<form id="dealform" class="text-left mb-3" method="post" action="">
			<div class="form-group">
				<label for="apartname">아파트 이름</label>
				<input type="text" class="form-control" name="apartname" id="apartname" placeholder="아파트 이름"/>
			</div>
			<div class="form-group">
				<label for="dongname">행정동</label>
				<input type="text" class="form-control" name="dongname" id="dongname" placeholder="행정동"/>
			</div>
			<div class="form-group">
				<label for="jibun">지번</label>
				<input type="text" class="form-control" name="jibun" id="jibun" placeholder="지번"/>
			</div>
			<div class="form-group">
				<label for="dealamount">거래 금액</label>
				<input type="text" class="form-control" name="dealamount" id="dealamount" placeholder="거래금액(만원)"/>
			</div>
			<div class="form-group">
				<label for="dealyear">거래 년도</label>
				<input type="text" class="form-control" name="dealyear" id="dealyear" placeholder="거래년도 (숫자만 입력)"/>
			</div>
			<div class="form-group">
				<label for="dealmonth">거래 월</label>
				<input type="text" class="form-control" name="dealmonth" id="dealmonth" placeholder="월 (숫자만 입력)"/>
			</div>
			<div class="form-group">
				<label for="dealday">거래 일</label>
				<input type="text" class="form-control" name="dealday" id="dealday" placeholder="일 (숫자만 입력)"/>
			</div>
			<div class="form-group">
				<label for="area">면적</label>
				<input type="text" class="form-control" name="area" id="area" placeholder="면적(m2)"/>
			</div>
			<div class="form-group">
				<label for="floor">층</label>
				<input type="text" class="form-control" name="floor" id="floor" placeholder="층 (숫자만 입력)"/>
			</div>
			<div class="form-group col-2 mx-auto">
				<input type="button" class="btn btn-primary mx-auto" id="dealRegisterBtn" value="등록"/>
			</div>
		
		</form>
		</div>
	</div>
</div>



<%@ include file="/WEB-INF/views/include/footer.jsp" %>