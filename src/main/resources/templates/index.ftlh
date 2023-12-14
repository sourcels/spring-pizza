<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pizza-Panel</title>
    <link rel="stylesheet" href="styles/style.css">
    <script src="scripts/script.js"></script>
</head>
<body>

    <header>
        <h1>Pizza-Panel</h1>
    </header>

    <nav>
        <a href="/" class="<#if springMacroRequestContext.requestUri?contains("/")>active</#if>">Main page</a>
        <a href="/pizzerias" class="<#if springMacroRequestContext.requestUri?contains("/pizzerias")>active</#if>">Pizzerias</a>
        <a href="/meals" class="<#if springMacroRequestContext.requestUri?contains("/meals")>active</#if>">Meals</a>
        <input type="text" id="nameInput" placeholder="Pizzeria name" onkeydown="handleEnter(event)">
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit">Sign Out</button>
        </form>
    </nav>

    <body>
        <div class="data-container">
            <div class="pizzeria-container">
                <#if pizzerias?size == 0>
                    No pizzerias! You can create them on <a href="/pizzerias">this page</a>.
                <#else>
                    <p>Pizzerias</p>
                    <table class="pizzeria-table" id="pizzerias_table">
                        <thead>
                        <tr>
                            <th>Name <button onclick="sortTable('pizzerias_table', 0)">Sort</button></th>
                            <th>Phone <button onclick="sortTable('pizzerias_table', 1)">Sort</button></th>
                            <th>Address <button onclick="sortTable('pizzerias_table', 2)">Sort</button></th>
                            <th>Description <button onclick="sortTable('pizzerias_table', 3)">Sort</button></th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list pizzerias as pizzeria>
                            <tr>
                                <td>${pizzeria.getName()}</td>
                                <td>${pizzeria.getPhone()}</td>
                                <td>${pizzeria.getAddress()}</td>
                                <td>${pizzeria.getDescription()}</td>
                                <td>
                                    <form action="/pizzerias/${pizzeria.pizzeria_id}" method="get">
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

            <div class="meal-container">
                <#if meals?size == 0>
                    No meals! You can create them on <a href="/meals">this page</a>.
                <#else>
                    <p>Meals</p>
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

    <footer>
        <a href="https://github.com/sourcels/spring-pizza" target="_blank">Project on GitHub</a>
    </footer>
    </body>
</html>