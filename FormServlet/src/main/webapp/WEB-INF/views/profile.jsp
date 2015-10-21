<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Profile</title>
  <script type="text/javascript" src="/WEB-INF/javascript/counter.js"></script>
</head>
<body>
<div>
  <p><%${text}%></p>
  <img src="http://findicons.com/files/icons/1580/devine_icons_part_2/128/account_and_control.png" alt="No avatar">
  <h2>*<%${email}%></h2>
  <h4><%${gender}%></h4>
  <p><b>About me:</b></p>
  <p><%${about}%></p>
</div><br><p>Update:</p>
<form action="" method="POST">
  <div>
    <p><textarea name="about" rows="5" cols="45" style="resize: none"></textarea></p>
    <p><input type="submit" id="button"> <span id="counter">0</span>/200</p>
  </div>
</form>
</body>
</html>