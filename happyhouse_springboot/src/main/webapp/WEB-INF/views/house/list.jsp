<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8273ad75e4cf13f650633b14013a60c0&libraries=services"></script>
<script type="text/javascript">
var selectAptCode;


$(function () {
	
	
	
// 	=========================  kakao map api 사용  =================================
	let container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
	let options = { //지도를 생성할 때 필요한 기본 옵션
		center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
		level: 3 //지도의 레벨(확대, 축소 정도)
	};

	let map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
	let geocoder = new kakao.maps.services.Geocoder();
	// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
	let mapTypeControl = new kakao.maps.MapTypeControl();

	// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
	// kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
	map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

	// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
	let zoomControl = new kakao.maps.ZoomControl();
	map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
	// 중복된 마커를 찍지 않게하기위해 사용
	let prevAptCode = 0;
	let markers = [];
	let count = 0;
	// =================== 1. 검색된 아파트마다 마커 생성 ==================================
	$("input[name=aptCode]").each(function(index, code){
		let value = $(this).val();
		let parents = $(this).parents('tr');
		if(value == prevAptCode) return true;
		prevAptCode = value;
		
// 		console.log('value : '+value);
		$.ajax({
			url : '${root}/region/address/'+value,
			type : 'GET',
			contentType:'application/json;charset=utf-8',
			success:function(info) {
// 				console.log(info);
				let address = info.dongName+' '+info.jibun
				
				
				geocoder.addressSearch(address, function(result, status) {

				    // 정상적으로 검색이 완료됐으면 
				     if (status === kakao.maps.services.Status.OK) {
				        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

				        // 결과값으로 받은 위치를 마커로 표시합니다
				        var marker = new kakao.maps.Marker({
				            map: map,
				            position: coords
				        });
				        // 인포윈도우로 장소에 대한 설명을 표시합니다
				        var infowindow = new kakao.maps.InfoWindow({
				            content: '<div style="width:200px;padding:6px 0;">'+
				            '이름 : '+info.aptName + '</br>'+
				            '주소 : '+ address + '</br>'+
				            '건축년도 : '+ info.buildYear +
				            '</div>'
				        });
				        infowindow.close(map, marker);
				        kakao.maps.event.addListener(marker, 'mouseover', function() {
			        	  // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
			        	    infowindow.open(map, marker);
			        	});

			        	// 마커에 마우스아웃 이벤트를 등록합니다
			        	kakao.maps.event.addListener(marker, 'mouseout', function() {
			        	    // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
			        	    infowindow.close();
			        	});
			        	
				        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
				        map.setCenter(coords);
				    } 
				}); // end of geocoder
			},
	        error: function (xhr, status, msg) {
		          console.log("상태값 : " + status + " Http에러메시지 : " + msg);
		        },
		}); // end of ajax
	}); // end of 1
	
	// =================== 2. 세부정보 버튼 클릭시 이벤트 생성 ==================================
	$(document).on("click", "[name=detailBtn]", function() {
		
		$('#starbucksmap').css("display", "none");
		let no = $(this).attr('data-id');
		$('#maptname').text('');
		$('#maddress').text('');
		$('#mbuildyear').text('');
		$('#mdealamount').text('');
		$('#mdate').text('');
		$('#marea').text('');
		$('#mno').text('');
		
		// 모달 정보 가져오는 비동기
		$.ajax({
			url : '${root}/region/detail/'+no,
			type : 'GET',
			contentType:'application/json;charset=utf-8',
			success:function(detail) {
				console.log(detail)
				var dealinfo = detail.deal;
				var houseinfo = detail.house;

				// 관심등록 여부 확인
				$.ajax({
					url : '${root}/interest/search/'+dealinfo.aptCode,
					type : 'Get',
					contentType:'application/json;charset=utf-8',
					success:function(response) {
						
						console.log('관심 등록여부 확인');
						// 관심등록 되어있으면 버튼 display : none;
						
						// 관심등록 안되어있으면 dispalay : ;
					
					},
					error: function (xhr, status, msg) {
				          console.log("상태값 : " + status + " Http에러메시지 : " + msg);
				        },
				}); // end of ajax

				
				
				333333
				
				
				drawModal(dealinfo, houseinfo);	
			},
			error: function (xhr, status, msg) {
		          console.log("상태값 : " + status + " Http에러메시지 : " + msg);
		        },
		}); // end of ajax
		
		
		
		
	});// end of click event
		
	// 상세정보 보기 버튼을 누른 아파트 코드
	
	function drawModal(deal, house) {
		console.log(deal, house);
		$('#maptname').text(house.aptName);
		$('#maddress').text(house.dongName+` `+house.jibun);
		$('#mbuildyear').text(house.buildYear+'');
		$('#mdealamount').text(deal.dealAmount+'');
		$('#mdate').text(deal.dealYear+'년 '+deal.dealMonth+'월 '+deal.dealDay+'일');
		$('#marea').text(deal.area+'');
		$('#mno').text(deal.no+'');
		$('#mdongcode').text(house.dongCode);

		console.log('select apt code : '+deal.aptCode);
		selectAptCode = deal.aptCode;
	}
	
	// =================== 3. 관심 등록 버튼 클릭 이벤트  ==================================
	$(document).on("click", "#interestBtn", function() {
	
		$.ajax({
			url : '${root}/interest/regist',
			type : 'POST',
			data : JSON.stringify({
				"aptCode" : selectAptCode
			}), 
			contentType:'application/json;charset=utf-8',
			success:function(response) {
				alert("관심 목록에 등록되었습니다.");
				$("#houseModal").modal('hide');
			},
			error: function (xhr, status, msg) {
		          console.log("상태값 : " + status + " Http에러메시지 : " + msg);
		        },
		}); // end of ajax
		
	});
	
}); // document on load





</script>


<div class="container text-center mt-3">
	<div class="col-lg-10 mx-auto ">
	
	
		<h4><strong>아파트 매매 내역</strong></h4>
		<br>
		<c:if test="${empty infos }">
			<strong>아파트 매매 정보가 존재하지 않습니다.</strong>
		</c:if>
		
		
		<c:if test="${!empty infos }">
		

			<div class="row mt-3">
				<div id="mapDiv" class="container col-sm-12">
					<div id="map" style="height: 450px"><strong>kakao map</strong></div>
				</div>
		 	</div >
			<div class="row mt-3">
				<table class="table table-striped">
					<thead>
					    <tr>
					      <th scope="col">아파트 이름</th>
					      <th scope="col" style="display : none">건축년도</th>
					      <th scope="col">매매 가격</th>
					      <th scope="col" style="display : none">면적</th>
					      <th scope="col" style="display : none">년</th>
					      <th scope="col" style="display : none">월</th>
					      <th scope="col" style="display : none">일</th>
					      <th scope="col">관심등록</th>
					    </tr>
					</thead>
					<tbody>
					<c:forEach var="info" items="${infos }">
						<tr>
							<input type="hidden" id="aptCode" name="aptCode" value="${info.aptCode}"/>
							<input type="hidden" id="no" name="no" value="${info.no}"/>
							<td>${info.aptName}</td>
							<td style="display : none">${info.buildYear}</td>
							<td>${info.dealAmount}</td>
							<td style="display : none">${info.area}</td>
							<td style="display : none">${info.dealYear}</td>
							<td style="display : none">${info.dealMonth}</td>
							<td style="display : none">${info.dealDay}</td>
							<td><input type="button" class="btn btn-outline-warning" name="detailBtn" data-id="${info.no}" data-toggle="modal" data-target="#houseModal" value="세부 정보" /></td>
						</tr>				
					</c:forEach>
					</tbody>
				</table>
			</div>

		
		
		</c:if>
		

	</div>
</div>


<!--		 modal			 -->
<div class="modal text-center" id="houseModal">
  <div class="modal-dialog modal-lg" >
    <div class="modal-content">
      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title"><strong>아파트 매매 상세 정보</strong></h4>
        <button type="button" class="close" data-dismiss="modal" >
        <span aria-hidden="true">&times;</span>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
      
      	<input type="hidden" id="mdongcode"/>
      	<table class="table table-hover">
		  <thead>
		  </thead>
		  <tbody>
		    <tr>
		      <th scope="row">아파트명</th>
		      <td><div id="maptname"></div></td>
		    </tr>
		    <tr>
		      <th scope="row">주소</th>
		      <td id="maddress"></td>
		    </tr>
		    <tr>
		      <th scope="row">건축 년도</th>
		      <td colspan="2" id="mbuildyear"></td>
		    </tr>
		    <tr>
		      <th scope="row">매매 가격</th>
		      <td colspan="2" id="mdealamount"></td>
		    </tr>
		    <tr>
		      <th scope="row">거래 년도</th>
		      <td colspan="2" id="mdate"></td>
		    </tr>
		    <tr>
		      <th scope="row">면적(m2)</th>
		      <td colspan="2" id="marea"></td>
		    </tr>
		    <tr style="display : none">
		      <th scope="row"></th>
		      <td colspan="2" id="mno" ></td>
		    </tr>
		    

		  </tbody>
		</table>
	      <div class="container col-sm-12">
	      	<div id="starbucksmap" style="width:100%;height:350px;"><strong>starbucks map</strong></div>
	      </div>
      </div>
      
       <div class="modal-footer mx-auto">
		<c:if test="${!empty userinfo}">
        <button type="button" class="btn btn-outline-success" id="starbucksBtn">주변 starbucks 매장</button>
        <button type="button" class="btn btn-outline-primary" id="interestBtn">관심 등록</button>
        </c:if>
        <button type="button" class="btn btn-outline-dark" data-dismiss="modal">목록</button>
      </div>
    </div>
  </div>
</div>




<script type="text/javascript">


// ============== 스타벅스 매장 버튼 클릭이벤트 등록 ==============
$(document).on('click', '#starbucksBtn', function(){
	
	
	let container = document.getElementById('starbucksmap'); //지도를 담을 영역의 DOM 레퍼런스
	let options = { //지도를 생성할 때 필요한 기본 옵션
		center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
		level: 3 //지도의 레벨(확대, 축소 정도)
	};

	let map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
	let geocoder = new kakao.maps.services.Geocoder();
	

	
	let dongcode = $('#mdongcode').text();
	console.log(dongcode)
	
	// 상세보기를 선택한 거래내역의 행정동 이름 요청
	$.ajax({
		url : '${root}/starbucks/search/'+dongcode,
		type : 'Get',
		contentType:'application/json;charset=utf-8',
		success:function(response, status) {
			
			// 같은 행정동에 있는 스타버스 매장 map에 표시
			for(let starbucks of response){
				
				geocoder.addressSearch(starbucks['address'], function(result, status) {

				    // 정상적으로 검색이 완료됐으면 
				     if (status === kakao.maps.services.Status.OK) {
				        let coords = new kakao.maps.LatLng(result[0].y, result[0].x);
						
				        // 결과값으로 받은 위치를 마커로 표시합니다
				        let marker = new kakao.maps.Marker({
				            map: map,
				            position: coords
				        });
				        // 인포윈도우로 장소에 대한 설명을 표시합니다
				        let infowindow = new kakao.maps.InfoWindow({
				            content: '<div style="width:200px;padding:6px 0;">'+
				            '매장 이름 : '+starbucks['name'] + '점</br>'+
				            '주소 : '+ starbucks['address'] + '</br>'+
				            '</div>'
				        });
				        infowindow.close(map, marker);
				        kakao.maps.event.addListener(marker, 'mouseover', function() {
			        	  // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
			        	    infowindow.open(map, marker);
			        	});
			        	// 마커에 마우스아웃 이벤트를 등록합니다
			        	kakao.maps.event.addListener(marker, 'mouseout', function() {
			        	    // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
			        	    infowindow.close();
			        	});
			        	
				        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
				        map.setCenter(coords);
				    } 
				}); // end of geocoder
				
				
			}
			// kakao map api 렌더링 오류로 크기 재설정
			map.relayout()
		
		},
		error: function (xhr, status, msg) {
	          console.log("상태값 : " + status + " Http에러메시지 : " + msg);
	        },
	}); // end of ajax
	
	
	$('#starbucksmap').css("display", "");
});




</script>




<%@ include file="/WEB-INF/views/include/footer.jsp" %>