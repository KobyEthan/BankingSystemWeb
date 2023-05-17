<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Management</title>
</head>
<body>
    <h1>Customer Management</h1>

    <%-- Display any messages if they exist --%>
    <c:if test="${not empty message}">
        <p>${message}</p>
    </c:if>
        
    <% String message = request.getParameter("message"); %>

        <% if ("success".equals(message)) { %>
             <div class="alert alert-success" role="alert">
             Success! You have created a new customer.
            </div>
        <% } %>

        <style>
            .alert-success {
                color: blue;
            }
        </style>

    <h2>View All Customers</h2>
    <table>
        <thead>
            <tr>
                <th>Customer ID:</th>
                <th>First Name:</th>
                <th>Last Name:</th>
                <th>Role Type:</th>
                <th>Phone Number:</th>

            </tr>
        </thead>
        <tbody>
            <c:forEach var="customer" items="${customers}">
                <tr>
                    <td>${customer.customerId}</td>
                    <td>${customer.firstName}</td>
                    <td>${customer.lastName}</td>
                    <td>${customer.roleType}</td>
                    <td>${customer.phoneNumber}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

        <h2>Create a New Customer</h2>
        <form method="post" action="manageCustomers">
            <input type="hidden" name="action" value="create"/>

            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" name="firstName" required><br><br>

            <label for="lastName">Last Name:</label>
            <input type="text" id="lastName" name="lastName" required><br>
            
            <p>This field must be 10 numbers long</p>
            <label for="phoneNumber">Phone Number:</label>
            <input type="tel" id="phoneNumber" name="phoneNumber" pattern="[0-9]{10}" required><br><br>
            
            <label for="userId">UserID:</lable>
            <input type = "text" id ="userId" name="userId" required><br><br>
            
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required><br><br>

            <label for="roleType">Role Type:</label>
            <select id="roleType" name="roleType">
                <option value="User">User</option>
                <option value="Management">Management</option>
                <option value="Administrator">Administrator</option>
            </select><br><br>

            <button type="submit">Create Customer</button>
        </form>
        
        <h2>Edit a Customer</h2>
        <form method="get" action="editCustomers">
            <label for="customerId">Select a customer:</label>
            <select id="customerId" name="customerId">
                <c:forEach var="customer" items="${customers}">
                    <option value="${customer.customerId}">${customer.customerId}</option>
                </c:forEach>
            </select><br><br>
            <button type="submit">Edit Customer</button>
        </form>
            
        <h2>Delete a Customer</h2>
        
        <form method="post" action="manageCustomers">
                <input type="hidden" name="action" value="delete"/>
            <label for="customerId">Select a customer:</label>
            <select id="customerId" name="customerId">
                <c:forEach var="customer" items="${customers}">
                 <option value="${customer.customerId}">${customer.customerId}</option>
                </c:forEach>
            </select><br><br>
            <button type="submit">Delete Customer</button>
        </form> <br><br>
        
        <form action="main_menu.jsp" method="get">
            <input type="submit" value="Return to Main Menu"/>
        </form>
</body>
</html>