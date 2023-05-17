<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage User Roles</title>
</head>
<body>
    <h1>Manage User Roles</h1>
    <h2>View all User Roles</h2>
            <table>
            <thead>
                <tr>
                    <th>Role Type:</th>
                    <th>All Users Power:</th>
                    <th>View Accounts:</th>
                    <th>Edit Accounts:</th>
                    <th>Create Accounts:</th>
                    <th>View Transactions:</th>
                    <th>Transfer Funds:</th>
                    <th>View Customers:</th>
                    <th>Edit Customers:</th>
                    <th>Create Customers:</th>
                    <th>Create User Roles:</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${allRoles}" var="role">
                    <tr>
                        <td>${role.roleType}</td>
                        <td>${role.allUsersPower}</td>
                        <td>${role.viewAccounts}</td>
                        <td>${role.editAccounts}</td>
                        <td>${role.createAccounts}</td>
                        <td>${role.viewTransactions}</td>
                        <td>${role.transferFunds}</td>
                        <td>${role.viewCustomers}</td>
                        <td>${role.editCustomers}</td>
                        <td>${role.createCustomers}</td>
                        <td>${role.createUserRoles}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    <h2>Create New User Role</h2>
    <form action="manageUserRoles" method="post">
        <input type="hidden" name="action" value="create" />
        <label for="roleType">Role Type:</label>
        <input type="text" id="roleType" name="roleType" required /><br><br>
        
        <label>Permissions:</label><br>
        
        <label for="allUsersPower">All Users Power:</label>
        <input type="checkbox" id="allUsersPower" name="allUsersPower" /><br>
        
        <label for="viewAccounts">View Accounts:</label>
        <input type="checkbox" id="viewAccounts" name="viewAccounts" /><br>
        
        <label for="editAccounts">Edit Accounts:</label>
        <input type="checkbox" id="editAccounts" name="editAccounts" /><br>
        
        <label for="createAccounts">Create Accounts:</label>
        <input type="checkbox" id="createAccounts" name="createAccounts" /><br>
        
        <label for="viewTransactions">View Transactions:</label>
        <input type="checkbox" id="viewTransactions" name="viewTransactions" /><br>
        
        <label for="transferFunds">Transfer Funds:</label>
        <input type="checkbox" id="transferFunds" name="transferFunds" /><br>
        
        <label for="viewCustomers">View Customers:</label>
        <input type="checkbox" id="viewCustomers" name="viewCustomers" /><br>
        
        <label for="editCustomers">Edit Customers:</label>
        <input type="checkbox" id="editCustomers" name="editCustomers" /><br>
        
        <label for="createCustomers">Create Customers:</label>
        <input type="checkbox" id="createCustomers" name="createCustomers" /><br>
        
        <label for="createUserRoles">Create User Roles:</label>
        <input type="checkbox" id="createUserRoles" name="createUserRoles" /><br><br>
        
        <button type="submit">Create User Role</button>
        
    </form><br><br>
    
<h2>Edit User Role</h2>
<form action="editUserRoles" method="get">
    <label for="roleType">Role Type:</label>
    <select name="roleType" id="roleType">
        <c:forEach items="${allRoles}" var="role">
            <option value="${role.roleType}">${role.roleType}</option>
        </c:forEach>
    </select>
    <button type="submit">Edit User Role</button>
</form>
    
    
    <h2>Delete User Role</h2>
        <form action="manageUserRoles" method="post">
            <input type="hidden" name="action" value="delete">
            <label for="roleType">Role Type:</label>
                <input type="text" id="roleType" name="roleType">
                <button type="submit">Delete</button>
        </form><br>
        
        <form action="main_menu.jsp" method="get">
            <input type="submit" value="Return to Main Menu"/>
        </form>
</body>
</html>