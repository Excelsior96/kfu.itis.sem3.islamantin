<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Users Table</title>
	<style>
		tr.s{
			float: top;
			margin-bottom: 20px;
			background-color: green;
			color: white;
			padding: 10px;
		}
	</style>
</head>
<body>
<table>
	<tr style="font-weight: bold;">
		<p>
		<td>E-mail</td>
		<td>Password</td>
		<td>Gender</td>
		<td>Newsletting</td>
		</p>
	</tr>
	<tr class="s">
		<c:forEach var="i" begin="0" end="${end}">
			<c:forTokens items="${email}${i},${password}${i},${gender}${i},${newsletter}${i},${about}${i}" delims="," var="value">
				<td><c:out value="${value}"/><p></td>
			</c:forTokens>
		</c:forEach>
	</tr>
</table>
</body>
</html>