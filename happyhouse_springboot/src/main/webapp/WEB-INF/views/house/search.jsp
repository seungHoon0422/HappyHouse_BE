<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
    
    
<script type="text/javascript">

$(document).ready(function(){
	
	
	// 아파트 명 input 박스 display none 처리
	$("#aptdiv").css("display", "none");

	// 지역으로 검색버튼 클릭 시 아파트 input x
	$("#regionBtn").on("click", function(){
		$("#aptdiv").css("display", "none");
	});
	// 아파트명으로 검색하기 버튼을 클릭하면 아파트명 입력 input box 보이게 처리
	$("#aptBtn").on("click", function(){
		$("#aptdiv").css("display", "");
	});
	
	
	// 검색 정보가 모두 입력되면 submit
	$("#dealListBtn").on("click", function(){
		$("#housesearchfrom").attr("action", "${root}/house/register").submit();
	});
	
	
	$.ajax({
		url:'${root}/region/sido',  
		type:'GET',
		contentType:'application/json;charset=utf-8',
		dataType:'json',
		success:function(sido) {
			console.log(sido);
			makeList(sido);
			
		},
		error:function(xhr, status, error){
			console.log("상태값 : " + xhr.status + "\tHttp 에러메시지 : " + xhr.responseText);
		},
		statusCode: {
			500: function() {
				alert("서버에러.");
				// location.href = "/error/e500";
			},
			404: function() {
				alert("페이지없다.");
				// location.href = "/error/e404";
			}
		}	
	});
	
	function makeList(sidos) {
		
		let options = ``;
		let initOption = `<option>Choose..</option>`;
		$(sido).empty().append(initOption);
		
		$(sidos).each(function(index, sido) {
			options += '<option value="' + sido["code"] + '">' + sido["name"] + '</option>\n';
		});//each
		$("#sido").append(options);
	} // end of makeList
	
	
}); // end of document ready


</script>





<div class="container text-center mt-3">
       <div class="col-lg-8 mx-auto">
			<div>
				<button type="button" class="btn btn-outline-warning" id="regionBtn" name="regionBtn">지역으로 검색</button>
				<button type="button" class="btn btn-outline-warning" id="aptBtn" name="aptBtn">아파트명으로 검색</button>
			</div>

            <form id="housesearchfrom" class="text-left mb-3" method="post" action="">
			

<!-- ==================================================================================== -->
			<div class="form-group row">
				    <label for="sido"><strong>시,도</strong></label>
				  <select class="custom-select" id="sido">
				  </select>
			</div>
			<div class="form-group row">
				    <label for="sido"><strong>구,군</strong></label>
				  <select class="custom-select" id="gugun">
				    <option selected>Choose...</option>
				  </select>
			</div>
			<div class="form-group row">
				    <label for="sido"><strong>동</strong></label>
				  <select class="custom-select" id="dong">
				    <option selected>Choose...</option>
				  </select>
			</div>
			<div class="form-group row" id="aptdiv">
				    <label for="aptName"><strong>아파트 이름</strong></label>
				  <select class="custom-select" id="aptName" name="aptName">
				    <option selected>Choose...</option>
				  </select>
			</div>
<!-- ==================================================================================== -->

<!-- 			<div class="form-group row"> -->
<!-- 				<label for="sidoName"><strong>시도명</strong></label> -->
<!-- 				<input class="form-control" type="text" id="sidoName" name="sidoName" placeholder="시/도"/> -->
<!-- 			</div> -->
<!-- 			<div class="form-group row"> -->
<!-- 				<label for="gugunName"><strong>구군명</strong></label> -->
<!-- 				<input class="form-control" type="text" id="gugunName" name="gugunName" placeholder="구/군"/> -->
<!-- 			</div> -->
<!-- 			<div class="form-group row"> -->
<!-- 				<label for="dongName"><strong>동명</strong></label> -->
<!-- 				<input class="form-control" type="text" id="dongName" name="dongName" placeholder="읍/면/동"/> -->
<!-- 			</div> -->
<!-- 			<div class="form-group row name" id="aptdiv"> -->
<!-- 				<label for="aptName"><strong>아파트명</strong></label> -->
<!-- 				<input class="form-control" type="text" id="aptName" name="aptName" placeholder="아파트명"/> -->
<!-- 			</div> -->
		</form>
			<input type="button" id="dealListBtn" class="btn btn-outline-warning" value="검색"/>
	</div>
</div>






<%@ include file="/WEB-INF/views/include/footer.jsp" %>