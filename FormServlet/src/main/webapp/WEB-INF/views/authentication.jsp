<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Authentication</title>
    <script type="text/javascript" src="/WEB-INF/javascript/inputchecker.js"></script>
</head>
<body>
<div style="float:right">
    <h3>Authentication</h3>
    <h4><%${text}%></h4>
    <form action="" method="POST">
        <table>
            <tr>
                <p id="inc1"></p><p><input type="textarea" name="email" placeholder="email" id="input1"></p>
            </tr>
            <tr>
                <p id="inc2"></p><p><input type="textarea" name="password" placeholder="password" id="input2"></p>
            </tr>
            <tr>
                <p><input type="checkbox" name="remember" value="off">Запомнить меня</p>
                <input type="submit" id="submit">
                <input type="submit" name="registration" value="Sign up">
            </tr>
        </table>
    </form>
</div>
</body>
</html>
