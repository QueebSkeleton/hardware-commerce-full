// Product IDs selected on table
var productIdsOnTable = [];

// Placed By Select Form - Select2 SSP
$("select[name='placedById']").select2({
	theme : 'bootstrap4',
	width : '100%',
	placeholder : "Pick a user",
	ajax : {
		url : "/admin/orders/add-custom-form/users",
		delay : 1000,
		data : function(params) {
			var parameters = {
				page : params.page || 0,
				size : 20,
				sort : "firstName,asc",
				search : params.term
			}

			return parameters;
		}
	}
});

// Product Select Form - Select2 SSP
$("select#productId").select2({
	theme : 'bootstrap4',
	width : '100%',
	placeholder : "Add an item to this order",
	ajax : {
		url : "/admin/orders/add-custom-form/products",
		delay : 1000,
		data : function(params) {
			var parameters = {
				page : params.page || 0,
				size : 20,
				sort : "name,asc",
				search : params.term
			}

			return parameters;
		}
	}
});

// Product ID Select On Change
$("select#productId").change(function() {
	var select = $(this);
	var id = parseInt($(this).val());
	if(!productIdsOnTable.includes(id))
		$.ajax({
			type : "GET",
			url : "/admin/orders/add-custom-form/order-item-row",
			dataType : "html",
			data : {
				productId: $(this).val(),
				itemIndex: productIdsOnTable.length
			},
			success : function(data) {
				$("#order-items-table > tbody").append(data);
				productIdsOnTable.push(id);
				updateTotal();
			}
		});
});

// Order Item Quantity On Change
$(document).on(
	"change",
	"table#order-items-table > tbody > tr > td.quantity-column > input",
	function() {
		var unitPrice = parseFloat($(this).parent().parent().find(
				"td.unit-price-column").data("unit-price"));
		var quantity = parseInt($(this).val());
		$(this).parent().parent().find("td.subtotal-column").find(
				"input[name='subtotal']").val(
				(unitPrice * quantity).toFixed(2));

		updateTotal();
});

// Delete Item Button On Click
$(document).on("click", "button.btn-delete-item", function() {
	var row = $(this).parent().parent();
	var productIdToRemove = parseInt($(row).data("product-id"));
	productIdsOnTable = productIdsOnTable.filter(function(value) {
		return value !== productIdToRemove;
	});
	$(row).remove();
	updateTotal();
});

function updateTotal() {
	var total = 0;
	$.each($("table#order-items-table > tbody").children(), function(index,
			value) {
		total += parseFloat($(value).find("td.subtotal-column").find(
				"input[name='subtotal']").val());
	});
	$("input#total").val(total.toFixed(2));
}