<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${(meal.name?has_content)?then(meal.name, 'Meal')} Details</title>
</head>
<body>

<h1>Meal Details</h1>

<#if meal??>
    <dl>
        <dt>Name:</dt>
        <dd>${(meal.name?has_content)?then(meal.name, ' - ')}</dd>

        <dt>Phone:</dt>
        <dd>${(meal.description?has_content)?then(meal.description, ' - ')}</dd>

        <dt>Address:</dt>
        <dd>${(meal.price?has_content)?then(meal.price, 0)}</dd>

        <dt>Description:</dt>
        <dd>${(meal.description?has_content)?then(meal.description, ' - ')}</dd>

        <dt>Pizzeria ID:</dt>
        <dd>${meal.meal_id}</dd>
    </dl>

    <form action="/meals/delete" method="post">
        <input type="hidden" name="meal_id" value="${meal.meal_id}">
        <button type="submit">Delete Meal</button>
    </form>
<#else>
    <p>Meal not found.</p>
</#if>

<a href="/meals">Back</a>

</body>
</html>