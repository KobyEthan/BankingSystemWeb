<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit User Role</title>
</head>
<body>
    <h1>Edit User Role</h1>
    <form action="editUserRoles" method="post">
            <c:if test="${not empty message}">
        <p>${message}</p>
            </c:if>

        <label>Permissions:</label><br>
        
        <label for="allUsersPower">All Users Power:</label>
        <input type="checkbox" id="allUsersPower" name="allUsersPower" ${userRoleToUpdate.allUsersPower ? 'checked' : ''} /><br>
        
        <label for="viewAccounts">View Accounts:</label>
        <input type="checkbox" id="viewAccounts" name="viewAccounts" ${userRoleToUpdate.viewAccounts ? 'checked' : ''} /><br>
        
        <label for="editAccounts">Edit Accounts:</label>
        <input type="checkbox" id="editAccounts" name="editAccounts" ${userRoleToUpdate.editAccounts ? 'checked' : ''} /><br>
        
        <label for="createAccounts">Create Accounts:</label>
        <input type="checkbox" id="createAccounts" name="createAccounts" ${userRoleToUpdate.createAccounts ? 'checked' : ''} /><br>
        
        <label for="viewTransactions">View Transactions:</label>
        <input type="checkbox" id="viewTransactions" name="viewTransactions" ${userRoleToUpdate.viewTransactions ? 'checked' : ''} /><br>
        
        <label for="transferFunds">Transfer Funds:</label>
        <input type="checkbox" id="transferFunds" name="transferFunds" ${userRoleToUpdate.transferFunds ? 'checked' : ''} /><br>
        
        <label for="viewCustomers">View Customers:</label>
        <input type="checkbox" id="viewCustomers" name="viewCustomers" ${userRoleToUpdate.viewCustomers ? 'checked' : ''} /><br>
        
        <label for="editCustomers">Edit Customers:</label>
        <input type="checkbox" id="editCustomers" name="editCustomers" ${userRoleToUpdate.editCustomers ? 'checked' : ''} /><br>
        
        <label for="createCustomers">Create Customers:</label>
        <input type="checkbox" id="createCustomers" name="createCustomers" ${userRoleToUpdate.createCustomers ? 'checked' : ''} /><br>
        
        <label for="createUserRoles">Create User Roles:</label>
        <input type="checkbox" id="createUserRoles" name="createUserRoles" ${userRoleToUpdate.createUserRoles ? 'checked' : ''} /><br><br>
        
        <button type="submit">Update User Role</button>
        
    </form><br><br>
    
        <form action="main_menu.jsp" method="get">
            <input type="submit" value="Return to Main Menu"/>
        </form>
    
</body>
</html>