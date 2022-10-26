<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>User</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
            crossorigin="anonymous">
    </head>
    <body>
        <h3>Do you really want to delete this user?</h3>
        <table>
            <tr>
                <td>${user.get("id")}</td>
                <td>${user.get("firstName")}</td>
                <td>${user.get("lastName")}</td>
                <td>${user.get("email")}</td>
            </tr>
        </table>
        <form action="/users/delete?id=${user.get("id")}" method="post">
            <button type="submit" class="btn btn-danger">Delete this user</button>
        </form>
    </body>
</html>
<!-- END -->
