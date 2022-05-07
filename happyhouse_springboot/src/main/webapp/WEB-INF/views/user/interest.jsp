<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8273ad75e4cf13f650633b14013a60c0&libraries=services"></script>
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
		$(document).on("click", ".listBtn", function() {
			
			let container = document.getElementById('interestmap'); //지도를 담을 영역의 DOM 레퍼런스
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
			
			
			let vid = $(this).parents("tr").attr("data-id");// 클릭한 아파트의 이름 
			console.log(vid)
			$.ajax({
				url : '${root}/interest/list/' + vid,
				type:'GET',
				contentType:'application/json;charset=utf-8',
				success:function(details) {
					$("#detaillist").empty();
					$(details).each(function(index, detail){
						let str=`
							<tr>
							<td>${'${detail.dealAmount}'}</td>
							<td>${'${detail.area}'}</td>
							<td>${'${detail.floor}'}</td>
						</tr>` ;
						$("#modalapt").text(detail.aptName);
						$('#maptname').text(detail.aptName);
						$('#maddress').text(detail.dongName+` `+detail.jibun);
						$('#mbuildyear').text(detail.buildYear+''); 
						
						// =================== 마커 생성 ==================================
							
							let address = detail.dongName+' '+detail.jibun
							
							geocoder.addressSearch(address, function(result, status) {

							    // 정상적으로 검색이 완료됐으면 
							     if (status === kakao.maps.services.Status.OK) {
							        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

							        // 결과값으로 받은 위치를 마커로 표시합니다
							        var marker = new kakao.maps.Marker({
							            map: map,
							            position: coords
							        });
							        /* // 인포윈도우로 장소에 대한 설명을 표시합니다
							        var infowindow = new kakao.maps.InfoWindow({
							            content: '<div style="width:200px;padding:6px 0;">'+
							            '이름 : '+info.aptName + '</br>'+
							            '주소 : '+ address + '</br>'+
							            '건축년도 : '+ info.buildYear +
							            '</div>'
							        }); */
							      /*   infowindow.close(map, marker); */
							       /*  kakao.maps.event.addListener(marker, 'mouseover', function() {
						        	  // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
						        	    infowindow.open(map, marker);
						        	});

						        	// 마커에 마우스아웃 이벤트를 등록합니다
						        	kakao.maps.event.addListener(marker, 'mouseout', function() {
						        	    // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
						        	    infowindow.close();
						        	}); */
						        	
							        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
							        map.setCenter(coords);
							    } 
							}); // end of geocoder
							
							map.relayout();
						//=================================================	
						
						$("#detaillist").append(str);
					});
					
					$("#aptModal").modal();	
					
					//매매가격낮은순 높은 순 눌렀을 때에 , 
					$(document).on("change", "#dealsort", function(){
						 var d = $('#dealsort').val();
			           	// 매매가격 높은 순 정렬 
						 if(d=="up"){
								$.ajax({
									url : '${root}/interest/up/' + vid, 
									type:'GET',
									contentType:'application/json;charset=utf-8',
									success:function(details) {
										$("#detaillist").empty();
										$(details).each(function(index, detail){
											let str=`
												<tr>
												<td>${'${detail.dealAmount}'}</td>
												<td>${'${detail.area}'}</td>
												<td>${'${detail.floor}'}</td>
											</tr>` ;
											
											$("#detaillist").append(str);
										});
									},
										error:function(xhr,status,msg){
											console.log("상태값 : " + status + " Http에러메시지 : "+msg);
										}
										
								});
			           	 }
			           	 // 매매가격 낮은 순 정렬 
						 else {
								$.ajax({
									url : '${root}/interest/down/' + vid, 
									type:'GET',
									contentType:'application/json;charset=utf-8',
									success:function(details) {
										$("#detaillist").empty();
										$(details).each(function(index, detail){
											let str=`
												<tr>
												<td>${'${detail.dealAmount}'}</td>
												<td>${'${detail.area}'}</td>
												<td>${'${detail.floor}'}</td>
											</tr>` ;
											
											$("#detaillist").append(str);
										});
									},
										error:function(xhr,status,msg){
											console.log("상태값 : " + status + " Http에러메시지 : "+msg);
										}
										
								});
						 }
					}); // end of 매매정렬
					
				
				}, // end of success
				error:function(xhr,status,msg){
					console.log("상태값 : " + status + " Http에러메시지 : "+msg);
				}
			});
			map.relayout();

		
		
		});
		
		//찜 갯수많은순 적은 순 눌렀을 때 , 
		$("#likesort").change(function(){
			 var d = $('#likesort').val();
			 if(d=="up"){ // 찜 오름차순
					$.ajax({
						url : '${root}/interest/interestup', 
						type:'GET',
						contentType:'application/json;charset=utf-8',
						success:function(interests) {
							makeLikeList(interests);
							
						},
							error:function(xhr,status,msg){
								console.log("상태값 : " + status + " Http에러메시지 : "+msg);
							}
							
					});
        	 }
        	 //찜 내림차순 
			 else {
					$.ajax({
						url : '${root}/interest/interestdown' , 
						type:'GET',
						contentType:'application/json;charset=utf-8',
						success:function(interests) {
							makeLikeList(interests);
						},
							error:function(xhr,status,msg){
								console.log("상태값 : " + status + " Http에러메시지 : "+msg);
							}
							
					});
			 }
				map.relayout();

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
					<td><button type="button" class="listBtn btn btn-outline-primary">목록</button>
					<button type="button" class="cancelBtn btn btn-outline-danger">삭제</button></td>
				</tr>	
			`;
			
			$("#interestlist").append(str);
		}) ; // each
	}
	function makeLikeList(interests){
		$("#interestlist").empty();
		$(interests).each(function(index, interest){
			let str =`
				<tr id="view_${'${interest.aptname}'}" class="view" data-id="${'${interest.aptName}'}">
					<td>${'${interest.aptName}'}</td>
					<td><button type="button" class="listBtn btn btn-outline-primary">목록</button>
					<button type="button" class="cancelBtn btn btn-outline-danger">삭제</button></td>
				</tr>	
			`;
			
			$("#interestlist").append(str);
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
		 	<!-- 찜 많은 순 적은 순 정렬  -->
			<div class="m-3 row justify-content-end">
				<select id="likesort" name="key">
					<option value="up">관심 많은순 
					<option value="down">관심 적은순
				</select>
			</div>
			
			<div class="row mt-3 ">
				<table class="table table-striped">
					<thead>
					    <tr>
					      <th scope="col">아파트 이름</th>
					      <th scope="col">-</th>
					  
					    </tr>
					</thead>
					<tbody id="interestlist">
					</tbody>
				</table>
			</div>

		

	</div>
</div>

	<!-- 아파트 매매 정보 모달  -->
	<div class="modal text-center " id="aptModal">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
			<!-- header -->
			<div class="modal-header">
		        <h5 class="modal-title align-self-center" id="modalapt">아파트 이름</h5>
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		    </div>
		    
		    <!-- body -->
		    <div class="modal-body">
		    <div class="container max-width">
		    <div class="row">
		    	<div class="col-6">
			    	<div id="interestmap" style="height:350px;"><strong>kakao map</strong></div>
		    	</div>
		    	<div class="col-6">
			    		<table class="table mt-5">
			    			<tbody>
					    	<tr>
					      		<th scope="row">아파트명</th>
					      		<td id="maptname"></td>
					    	</tr>
					    	<tr>
					      		<th scope="row">주소</th>
					      		<td id="maddress"></td>
					    	</tr>
					    	<tr>
					      		<th scope="row">건축 년도</th>
					      		<td colspan="2" id="mbuildyear"></td>
					    	</tr>
					  	</tbody>	
			    		</table>
		    	</div>
		    
		    </div>
		 	</div >
			
			 <!-- 매매가격 순  정렬  -->
			<div class="m-3 row justify-content-end">
				<select id="dealsort" name="key">
					<option value="up">가격 높은 순 
					<option value="down">가격 낮은 순
				</select>
			</div>
			
			<div>
				<table class="table table-striped">
					<thead>
					    <tr>
					      <th scope="col">매매 가격</th>
					      <th scope="col">면적(m2)</th>
					      <th scope="col">층</th>
					    </tr>
					</thead>
					<tbody id="detaillist">
					</tbody>				
					</table>
			</div>
			</div>
			</div>
		</div>
	</div>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

<%@ include file="/WEB-INF/views/include/footer.jsp" %>