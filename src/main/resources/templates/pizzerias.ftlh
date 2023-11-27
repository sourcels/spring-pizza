<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pizzerias</title>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="create_dialog.js"></script>
</head>
<body>
<div id="dialog-form" title="Add Pizzeria">
    <h2>Add New Pizzeria</h2>

    <form action="/pizzerias/create" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" maxlength="64" required><br>

        <label for="phone">Phone:</label>
        <input type="text" id="phone" name="phone" maxlength="12" required><br>

        <label for="address">Address:</label>
        <input type="text" id="address" name="address" maxlength="128" required><br>

        <label for="description">Description:</label>
        <input type="text" id="description" name="description"><br>

        <button type="submit">Add Pizzeria</button>
    </form>
</div>

<h1>List of Pizzerias</h1>

<button type="button" id="addPizzeriaButton">Add New Pizzeria</button>

<#if pizzerias?size == 0>
    No pizzerias!
<#else>
<table border="1">
    <thead>
    <tr>
        <th>Name</th>
        <th>Phone</th>
        <th>Address</th>
        <th>Description</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <#list pizzerias as pizzeria>
        <tr>
            <td>${(pizzeria.name?has_content)?then(pizzeria.name, ' - ')}</td>
            <td>${(pizzeria.phone?has_content)?then(pizzeria.phone, ' - ')}</td>
            <td>${(pizzeria.address?has_content)?then(pizzeria.address, ' - ')}</td>
            <td>${(pizzeria.description?has_content)?then(pizzeria.description, ' - ')}</td>
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
</body>
</html>