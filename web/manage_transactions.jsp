<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Transactions</title>
    </head>
    <body>
        <h1>View All Transactions</h1>
        
        <table>
            <tr>
                <th>Transaction ID:</th>
                <th>Account Number:</th>
                <th>Transaction Amount:</th>
                <th>Transaction Date:</th>
            </tr>
            <c:forEach var="transaction" items="${allTransactions}">
                <tr>
                    <td>${transaction.transactionId}</td>
                    <td>${transaction.accountNumber}</td>
                    <td>${transaction.transactionAmount}</td>
                    <td>${transaction.transactionDate}</td>
                </tr>
            </c:forEach>
        </table>
        
        <form action="main_menu.jsp" method="get">
            <button type="submit">Return to Main Menu</button>
        </form>
    </body>
</html>