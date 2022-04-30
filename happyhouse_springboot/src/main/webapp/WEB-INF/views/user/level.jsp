<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

	<script type="text/javascript">
		$(document).ready(function() {
			//일반 회원 가입 
			$("#userRegist").click(function(){
				$("#userform").submit();
			});
			
			//중개 회원 가입
			$("#sellerRegist").click(function(){
				$("#sellerform").submit();
			});
		}); 
	</script>
	
	<div class="container text-center mt-4">
		<form id="userform" method="get" action="${root}/user/mvregister">
			<input type="hidden" name="level" id="level" value="0">
				<button type="button" id="userRegist">일반 회원</button>
		</form>

	<form id="sellerform" method="get" action="${root}/user/mvregister">
			<input type="hidden" name="level" id="level" value="1">
			<button type="button" id="sellerRegist">중개 회원</button>
		</form>
	</div>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>