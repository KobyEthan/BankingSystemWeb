<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>CIS 640 Project Main Menu</title>
</head>
<body>
    <h1>Main Menu</h1>
    <h2>Banking Company Name</h2>
    <p>Welcome ${customer.firstName} ${customer.lastName}</p><br>
    
    <% 
    Boolean allUsersPower = (Boolean) session.getAttribute("allUsersPower");
    Boolean viewAccounts = (Boolean) session.getAttribute("viewAccounts");
    Boolean editAccounts = (Boolean) session.getAttribute("editAccounts");
    Boolean createAccounts = (Boolean) session.getAttribute("createAccounts");
    Boolean viewTransactions = (Boolean) session.getAttribute("viewTransactions");
    Boolean transferFunds = (Boolean) session.getAttribute("transferFunds");
    Boolean viewCustomers = (Boolean) session.getAttribute("viewCustomers");
    Boolean editCustomers = (Boolean) session.getAttribute("editCustomers");
    Boolean createCustomers = (Boolean) session.getAttribute("createCustomers");
    Boolean createUserRoles = (Boolean) session.getAttribute("createUserRoles");
    
    if (viewAccounts && viewTransactions) {
    %>
        <form action = "displayAccounts" method="get">
            <input type="hidden" name="customerId" value="${customer.customerId}">
            <button type="submit">Manage Your Accounts</button>
        </form> <br>
        
    <%} if(allUsersPower && viewTransactions){%>
    <form action="manageTransactions" method="get">
        <button type="submit">View all Transactions</button>
    </form> <br>
    
    <% } if (transferFunds) { %>
        <form action="transferFunds" method="get">
            <input type="hidden" name="customerId" value="${customer.customerId}">
            <button type="submit">Transfer Funds</button>
        </form> <br>
       
    <% } if (allUsersPower && viewCustomers && editCustomers && createCustomers && editAccounts && createAccounts) { %>
    
        <form action = "manageCustomers" method="get">
            <button type="submit">Manage Customers</button>
        </form> <br>
        
        <form action = "manageAccounts" method="get">
            <button type="submit">Manage Accounts</button>
        </form> <br>
        
        
    <% } if (createUserRoles) { %>
        
        <form action = "manageUserRoles" method="get">
            <button type="submit">Manage User Roles</button>
        </form> <br>
    <% } %>
    
    
    <form action="logout" method="post">
        <button type="submit">Logout</button>
    </form>
</body>
</html>