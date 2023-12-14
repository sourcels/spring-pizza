<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Meals</title>
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="styles/style.css">
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script src="scripts/script.js"></script>
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

        <body>
            <div class="data-container">
                <div class="meal-container">
                    <button type="button" onclick="createDialogMeal('${_csrf.token}')">Add New Meal</button>
                    <#if meals?size == 0>
                        No meals!
                    <#else>
                        <table class="meal-table" id="meals_table">
                            <thead>
                            <tr>
                                <th>Name <button onclick="sortTable('meals_table', 0)">Sort</button></th>
                                <th>Description <button onclick="sortTable('meals_table', 1)">Sort</button></th>
                                <th>Price <button onclick="sortTable('meals_table', 2)">Sort</button></th>
                                <th>Category <button onclick="sortTable('meals_table', 3)">Sort</button></th>
                                <th>Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list meals as meal>
                                <tr>
                                    <td>${meal.getName()}</td>
                                    <td>${meal.getDescription()}</td>
                                    <td>${meal.getPrice()}€</td>
                                    <td>${meal.getCategory()}</td>
                                    <td>
                                        <form action="/meals/delete" method="post" onsubmit="return confirm('Are you sure you want to delete this meal?')">
                                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                                            <input type="hidden" name="mealId" value="${meal.meal_id}">
                                            <button class="delete-btn" type="submit">Delete</button>
                                        </form>
                                        <form action="/meals/${meal.meal_id}" method="get">
                                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                                            <button class="detail-btn" type="submit">Details</button>
                                        </form>
                                    </td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    </#if>
                </div>
            </div>
        </body>
    </body>
    <footer>
        <a href="https://github.com/sourcels/spring-pizza" target="_blank">Project on GitHub</a>
    </footer>
</html>