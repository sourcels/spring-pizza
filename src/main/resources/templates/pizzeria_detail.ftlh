<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="../styles/style.css">
        <link rel="stylesheet" href="../styles/detail.css">
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script src="../scripts/script.js"></script>
        <title>${(pizzeria.name?has_content)?then(pizzeria.name, 'Pizzeria')} Details</title>
    </head>
    <body>
        <header>
            <h1>Pizza-Panel</h1>
        </header>

        <nav>
            <a href="/" class="<#if springMacroRequestContext.requestUri == "/">active</#if>">Main Page</a>
            <a href="/pizzerias" class="<#if springMacroRequestContext.requestUri == "/pizzerias">active</#if>">Pizzerias</a>
            <a href="/meals" class="<#if springMacroRequestContext.requestUri == "/meals">active</#if>">Meals</a>
            <form action="/logout" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button type="submit">Sign Out</button>
            </form>
        </nav>

        <#if pizzeria??>
            <div class="pizzeria-details">
                <dl class="pizzeria-details-list">
                    <dt>Name:</dt>
                    <dd>${pizzeria.getName()}</dd>

                    <dt>Phone:</dt>
                    <dd>${pizzeria.getPhone()}</dd>

                    <dt>Address:</dt>
                    <dd>${pizzeria.getAddress()}</dd>

                    <dt>Description:</dt>
                    <dd>${pizzeria.getDescription()}</dd>

                    <dt>Pizzeria ID:</dt>
                    <dd>${pizzeria.pizzeria_id}</dd>
                </dl>

                <button class="edit-btn" onclick="editableDialogPizzeria('${_csrf.token}', {'id': ${pizzeria.pizzeria_id},'name': '${pizzeria.name}', 'phone': '${pizzeria.phone}', 'address': '${pizzeria.address}', 'description': '${pizzeria.description}'})">Edit Pizzeria</button>
                <form action="/pizzerias/delete" method="post" onsubmit="return confirm('Are you sure you want to delete this pizzeria?')">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="hidden" name="pizzeriaId" value="${pizzeria.pizzeria_id}">
                    <button class="delete-btn" type="submit">Delete Pizzeria</button>
                </form>
                <#if meals?size == 0>
                    <p class="not-found">Select Meal: no meal available, you can <a href="/meals">create meal</a>.</p>
                <#elseif mealsNotInPizzeria?size == 0>
                    <p class="not-found">All meals are already in this pizzeria.</p>
                <#else>
                    <div class="add-meals-container">
                        <h2>Add Meals</h2>
                        <form action="/pizzerias/addMeal" method="post">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <label for="mealId">Select Meal:</label>
                            <input type="hidden" name="pizzeriaId" value="${pizzeria.pizzeria_id}">
                            <select name="mealId" id="meal_id">
                                <#list mealsNotInPizzeria as mealNotInPizzeria>
                                    <option value="${mealNotInPizzeria.meal_id}">${mealNotInPizzeria.name}</option>
                                </#list>
                            </select>
                            <button type="submit">Add</button>
                        </form>
                    </div>
                </#if>

                <table class="meals-table" id="meals_table">
                    <thead>
                    <tr>
                        <th>Name <button onclick="sortTable('meals_table', 0)">Sort</button></th>
                        <th>Description <button onclick="sortTable('meals_table', 1)">Sort</button></th>
                        <th>Price <button onclick="sortTable('meals_table', 2)">Sort</button></th>
                        <th>Category <button onclick="sortTable('meals_table', 3)">Sort</button></th>
                        <th>Actions </th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list pizzeriaMeals as pizzeriaMeal>
                        <tr>
                            <td>${pizzeriaMeal.getName()}</td>
                            <td>${pizzeriaMeal.getDescription()}</td>
                            <td>${pizzeriaMeal.getPrice()}€</td>
                            <td>${pizzeriaMeal.getCategory()}</td>
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
            </div>
        <#else>
            <p class="not-found">Pizzeria not found.</p>
        </#if>

    </body>
    <footer>
        <a href="https://github.com/sourcels/spring-pizza" target="_blank">Project on GitHub</a>
    </footer>
</html>