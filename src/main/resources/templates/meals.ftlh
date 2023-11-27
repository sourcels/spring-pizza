<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Meals</title>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="create_dialog.js"></script>
</head>
<body>
<div id="dialog-form" title="Add Meal">
    <h2>Add New Meal</h2>

    <form action="/meals/create" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" maxlength="64" required><br>

        <label for="description">Description:</label>
        <input type="text" id="description" name="description"><br>

        <label for="price">Price:</label>
        <input type="number" id="price" name="price" min="0" step="1" required><br>

        <label for="category">Category:</label>
        <input type="text" id="category" name="category" maxlength="64" required><br>

        <button type="submit">Add Meal</button>
    </form>
</div>
<h1>List of Meals</h1>
<button type="button" id="addMealButton">Add New Meal</button>
<#if meals?size == 0>
    No meals!
<#else>
    <table border="1">
        <thead>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Category</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <#list meals as meal>
            <tr>
                <td>${(meal.name?has_content)?then(meal.name, ' - ')}</td>
                <td>${(meal.description?has_content)?then(meal.description, ' - ')}</td>
                <td>${(meal.price?has_content)?then(meal.price, 0)}</td>
                <td>${(meal.category?has_content)?then(meal.category, ' - ')}</td>
                <td>
                    <form action="/meals/delete" method="post">
                        <input type="hidden" name="meal_id" value="${meal.meal_id}">
                        <button type="submit">Delete</button>
                    </form>
                    <form action="/meals/${meal.meal_id}" method="get">
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