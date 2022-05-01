<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8273ad75e4cf13f650633b14013a60c0&libraries=services"></script>
<script type="text/javascript">


	// geocoder API 인증키 : 81AC2C2C-517F-346E-BC83-A387C88B194E	
$(function () {
	
	
	var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
	var options = { //지도를 생성할 때 필요한 기본 옵션
		center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
		level: 3 //지도의 레벨(확대, 축소 정도)
	};

	var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
	
	// 마커가 표시될 위치입니다 
	var markerPosition  = new kakao.maps.LatLng(33.450701, 126.570667); 

	// 마커를 생성합니다
	var marker = new kakao.maps.Marker({
	    position: markerPosition
	});

	// 마커가 지도 위에 표시되도록 설정합니다
	marker.setMap(map);

	// 아래 코드는 지도 위의 마커를 제거하는 코드입니다
	// marker.setMap(null);    

	var geocoder = new kakao.maps.services.Geocoder();
	
	// 주소로 좌표를 검색합니다
	geocoder.addressSearch("도화동 83", function(result, status) {

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
	            content: '<div style="width:150px;text-align:center;padding:6px 0;">장소</div>'
	        });
	        infowindow.open(map, marker);

	        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	        map.setCenter(coords);
	    } 
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
		</div>
		<table class="table table-striped">
			<thead>
			    <tr>
			      <th scope="col">아파트 이름</th>
			      <th scope="col">건축년도</th>
			      <th scope="col">매매 가격</th>
			      <th scope="col">면적</th>
			      <th scope="col">년</th>
			      <th scope="col">월</th>
			      <th scope="col">일</th>
			      <th scope="col">관심등록</th>
			    </tr>
			</thead>
			<tbody>
			<c:forEach var="info" items="${infos }">
				<tr>
					<input type="hidden" id="aptCode" name="aptCode" value="${info.aptCode}"/>
					<td>${info.aptName}</td>
					<td>${info.buildYear}</td>
					<td>${info.dealAmount}</td>
					<td>${info.area}</td>
					<td>${info.dealYear}</td>
					<td>${info.dealMonth}</td>
					<td>${info.dealDay}</td>
					<td><input type="button" class="btn btn-outline-warning" value="등록" /></td>
				</tr>				
			</c:forEach>
			</tbody>
		</table>
		
		</c:if>
		

	</div>
</div>

<script type="text/javascript">






</script>




<%@ include file="/WEB-INF/views/include/footer.jsp" %>