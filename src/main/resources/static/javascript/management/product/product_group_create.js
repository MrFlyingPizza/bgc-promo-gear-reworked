/**
 * Whenever a dropdown option in any select tag is picked,
 * this function marks that option with the attribute "selected".
 * Additionally, any other options have their "selected" attribute removed.
 */
$(document).on("change","select",function(){
    $("option[value=" + this.value + "]", this)
        .attr("selected", true).siblings()
        .removeAttr("selected")
});

/**
 * Upon page load, get all the current categories from the database
 * and load them into the empty <select> tag in the html.
 */
$(document).ready(function(){
    fill_current_attributes();
});

/**
 * Gets all current attributes from the database. If:
 * (1) - There are no categories, dont let them submit at all
 * (2) - Load all the existing categories into the <select> tags
 */
function fill_current_attributes(){
    $.ajax({
        type: "GET",
        url: '/management/api/category/',
        beforeSend: function(xhr) {
            xhr.setRequestHeader(
                $('meta[name=_csrf_header]').attr('content'),
                $('meta[name=_csrf]').attr('content')
            )
        },
        success: function(data)
        {
            if(jQuery.isEmptyObject(data)){
                $("#submit-create-product-group").prop("disabled", true);
                $('#if-no-categories').css("display", "");
            }
            else{
                $('#categories').empty();
                $('#current-categories').empty();
                //Option to be a part of no category
                $('#current-categories').append($('<option>', {
                    selected: true,
                    value: "null",
                    text : "None"
                }));
                $.each(data, function (i, category) {
                    $('#categories').append($('<option>', {
                        value: category['ID'],
                        text : category['name']
                    }));
                    $('#current-categories').append($('<option>', {
                        value: category['ID'],
                        text : category['name']
                    }));
                });
            }
        },
        error: function(xhr) {
            $('#get-stored-category-fail-message').text("Error Message:\'"+ xhr.responseJSON.message +"\'")
            $('#get-stored-category-fail-modal').modal('show');
        }
    });
}


/**
 * Note: The function may show as unused, however it IS being used.
 *       It shows as unused since the function call is inside the following
 *       Thymeleaf attribute which is from product_group_create.html:
 *       - th:attr="onclick=|fill_attributes()|"
 */
function fill_attributes(){
    fill_current_attributes();
    $('#create-category-form-modal').modal('show');
}

function display_new_product_group_details(){
    $('#display-new-name').text($('#name').val());
    $('#display-new-brand').text($('#brand').val());
    $('#display-new-price').text("$" + $('#price').val());
    if ($('#is-published').is(":checked")){
        $('#display-new-is-published').text("Yes");
    }
    else{
        $('#display-new-is-published').text("No");
    }
    if ($('#is-big-item').is(":checked")){
        $('#display-new-is-big-item').text("Yes");
    }
    else{
        $('#display-new-is-big-item').text("No");
    }
    if ($('#is-waitlistable').is(":checked")){
        $('#display-new-is-waitlisted').text("Yes");
    }
    else{
        $('#display-new-is-waitlisted').text("No");
    }
    $('#display-new-description').text($('#description').val());
    $('#display-new-category').text($('#categories option:selected').text());
}

$('#submit-create-product-group').click(display_new_product_group_details);

function display_new_category_details(){
    $('#display-new-category-name').text($('#category-name').val());
    $('#display-new-parent-category').text($('#current-categories option:selected').text());
}
$('#submit-create-category').click(display_new_category_details);

$('#create-category-form').submit(
    function (e) {
        e.preventDefault();

        const form = $(this);
        const url = form.attr('action');
        const data = {
            'name': $('#category-name').val(),
            'parentID': $('#current-categories option:selected').val()
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
            success: function(data)
            {
                $('#categories').append($('<option>', {
                    value: data['ID'],
                    text : data['name']
                }));
                $('#create-category-form-modal').modal('hide');
                $('#confirm-category-submit').modal('hide');
                $('#create-category-success-modal').modal('show');
            },
            error: function(xhr) {
                $('#confirm-category-submit').modal('hide');
                $('#create-product-group-fail-message').text("Error Message:\'"+ xhr.responseJSON.message +"\'")
                $('#create-product-group-fail-modal').modal('show');
            }
        });
    }
);

$('#create-product-group-form').submit(
    function (e) {
        e.preventDefault();

        const form = $(this);
        const url = form.attr('action');
        const data = {
            'name': $('#name').val(),
            'brand': $('#brand').val(),
            'price': parseFloat($("#price").val()).toFixed(2),
            'isPublished': $('#is-published').is(':checked'),
            'isBigItem': $('#is-big-item').is(':checked'),
            'isWaitlistable': $('#is-waitlistable').is(':checked'),
            'description': $('#description').val(),
            'categoryID': $('#categories option:selected').val()
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
            success: function(data)
            {
                $('#confirm-product-group-create-modal').modal('hide');
                $('#success-footer').append("<a class=\"btn btn-outline-primary\" href=\"/management/product/create?id="+data['ID'].toString()+"\">Create variations? of product</a>")
                $('#create-product-group-success-modal').modal('show');
            },
            error: function(xhr) {
                $('#confirm-product-group-create-modal').modal('hide');
                $('#create-product-group-fail-message').text("Error Message:\'"+ xhr.responseJSON.message +"\'")
                $('#create-product-group-fail-modal').modal('show');
            }
        });
    }
);