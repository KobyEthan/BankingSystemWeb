<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Accounts</title>
</head>
<body>
    <h1>Manage Accounts</h1>
    <table>
        <thead>
            <tr>
                <th>Account Number:</th>
                <th>Customer ID:</th>
                <th>Account Type:</th>
                <th>Date Opened:</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${allAccounts}" var="account">
                <tr>
                    <td>${account.accountNumber}</td>
                    <td>${account.customerId}</td>
                    <td>${account.accountType}</td>
                    <td>${account.dateOpened}</td>
                    <td>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
                            
       <% String message = (String) session.getAttribute("message");
            if (message != null) { %>
            <div><%= message %></div>
        <% } %>

    <h2>Create a New Account</h2>
    <form action="manageAccounts" method="post">
        <input type="hidden" name="action" value="create" />
        
        <label for="accountNumber">Account Number:</label>
        <input type="text" id="accountNumber" name="accountNumber" required /><br><br>
        
        <label for="customerId">Customer ID:</label>
        <input type="text" id="customerId" name="customerId" required /><br><br>
        
        <label for="accountType">Account Type:</label>
        <select id="accountType" name="accountType" required>
            <option value="">-- Select Account Type --</option>
            <option value="Asset">Asset</option>
            <option value="Liability">Liability</option>
        </select><br><br>
        
        <label for="dateOpened">Date Opened:</label>
        <input type="date" name="dateOpened" value="<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())) %>">
        
        <button type="submit">Create</button>
    </form>
        
    <h2>Delete Account</h2>   
    <form method="post" action="manageAccounts">
            <input type="hidden" name="action" value="delete"/>
    <label for="accountNumber">Select Account to Delete:</label>
    <select name="accountNumber" id="accountNumber">
        <c:forEach items="${allAccounts}" var="account">
            <option value="${account.accountNumber}">${account.accountNumber}</option>
        </c:forEach>
    </select>
    <br><br>
    <input type="submit" value="Delete Account"/>
</form> <br><br>   
        <form action="main_menu.jsp" method="get">
            <input type="submit" value="Return to Main Menu"/>
        </form>                
</body>
</html>