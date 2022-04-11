function display_new_details(){
    $('#display-new-name').text($('#name').val());
}

$('#create-attribute-group-submit').click(display_new_details);

$('#create-attribute-group-form').submit(
    function (e) {
        e.preventDefault();

        const form = $(this);
        const url = form.attr('action');
        const data = {
            'name': $('#name').val()
        }

        $.ajax({
            type: "POST",
            url: url,
            contentType: "application/json",
            data: JSON.stringify(data), // serializes the form's elements.
            beforeSend: function(xhr) {
                xhr.setRequestHeader(
                    $('meta[name=_csrf_header]').attr('content'),
                    $('meta[name=_csrf]').attr('content')
                )
            },
            success: function()
            {
                $('#confirm-create-attribute-group-modal').modal('hide');
                $('#create-attribute-group-success-modal').modal('show');
            },
            error: function(xhr) {
                $('#confirm-create-attribute-group-modal').modal('hide');
                $('#create-attribute-group-fail-message').text("Error Message:\'"+ xhr.responseJSON.message +"\'")
                $('#create-attribute-group-fail-modal').modal('show');
            }
        });
    }
);