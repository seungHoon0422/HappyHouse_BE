<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

	<script type="text/javascript">
	$(document).ready(function(){
		//관심목록 불러오기
		$.ajax({
			url:'${root}/interest/interest',
			type : 'GET',
			contentType:'application/json;charset=utf-8',
			dataType:'json',
			success:function(interests) {
				makeList(interests);
			},
			error:function(xhr, status, error){
				console.log("상태값 : " + xhr.status + "\tHttp 에러메시지 : " + xhr.responseText);
			}
		});
		
		// 해당 아파트에 대한 목록 보기 
		$(document).on("click", ".detailBtn", function() {
			let vid = $(this).parents("tr").attr("data-id"); // 클릭한 아파트의 이름 
			$.ajax({
				url : '${root}/interest/list/' + vid,
				type:'GET',
				contentType:'application/json;charset=utf-8',
				success:function(details) {
					makeDetail(details);
				},
				error:function(xhr,status,msg){
					console.log("상태값 : " + status + " Http에러메시지 : "+msg);
				}
			});
			
		});
		
		
		//관심목록 삭제 
		$(document).on("click", ".cancelBtn", function() {
			if(confirm("관심 목록에서 삭제하시겠습니까 ?")){
				// 삭제 
				let deleteid = $(this).parents("tr").attr("data-id");
				console.log(deleteid);
				$.ajax({
					url:'${root}/interest/interest/'+deleteid,
					type :'DELETE',
					contentType:'application/json;charset=utf-8',
					dataType:'json',
					success:function(interests) {
						makeList(interests);
					},
					error:function(xhr,status,msg){
						console.log("상태값 : " + status + " Http에러메시지 : "+msg);
					}
				});
				
			}
		}); 
	}); 
	
	function makeList(interests){
		$("#interestlist").empty();
		$(interests).each(function(index, aptname){
			let str =`
				<tr id="view_${'${aptname}'}" class="view" data-id="${'${aptname}'}">
					<td>${'${aptname}'}</td>
					<td><button type="button" class="detailBtn btn btn-outline-danger">목록</button></td>
					<td><button type="button" class="cancelBtn btn btn-outline-danger">삭제</button></td>
				</tr>	
			`;
			
			$("#interestlist").append(str);
		}) ; // each
	}
	
	function makeDetail(details){
		$("#interestdetail").empty();
		$(details).each(function(index, detail){
			let str =`
				<thead>
			    <tr>
			      <th scope="col">아파트 이름</th>
			      <th scope="col">매매 가격</th>
			      <th scope="col">면적(m2)</th>
			      <th scope="col">층(m2)</th>
			    </tr>
				</thead>	
				<tr id="view_${'${aptdetail}'}" class="view" data-id="${'${aptdetail}'}">
				<td>${'${detail.aptName}'}</td>
				<td>${'${detail.area}'}</td>
				<td>${'${detail.dealAmount}'}</td>
				<td>${'${detail.floor}'}</td>
			</tr>	
			`;
			
			$("#interestdetail").append(str);
		}) ; // each
	}
	</script>

	<div class="container text-center mt-3">
		<div class="col-lg-10 mx-auto ">
	
		
		<h4 class="mt-3 mb-5">관심 목록</h4>
		<br>
<%-- 		<c:if test="${empty apartlist}">
			<strong>관심 등록한 아파트가 존재하지 않습니다.</strong>
		</c:if>
		 --%>
		
<%-- 		<c:if test="${!empty apartlist }"> --%>
		

<!-- 			<div class="row mt-3">
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
					<tbody id="interestlist">
					</tbody>
					<tbody id="interestdetail">
					</tbody>
				</table>
			</div>

		
		
<%-- 		</c:if> --%>
		

	</div>
</div>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>