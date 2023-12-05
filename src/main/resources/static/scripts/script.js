$(function() {
    function createDialogPizzeria() {
        let csrfToken = $("#addPizzeriaButton").data("csrf");
        let dialogForm = $("<div>").attr("id", "dialog-form").attr("title", "Add Pizzeria");
        let form = $("<form>").attr("action", "/pizzerias/create").attr("method", "post");

        form.append('<input type="hidden" name="_csrf" value="' + csrfToken + '">');
        form.append('<label for="name">Name:</label>');
        form.append('<input type="text" id="name" name="name" maxlength="64" required><br>');

        form.append('<label for="phone">Phone:</label>');
        form.append('<input type="text" id="phone" name="phone" maxlength="12" required><br>');

        form.append('<label for="address">Address:</label>');
        form.append('<input type="text" id="address" name="address" maxlength="128" required><br>');

        form.append('<label for="description">Description:</label>');
        form.append('<input type="text" id="description" name="description"><br>');

        dialogForm.append(form);

        dialogForm.dialog({
            autoOpen: false,
            height: 275,
            width: 350,
            modal: true,
            buttons: {
                "Submit": function() {
                    form.submit();
                    $(this).dialog("close");
                },
                "Cancel": function() {
                    $(this).dialog("close");
                }
            },
            close: function() {
                // Ваш код для закрытия диалогового окна
            }
        });

        return dialogForm;
    }

    $("#addPizzeriaButton").on("click", function() {
        let dialogForm = createDialogPizzeria();
        dialogForm.dialog("open");
    });

    function createDialogMeal() {
        let csrfToken = $("#addMealButton").data("csrf");
        let dialogForm = $("<div>").attr("id", "dialog-form").attr("title", "Add Meal");
        let form = $("<form>").attr("action", "/meals/create").attr("method", "post");

        form.append('<input type="hidden" name="_csrf" value="' + csrfToken + '">');
        form.append('<label for="name">Name:</label>');
        form.append('<input type="text" id="name" name="name" maxlength="64" required><br>');

        form.append('<label for="description">Description:</label>');
        form.append('<input type="text" id="description" name="description"><br>');

        form.append('<label for="price">Price:</label>');
        form.append('<input type="number" id="price" name="price" min="0" step="1" required><br>');

        form.append('<label for="category">Category:</label>');
        form.append('<input type="text" id="category" name="category" maxlength="64" required><br>');

        dialogForm.append(form);

        dialogForm.dialog({
            autoOpen: false,
            height: 275,
            width: 350,
            modal: true,
            buttons: {
                "Submit": function() {
                    form.submit();
                    $(this).dialog("close");
                },
                "Cancel": function() {
                    $(this).dialog("close");
                }
            },
            close: function() {
                // Ваш код для закрытия диалогового окна
            }
        });

        return dialogForm;
    }

    $("#addMealButton").on("click", function() {
        let dialogForm = createDialogMeal();
        dialogForm.dialog("open");
    });
});

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