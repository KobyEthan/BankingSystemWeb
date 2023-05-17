<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Customer</title>
</head>
<body>
    <h1>Edit Customer</h1>

    <c:if test="${not empty message}">
        <p>${message}</p>
    </c:if>

    <form method="post" action="editCustomers">
        <input type="hidden" name="customerId" value="${customerToUpdate.customerId}"/>
        
        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" name="firstName" required value="<c:out value="${customerToUpdate.firstName}"/>"><br><br>

        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName" required value="<c:out value="${customerToUpdate.lastName}"/>"><br><br>

        <p>This field must be 10 numbers long</p>
        <label for="phoneNumber">Phone Number:</label>
        <input type="tel" id="phoneNumber" name="phoneNumber" pattern="[0-9]{10}" required value="<c:out value="${customerToUpdate.phoneNumber}"/>"><br><br>

        <label for="userId">UserID:</label>
        <input type="text" id="userId" name="userId" required value="<c:out value="${customerToUpdate.userId}"/>"><br><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required value="<c:out value="${customerToUpdate.password}"/>"><br><br>

        <label for="roleType">Role Type:</label>
        <select id="roleType" name="roleType">
            <option value="User" <c:if test="${customerToUpdate.roleType == 'User'}">selected</c:if>>User</option>
            <option value="Management" <c:if test="${customerToUpdate.roleType == 'Management'}">selected</c:if>>Management</option>
            <option value="Administrator" <c:if test="${customerToUpdate.roleType == 'Administrator'}">selected</c:if>>Administrator</option>
        </select><br><br>

        <input type="submit" value="Save">
    </form>

    <form action="main_menu.jsp" method="get">
        <button type="submit">Return to Main Menu</button>
    </form>

</body>
</html>