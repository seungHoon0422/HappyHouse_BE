<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
    


<div class="container text-center mt-3">
	<div class="col-lg-10 mx-auto ">
		<h4><strong>아파트 매매 정보 입력</strong></h4>
		<div class="col-8 mx-auto">
		<form id="dealform" class="text-left mb-3" method="post" action="">

			<div class="form-group" id="sidodiv">
				    <label for="sido"><strong>시,도</strong></label>
				  <select class="custom-select" id="sido" name="sido">
				  </select>
			</div>
			<div class="form-group" id="gugundiv">
				    <label for="sido"><strong>구,군</strong></label>
				  <select class="custom-select" id="gugun" name="gugun">
				    <option selected>구/군을 선택하세요</option>
				  </select>
			</div>
			<div class="form-group" id="dongdiv">
				    <label for="sido"><strong>동</strong></label>
				  <select class="custom-select" id="dong" name="dong">
				    <option selected>행정동을 선택하세요</option>
				  </select>
			</div>
			<div class="form-group" id="aptdiv">
				<label for="aptName"><strong>아파트명</strong></label>
				<input class="form-control" type="text" id="aptName" name="aptName" placeholder="아파트 이름을 입력해주세요"/>
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

<script type="text/javascript">



$(document).ready(function () {
	
		
	// 1. 시/도 select options 비동기로 입력
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
	}); // end of sido ajax
		
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

<%@ include file="/WEB-INF/views/include/footer.jsp" %>