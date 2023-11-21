<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PIZZA</title>
</head>
<body>
<h1>Pizza</h1><hr>
<h4>Pizza list</h4>
<#list pizzerias as product>
    <div>
        <p>${product.name} - ${product.phone} - ${product.address} | <form action="/pizzerias/delete/${product.pizzeria_id}" method="post"><input type="submit" value="Delete"/></form></p>
    </div>
    <#else>
    <h3>No pizzerias!</h3>
</#list><hr>
<h3>New property:</h3>
<form action="/pizzerias/create" method="post">
    Pizza name: <input type="text" name="name"/><br><br>
    Pizza phone: <input type="text" maxlength="13" name="phone"/><br><br>
    Pizza address: <input type="text" name="address"/><br><br>
    <button type="submit">Add</button>
</form>
</body>
</html>