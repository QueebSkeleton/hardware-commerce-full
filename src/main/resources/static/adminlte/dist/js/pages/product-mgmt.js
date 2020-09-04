// TODO: Not yet robust :) Fix soon.

/* DEPENDENCIES
 * - jQuery
 * - categoryTableCard : CardBasedPageableTable object
 * - Sweet Alert 2 JS
*/

var productTableCard = new CardBasedPageableTable("#product-table-card", "products/table", "name", "asc");

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
    	},
		error: function(xhr, testStatus, errorThrown) {
			$("div#delete-modal").modal("hide");
			CrudNotifToast.fire({
				icon: 'success',
				title: 'An error has occured while trying to remove Category. ' 
					+ 'If the problem persists after trying again, please contact support.'
			});
		}
    });
});