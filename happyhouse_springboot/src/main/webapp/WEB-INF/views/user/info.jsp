<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>


		<div class="container text-center mt-4">
			<h4 class="mt-3 mb-5">회원가입</h4>
			<div class="col-lg-8 mx-auto">
				<form id="updateform" class="text-left mb-3" method="post" action="">
				<div class="form-group">
                    <label for="username">이름</label>
                    <input type="text" class="form-control" id="username" name="username" value="${userlist.name}" disabled>
                </div>
                <div class="form-group">
                    <label for="userid">아이디</label>
                  	<input type="text" class="form-control" id="userid" name="userid" value="${userlist.userid}" disabled>     
                    <div id="idresult" class="mt-1"></div>
                </div>
                <div class="form-group">
                    <label for="userpwd">비밀번호</label>
                    <input type="text" class="form-control" id="userpass" name="userpass" value="${userlist.userpass}" disabled>
                </div>
                <div class="form-group">
                    <label for="phone">전화번호</label>
                   <input type="text" class="form-control" id="phone" name="phone" value="${userlist.phone}" disabled>
                </div>
                <div class="form-group">
                    <label for="email">이메일</label><br>
                    <input type="text" class="form-control" id="email" name="email" value="${userlist.email}" disabled>
                </div>
                
                <div class="form-group text-center">
                	<button type="button" id="updateBtn" class="btn btn-outline-primary">회원정보 수정</button>
                	<button type="button" id="deleteBtn" class="btn btn-outline-primary">회원탈퇴</button>
                </div>
				</form>
			</div>
		</div>
	

<%@ include file="/WEB-INF/views/include/footer.jsp" %>