// TODO: Not yet robust :) Fix soon.

/* DEPENDENCIES
 * - jQuery
 * - categoryTableCard : CardBasedPageableTable object
 * - Sweet Alert 2 JS
*/

// Add Button Click
$("a[data-target='#add-update-modal']").click(function() {
	
	// Request Server for empty Category Modal Form,
	// then populate add-update-modal with response html data
	$.ajax({
		type: "GET",
		url: "categories/add-modal-form",
		dataType: "html",
		success: function(data) {
			$("div#add-update-modal > div.modal-dialog").html(data);
		}
	});
	
});

// Update Button Click
$("div#category-table-card > div.card-content").on("click", "button.btn-update", function() {
	
	// Request Server for Category Modal form given its database id,
	// then populate add-update-modal with response html data
	
	// ID is set as data attribute on the button
	$.ajax({
		type: "GET",
		url: "categories/update-modal-form",
		dataType: "html",
		data: {
			"id": $(this).data("id")
		},
		success: function(data) {
			$("div#add-update-modal > div.modal-dialog").html(data);
		}
	});
});

// Delete Button Click (popup confirmation modal)
$("div#category-table-card > div.card-content").on("click", "button.btn-delete", function() {
    
	// Populate delete-modal with the proper message
	// Set Confirm Delete Button href attribute to proper link with id parameter
	// Category details for now is from the table itself (too reliant on a specific html structure)
	
	var modalContent = $("div#delete-modal > div.modal-dialog > div.modal-content")
    $(modalContent).find("b#category-name").html($(this).data("name"));
    var confirmDeleteLink = $(modalContent).find("div.modal-footer > a#confirm-delete-link");
    
    // Temporarily disable confirm delete link, for setting proper links
    $(confirmDeleteLink).addClass("disabled");
    $(confirmDeleteLink).attr("href", "categories/delete?id=" + $(this).data("id"));
    // Re-enable
    $(confirmDeleteLink).removeClass("disabled");
});

// Confirm Delete Click
$("div#delete-modal > div.modal-dialog > div.modal-content > div.modal-footer > a#confirm-delete-link").click(function(e) {
    // Prevent Link from being processed in default
	e.preventDefault();
	
    // Run a DELETE request for the Category (somehow RESTful), giving ID as parameter
	// ID is set properly on the url (href attribute) after table delete button is clicked
    $.ajax({
    	type: "DELETE",
    	url: $(this).attr("href"),
    	success: function() {
			$("div#delete-modal").modal("hide");
			categoryTableCard.render();
			CrudNotifToast.fire({
				icon: 'success',
				title: 'Category was successfully removed.'
			});
    	}
    });
});

// Add-Update Form Submit
$("div#add-update-modal").on("submit", "#add-update-form", function(e) {
	// Prevent default form submit action
	e.preventDefault();
	
	// Create POST request containing all category data from the form
	$.ajax({
		type: $(this).attr("method"),
		url: $(this).attr("action"),
		data: $(this).serialize(),
		success: function() {
			$("div#add-update-modal").modal("hide");
			categoryTableCard.render();
			CrudNotifToast.fire({
				icon: 'success',
				title: 'Category was successfully saved.'
			});
		}
	});
});