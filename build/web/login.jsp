
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>    

</head>
<body>
    <h1>Login</h1>
        <c:if test="${not empty errorMessage}">
            <p style="color: red;">${errorMessage}</p>
        </c:if>
        <form action="login" method="post"> 
            <input type="hidden" name="option" value="add">
            <label class="pad_top">UserID: </label>
            <input type="text" name="userId" required><br>
            <label class="pad_top">Password: </label>
            <input type="password" name="password" required><br>
            <button type="submit">Login</button>
        </form>
</body>
</html>