<!DOCTYPE html>
<html>
<head>
    <title>Pizzerias</title>
</head>
<body>

<h1>List of Pizzerias</h1>
<#if pizzerias?size == 0>
    No pizzerias!
<#else>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Phone</th>
        <th>Address</th>
        <th>Description</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <#list pizzerias as pizzeria>
        <tr>
            <td>${pizzeria.pizzeria_id}</td>
            <td>${pizzeria.phone}</td>
            <td>${pizzeria.address}</td>
            <td>${pizzeria.description}</td>
            <td>
                <form action="/pizzerias/delete/${pizzeria.pizzeria_id}" method="post">
                    <button type="submit">Delete</button>
                </form>
                <form action="/pizzerias/${pizzeria.pizzeria_id}" method="get">
                    <button type="submit">Details</button>
                </form>
            </td>
        </tr>
    </#list>
    </tbody>
</table>
</#if>
<h2>Add New Pizzeria</h2>

<form action="/pizzerias/create" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br>

    <label for="phone">Phone:</label>
    <input type="text" id="phone" name="phone" required><br>

    <label for="address">Address:</label>
    <input type="text" id="address" name="address" required><br>

    <label for="description">Description:</label>
    <input type="text" id="description" name="description" required><br>

    <button type="submit">Add Pizzeria</button>
</form>

</body>
</html>