<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="/resources/js/jquery-3.6.0.js"></script>
<!DOCTYPE html>
<html>
<head>
<title>제이쿼리로 동적으로 생성된 버튼에 이벤트를 달아보자</title>
<script type="text/javascript">
$(function(){
	
	// 첫번째 버튼 이벤트
	$("#firstButton").on("click",function(e){
		let bodyHtml = "<button type='button' id='secondButton'>두번째 버튼(동적생성)</button>";
		//mainDiv 마지막 요소로 추가
		$("#mainDiv").append(bodyHtml);
	});
	
	// 두번째 버튼 이벤트
	// 두번째 버튼 이벤트를 클릭하면 alert("개똥이");를 처리하기
	//$("#secondButton").on("click",function(){
	// alert("개똥이");	
	//})
	
	$(document).on("click","#secondButton",function(){
		alert("개똥이브라더스");
	});
	
});

</script>





<script type="text/javascript">
//쉼표 문자열에서 최대값 구하기???
let c_values = "500,200,600,700,100,300";

console.log(c_values.split(","));

let values = c_values.split(",").map(str => Number(str));
console.log("values : " + values);
console.log("values.length : " + values.length);

// reduce : 배열.reduce((누적값, 현재값, 인덱스, 요소))
// -Infinity : 초기값을 제공하지 않을 경우 배열의 첫 번째 요소를 사용함
// 500, 200, 600, 700, 100, 300
let max = values.reduce(function(a,b){
   return Math.max(a,b);
}, -Infinity);

console.log("max : " + max);

	
	
	
</script>






</head>
<body>
	<div id="mainDiv">
		<button type="button" id="firstButton">첫번째 버튼</button>
	</div>
<script type="text/javascript">
	let key2 = sessionStorage.getItem("key2");
	
	console.log("key2 : " + key2);
</script>	
	
	 
</body>
</html>