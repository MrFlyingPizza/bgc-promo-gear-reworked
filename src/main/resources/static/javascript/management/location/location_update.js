//JS Object that dynamically contains only form input values that are changed
const updateData = {};

/**
 * Activates/Deactivates "Submit Update" button whenever updateData is non-empty/empty respectively
 */
function check_button_activation(){
    if(jQuery.isEmptyObject(updateData)){
        $("#submit-update-location").prop("disabled", true);
    }
    else{
        $("#submit-update-location").removeAttr('disabled');
    }
}

//The following JQuery .change()'s dynamically change:
// (1) the content of updateData and
// (2) the activation of the "Submit Update" button
$("#name").change(function (){
    if($("#name").val().trim() === ""){
        delete updateData['name'];
    }
    else{
        updateData['name'] = $("#name").val().trim();
    }
    check_button_activation();
})
$("#country").change(function (){
    if($("#country").val().trim() === ""){
        delete updateData['country'];
    }
    else{
        updateData['country'] = $("#country").val().trim();
    }
    check_button_activation();
})
$("#province").change(function (){
    if($("#province").val().trim() === ""){
        delete updateData['state'];
    }
    else{
        updateData['state'] = $("#province").val().trim();
    }
    check_button_activation();
})
$("#city").change(function (){
    if($("#city").val().trim() === ""){
        delete updateData['city'];
    }
    else{
        updateData['city'] = $("#city").val().trim();
    }
    check_button_activation();
})
$("#street-address-one").change(function (){
    if($("#street-address-one").val().trim() === ""){
        delete updateData['addressLine1'];
    }
    else{
        updateData['addressLine1'] = $("#street-address-one").val().trim();
    }
    check_button_activation();
})
$("#postal-code").change(function (){
    if($("#postal-code").val().trim() === ""){
        delete updateData['zipCode'];
    }
    else{
        updateData['zipCode'] = $("#postal-code").val().trim();
    }
    check_button_activation();
})
$("#street-address-two").change(function (){
    if($("#street-address-two").val().trim() === ""){
        delete updateData['addressLine2'];
    }
    else{
        updateData['addressLine2'] = $("#street-address-two").val().trim();
    }
    check_button_activation();
})

/**
 * When the user presses "Submit" to update a location, this function fills in a
 * modal popup to allow them to confirm what they have just entered
 */
function display_new_details(){
    $('#display-new-name').text($('#name').val());
    $('#display-new-country').text($('#country').val());
    $('#display-new-province').text($('#province').val());
    $('#display-new-city').text($('#city').val());
    $('#display-new-address-one').text($('#street-address-one').val());
    $('#display-new-postal-code').text($('#postal-code').val());
    $('#display-new-address-two').text($('#street-address-two').val());
}

/**
 * @param currentData The current stored data of the location in the database
 *
 *  This function fills in a modal popup to let them compare their entered values to
 *  the current stored data of the location
 *
 * Note: Importance of this function is to ensure that the "true" current data is displayed.
 *       Consider the following situation:
 *        1. User 1 gets location page
 *        2. User 1 starts to edit
 *        3. User 2 gets location page
 *        4. User 2 starts to edit
 *        5. User 2 finishes edit and submits to server
 *        ...
 *
 *       Here we can see that the data of the location when User 1 loads the page may be old.
 *       Therefore we use currentData to get the "true" current data, which in the case of the
 *       example would be the data that User 2 just submitted
 */
function display_currently_stored_values(currentData){
    $('#currently-stored-name').text(currentData['name']);
    $('#currently-stored-country').text(currentData['country']);
    $('#currently-stored-province').text(currentData['state']);
    $('#currently-stored-city').text(currentData['city']);
    $('#currently-stored-address-one').text(currentData['addressLine1']);
    $('#currently-stored-postal-code').text(currentData['zipCode']);
    $('#currently-stored-address-two').text(currentData['addressLine2']);
}

/**
 * If any values are unchanged, then do not display them in the modal popup that
 * shows them their new values
 */
function hide_rows(){
    if(('name' in updateData)){
        $("#name-row").css("display", "");
    }
    else{
        $("#name-row").css("display", "none");
    }
    if(('country' in updateData)){
        $("#country-row").css("display", "");
    }
    else{
        $("#country-row").css("display", "none");
    }
    if(('state' in updateData)){
        $("#province-row").css("display", "");
    }
    else{
        $("#province-row").css("display", "none");
    }
    if(('city' in updateData)){
        $("#city-row").css("display", "");
    }
    else{
        $("#city-row").css("display", "none");
    }
    if(('addressLine1' in updateData)){
        $("#address-one-row").css("display", "");
    }
    else{
        $("#address-one-row").css("display", "none");
    }
    if(('addressLine2' in updateData)){
        $("#address-two-row").css("display", "");
    }
    else{
        $("#address-two-row").css("display", "none");
    }
    if(('zipCode' in updateData)){
        $("#postal-code-row").css("display", "");
    }
    else{
        $("#postal-code-row").css("display", "none");
    }
}

/**
 * @param currentData The current stored data of the location in the database
 *
 * This function is called after the AJAX request in get_currently_stored_location(id).
 * If that request is successful, then...
 *  1. display_new_details(),
 *  2. display_currently_stored_values(currentData) and,
 *  3. hide_rows()
 *  ...will fill in the modal popup confirming their entered info.
 *  4. "$('#confirm-location-update-modal').modal('show')" - This activates the modal popup
 */
function display_confirm_update_modal(currentData){
    display_new_details();
    display_currently_stored_values(currentData);
    hide_rows();
    $('#confirm-location-update-modal').modal('show');
}

/**
 * Function that sends an Ajax Request for retrieving data about a location
 * @param id location_id used to get data about that location
 *
 * Note: The function may show as unused, however it IS being used.
 *       It shows as unused since the function call is inside the following
 *       Thymeleaf attribute which is from location_update.html:
 *       - th:attr="onclick=|get_currently_stored_location('${location?.getID()}')|"
 */
function get_currently_stored_location(id){
    $.ajax({
        type: "GET",
        url: '/api/office_locations/'.concat(id.toString()),
        success: function(data)
        {
            display_confirm_update_modal(data);
        },
        error: function(xhr) {
            $('#get-stored-location-fail-message').text("Error Message:\'"+ xhr.responseJSON.message +"\'")
            $('#get-stored-location-fail-modal').modal('show');
        }
    });
}


$('#update-location-form').submit(
    function (e) {
        e.preventDefault();

        const form = $('#update-location-form');
        const url = form.attr('action');

        $.ajax({
            type: "PATCH",
            url: url,
            contentType: "application/json",
            data: JSON.stringify(updateData), // serializes the form's elements.
            success: function()
            {
                $('#confirm-location-update-modal').modal('hide');
                $('#update-location-success-modal').modal('show');
            },
            error: function(xhr) {
                $('#confirm-location-update-modal').modal('hide');
                $('#update-fail-message').text("Error Message:\'"+ xhr.responseJSON.message +"\'")
                $('#update-fail-modal').modal('show');
            }
        });
    }
);

/**
 * Function that sends an Ajax Request for deleting a location
 * @param id location_id used to delete the location that is currently being viewed
 *
 * Note: The function may show as unused, however it IS being used.
 *       It shows as unused since the function call is inside the following
 *       Thymeleaf attribute which is from location_update.html:
 *       - th:attr="onclick=|delete_location('${location?.getID()}')|"
 */
function delete_location(id){
    $.ajax({
        type: "DELETE",
        url: '/api/secured/office_locations/'.concat(id.toString()),
        success: function()
        {
            $('#confirm-delete-location-modal').modal('hide');
            $('#delete-location-success-modal').modal('show');
        },
        error: function(xhr) {
            $('#confirm-delete-location-modal').modal('hide');
            $('#delete-fail-message').text("Error Message:\'"+ xhr.responseJSON.message +"\'")
            $('#delete-location-fail-modal').modal('show');
        }
    });
}