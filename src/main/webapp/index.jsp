<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>MC ForestMed</title>
</head>
<body style="background: aqua"  >
    <h1><%= "Welcome to \"ForestMedClinic\" " %></h1>
    <p>Today <%= new java.util.Date() %></p>
    <br/>
    <h3><%= "Please, input your login and password :" %></h3>
    <br/>
  login:  <input type="email" name="email">
    <br/>
 password   <input type="password" name="password" minlength="6">
     <input type="button" value="push">
    <br/>
    <a href="hello-servlet">Registration</a>

</body>
</html>