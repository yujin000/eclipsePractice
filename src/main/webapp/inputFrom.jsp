<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
*{
	text-align:center;
}
</style>
<body>
<form action="input.message">
	<table border =1 align= center>
		
		<tr>
			<th>Input Message
		</tr>
		<tr>
			<td><input type= text name =writer placeholder = "Input your name"></td>
		</tr>
		<tr>
			<td><input type= text name =msg placeholder = "Input your msg"></td>
		</tr>
		<tr>
			<td><button>send</button><a href="index.html"><button type=button>Back</button></a>
			</td>
		</tr>
	</table>
	</form>
</body>
</html>