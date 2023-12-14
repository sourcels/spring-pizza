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
        <title>${(meal.name?has_content)?then(meal.name, 'Meal')} Details</title>
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

        <#if meal??>
            <div class="pizzeria-details">
                <dl clasS="pizzeria-details-list">
                    <dt>Name:</dt>
                    <dd>${meal.getName()}</dd>

                    <dt>Description:</dt>
                    <dd>${meal.getDescription()}</dd>

                    <dt>Price:</dt>
                    <dd>${meal.getPrice()}€</dd>

                    <dt>Category:</dt>
                    <dd>${meal.getCategory()}</dd>

                    <dt>Pizzeria ID:</dt>
                    <dd>${meal.meal_id}</dd>
                </dl>

                <button class="edit-btn" onclick="editableDialogMeal('${_csrf.token}', {'id': ${meal.meal_id},'name': '${meal.name}', 'description': '${meal.description}', 'price': '${meal.price}', 'category': '${meal.category}'})">Edit Meal</button>
                <form action="/meals/delete" method="post" onsubmit="return confirm('Are you sure you want to delete this meal?')">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="hidden" name="mealId" value="${meal.meal_id}">
                    <button class="delete-btn" type="submit">Delete Meal</button>
                </form>
            </div>
        <#else>
            <p class="not-found">Meal not found.</p>
        </#if>

        </body>
    <footer>
        <a href="https://github.com/sourcels/spring-pizza" target="_blank">Project on GitHub</a>
    </footer>
</html>