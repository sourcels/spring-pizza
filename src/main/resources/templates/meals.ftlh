<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Meals</title>
</head>
<body>

<h1>List of Meals</h1>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Description</th>
        <th>Price</th>
        <th>Category</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <#list meals as meal>
        <tr>
            <td>${meal.mealId}</td>
            <td>${meal.description}</td>
            <td>${meal.price}</td>
            <td>${meal.category}</td>
            <td>
                <form action="/meal/delete" method="post">
                    <input type="hidden" name="mealId" value="${meal.mealId}">
                    <button type="submit">Delete</button>
                </form>
                <form action="/meal/details" method="get">
                    <input type="hidden" name="mealId" value="${meal.mealId}">
                    <button type="submit">Details</button>
                </form>
            </td>
        </tr>
    </#list>
    </tbody>
</table>

<h2>Add New Meal</h2>

<form action="/meal/create" method="post">
    <label for="description">Description:</label>
    <input type="text" id="description" name="description" required><br>

    <label for="price">Price:</label>
    <input type="number" id="price" name="price" min="0" step="0.01" required><br>

    <label for="category">Category:</label>
    <input type="text" id="category" name="category" required><br>

    <button type="submit">Add Meal</button>
</form>

</body>
</html>