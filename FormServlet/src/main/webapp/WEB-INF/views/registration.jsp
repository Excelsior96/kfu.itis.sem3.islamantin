<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
        <script type="text/javascript" src="/javascript/inputchecker.js"></script>
    </head>
    <body>
        <div>
            <h3>Registration</h3>
            <h4>${text}</h4>
            <form action="<c:url value='/reg'/>" method="POST">
                <table>
                       <tr>
                           <p id="inc1"></p><p><input type="textarea" name="email" placeholder="email" id="input1" value=""></p>
                        </tr>
                        <tr>
                            <p id="inc2"></p><p><input type="textarea" name="password" placeholder="password" id="input2" value=""></p>
                        </tr>
                        <tr>
                            <p><input type="radio" name="gender" value="male" checked>М
                                <input type="radio" name="gender" value="female">Ж
                                <input type="radio" name="gender" value="other">Другое</p>
                        </tr>
                        <tr>
                            <p><input type="checkbox" name="subscription" value="true">Подписаться на новости</p>
                            <input type="submit" id="submit">                     
                        </tr>
                </table>
                
            </form>
                <form action="<c:url value='/form'/>" method="GET">
                    <input type="submit" name="authentification" value="Log in">
                </form>
        </div>
    </body>
</html>
