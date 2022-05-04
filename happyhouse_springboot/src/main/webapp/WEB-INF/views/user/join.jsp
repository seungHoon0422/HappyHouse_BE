<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

	<script type="text/javascript">
		$(document).ready(function() {

			var isId = false ; 
			// 아이디 중복 검사 
			$("#userid").keyup(function(){
				var ckid = $("#userid").val();
				$.ajax({
					url : '${root}/user/idcheck',
					data: {'ckid' : ckid },
					type :'GET',
					dataType: 'json',
					success:function(response){
						var cnt = response.idcount;
					 	if(cnt == 0) {
                    		$("#idresult").text(ckid + "는 사용가능한 아이디 입니다.").removeClass('text-dark').removeClass('text-danger').addClass('text-primary');
                    		isId = true;
                    	} else {
                    		$("#idresult").text(ckid + "는 사용할 수 없는 아이디 입니다.").removeClass('text-dark').removeClass('text-primary').addClass('text-danger');
                    		isId = false;
                    	}
                  	}, 
                  	error: function(request, status, error) {
                  		console.log("status : " + request.status + "\tmsg : " + error);
                  	}
						
					})
				});
			
		//회원가입 버튼 클릭 
		$("#registerBtn").click(function() {
			if(!$("#username").val()){
				alert("이름을 입력해주세요 ! ");
				return;
			}else if (!isId) {
                alert("아이디를 확인하여 주세요 ! ");
                return;
            }else if(!$("#userpass").val()){
				alert("비밀번호를 입력해주세요 ! ");
				return;
			}else if ($("#userpass").val() != $("#pwdcheck").val()) {
                alert("비밀번호를 확인해주세요 ! ");
                return;
			}else{
				$("#phone").val($("#phone1").val() +"-" + $("#phone2").val()+"-" + $("#phone3").val());
				$("#email").val($("#emailid").val() +"@" + $("#emaildomain").val());
		
				$("#registerform").attr("action", "${root}/user/register").submit();
			}
		});
			
		});
	</script>
	
	<body>
		<div class="container text-center mt-4">
			<h4 class="mt-3 mb-5">회원가입</h4>
			<div class="col-lg-8 mx-auto">
				<form id="registerform" class="text-left mb-3" method="post" action="">
				<input type ="hidden" id="email" name="email">
				<input type ="hidden" id="phone" name="phone">
				<input type ="hidden" id="level" name="level" value="${level}">
				
					<!-- <div class="form-group">
					<select class="form-control" id="level" name="level">
						<option value="0">일반 사용자</option>
						<option value="1">중개사무소</option>
					</select>
					</div> -->
					<div class="form-group">
                    <label for="username">이름</label>
                    <input type="text" class="form-control" id="username" name="username" placeholder="이름을 입력하세요">
                </div>
                <div class="form-group">
                    <label for="userid">아이디</label>
                    <input type="text" class="form-control" id="userid" name="userid" placeholder="아이디를 입력하세요">
                    <div id="idresult" class="mt-1"></div>
                </div>
                <div class="form-group">
                    <label for="userpwd">비밀번호</label>
                    <input type="password" class="form-control" id="userpass" name="userpass" placeholder="비밀번호를 입력하세요">
                </div>
                <div class="form-group">
                    <label for="pwdcheck">비밀번호재입력</label>
                    <input type="password" class="form-control" id="pwdcheck" name="pwdcheck" placeholder="비밀번호 확인">
                </div>
                <div class="form-group">
                    <label for="phone1">전화번호</label>
                    <div id="tel" class="custom-control-inline">
                        <select class="form-control" id="phone1" name="phone1">
                            <option value="010">010</option>
                            <option value="02">02</option>
                            <option value="031">031</option>
                            <option value="032">032</option>
                            <option value="041">041</option>
                            <option value="051">051</option>
                            <option value="061">061</option>
                        </select> 
                        <input type="text" class="form-control" id="phone2" name="phone2" placeholder="">
                        <input type="text" class="form-control" id="phone3" name="phone3" placeholder="">
                    </div>
                </div>
                <div class="form-group">
                    <label for="emailid">이메일</label><br>
                    <div id="email" class="custom-control-inline">
                        <input type="text" class="form-control" id="emailid" name="emailid" placeholder="이메일 입력하세요"
                            size="25"> @
                        <select class="form-control" id="emaildomain" name="emaildomain">
                            <option value="naver.com">naver.com</option>
                            <option value="google.com">google.com</option>
                            <option value="daum.net">daum.net</option>
                        </select>
                    </div>
                </div>
                
                <div class="form-group text-center">
                	<button type="button" id="registerBtn" class="btn btn-outline-primary">회원가입</button>
                </div>
				</form>
			</div>
		</div>
	</body>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>