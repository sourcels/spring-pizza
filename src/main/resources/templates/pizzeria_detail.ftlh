<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${(pizzeria.name?has_content)?then(pizzeria.name, 'Pizzeria')} Details</title>
</head>
<body>

<h1>Pizzeria Details</h1>

<#if pizzeria??>
    <dl>
        <dt>Name:</dt>
        <dd>${(pizzeria.name?has_content)?then(pizzeria.name, ' - ')}</dd>

        <dt>Phone:</dt>
        <dd>${(pizzeria.phone?has_content)?then(pizzeria.phone, ' - ')}</dd>

        <dt>Address:</dt>
        <dd>${(pizzeria.address?has_content)?then(pizzeria.address, ' - ')}</dd>

        <dt>Description:</dt>
        <dd>${(pizzeria.description?has_content)?then(pizzeria.description, ' - ')}</dd>

        <dt>Pizzeria ID:</dt>
        <dd>${pizzeria.pizzeria_id}</dd>
    </dl>

    <form action="/pizzerias/delete/${pizzeria.pizzeria_id}" method="post">
        <button type="submit">Delete</button>
    </form>


    <h2>Add Meals</h2>
    <form action="/pizzerias/addMeal" method="post">
        <label for="meal_id">Select Meal:</label>
        <input type="hidden" name="pizzeria_id" value="${pizzeria.pizzeria_id}">
        <select name="meal_id" id="meal_id">
            <#list meals as meal>
                <option value="${meal.meal_id}">${meal.name}</option>
            </#list>
        </select>
        <button type="submit">Add</button>
    </form>

<#else>
    <p>Pizzeria not found.</p>
</#if>

<a href="/pizzerias">Back</a>

</body>
</html>