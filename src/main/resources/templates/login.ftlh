<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign In</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }

        #login-container {
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
    <div id="login-container">
        <p>Login</p>
        <form action="/login" method="post">
            <label>User Name: <input type="text" name="username" required /></label>
            <label>Password: <input type="password" name="password" required /></label>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="submit" value="Sign In" />
        </form>
        <div id="messageDiv"></div>
        <a href="/registration">Create new account</a>
    </div>
    <script>
        function getParameterByName(name, url) {
            if (!url) url = window.location.href;
            name = name.replace(/[\[\]]/g, '\\$&');
            let regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
                results = regex.exec(url);
            if (!results) return null;
            if (!results[2]) return '';
            return decodeURIComponent(results[2].replace(/\+/g, ' '));
        }

        let errorValue = getParameterByName('error');
        if (errorValue === 'true') {
            let errorMessage = document.createElement('p');
            errorMessage.textContent = 'User not found';
            errorMessage.style.color = 'red';

            let errorDiv = document.getElementById('messageDiv');
            errorDiv.appendChild(errorMessage);
        }
    </script>
</body>

<footer>
    <a href="https://github.com/sourcels/spring-pizza" target="_blank">Project on GitHub</a>
</footer>
</html>