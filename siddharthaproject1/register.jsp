<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="register" method="post">
    <label for="phoneNumber">Enter Number:</label>
    <input type="text" id="phoneNumber" name="phoneNumber" required>
    
    <label for="password">Enter Password:</label>
    <input type="password" id="password" name="password" required>
    
    <label for="name">Enter Name:</label>
    <input type="text" id="name" name="name" required>
    
    <button type="submit">Register</button>
</form>

</body>
</html>