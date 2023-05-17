<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Account</title>
    </head>
    <body>
        <h1>Edit Account</h1>
        <form method="post" action="editAccounts">
            <table>
                <tr>
                    <td>Account Type:</td>
                    <td><select id="accountType" name="accountType" required>
                        <option value="">-- Select Account Type --</option>
                        <option value="Asset" <c:if test="${accountToUpdate.accountType == 'Asset'}">selected</c:if>>Asset</option>
                        <option value="Liability" <c:if test="${accountToUpdate.accountType == 'Liability'}">selected</c:if>>Liability</option>
                        </select> 
                        <td>
                </tr>
                <tr>
                    <td>Account Name:</td>
                    <td><input type="text" name="accountName" value="${accountToUpdate.accountName}" required></td>
                </tr>
            </table>
            <br>
            <input type="submit" value="Save Changes">
        </form>
                
    <form action="main_menu.jsp" method="get">
        <button type="submit">Return to Main Menu</button>
    </form>
    </body>
</html>