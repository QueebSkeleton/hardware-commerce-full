var pageNumber = 0;

$.ajax({
	type : "GET",
	url : "products",
	dataType : "html",
	success : function(data) {
		$("#store > #store-products").remove();
		$("#store > #store-filter").remove();
		$("#store").append(data);
	}
});

function refreshProductsView() {
	// Get Category IDs
	var categoryIds = [];
	$("input[name='categoriesFilter']:checkbox:checked").each(function() {
		categoryIds.push($(this).val());
	});

	// Get Vendor IDs
	var vendorIds = [];
	$("input[name='vendorsFilter']:checkbox:checked").each(function() {
		vendorIds.push($(this).val());
	});

	$.ajax({
		type : "GET",
		url : "products",
		dataType : "html",
		traditional : true,
		data : {
			page : pageNumber,
			size : $("select[name='productViewSize']").val(),
			categoryIds : categoryIds,
			vendorIds : vendorIds,
			minPrice : $("#price-min").val(),
			maxPrice : $("#price-max").val(),
		},
		success : function(data) {
			$("#store > #store-products").remove();
			$("#store > #store-filter").remove();
			$("#store").append(data);
		}
	});
	
	document.getElementById("aside-title").scrollIntoView();
}

// Categories Filter Tick Event
$("input[name='categoriesFilter']:checkbox").change(function() {
	refreshProductsView();
});

// Vendors Filter Tick Event
$("input[name='vendorsFilter']:checkbox").change(function() {
	refreshProductsView();
});

// Min Price Change
$("#price-min").change(function() {
	refreshProductsView();
});

// Max Price Change
$("#price-max").change(function() {
	refreshProductsView();
});

// Page Size Change Event
$("select[name='productViewSize']").change(function() {
	refreshProductsView();
});

// Page Number Click Event
$(document).on("click", ".store-pagination > li.page-number", function() {
	pageNumber = $(this).data("page-number");
	refreshProductsView();
});

// Prev Page Click Event
$(document).on("click", ".store-pagination > li.prev-page", function() {
	pageNumber--;
	refreshProductsView();
});

// Next Page Click Event
$(document).on("click", ".store-pagination > li.next-page", function() {
	pageNumber++;
	refreshProductsView();
});

var sliderTimeout;

// Price Slider Change Event
document.getElementById('price-slider').noUiSlider.on('update', function(
		values, handle) {
	clearTimeout(sliderTimeout);
	sliderTimeout = setTimeout(refreshProductsView, 500);
});