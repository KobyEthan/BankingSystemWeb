<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="domain.Transaction" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Transactions</title>
    </head>
    <body>
        <h1>Transactions for account number ${account.accountNumber}</h1>
        <table>
            <tr>
                <th>Transaction ID:</th>
                <th>Account Number:</th>
                <th>Description:</th>    
                <th>Transaction Date:</th>
                <th>Amount:</th>

            </tr>
            <c:forEach var="transaction" items="${transactions}">
                <tr>
                    <td>${transaction.transactionId}</td>
                    <td>${transaction.accountNumber}</td>
                    <td>${transaction.description}</td>
                    <td>${transaction.transactionDate}</td>
                    <td>${transaction.transactionAmount}</td>
                    
                </tr>
            </c:forEach>
        </table>
        
    <form action = "displayAccounts" method="get">
        <input type="hidden" name="customerId" value="${customer.customerId}">
        <button type="submit">Return to Accounts</button>
    </form>
    </body>
</html>