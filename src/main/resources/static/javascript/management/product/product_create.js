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
    fill_current_categories();
});

/**
 * Gets all current attributes from the database. If:
 * (1) - There are no categories, dont let them submit at all
 * (2) - Load all the existing categories into the <select> tags
 */
function fill_current_categories(){
    $.ajax({
        type: "GET",
        url: '/management/api/category/root',
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
                $('#if-no-categories').css("display", "none");
                $('#categories').empty();
                $('#current-categories').empty();
                //Option to be a part of no category
                $('#current-categories').append($('<option>', {
                    selected: true,
                    value: "null",
                    text : "None"
                }));
                $.each(data, function (i, category) {
                    let parentOption = $('#categories').append($('<option>', {
                        value: category['ID'],
                        text : category['name']
                    }));
                    $('#categories option:last').css('background-color', '#f5f5f5');
                    let subcategoriesList = category['subcategories'];
                    for(let i = 0; i < subcategoriesList.length; i++){
                        $('#categories').append($('<option>', {
                            value: subcategoriesList[i]['ID'],
                            text : " - " + subcategoriesList[i]['name']
                        }));
                    }
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
 *       - th:attr="onclick=|fill_categories()|"
 */
function fill_categories(){
    fill_current_categories();
    $('#categories-landing-modal').modal('show');
}

// function display_new_product_group_details(){
//     $('#display-new-name').text($('#name').val());
//     $('#display-new-brand').text($('#brand').val());
//     $('#display-new-price').text("$" + $('#price').val());
//     if ($('#is-published').is(":checked")){
//         $('#display-new-is-published').text("Yes");
//     }
//     else{
//         $('#display-new-is-published').text("No");
//     }
//     if ($('#is-big-item').is(":checked")){
//         $('#display-new-is-big-item').text("Yes");
//     }
//     else{
//         $('#display-new-is-big-item').text("No");
//     }
//     if ($('#is-waitlistable').is(":checked")){
//         $('#display-new-is-waitlisted').text("Yes");
//     }
//     else{
//         $('#display-new-is-waitlisted').text("No");
//     }
//     $('#display-new-description').text($('#description').val());
//     $('#display-new-category').text($('#categories option:selected').text());
// }

//$('#submit-create-product-group').click(display_new_product_group_details);

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
                fill_current_categories();
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

//End Categories
//-----------------------------------------------------------------------------------------------------------------------
//Start Products
function set_all_low_stock_input(){
    $('.lowStockLimitInput').each(function (){
        $(this).val($('#changeAllLowStockLimitInput').val());
    });
}

function delete_variant(rowNumber){
    if(deleteTracker.includes(rowNumber)){
        const index = deleteTracker.indexOf(rowNumber);
        if (index > -1){
            deleteTracker.splice(index, 1);
        }
        $("#deleteVariant"+(rowNumber)).html("Disable");
        $("#product-create"+rowNumber).css("background-color", "transparent");
    }
    else{
        deleteTracker.push(rowNumber);
        $("#deleteVariant"+(rowNumber)).html("Restore");
        $("#product-create"+rowNumber).css("background-color", "#f78686");
    }
    console.log(deleteTracker);
}

var deleteTracker;
function finish_products_table(valueCombinations, idCombinations){
    $('#finishProductsFormBody').html("");
    for(let i = 0; i < valueCombinations.length; i++){
        $('#finishProductsFormBody').append("<tr id='product-create"+(i+1)+"' data-attrids='"+idCombinations[i]+"'>" +
            "            <td>"+valueCombinations[i].toString().replaceAll(" ", "/")+"</td>\n" +
            //"            <div style=\"display: table-cell;\"><input id='product-file-input"+(i+1)+"' type=\"file\"></div>\n" +
            "            <td><input id='product-low-stock-limit-input"+(i+1)+"' class=\"lowStockLimitInput\" type=\"number\" min=\"0\" value=\"0\"></td>" +
            "            <td><button type=\"button\" class=\"btn btn-danger rounded-pill text-decoration-none\" id='deleteVariant"+(i+1)+"' onclick='delete_variant("+(i+1)+")'>Disable</button></td>" +
            "           </tr>");
    }
    $('#finishProductsForm').data("count", valueCombinations.length);
    $('#create-part-two').css("display", "");
    deleteTracker = [];
    //$('#create-part-one').css("display", "none");
}

var allAttributeValues;
var allAttributeIDS;
var valueCombinations;
var idCombinations;
function create_all_possible_products(){
    //$('#create-part-two').css("display", "none");
    let totalNumAttributesOnPage = parseInt($('#attributesTable tr:last').data("index"))+1;
    //let allAttributes = new Array(totalNumAttributesOnPage);
    //console.log(totalNumAttributesOnPage);
    allAttributeValues = new Array(totalNumAttributesOnPage);
    allAttributeIDS = new Array(totalNumAttributesOnPage);
    for(let i = 0; i < allAttributeValues.length; i++){
        allAttributeValues[i] = [];
        allAttributeIDS[i] = [];
    }
    for(let i = 0; i < allAttributeValues.length; i++){
        let selectionString = 'selection'+i;
        $("input[name="+selectionString+"]:checked").each(function(index, input){
            allAttributeValues[i].push(input.dataset.attrvalue);
            allAttributeIDS[i].push(input.dataset.attrid);
        });
    }
    let allAttributeValuesFiltered = allAttributeValues.filter(function(el){
        return el.length > 0;
    });
    let allAttributeIDSFiltered = allAttributeIDS.filter(function(el){
        return el.length > 0;
    });
    allAttributeValues = allAttributeValuesFiltered;
    allAttributeIDS = allAttributeIDSFiltered;

    valueCombinations = generate_all_attribute_combinations(allAttributeValues);
    idCombinations = generate_all_attribute_combinations(allAttributeIDS);

    finish_products_table(valueCombinations, idCombinations);
}

// function go_back_to_part_one(){
//     $('#create-part-two').css("display", "none");
//     $('#create-part-one').css("display", "");
// }

function generate_all_attribute_combinations(allAttributeArrays){

    // First, handle some degenerate cases...
    if(!allAttributeArrays){
        // Or maybe we should toss an exception...?
        return [];
    }

    if(!Array.isArray(allAttributeArrays)){
        // Or maybe we should toss an exception...?
        return [];
    }

    if(allAttributeArrays.length == 0){
        return [];
    }

    for(let i=0; i < allAttributeArrays.length; i++){
        if(!Array.isArray(allAttributeArrays[i]) || allAttributeArrays[i].length == 0){
            // If any of the arrays in allAttributeArrays are not arrays or zero-length, return an empty array...
            return [];
        }
    }
    // Done with degenerate cases...

    // Start "odometer" with a 0 for each array in array_of_arrays.
    let odometer = new Array(allAttributeArrays.length);
    odometer.fill(0);

    let output = [];

    let newCombination = formCombination(odometer, allAttributeArrays);

    output.push(newCombination);

    while(odometer_increment(odometer, allAttributeArrays)){
        newCombination = formCombination(odometer, allAttributeArrays);
        output.push(newCombination);
    }

    return output;
}/* combineArrays() */


// Translate "odometer" to combinations from allAttributeArrays
function formCombination(odometer, allAttributeArrays){
    //In Imperative Programmingese (i.e., English):
    let s_output = "";
    for(let i=0; i < odometer.length; i++){
        s_output += " " + allAttributeArrays[i][odometer[i]];
    }
    let trim_output = s_output.trim();
    //console.log(trim_output);
    return trim_output;

    // // In Functional Programmingese (Henny Youngman one-liner):
    // return odometer.reduce(
    //     function(accumulator, odometer_value, odometer_index){
    //         return "" + accumulator + array_of_arrays[odometer_index][odometer_value];
    //     },
    //     ""
    // );
}/* formCombination() */

function odometer_increment(odometer, allAttributeArrays){

    // Basically, work you way from the rightmost digit of the "odometer"...
    // if you're able to increment without cycling that digit back to zero,
    // you're all done, otherwise, cycle that digit to zero and go one digit to the
    // left, and begin again until you're able to increment a digit
    // without cycling it...simple, huh...?

    for(let i_odometer_digit=odometer.length-1; i_odometer_digit>=0; i_odometer_digit--){

        let maxee = allAttributeArrays[i_odometer_digit].length - 1;

        if(odometer[i_odometer_digit] + 1 <= maxee){
            // increment, and you're done...
            odometer[i_odometer_digit]++;
            return true;
        }
        else{
            if(i_odometer_digit - 1 < 0){
                // No more digits left to increment, end of the line...
                return false;
            }
            else{
                // Can't increment this digit, cycle it to zero and continue
                // the loop to go over to the next digit...
                odometer[i_odometer_digit]=0;
                continue;
            }
        }
    }/* for( let odometer_digit = odometer.length-1; odometer_digit >=0; odometer_digit-- ) */

}/* odometer_increment() */


// $('#create-product-group-form').submit(
//     function (e) {
//         e.preventDefault();
//
//         const form = $(this);
//         const url = form.attr('action');
//         const data = {
//             'name': $('#name').val(),
//             'brand': $('#brand').val(),
//             'price': parseFloat($("#price").val()).toFixed(2),
//             'isPublished': $('#is-published').is(':checked'),
//             'isBigItem': $('#is-big-item').is(':checked'),
//             'isWaitlistable': $('#is-waitlistable').is(':checked'),
//             'description': $('#description').val(),
//             'categoryID': $('#categories option:selected').val()
//         }
//
//         $.ajax({
//             type: "POST",
//             url: url,
//             contentType: "application/json",
//             data: JSON.stringify(data), // serializes the form's elements.
//             beforeSend: function(xhr) {
//                 xhr.setRequestHeader(
//                     $('meta[name=_csrf_header]').attr('content'),
//                     $('meta[name=_csrf]').attr('content')
//                 )
//             },
//             success: function(data)
//             {
//                 $('#confirm-product-group-create-modal').modal('hide');
//                 $('#create-product-group-success-modal').modal('show');
//                 submit_product_forms(data['ID']);
//             },
//             error: function(xhr) {
//                 $('#confirm-product-group-create-modal').modal('hide');
//                 $('#create-product-group-fail-message').text("Error Message:\'"+ xhr.responseJSON.message +"\'")
//                 $('#create-product-group-fail-modal').modal('show');
//             }
//         });
//     }
// );

const showError = (input, message) => {
    const formField = input.parent();

    formField.find('input').css('border-color', '#dc3545');
    formField.find('textarea').css('border-color', '#dc3545');

    const error = formField.find('small');
    error.text(message);
};

const showSuccess = (input) => {
    const formField = input.parent();

    formField.find('input').css('border-color', '#28a745');
    formField.find('textarea').css('border-color', '#28a745');

    const error = formField.find('small');
    error.text('');
};

const isRequired = (value) => {return value !== ''};

const checkName = () => {
    let valid = false;
    const name = $('#name');
    if(!isRequired(name.val().trim())){
        showError(name, 'Name cannot be blank.')
    }
    else {
        showSuccess(name);
        valid = true;
    }
    return valid;
};

const checkBrand = () => {
    let valid = false;
    const brand = $('#brand');
    if(!isRequired(brand.val().trim())){
        showError(brand, 'Brand cannot be blank.')
    }
    else {
        showSuccess(brand);
        valid = true;
    }
    return valid;
};

const countDecimals = (value) => {
    if(Math.floor(value) != value){
        return value.toString().split(".")[1].length || 0;
    }
    return 0;
};

const checkPrice = () => {
    let valid = false;
    const price = $("#price");
    if(countDecimals(price.val()) > 2){
        showError(price, 'A price may not have more than 2 decimal places.');
    }
    else if(price.val().length === 0){
        showError(price, 'Price cannot be blank.');
    }
    else {
        showSuccess(price);
        valid = true;
    }
    return valid;
};

const checkDescription = () => {
    let valid = false;
    const description = $('#description');
    if(!isRequired(description.val().trim())){
        showError(description, 'Description cannot be blank.')
    }
    else {
        showSuccess(description);
        valid = true;
    }
    return valid;
};

$('#create-product-group-form').on('input', function(e){
    console.log(e);
    switch(e.target.id) {
        case 'name':
            checkName();
            break;
        case 'brand':
            checkBrand();
            break;
        case 'price':
            checkPrice();
            break;
        case 'description':
            checkDescription();
            break;
    }
});



function submit_all(){

    let isFormValid = checkName() && checkPrice() && checkBrand() && checkDescription();
    if(!isFormValid){
        $('html, body').animate({ scrollTop: 0 }, 'fast');
        return;
    }
    const url = '/management/api/product';
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
            );
            $('#wait-modal').modal('show');
        },
        success: function(data)
        {
            //$('#confirm-product-group-create-modal').modal('hide');
            //$('#create-product-group-success-modal').modal('show');
            submit_product_forms(data['ID']);
        },
        error: function(xhr) {
            console.log("Modal should hide...");
            $('#wait-modal').modal('hide');
            //$('#confirm-product-group-create-modal').modal('hide');
            $('#create-product-group-fail-message').text("Error Message:\'"+ xhr.responseJSON.message +"\'")
            $('#create-product-group-fail-modal').modal('show');
        }
    });
}

function submit_product_forms(pgid){
    let products = [];
    let numProducts = parseInt($('#finishProductsForm').data("count"));
    for(let j = 0; j < numProducts; j++){
        if(deleteTracker.includes(j+1)){
            continue;
        }
        let attributes = [];
        let arrayOfAttrIDs = $("#product-create"+(j+1)).data("attrids").toString().split(" ").map(i=>Number(i));
        for(let k = 0; k < arrayOfAttrIDs.length; k++){
            attributes.push({ "ID": arrayOfAttrIDs[k]});
        }
        const product = {
            //'imagePath': $('#imagepath').val(),
            'PGID': pgid,
            'lowStockLimit': $("#product-low-stock-limit-input"+(j+1)).val(),
            'attributes': attributes
        }
        products.push(product);
    }
    console.log(products);
    if(products.length !== 0){
        $.ajax({
            type: "POST",
            url: '/management/api/product_variant/batch',
            contentType: "application/json",
            data: JSON.stringify(products), // serializes the form's elements.
            beforeSend: function(xhr) {
                xhr.setRequestHeader(
                    $('meta[name=_csrf_header]').attr('content'),
                    $('meta[name=_csrf]').attr('content')
                )
            },
            success: function()
            {
                $('#success-redirect').attr("href", "/management/product_group/update?id="+pgid)
                $('#wait-modal').modal('hide');
                $('#create-product-group-success-modal').modal('show');
            },
            error: function(xhr) {
                $('#wait-modal').modal('hide');
                // $('#confirm-submit').modal('hide');
                $('#create-product-group-fail-message').text("Error Message:\'"+ xhr.responseJSON.message +"\'")
                $('#create-product-group-fail-modal').modal('show');
            }
        });
    }
    else {
        $('#wait-modal').modal('hide');
        $('#create-product-group-success-modal').modal('show');
    }
}

let num_attributes = 0;
let iterator_for_attribute_input = 0;
let arr_attribute_html_ids = [];
function generate_create_attribute_form(){
    num_attributes = 0;
    iterator_for_attribute_input = 0;
    arr_attribute_html_ids = [];
    $('#attributes-create-new-modal').modal('hide');
    const blankForm = `<table id="create-attribute-table" class="table table-hover">
            <tbody id="create-attribute-table-body">
              <tr>
                <td>
                  New Attribute Name (Ex. Size, Color,...)
                </td>
                <td>
                  <input type="text" id="attribute-group-name" name="attribute-group-name" class="form-control" placeholder="Ex. Size">
                </td>
              </tr>
              <tr id="first-row-replaced">
                  <td rowspan="2">No Attribute Options Entered</td>
              </tr>
            </tbody>
            <tfoot>
            <td rowspan="2"><button class="btn btn-outline-primary btn-sm space" onclick="add_input_for_attributes()">Add Attribute Option+</button></td>
            </tfoot>
          </table>`;
    $('#attributes-create-new-modal2-body').html(blankForm);
    $('#create-attributes-landing-modal').modal('hide');
    $('#attributes-create-new-modal').modal('hide');
    $('#attributes-create-new-modal2').modal('show');
}


function add_input_for_attributes(){
    if(num_attributes === 0){
        $('#first-row-replaced').html("<td>" +
            "<input type=\"text\" class=\"form-control\" id='attribute-value"+iterator_for_attribute_input+"' " +
            "name='attribute-value"+iterator_for_attribute_input+"'>" +
            "</td>" +
            "<td>" +
            "<button id='"+iterator_for_attribute_input+"' class=\"btn btn-outline-danger btn-sm space\" onclick='remove_attribute_input(this)'>Remove Attribute</button>" +
            "</td>");
    }
    else{
        $('#create-attribute-table-body').append("<tr><td>" +
            "<input type=\"text\" class=\"form-control\" id='attribute-value"+iterator_for_attribute_input+"' " +
            "name='attribute-value"+iterator_for_attribute_input+"'>" +
            "</td>" +
            "<td>" +
            "<button id='"+iterator_for_attribute_input+"' class=\"btn btn-outline-danger btn-sm space\" onclick='remove_attribute_input(this)'>Remove Attribute</button>" +
            "</td></tr>");
    }
    num_attributes++;
    arr_attribute_html_ids.push(iterator_for_attribute_input);
    iterator_for_attribute_input++;
}

function remove_attribute_input(buttonElement){
    console.log("BEGIN: " + arr_attribute_html_ids);
    let removedIdNumber = parseInt(buttonElement.id);
    buttonElement.closest('tr').remove();
    num_attributes--;
    if(num_attributes === 0){
        $('#create-attribute-table-body').append("<tr id='first-row-replaced'><td>No Attributes entered.</td></tr>");
    }
    for(let i = 0; i < arr_attribute_html_ids.length; i++) {
        if(removedIdNumber === parseInt(arr_attribute_html_ids[i])) {
            arr_attribute_html_ids.splice(i, 1);
        }
    }
    console.log("END: " + arr_attribute_html_ids);
}

function submit_attribute(){
    const data = {
        'name': $('#attribute-group-name').val().trim()
    }

    $.ajax({
        type: "POST",
        url: '/management/api/attribute_group',
        contentType: "application/json",
        data: JSON.stringify(data), // serializes the form's elements.
        beforeSend: function(xhr) {
            xhr.setRequestHeader(
                $('meta[name=_csrf_header]').attr('content'),
                $('meta[name=_csrf]').attr('content')
            );
            $('#attributes-create-new-modal2').modal('hide');
            $('#wait-modal').modal('show');
        },
        success: function(data)
        {
            submit_attributes(data['ID']);
        },
        error: function(xhr) {
            $('#confirm-create-attribute-group-modal').modal('hide');
            $('#create-attribute-group-fail-message').text("Error Message:\'"+ xhr.responseJSON.message +"\'")
            $('#create-attribute-group-fail-modal').modal('show');
        }
    });
}

function submit_attributes(agID){
    let attributes = [];
    for(let i = 0; i < arr_attribute_html_ids.length; i++){
        attributes.push({
            'attributeGroupID': agID,
            "value": $('#attribute-value'+arr_attribute_html_ids[i]+'').val()
        });
    }
    console.log(attributes);
    if(attributes.length !== 0){
        $.ajax({
            type: "POST",
            url: '/management/api/attribute/batch',
            contentType: "application/json",
            data: JSON.stringify(attributes), // serializes the form's elements.
            beforeSend: function(xhr) {
                xhr.setRequestHeader(
                    $('meta[name=_csrf_header]').attr('content'),
                    $('meta[name=_csrf]').attr('content')
                )
            },
            success: function()
            {
                $('#wait-modal').modal('hide');
            },
            error: function(xhr) {
                console.log("Modal Should Hide");
                $('#wait-modal').modal('hide');
                // $('#confirm-submit').modal('hide');
                $('#create-product-group-fail-message').text("Error Message:\'"+ xhr.responseJSON.message +"\'")
                $('#create-product-group-fail-modal').modal('show');
            }
        });
    }
    else {
        $('#wait-modal').modal('hide');
    }
}

