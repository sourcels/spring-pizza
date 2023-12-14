function createDialogPizzeria(csrf) {
    let dialogForm = $("<div>").attr("id", "dialog-form").attr("title", "Add Pizzeria");
    let form = $("<form>").attr("action", "/pizzerias/create").attr("method", "post");

    form.append('<input type="hidden" name="_csrf" value="' + csrf + '">');
    form.append('<label for="name">Name:</label>');
    form.append('<input type="text" id="name" name="name" maxlength="64" required><br>');

    form.append('<label for="phone">Phone:</label>');
    form.append('<input type="text" id="phone" name="phone" maxlength="12" required><br>');

    form.append('<label for="address">Address:</label>');
    form.append('<input type="text" id="address" name="address" maxlength="128" required><br>');

    form.append('<label for="description">Description:</label>');
    form.append('<textarea id="description" name="description"></textarea><br>');

    form.append('<input type="submit" value="Submit">');

    dialogForm.append(form);

    dialogForm.dialog({
        autoOpen: false,
        height: 275,
        width: 350,
        modal: true
    });
    dialogForm.dialog("open");
}

function editableDialogPizzeria(csrf, pizzeria) {
    let dialogForm = $("<div>").attr("id", "dialog-form").attr("title", "Edit Pizzeria");
    let form = $("<form>").attr("action", "/pizzerias/edit/" + pizzeria.id).attr("method", "post");

    form.append('<input type="hidden" name="_csrf" value="' + csrf + '">');
    form.append('<label for="name">Name:</label>');
    form.append('<input type="text" id="name" name="name" maxlength="64" value="' + pizzeria.name + '" required><br>');

    form.append('<label for="phone">Phone:</label>');
    form.append('<input type="text" id="phone" name="phone" maxlength="12" value="' + pizzeria.phone + '" required><br>');

    form.append('<label for="address">Address:</label>');
    form.append('<input type="text" id="address" name="address" maxlength="128" value="' + pizzeria.address + '" required><br>');

    form.append('<label for="description">Description:</label>');
    form.append('<textarea id="description" name="description" value="' + pizzeria.description + '"></textarea><br>');

    form.append('<input type="submit" value="Submit">');

    dialogForm.append(form);

    dialogForm.dialog({
        autoOpen: false,
        height: 275,
        width: 350,
        modal: true
    });

    dialogForm.dialog("open");
}

function createDialogMeal(csrf) {
    let dialogForm = $("<div>").attr("id", "dialog-form").attr("title", "Add Meal");
    let form = $("<form>").attr("action", "/meals/create").attr("method", "post");

    form.append('<input type="hidden" name="_csrf" value="' + csrf + '">');
    form.append('<label for="name">Name:</label>');
    form.append('<input type="text" id="name" name="name" maxlength="64" required><br>');

    form.append('<label for="description">Description:</label>');
    form.append('<textarea id="description" name="description"></textarea><br>');

    form.append('<label for="price">Price:</label>');
    form.append('<input type="number" id="price" name="price" min="0" step="1" required><br>');

    form.append('<label for="category">Category:</label>');
    form.append('<select id="category" name="category" required> <option value="Salad">Salad</option><option value="Pizza">Pizza</option><option value="Pasta">Pasta</option><option value="Dessert">Dessert</option></select>');

    form.append('<input type="submit" value="Submit">');

    dialogForm.append(form);

    dialogForm.dialog({
        autoOpen: false,
        height: 275,
        width: 350,
        modal: true
    });
    dialogForm.dialog("open");
}

function editableDialogMeal(csrf, meal) {
    let dialogForm = $("<div>").attr("id", "dialog-form").attr("title", "Edit Meal");
    let form = $("<form>").attr("action", "/meals/edit/" + meal.id).attr("method", "post");

    form.append('<input type="hidden" name="_csrf" value="' + csrf + '">');
    form.append('<label for="name">Name:</label>');
    form.append('<input type="text" id="name" name="name" maxlength="64" value="' + meal.name + '" required><br>');

    form.append('<label for="description">Description:</label>');
    form.append('<textarea id="description" name="description" value="' + meal.description + '"></textarea><br>');

    form.append('<label for="price">Price:</label>');
    form.append('<input type="number" id="price" name="price" min="0" step="1" value="' + meal.price + '" required><br>');

    form.append('<label for="description">Category:</label>');
    form.append('<select id="category" name="category" required> ' +
        '<option value="Salad" ' + (meal.category === 'Salad' ? 'selected' : '') + '>Salad</option>' +
        '<option value="Pizza" ' + (meal.category === 'Pizza' ? 'selected' : '') + '>Pizza</option>' +
        '<option value="Pasta" ' + (meal.category === 'Pasta' ? 'selected' : '') + '>Pasta</option>' +
        '<option value="Dessert" ' + (meal.category === 'Dessert' ? 'selected' : '') + '>Dessert</option></select>');

    form.append('<input type="submit" value="Submit">');

    dialogForm.append(form);

    dialogForm.dialog({
        autoOpen: false,
        height: 275,
        width: 350,
        modal: true
    });

    dialogForm.dialog("open");
}

function handleEnter(event) {
    if (event.key === "Enter") {
        let enteredName = document.getElementById("nameInput").value;
        if (enteredName !== "") {
            window.location.href = "/?name=" + encodeURIComponent(enteredName);
        }
        else {
            window.location.href = "/"
        }
    }
}

function sortTable(target_table, column_index) {
    let table, rows, switching, i, x, y, shouldSwitch;
    table = document.getElementById(target_table);
    switching = true;
    while (switching) {
        switching = false;
        rows = table.rows;
        for (i = 1; i < (rows.length - 1); i++) {
            shouldSwitch = false;
            x = rows[i].getElementsByTagName("TD")[column_index];
            y = rows[i + 1].getElementsByTagName("TD")[column_index];
            if (x.textContent.toLowerCase() > y.textContent.toLowerCase()) {
                shouldSwitch = true;
                break;
            }
        }
        if (shouldSwitch) {
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
        }
    }
}