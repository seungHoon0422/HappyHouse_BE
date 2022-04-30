<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
    
<script type="text/javascript">
$(document).ready(function () {
    $("#dealRegisterBtn").click(function () {
        if (!$("#dealAmount").val()) {
        	alert("");
            return;
        } else if (!$("#dealYear").val()) {
        	alert("");
            return;
        } else if (!$("#dealMonth").val()) {
        	alert("");
            return;
        } else if (!$("#dealDay").val()) {
        	alert("");
            return;
        } else if (!$("#aptName").val()) {
        	alert("");
            return;
        } else if (!$("#buildYear").val()) {
        	alert("");
            return;
        } else if (!$("#sidoName").val()) {
        	alert("");
            return;
        } else if (!$("#gugunName").val()) {
        	alert("");
            return;
        } else if (!$("#dongName").val()) {
        	alert("");
            return;
        } else if (!$("#jibun").val()) {
        	alert("");
            return;
        } else if (!$("#area").val()) {
        	alert("");
            return;
        } else if (!$("#floor").val()) {
        	alert("");
            return;
        } else {
            $("#dealform").attr("action", "${root}/house/register").submit();
        }
    });
	
});
    
</script>


<form id="dealform" class="text-left mb-3" method="post" action="">
	<div class="form-group">
		<label for="dealAmount"></label>
		<input type="text" name="dealAmount" id="dealAmount" placeholder="거래금액(만원)"/>
	</div>
	<div class="form-group">
		<label for="dealYear"></label>
		<input type="text" name="dealYear" id="dealYear" placeholder="거래년도"/>
	</div>
	<div class="form-group">
		<label for="dealMonth"></label>
		<input type="text" name="dealMonth" id="dealMonth" placeholder="월"/>
	</div>
	<div class="form-group">
		<label for="dealDay"></label>
		<input type="text" name="dealDay" id="dealDay" placeholder="일"/>
	</div>
	<div class="form-group">
		<label for="aptName"></label>
		<input type="text" name="aptName" id="aptName" placeholder="아파트명"/>
	</div>
	<div class="form-group">
		<label for="buildYear"></label>
		<input type="text" name="buildYear" id="buildYear" placeholder="건축년도"/>
	</div>
	<div class="form-group">
		<label for="sidoName"></label>
		<input type="text" name="sidoName" id="sidoName" placeholder="시/도"/>
	</div>
	<div class="form-group">
		<label for="gugunName"></label>
		<input type="text" name="gugunName" id="gugunName" placeholder="구/군"/>
	</div>
	<div class="form-group">
		<label for="dongName"></label>
		<input type="text" name="dongName" id="dongName" placeholder="읍/면/동"/>
	</div>
	<div class="form-group">
		<label for="jibun"></label>
		<input type="text" name="jibun" id="jibun" placeholder="지번"/>
	</div>
	<div class="form-group">
		<label for="area"></label>
		<input type="text" name="area" id="area" placeholder="전용면적(m2)"/>
	</div>
	<div class="form-group">
		<label for="floor"></label>
		<input type="text" name="floor" id="floor" placeholder="층"/>
	</div>
	<div class="form-group">
		<input type="submit" id="dealRegisterBtn" value="등록"/>
	</div>
</form>


<%@ include file="/WEB-INF/views/include/footer.jsp" %>