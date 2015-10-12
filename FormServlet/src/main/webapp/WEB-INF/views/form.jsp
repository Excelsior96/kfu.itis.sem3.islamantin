<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
    </head>
    <body>
        <div>
            <h1><% ${text}%></h1>
            <form action="" method="POST">
                <table>
                       <tr>
                            <p><input type="textarea" name="email" placeholder="email"></p>
                        </tr>
                        <tr>
                            <p><input type="textarea" name="password" placeholder="password"></p>
                        </tr>
                        <tr>
                            <p><input type="radio" name="gender" value="male" checked>М
                                <input type="radio" name="gender" value="female">Ж</p>
                        </tr>
                        <tr>
                            <p><input type="checkbox" name="newsletter" value="off">Подписаться на новости</p><input type="submit">
                        </tr>
                </table>
            </form>
        </div>
    </body>
</html>
