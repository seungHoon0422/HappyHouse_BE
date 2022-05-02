<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

		<script type="text/javascript">
			$(document).ready(function() {
				$("#updateUser").css("display", "none"); 
				$("#cancelBtn").css("display", "none"); 
			//회원 정보 수정 
				$("#updateBtn").click(function(){
					$("#userpass").attr("readonly", false);
					$("#updateBtn").css("display", "none"); 
					$("#deleteBtn").css("display", "none");
					$("#updateUser").css("display", ""); 
					$("#cancelBtn").css("display", ""); 
				});
				
			$(document).on("click", "#updateUser", function(){
				$("#updateform").attr("action", "${root}/user/update").submit();
			}); 
			
			
			//회원 탈퇴 
				$("#deleteBtn").click(function() {
					if(confirm("회원 탈퇴 하시겠습니까 ?")){
						location.href="${root}/user/delete"; 
					}
				});
			
			});
			
		
			
		</script>


		<div class="container text-center mt-4">
			<h4 class="mt-3 mb-5">회원정보</h4>
			<div class="col-lg-8 mx-auto">
				<form id="updateform" class="text-left mb-3" method="post" action="">
				<input type ="hidden" id="level" name="level" value="${userlist.level}">
				<div class="form-group">
                    <label for="username">이름</label>
                    <input type="text" class="form-control" id="username" name="username" value="${userlist.username}" readonly>
                </div>
                <div class="form-group">
                    <label for="userid">아이디</label>
                  	<input type="text" class="form-control" id="userid" name="userid" value="${userlist.userid}" readonly>     
                    <div id="idresult" class="mt-1"></div>
                </div>
                <div class="form-group">
                    <label for="userpwd">비밀번호</label>
                    <input type="text" class="form-control" id="userpass" name="userpass" value="${userlist.userpass}" readonly>
                </div>
                <div class="form-group">
                    <label for="phone">전화번호</label>
                   <input type="text" class="form-control" id="phone" name="phone" value="${userlist.phone}" readonly>
                </div>
                <div class="form-group">
                    <label for="email">이메일</label><br>
                    <input type="text" class="form-control" id="email" name="email" value="${userlist.email}" readonly>
                </div>
                
                <div class="form-group text-center">
                	<button type="button" id="updateBtn" class="btn btn-outline-primary" >회원정보 수정</button>
                	<button type="button" id="deleteBtn" class="btn btn-outline-danger">회원탈퇴</button>
                	<button type="button" id="updateUser" class="btn btn-outline-primary">수정</button>
                	<button type="button" id="cancelBtn" class="btn btn-outline-danger">취소</button>
                </div>
				</form>
			</div>
		</div>
	

<%@ include file="/WEB-INF/views/include/footer.jsp" %>