<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="/resources/js/jquery-3.6.0.js"></script>
<script type="text/javascript" src="/resources/ckeditor/ckeditor.js"></script>
<!DOCTYPE html>
<html>
<head>
<title>Agent 목록 보기</title>
<h1>Agent 목록 보기</h1>
<script type="text/javascript">
	$(function() {
		let str = "${agentsVO}";
		console.log("str : " + str);
	});
</script>
</head>
<body>
	
	<%-- <input type="text" name="agentCode" value="${agentsVO.agentCode}"> --%>
	<table border="1">
		<tr>
			<td>회원 아이디</td>
			<td>${AgentsVO.agentCode}</td>	
		</tr>
		<tr>
			<td>회원 이름</td>
			<td>${AgentsVO.agentName}</td>
		</tr>
		<tr>
			<td>직장</td>
			<td>${AgentsVO.workingArea}</td>	
		</tr>
		<tr>
			<td>수수료</td>
			<td>${AgentsVO.commission}</td>	
		</tr>
		<tr>
			<td>전화번호</td>
			<td>${AgentsVO.phoneNo}</td>		
		</tr>
		<tr>
			<td>국적</td>	
			<td>${AgentsVO.country}</td>	
		</tr>
	</table>
	<a href="/agents/Agwrite" >등록</a>

</body>
</html>