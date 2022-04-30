<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

	<script type="text/javascript">
		$(document).ready(function() {
			//로그인 버튼 클릭
			$("#loginBtn").click(function(){
				if (!$("#userid").val()) {
	                alert("아이디를 입력하세요 !");
	                return;
	            } else if (!$("#userpass").val()) {
	                alert("비밀번호를 입력하세요 !");
	                return;
	            } else {
	                $("#loginform").attr("action", "${root}/user/login").submit();
	            }
			});
			
			//회원가입 버튼 클릭
			$("#registerBtn").click(function(){
				location.href="${root}/user/userregister"; 
			});
		});
	
	</script>
	
	
	<div class="container text-center mt-4">
			<h4 class="mt-3 mb-5">로그인</h4>
			<div class="col-lg-8 mx-auto">
				<form id="loginform" class="text-left mb-3" method="post" action="">
			
                <div class="form-group">
                    <label for="userid">아이디</label>
                    <input type="text" class="form-control" id="userid" name="userid" placeholder="아이디를 입력하세요">
                    <div id="idresult" class="mt-1"></div>
                </div>
                <div class="form-group">
                    <label for="userpwd">비밀번호</label>
                    <input type="password" class="form-control" id="userpass" name="userpass" placeholder="비밀번호를 입력하세요">
                </div>
                <div class="text-danger mb-2">${msg}</div>
                <div class="form-group text-center">
                	<button type="button" id="loginBtn" class="btn btn-outline-primary">로그인</button>
                	<button type="button" id="registerBtn" class="btn btn-outline-primary">회원가입</button>
                </div>
				</form>
			</div>
		</div>
	</body>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>