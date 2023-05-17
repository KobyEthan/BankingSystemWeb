<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transfer Funds</title>
    </head>
       <% String message = (String) session.getAttribute("message");
            if (message != null) { %>
            <div><%= message %></div>
        <% } %>
    <body>
        
        <h1>Transfer Funds for ${customer.firstName} ${customer.lastName}</h1>
        
        <form action="transferFunds" method="get">
            <label for="fromAccount">From Account:</label>
    <select id="fromAccount" name="fromAccount">
        <c:if test="${not empty accounts}">
            <c:forEach var="account" items="${accounts}">
                <option value="${account.accountNumber}">${account.accountNumber}</option>
            </c:forEach>
        </c:if>
    </select><br><br>


    <label for="toAccount">To Account:</label>
    <select id="toAccount" name="toAccount">
        <c:if test="${not empty accounts}">
            <c:forEach var="account" items="${accounts}">
                <option value="${account.accountNumber}">${account.accountNumber}</option>
            </c:forEach>
        </c:if>
    </select><br><br>

<form action="transferFunds" method="post">
    <label for="transferAmount">Amount:</label>
    <input type="text" id="transferAmount" name="transferAmount"><br><br>
    <button type="submit">Transfer</button> 
</form>
        
        <form action="main_menu.jsp" method="get">
            <button type="submit">Return to Main Menu</button>
        </form>
    </body>
</html>