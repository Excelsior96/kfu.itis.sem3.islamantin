<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
        <script type="text/javascript" src="/WEB-INF/javascript/inputchecker.js"></script>
    </head>
    <body>
        <div>
            <h3>Registration</h3>
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
                            <p><input type="radio" name="gender" value="male" checked>М
                                <input type="radio" name="gender" value="female">Ж</p>
                        </tr>
                        <tr>
                            <p><input type="checkbox" name="newsletter" value="off">Подписаться на новости</p>
                            <input type="submit" id="submit">
                            <input type="submit" name="authentification" value="Log in">
                        </tr>
                </table>
            </form>
        </div>
    </body>
</html>
