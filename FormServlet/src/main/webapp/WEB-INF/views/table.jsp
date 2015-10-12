<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Users Table</title>
		<style>
			tr.s{
				float: up;
				margin-bottom: 20px;
				background-color: green;
				padding: 10px;
			}
		</style>
	</head>
	<body>
            <table>
                <tr style="font-weight: bold;">
                    <td>E-mail</td>
                    <td>Password</td>
                    <td>Gender</td>
                    <td>Newsletting</td>
                </tr>
                <tr class="s">
                    <c:forTokens items="${email},${password},${gender},${newsletter}" delims="," var="value">

                        <td><c:out value="${value}"/><p></td>

                     </c:forTokens>
                 </tr>
            </table>
	</body>
</html>