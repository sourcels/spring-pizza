<!DOCTYPE html>
<html>
<head>
    <title>Pizzeria Details</title>
</head>
<body>

<h1>Pizzeria Details</h1>

<#if pizzeria??>
    <dl>
        <dt>ID:</dt>
        <dd>${pizzeria.pizzeria_id}</dd>

        <dt>Phone:</dt>
        <dd>${pizzeria.phone}</dd>

        <dt>Address:</dt>
        <dd>${pizzeria.address}</dd>

        <dt>Description:</dt>
        <dd>${pizzeria.description}</dd>
    </dl>

    <form action="/pizzeria/addMeals/" method="post">
        <input type="hidden" name="pizzeria_id" value="${pizzeria.pizzeria_id}">
        <label for="meals">Select Meals:</label>
        <select multiple name="meals" id="meals">
            <#list allMeals as meal>
                <option value="${meal.meal_id}">${meal.name}</option>
            </#list>
        </select>
        <button type="submit">Add Meals</button>
    </form>

    <form action="/pizzeria/delete/${pizzeria.pizzeria_id}" method="post">
        <button type="submit">Delete</button>
    </form>

<#else>
    <p>Pizzeria not found.</p>
</#if>

<a href="/pizzerias">Back to Pizzerias List</a>

</body>
</html>