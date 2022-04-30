<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- header login 정보 -->  
<c:set value ="${pageContext.request.contextPath}" var="root" ></c:set>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Happy House</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>

<body>

<nav class="navbar navbar-expand-sm bg-light fixed-top">
<div class="container">
<div class="text-center">
		<h3 class="font-weight-bold text-info"><a href="${root}/">Happy house</a></h3>
</div>
<c:if test="${empty userinfo}">
	<ul class="navbar-nav justify-content-end">
	<li class="nav-item"><a class="nav-link text-secondary" href = "${root}/user/userregister">Sign Up</a></li>
	<li class="nav-item"><a class="nav-link text-secondary" href = "${root}/user/mvlogin">Sign In</a></li>
	</ul>
</c:if>
<c:if test="${!empty userinfo}">
	<ul class="navbar-nav justify-content-end">
	<li class="nav-item"><a class="nav-link text-secondary" href = "${root}/user?act=userList">회원정보</a></li>
	<li class="nav-item"><a class="nav-link text-secondary" href = "${root}/interest?act=interestList">관심목록</a></li>
	<c:if test="${userinfo.level ==0}">
	<li class="nav-item"><a class="nav-link text-secondary" href="${root}/house?act=mvDealList">매매 목록</a></li>
	</c:if>
	<c:if test="${userinfo.level ==1}">
	<li class="nav-item"><a class="nav-link text-secondary" href="${root}/house?act=mvDealList">매매 등록</a></li>
	</c:if>
	<li class="nav-item"><a class="nav-link text-secondary" href = "${root}/user/logout">LogOut</a></li>
	</ul>
</c:if>
</div>
</nav>
 <div style="height:130px"> </div>