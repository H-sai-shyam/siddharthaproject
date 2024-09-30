<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="login" method="POST">
    <label for="phoneNumber">Enter Number:</label>
    <input type="text" id="phoneNumber" name="phoneNumber" required>
    
    <label for="password">Enter Password:</label>
    <input type="password" id="password" name="password" required>
    
    <button type="submit">Login</button>
    <a href="register.jsp">Register instead</a>
</form>

</body>
</html>