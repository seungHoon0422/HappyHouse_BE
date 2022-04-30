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
	
	// 	검색 정보가 모두 입력되면 submit
	$("#dealListBtn").on("click", function(){
		console.log(`btn clicked`);
		$("#HouseSearchform").attr("action", "${root}/house/list").submit();
	});
	
	
	
	
	// 시,도 select options 비동기로 입력
	$.ajax({
		url:'${root}/region/sido',  
		type:'GET',
		contentType:'application/json;charset=utf-8',
		dataType:'json',
		success:function(data) {
			console.log(data);
			makeList("sido", data);
			
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
	
	$(document).on("change", "#sido", function () {
		let regcode = $("option:selected", this).val();
		sendRequest("gugun", regcode);
	});
	$(document).on("change", "#gugun", function () {
		let regcode = $("option:selected", this).val();
		sendRequest("dong", regcode);
	});
	
	
	function sendRequest(selid, regcode) {
	      $.ajax({
	        url: `${root}/region/`+selid+`/`+regcode,
	        type: "GET",
			contentType:'application/json;charset=utf-8',
			dataType:'json',
	        success: function (response) {
	          addOption(selid, response);
	        },
	        error: function (xhr, status, msg) {
	          console.log("상태값 : " + status + " Http에러메시지 : " + msg);
	        },
	      });
  	}
	function addOption(selid, data) {
		let options = ``;
		let initOption = `<option>Choose..</option>`;
		let codeOption = "code";
		let nameOption = "name";
		selid = "#" + selid;
		$(selid).empty().append(initOption);
		for(let cur of data) {
			options += '<option value="' + cur[codeOption] + '">' + cur[nameOption] + '</option>\n';
		}
		$(selid).append(options);
    }
	
	// makeList : 지역구분과 데이터를 받아 select option을 그리는 함수
	function makeList(part, sidos) {
		
		let options = ``;
		let initOption = `<option>Choose..</option>`;
		$(sido).empty().append(initOption);
		
		$(sidos).each(function(index, sido) {
			options += '<option value="' + sido["code"] + '">' + sido["name"] + '</option>\n';
		});//each
		$("#"+part).append(options);
	} // end of makeList
	
}); // end of document ready


</script>





<div class="container text-center mt-3">
       <div class="col-lg-8 mx-auto">
			<div>
				<button type="button" class="btn btn-outline-warning" id="regionBtn" name="regionBtn">지역으로 검색</button>
				<button type="button" class="btn btn-outline-warning" id="aptBtn" name="aptBtn">아파트명으로 검색</button>
			</div>

            <form id="HouseSearchform" class="text-left mb-3" method="POST" action="">
			
<!-- ==================================================================================== -->
			<div class="form-group row">
				    <label for="sido"><strong>시,도</strong></label>
				  <select class="custom-select" id="sido" name="sido">
				  </select>
			</div>
			<div class="form-group row">
				    <label for="sido"><strong>구,군</strong></label>
				  <select class="custom-select" id="gugun" name="gugun">
				    <option selected>Choose...</option>
				  </select>
			</div>
			<div class="form-group row">
				    <label for="sido"><strong>동</strong></label>
				  <select class="custom-select" id="dong" name="dong">
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