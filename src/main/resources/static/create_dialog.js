$(function() {
    $("#addPizzeriaButton").on("click", function() {
        $("#dialog-form").dialog("open");
    });

    $("#addMealButton").on("click", function() {
        $("#dialog-form").dialog("open");
    });

    $("#dialog-form").dialog({
        autoOpen: false,
        height: 275,
        width: 350,
        modal: true,
        /*buttons: {
            "Add Pizzeria": function() {
                $(this).dialog("close");
            },
            Cancel: function() {
                $(this).dialog("close");
            }
        },
        close: function() {
            // Ваш код для закрытия диалогового окна
        }*/
    });
});