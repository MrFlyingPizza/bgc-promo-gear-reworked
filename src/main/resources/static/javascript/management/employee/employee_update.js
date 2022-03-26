//JS Object that dynamically contains only form input values that are changed
const updateData = {};

//KEEP THIS. NEEDED FOR LATER
//Upon page load, if:
// (1) - There are no locations, dont let them submit at all
// (2) - Load all the existing locations into the <select> and set the default to the current value
// $(document).ready(function(){
//     if(jQuery.isEmptyObject(allLocations)){
//         $("#location").prop("disabled", true);
//         $("#credit").prop("disabled", true);
//         $("#lastBigItemTimeStamp").prop("disabled", true);
//         $('#ifNoLocations').css("display", "");
//     }
//     else{
//         $.each(allLocations, function (i, location) {
//             if(allLocations.length === 1){
//                 updateData['locationID'] = location['id'];
//             }
//             $('#location').append($('<option>', {
//                 value: location['id'],
//                 text : location['name']
//             }));
//             if(location['name'] === oldLocationName){
//                 $('#location').val(location['id']).attr("selected", "selected");
//             }
//         });
//     }
// });

/**
 * Activates/Deactivates "Submit Update" button whenever updateData is non-empty/empty respectively
 */
function check_button_activation(){
    if(jQuery.isEmptyObject(updateData)){
        $("#submit-update-employee").prop("disabled", true);
    }
    else{
        $("#submit-update-employee").removeAttr('disabled');
    }
}

// function check_if_location_changed(){
//     if($("#location").text() === oldLocationName){
//         delete updateData['locationID'];
//     }
//     else{
//         updateData['locationID'] = $("#location").val();
//     }
// }

//The following JQuery .change()'s dynamically change:
// (1) the content of updateData and
// (2) the activation of the "Submit Update" button
$("#credit").change(function (){
    updateData['credit'] = parseFloat($("#credit").val()).toFixed(2);
    //check_if_location_changed();
    check_button_activation();
})
// $("#location").change(function (){
//     check_if_location_changed();
//     check_button_activation();
// })
$("#last-big-item-timestamp").change(function (){
    updateData['lastBigItemTimeStamp'] = new Date($("#last-big-item-timestamp").val()).toISOString();
    //check_if_location_changed();
    check_button_activation();
})


/**
 * When the user presses "Submit" to update an employee, this function fills in a
 * modal popup to allow them to confirm what they have just entered
 */
function display_new_details(){
    $('#display-new-credit').text($('#credit').val());
    //$('#displayNewLocationName').text($('#location').val());
    $('#display-new-last-big-item-timestamp').text($('#last-big-item-timestamp').val());
}

/**
 * @param currentData The current stored data of the employee in the database
 *
 *  This function fills in a modal popup to let them compare their entered values to
 *  the current stored data of the employee
 *
 * Note: Importance of this function is to ensure that the "true" current data is displayed.
 *       Consider the following situation:
 *        1. User 1 gets employee page
 *        2. User 1 starts to edit
 *        3. User 2 gets employee page
 *        4. User 2 starts to edit
 *        5. User 2 finishes edit and submits to server
 *        ...
 *
 *       Here we can see that the data of the employee when User 1 loads the page may be old.
 *       Therefore we use currentData to get the "true" current data, which in the case of the
 *       example would be the data that User 2 just submitted
 */
function display_currently_stored_values(currentData){
    if(currentData['credit'] === null){
        $('#currently-stored-credit').text("None");
    }
    else{
        $('#currently-stored-credit').text(currentData['credit']);
    }
    if(currentData['lastBigItemTimeStamp'] === null){
        $('#currently-stored-last-big-item-timestamp').text("None");
    }
    else{
        $('#currently-stored-last-big-item-timestamp').text(currentData['lastBigItemTimeStamp']);
    }
    // $('#currentlyStoredLocationName').text(currentData['location']);
}

/**
 * If any values are unchanged, then do not display them in the modal popup that
 * shows them their new values
 */
function hide_rows(){
    if(('credit' in updateData)){
        $("#credit-row").css("display", "");
    }
    else{
        $("#credit-row").css("display", "none");
    }
    // if(('location' in updateData)){
    //     $("#locationRow").css("display", "");
    // }
    // else{
    //     $("#locationRow").css("display", "none");
    // }
    if(('lastBigItemTimeStamp' in updateData)){
        $("#last-big-item-timestamp-row").css("display", "");
    }
    else{
        $("#last-big-item-timestamp-row").css("display", "none");
    }
}

/**
 * @param currentData The current stored data of the employee in the database
 *
 * This function is called after the AJAX request in get_currently_stored_employee(id).
 * If that request is successful, then...
 *  1. display_new_details(),
 *  2. display_currently_stored_values(currentData) and,
 *  3. hide_rows()
 *  ...will fill in the modal popup confirming their entered info.
 *  4. "$('#confirm-employee-update-modal').modal('show')" - This activates the modal popup
 */
function display_confirm_update_modal(currentData){
    display_new_details();
    display_currently_stored_values(currentData);
    hide_rows();
    $('#confirm-employee-update-modal').modal('show');
}

/**
 * Function that sends an Ajax Request for retrieving data about a employee
 * @param id employee_id used to get data about that location
 *
 * Note: The function may show as unused, however it IS being used.
 *       It shows as unused since the function call is inside the following
 *       Thymeleaf attribute which is from employee_update.html:
 *       - th:attr="onclick=|get_currently_stored_employee('${employee?.getID()}')|"
 */
function get_currently_stored_employee(id){
    $.ajax({
        type: "GET",
        url: '/management/api/employee/'.concat(id.toString()),
        beforeSend: function(xhr) {
            xhr.setRequestHeader(
                $('meta[name=_csrf_header]').attr('content'),
                $('meta[name=_csrf]').attr('content')
            )
        },
        success: function(data)
        {
            display_confirm_update_modal(data);
        },
        error: function(xhr) {
            $('#get-stored-employee-fail-message').text("Error Message:\'"+ xhr.responseJSON.message +"\'")
            $('#get-stored-employee-fail-modal').modal('show');
        }
    });
}


$('#update-employee-form').submit(
    function (e) {
        e.preventDefault();

        const form = $(this);
        const url = form.attr('action');

        $.ajax({
            type: "PUT",
            url: url,
            contentType: "application/json",
            data: JSON.stringify(updateData), // serializes the form's elements.
            beforeSend: function(xhr) {
                xhr.setRequestHeader(
                    $('meta[name=_csrf_header]').attr('content'),
                    $('meta[name=_csrf]').attr('content')
                )
            },
            success: function()
            {
                $('#confirm-employee-update-modal').modal('hide');
                $('#update-employee-success-modal').modal('show');
            },
            error: function(xhr) {
                $('#confirm-employee-update-modal').modal('hide');
                $('#update-fail-message').text("Error Message:\'"+ xhr.responseJSON.message +"\'")
                $('#update-employee-fail-modal').modal('show');
            }
        });
    }
);

/**
 * Function that sends an Ajax Request for deleting an employee
 * @param id employee_id used to delete the employee that is currently being viewed
 *
 * Note: The function may show as unused, however it IS being used.
 *       It shows as unused since the function call is inside the following
 *       Thymeleaf attribute which is from employee_update.html:
 *       - th:attr="onclick=|delete_employee('${employee?.getID()}')|"
 */
function delete_employee(id){
    $.ajax({
        type: "DELETE",
        url: '/management/api/employee/'+id.toString(),
        beforeSend: function(xhr) {
            xhr.setRequestHeader(
                $('meta[name=_csrf_header]').attr('content'),
                $('meta[name=_csrf]').attr('content')
            )
        },
        success: function()
        {
            $('#confirm-employee-delete-modal').modal('hide');
            $('#delete-employee-success-modal').modal('show');
        },
        error: function(xhr) {
            $('#confirm-employee-delete-modal').modal('hide');
            $('#delete-fail-message').text("Error Message:\'"+ xhr.responseJSON.message +"\'")
            $('#delete-employee-fail-modal').modal('show');
        }
    });
}