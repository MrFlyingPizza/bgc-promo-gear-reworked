/**
 * When the user presses "Submit" to create a new location, this function fills in a
 * modal popup to allow them to confirm what they have just entered
 */
function display_new_details(){
    $('#display-new-name').text($('#name').val());
    $('#display-new-country').text($('#country').val());
    $('#display-new-province').text($('#province').val());
    $('#display-new-city').text($('#city').val());
    $('#display-new-street-address-one').text($('#street-address-one').val());
    $('#display-new-street-address-two').text($('#street-address-two').val());
    $('#display-new-postal-code').text($('#postal-code').val());
}

$('#submit-create-location').click(display_new_details);

$('#create-location-form').submit(
    function (e) {
        e.preventDefault();

        const form = $(this);
        const url = form.attr('action');
        const data = {
            'name': $('#name').val(),
            'country': $('#country').val(),
            'state': $('#province').val(),
            'city': $('#city').val(),
            'zipCode': $('#postal-code').val(),
            'addressLine1': $('#street-address-one').val(),
            'addressLine2': ($('#street-address-two').val().trim() === "" ? null : $('#street-address-two').val())
        }

        $.ajax({
            type: "POST",
            url: url,
            contentType: "application/json",
            data: JSON.stringify(data), // serializes the form's elements.
            success: function()
            {
                $('#confirm-location-submit-modal').modal('hide');
                $('#create-location-success-modal').modal('show');
            },
            error: function(xhr) {
                $('#confirm-location-submit-modal').modal('hide');
                $('#create-fail-message').text("Error Message: \'"+ xhr.responseJSON.message +"\'")
                $('#create-fail-modal').modal('show');
            }
        });
    }
);




