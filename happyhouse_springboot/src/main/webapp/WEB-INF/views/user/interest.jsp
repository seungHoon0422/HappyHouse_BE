<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

	<script type="text/javascript">
	$(document).ready(function(){
		//관심목록 삭제 
		$("#cancelBtn").click(function(){
			if(confirm("관심 목록에서 삭제하시겠습니까 ?")){
				// 삭제 
			}
		}); 
	}); 
	</script>

	<div class="container text-center mt-3">
		<div class="col-lg-10 mx-auto ">
	
	
		<h4 class="mt-3 mb-5">관심 목록</h4>
		<br>
		<c:if test="${empty apartlist}">
			<strong>관심 등록한 아파트가 존재하지 않습니다.</strong>
		</c:if>
		
		
		<c:if test="${!empty apartlist }">
		

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
					      <th scope="col">목록</th>
					    </tr>
					</thead>
					<tbody>
					<c:forEach var="aptname" items="${apartlist}">
						<tr>
							<td>${aptname}</td>
							
							<td><input type="button" class="btn btn-outline-danger" name="cancelBtn" id="cancelBtn" value="삭제" /></td>
						</tr>				
					</c:forEach>
					</tbody>
				</table>
			</div>

		
		
		</c:if>
		

	</div>
</div>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>