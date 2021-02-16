<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h2>Login</h2>
        <form method="post" action="login">
            <label>Username:</label><input type="text" name="username" value="${username}"><br>
            <label>Password:</label><input type="password" name="password" value="${password}"><br>
            <button value="login" name="login">Login</button><br>
            <p>${message}</p>
        </form>
    </body>
</html>
