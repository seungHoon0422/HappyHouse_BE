<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>


<div class="container text-center mt-3">
	<div class="col-lg-10 mx-auto ">
	
	
		<h4 class="mt-3 mb-5">관심 목록</h4>
		<br>
		<c:if test="${empty apartlist}">
			<strong>관심 등록한 아파트가 존재하지 않습니다.</strong>
		</c:if>
		
		
		<c:if test="${!empty apartlist }">
		
<!-- 
			<div class="row mt-3">
				<div id="mapDiv" class="container col-sm-12">
					<div id="map" style="height: 450px"><strong>kakao map</strong></div>
				</div>
		 	</div > -->
			<div class="row mt-3">
				<table class="table table-striped">
					<thead>
					    <tr>
					      <th scope="col">아파트 이름</th>
					      <th scope="col">목록</th>
					    </tr>
					</thead>
					<tbody>
					<c:forEach var="info" items="${infos }">
						<tr>
							<td>${apartlist.aptCode}</td>
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

<%@ include file="/WEB-INF/views/include/footer.jsp" %>