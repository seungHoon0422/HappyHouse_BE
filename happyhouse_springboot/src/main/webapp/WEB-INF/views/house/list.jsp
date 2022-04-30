<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>


<div class="container text-center mt-3">
	<div class="col-lg-8 mx-auto">
	
	
		<h4><strong>아파트 매매 내역</strong></h4>
		<br>
		<c:if test="${empty infos }">
			<strong>아파트 매매 정보가 존재하지 않습니다.</strong>
		</c:if>
		<c:if test="${!empty infos }">
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
			    </tr>
			</thead>
			<tbody>
			<c:forEach var="info" items="${infos }">
				<tr>
					<td>${info.aptName}</td>
					<td>${info.buildYear}</td>
					<td>${info.dealAmount}</td>
					<td>${info.area}</td>
					<td>${info.dealYear}</td>
					<td>${info.dealMonth}</td>
					<td>${info.dealDay}</td>
				</tr>				
			</c:forEach>
			</tbody>
		</table>
		
		</c:if>

	</div>
</div>






<%@ include file="/WEB-INF/views/include/footer.jsp" %>