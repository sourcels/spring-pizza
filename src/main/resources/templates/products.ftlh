<!DOCTYPE html>
<html>
<head>
    <title>PIZZA</title>
</head>
<body>
<h1>Pizza</h1><hr>
<h4>Pizza list</h4>
<#list products as product>
    <div>
        <p>${product.name} - ${product.phone} - ${product.address} | <form action="/product/delete/${product.id}" method="post"><input type="submit" value="Delete"/></form></p>
    </div>
</#list><hr>
<h3>New property:</h3>
<form action="/product/create" method="post">
    Pizza name: <input type="text" name="name"/><br><br>
    Pizza phone: <input type="number" name="phone"/><br><br>
    Pizza address: <input type="text" name="address"/><br><br>
    <input type="submit" value="Add"/>
</form>
</body>
</html>