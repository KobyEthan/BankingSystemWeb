<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accounts</title>
    </head>
    <body>
        <h1>Accounts for ${customer.firstName} ${customer.lastName}</h1>
        <table>
            <tr>
                <th>Account Number: </th>
                <th>Customer ID: </th>
                <th>Account Type </th>
                <th>Date Opened: </th>
                <th>Balance: </th>
                
                <th></th>
          <c:forEach var="account" items="${accounts}">
                <tr>
                        <td>${account.accountNumber}</td>
                        <td>${account.customerId}</td>
                        <td>${account.accountType}</td>
                        <td>${account.dateOpened}</td>
                        <th>${account.balance}</td>

                        <td>
                            <form action="displayTransactions" method="get">
                                <input type="hidden" name="accountNumber" value="${account.accountNumber}">
                                <input type = "hidden" name ="option" value="${account.accountName}">
                                <input type="submit" value="View Transactions"/>
                            </form>
                        </td>
                </tr>
            </c:forEach>
        </table>
                    
        </form>
        
    <h2>Create a New Account</h2>
    <form action="manageAccounts" method="post">
        <input type="hidden" name="action" value="create" />
        
        <label for="accountNumber">Account Number:</label>
        <input type="text" id="accountNumber" name="accountNumber" required /><br><br>
        
        <label for="customerId">Customer ID:</label>
        <input type="text" id="customerId" name="customerId" value="${customer.customerId}" required readonly /><br><br>
        
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
        
        <form action="main_menu.jsp" method="get">
            <input type="submit" value="Return to Main Menu"/>
        </form>
    </body>
</html>


