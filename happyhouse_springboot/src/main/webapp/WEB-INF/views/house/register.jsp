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
				<label for="aptCode"><strong>아파트명</strong></label>
				  <select class="custom-select" id="aptCode" name="aptCode" >
				    <option selected>아파트 이름을 입력해주세요</option>
				  </select>
			</div>
			<div class="form-group">
				<label for="dealAmount">거래 금액</label>
				<input type="text" class="form-control" name="dealAmount" id="dealAmount" placeholder="거래금액(만원)"/>
			</div>
			<div class="form-group">
				<label for="dealyear">거래 년도</label>
				  <select class="custom-select" id="dealYear" name="dealYear" >
				    <option selected>거래년도</option>
				  </select>
			</div>
			<div class="form-group">
				<label for="dealNonth">거래 월</label>
				  <select class="custom-select" id="dealMonth" name="dealMonth" >
				    <option selected>월</option>
				  </select>
			</div>
			<div class="form-group">
				<label for="dealDay">거래 일</label>
				  <select class="custom-select" id="dealDay" name="dealDay" >
				    <option selected>일</option>
				  </select>
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
	
		
	// 거래년도 select option 추가
	let initOption = `<option>거래년도</option>`;
	$('#dealYear').empty().append(initOption);
	let option = '';
	for(let i=2022; i>=2000; i--){
		option += '<option value="' +i+'">' + i + '년도</option><br>'; 
	}
	$('#dealYear').append(option);
		

	
	// 거래월 select option 추가
	initOption = `<option>월</option>`;
	$('#dealMonth').empty().append(initOption);
	option = '';
	for(let i=1; i<=12; i++){
		option += '<option value="' +i+'">' + i + '월</option><br>'; 
	}
	$('#dealMonth').append(option);
	
	
	// 거래일 select option 추가
	initOption = `<option>일</option>`;
	$('#dealDay').empty().append(initOption);
	option = '';
	for(let i=1; i<=31; i++){
		option += '<option value="' +i+'">' + i + '일</option><br>'; 
	}
	$('#dealDay').append(option);
	
	
	// 등록 버튼 눌렀을 떄 regist
	$('#dealRegisterBtn').on('click', function(){
		
		// 1. 유효성 검사 필요
		
		
		
		// 2. post mapping으로 house deal 정보 register
		$("#dealform").attr("action", "${root}/house/register").submit();
	});
	
	
	
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
		
	$(document).on("change", "#dong", function () {
		let regcode = $("option:selected", this).val();
		$.ajax({
	        url: `${root}/house/search/`+regcode,
	        type: "GET",
			contentType:'application/json;charset=utf-8',
			dataType:'json',
	        success: function (response) {
	        	console.log('response : ' + response);
	    		let options = ``;
	    		let initOption = `<option>Choose..</option>`;
	    		let selid = "#aptCode";
	    		$(selid).empty().append(initOption);
	    		
	    		
	    		for(let info of response) {
	    			console.log('info : '+ info);
	    			console.log(info.aptCode + " " + info['aptName'])
	    			options += '<option value="' + info.aptCode +  '">'+info.aptName +'</option>\n';
	    		}
	    		
	    		$(selid).append(options);
	        },
	        error: function (xhr, status, msg) {
	          console.log("상태값 : " + status + " Http에러메시지 : " + msg);
	        },
	    });
		
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