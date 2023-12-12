<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Error Page</title>
    </head>
    <body>
        <h1>Error Page</h1>

        <div>
            <p>Oops! Something went wrong.</p>

            <p>Error message:</p>
            <pre>${(errorMessage?has_content)?then(errorMessage, ' - ')}</pre>

            <p>Error details:</p>
            <pre>${(error?has_content)?then(error, ' - ')}</pre>
        </div>
    </body>
    <footer>
        <a href="https://github.com/sourcels/spring-pizza" target="_blank">Project on GitHub</a>
    </footer>
</html>