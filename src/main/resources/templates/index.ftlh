<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org"
      xmlns:sec="https://www.thymeleaf.org/thymeleaf-extras-springsecurity6">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pizza-Panel</title>
    <link rel="stylesheet" href="styles/style.css">
    <script src="scripts/script.js"></script>
</head>
<body>

    <header>
        <h1>Welcome to Pizza-Panel</h1>
    </header>

    <nav>
        <a href="/" class="<#if springMacroRequestContext.requestUri?contains("/")>active</#if>">Main page</a>
        <a href="/pizzerias" class="<#if springMacroRequestContext.requestUri?contains("/pizzerias")>active</#if>">Pizzerias</a>
        <a href="/meals" class="<#if springMacroRequestContext.requestUri?contains("/meals")>active</#if>">Meals</a>
        <input type="text" id="nameInput" placeholder="Pizzeria name" onkeydown="handleEnter(event)">
        <form th:action="@{/logout}" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="submit" value="Sign Out"/>
        </form>
    </nav>

    <body>
        <div class="data-container">
            <div class="pizzeria-container">
                <#if pizzerias?size == 0>
                    No pizzerias! You can create them on <a href="/pizzerias">this page</a>.
                <#else>
                    <p>Meals</p>
                    <table class="pizzeria-table">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Phone</th>
                            <th>Address</th>
                            <th>Description</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list pizzerias as pizzeria>
                            <tr>
                                <td>${(pizzeria.name?has_content)?then(pizzeria.name, ' - ')}</td>
                                <td>${(pizzeria.phone?has_content)?then(pizzeria.phone, ' - ')}</td>
                                <td>${(pizzeria.address?has_content)?then(pizzeria.address, ' - ')}</td>
                                <td>${(pizzeria.description?has_content)?then(pizzeria.description, ' - ')}</td>
                                <td>
                                    <form action="/pizzerias/${pizzeria.pizzeria_id}" method="get">
                                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                                        <button type="submit">Details</button>
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
                    <p>Pizzerias</p>
                    <table class="meal-table">
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
                                    <form action="/meals/${meal.meal_id}" method="get">
                                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                                        <button type="submit">Details</button>
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