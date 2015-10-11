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
                    <td>
                        <tr>
                            <p><input type="textarea" name="e-mail" placeholder="e-mail"></p>
                        </tr>
                        <tr>
                            <p><input type="textarea" name="password" placeholder="password"></p>
                        </tr>
                        <tr>
                            <p><input type="radio" name="gender" value="m" checked>М
                                <input type="radio" name="gender" value="f">Ж</p>
                        </tr>
                        <tr>
                            <p><input type="checkbox" name="newsletter">Подписаться на новости</p><input type="submit">
                        </tr>
                    </td>
                </table>
            </form>
        </div>
    </body>
</html>
