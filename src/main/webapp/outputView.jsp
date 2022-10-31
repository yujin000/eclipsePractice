<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	EL : Expression Language : MV2 구조에서 Servlet을 거쳐 넘어온 자바 데이터를 JSP에 표현하기
	위한 표기법 JSTL : Java Standard Tag Library : EL이 가지지 못한 기본적인 기능을 지원하는
	태그형식의 문법 라이브러리
	<table border=1>
		<tr>
			<th colspan=3>Message List
		</tr>
		<tr>
			<th>Seq
			<th>Writer
			<th>msg
		</tr>
		<c:choose>
			<c:when test="${not empty list}">
				<c:forEach var="i" items="${list }">
					<tr>
						<td>${i.seq }
						<td>${i.writer }
						<td>${i.message }
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<th colspan=3>출력할 내용이 없습니다.
				</tr>
			</c:otherwise>
		</c:choose>
		<tr>
			<td colspan=3>
				<form action="DeleteServlet">
					<input type=text name=delSeq placeholder="Input seq to delete">
					<button>Delete</button>
				</form>
		</tr>
		<tr>
			<td colspan=3>
				<form action="UpdateServlet">
					<input type=text name=seq placeholder="Input seq to Modify"><br>
					<input type=text name=writer placeholder="Input writer to Modify"><br>
					<input type=text name=msg placeholder="Input msg to Modify">
					<button>Modify</button>
			</form>
		</tr>
		<tr>
			<td colspan=3 align=center><a href="index.html"><button>Back</button></a>
		</tr>
	</table>
</body>
</html>