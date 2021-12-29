<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error 500</title>
</head>
<body>
<div style="font-size: 24px">Request from ${pageContext.errorData.requestURI} is failed</div><br/>
<div style="font-size: 20px">Servlet name: ${pageContext.errorData.servletName} </div><br/>
<hr/>
<div style="font-size: 14px">Exception: ${pageContext.exception}</div><br/>
<hr/>
<div style="font-size: 14px">Message from exception: ${pageContext.exception}</div>
<br>
<a href="${pageContext.request.contextPath}/index.jsp">Back to homepage</a>
</body>
</html>
