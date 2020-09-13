// TODO: Not yet robust :) Fix soon.

/* DEPENDENCIES
 * - jQuery
 * - administratorTableCard : CardBasedPageableTable object
 * - Sweet Alert 2 JS
*/

var administratorTableCard = new CardBasedPageableTable("#administrator-table-card", "administrators/table", "firstName", "asc");

// Add Button Click
$("a#btn-add").click(function() {
	
	// Request Server for empty Category Modal Form,
	// then populate add-update-modal with response html data
	$.ajax({
		type: "GET",
		url: "administrators/add-modal-form",
		dataType: "html",
		success: function(data) {
			$("div#add-update-modal > div.modal-dialog").empty();
			$("div#add-update-modal > div.modal-dialog").append(data);
		},
		error: function(xhr, textStatus, errorThrown) {
			if(xhr.status === 400)
				CrudNotifToast.fire({
					icon: 'error',
					title: 'An error has occured. Please refresh the page and try again.'
				});
		}
	});
	
});

// Update Button Click
$("div#administrator-table-card > div.card-content").on("click", "button.btn-update", function() {
	
	// Request Server for administrator Modal form given its database id,
	// then populate add-update-modal with response html data
	
	// ID is set as data attribute on the button
	$.ajax({
		type: "GET",
		url: "administrators/update-modal-form",
		dataType: "html",
		data: {
			"id": $(this).data("id")
		},
		success: function(data) {
			$("div#add-update-modal > div.modal-dialog").empty();
			$("div#add-update-modal > div.modal-dialog").append(data);
		},
		error: function(xhr, textStatus, errorThrown) {
			if(xhr.status === 400)
				CrudNotifToast.fire({
					icon: 'error',
					title: 'An error has occured. Please refresh the page and try again.'
				});
		}
	});
});

// Delete Button Click (popup confirmation modal)
$("div#administrator-table-card > div.card-content").on("click", "button.btn-delete", function() {
    
	// Populate delete-modal with the proper message
	// Set Confirm Delete Button href attribute to proper link with id parameter
	// Category details for now is from the table itself (too reliant on a specific html structure)
	
	var modalContent = $("div#delete-modal > div.modal-dialog > div.modal-content")
    $(modalContent).find("b#administrator-name").html($(this).data("name"));
    var confirmDeleteLink = $(modalContent).find("div.modal-footer > a#confirm-delete-link");
    
    // Temporarily disable confirm delete link, for setting proper links
    $(confirmDeleteLink).addClass("disabled");
    $(confirmDeleteLink).attr("href", "administrators/delete?id=" + $(this).data("id"));
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
			administratorTableCard.render();
			CrudNotifToast.fire({
				icon: 'success',
				title: 'Administrator was successfully removed.'
			});
    	},
		error: function(xhr, testStatus, errorThrown) {
			$("div#delete-modal").modal("hide");
			CrudNotifToast.fire({
				icon: 'success',
				title: 'An error has occured while trying to remove administrator. ' 
					+ 'If the problem persists after trying again, please contact support.'
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
			administratorTableCard.render();
			CrudNotifToast.fire({
				icon: 'success',
				title: 'Administrator was successfully saved.'
			});
		},
		error: function(xhr, testStatus, errorThrown) {
			$("div#add-update-modal").modal("hide");
			CrudNotifToast.fire({
				icon: 'success',
				title: 'An error has occured while trying to save administrator.' 
					+ 'If the problem persists after trying again, please contact support.'
			});
		}
	});
});