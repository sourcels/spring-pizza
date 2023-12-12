<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }

        #registration-container {
            max-width: 400px;
            margin: auto;
            padding: 20px;
            box-sizing: border-box;
            text-align: center;
        }

        form {
            display: flex;
            flex-direction: column;
        }

        label {
            margin-bottom: 10px;
        }

        input {
            padding: 8px;
            margin-bottom: 15px;
        }

        #messageDiv {
            margin-top: 15px;
        }

        footer {
            margin-top: auto;
            padding: 10px;
            text-align: center;
            background-color: #f0f0f0;
        }
    </style>
</head>
<body>
<div id="registration-container">
    <p>Registration</p>
    <form action="/registration" method="post">
        <div><label> User Name : <input type="text" name="username"/> </label></div>
        <div><label> Password: <input type="password" name="password"/> </label></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <div><input type="submit" value="Sign In"/></div>
    </form>
    <div id="messageDiv">
        <#if message??>
            ${message}
        </#if>
    </div>
</div>
</body>
<footer>
    <a href="https://github.com/sourcels/spring-pizza" target="_blank">Project on GitHub</a>
</footer>
</html>