<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Meals</title>
</head>
<body>

<h1>List of Meals</h1>

<!-- Список всех блюд -->
<ul>
    <#list meals as meal>
        <li>${meal.name} - ${meal.description} - ${meal.price} - ${meal.category}</li>
    </#list>
</ul>

<!-- Форма для добавления нового блюда -->
<form action="/meals/create" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br>

    <label for="description">Description:</label>
    <textarea id="description" name="description" required></textarea><br>

    <label for="price">Price:</label>
    <input type="number" id="price" name="price" required><br>

    <label for="category">Category:</label>
    <input type="text" id="category" name="category" required><br>

    <button type="submit">Add</button>
</form>

</body>
</html>