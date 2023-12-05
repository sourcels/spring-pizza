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
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button type="submit">Delete Pizzeria</button>
            </form>
            <#if meals?size == 0>
                Select Meal: no meal available, you can <a href="/meals">create meal</a>.
            <#elseif mealsNotInPizzeria?size == 0>
                All meals are already in this pizzeria.
            <#else>
            <h2>Add Meals</h2>
            <form action="/pizzerias/addMeal" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <label for="mealId">Select Meal:</label>
                <input type="hidden" name="pizzeriaId" value="${pizzeria.pizzeria_id}">
                <select name="mealId" id="meal_id">
                    <#list meals as meal>
                        <option value="${meal.meal_id}">${meal.name}</option>
                    </#list>
                </select>
                <button type="submit">Add</button>
            </form>
            </#if>

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
                <#list pizzeriaMeals as pizzeriaMeal>
                    <tr>
                        <td>${(pizzeriaMeal.name?has_content)?then(pizzeriaMeal.name, ' - ')}</td>
                        <td>${(pizzeriaMeal.description?has_content)?then(pizzeriaMeal.description, ' - ')}</td>
                        <td>${(pizzeriaMeal.price?has_content)?then(pizzeriaMeal.price, 0)}</td>
                        <td>${(pizzeriaMeal.category?has_content)?then(pizzeriaMeal.category, ' - ')}</td>
                        <td>
                            <form action="/pizzerias/deleteMeal" method="post">
                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                                <input type="hidden" name="mealId" value="${pizzeriaMeal.meal_id}">
                                <input type="hidden" name="pizzeriaId" value="${pizzeria.pizzeria_id}">
                                <button type="submit">Delete</button>
                            </form>
                            <form action="/meals/${pizzeriaMeal.meal_id}" method="get">
                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                                <button type="submit">Details</button>
                            </form>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>

        <#else>
            <p>Pizzeria not found.</p>
        </#if>

    </body>
    <footer></footer>
</html>