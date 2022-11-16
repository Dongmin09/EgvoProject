<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>Agent 목록 보기</title>
<h1>Agent 목록 보기</h1>
</head>
<body>
	<table border="1">
		<tr>
			<td>회원 아이디</td>
			<td>회원 이름</td>
			<td>직장</td>
			<td>수수료</td>
			<td>전화번호</td>		
			<td>국적</td>		
		</tr>
		 <c:forEach var="agentsVO" items="${agentsVOList}">
		<tr>
			<td><a href="/agents/Agdetail?agentCode=${agentsVO.agentCode}">${agentsVO.agentCode}</a> </td>
			<td>${agentsVO.agentName}</td>
			<td>${agentsVO.workingArea}</td>
			<td>${agentsVO.commission}</td>
			<td>${agentsVO.phoneNo}</td>		
			<td>${agentsVO.country}</td>		
		</tr>
		 </c:forEach>
	</table>
	<a href="/agents/Agwrite" >등록</a>

</body>
</html>