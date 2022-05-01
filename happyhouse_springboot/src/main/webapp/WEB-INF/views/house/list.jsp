<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8273ad75e4cf13f650633b14013a60c0"></script>

<script type="text/javascript">

var map;
var markers = []; //마커 제거를 위해 현재 마커들 저장해놓음
var storeData;
var totalData = 0;

$(function () {
	
	
	var container = document.getElementById("kmap");
	var options = {
	   	center: new kakao.maps.LatLng(33.450701, 126.570667),
	   	level: 3,
	 	};
	 
	map = new kakao.maps.Map(container, options);
	
	

} // document on load





</script>


<div class="container text-center mt-3">
	<div class="col-lg-10 mx-auto ">
	
	
		<h4><strong>아파트 매매 내역</strong></h4>
		<br>
		<c:if test="${empty infos }">
			<strong>아파트 매매 정보가 존재하지 않습니다.</strong>
		</c:if>
		
		
		<c:if test="${!empty infos }">
		<div class="col-8 mt-3 bg-warning">
			<div id="mapDiv" class="container col-sm-12">
				<div id="kmap" style="height: 450px"></div>
			</div>
	 	</div >
		
		<div class="col-4 mt-3">
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