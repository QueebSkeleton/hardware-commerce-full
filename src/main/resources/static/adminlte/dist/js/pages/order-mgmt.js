// TODO: Not yet robust :) Fix soon.

/* DEPENDENCIES
 * - jQuery
 * - orderTableCard : CardBasedPageableTable object
 * - Sweet Alert 2 JS
*/

var orderTableCard = new CardBasedPageableTable("#order-table-card", "orders/table", "placedOn", "asc");

//Delete Button Click (popup confirmation modal)
$("div#order-table-card > div.card-content").on("click", "button.btn-delete", function() {
    
	// Populate delete-modal with the proper message
	// Set Confirm Delete Button href attribute to proper link with id parameter
	// Order details for now is from the table itself (too reliant on a specific html structure)
	
	var modalContent = $("div#delete-modal > div.modal-dialog > div.modal-content")
    $(modalContent).find("b#order-info").html($(this).data("info"));
    var confirmDeleteLink = $(modalContent).find("div.modal-footer > a#confirm-delete-link");
    
    // Temporarily disable confirm delete link, for setting proper links
    $(confirmDeleteLink).addClass("disabled");
    $(confirmDeleteLink).attr("href", "orders/delete?id=" + $(this).data("id"));
    // Re-enable
    $(confirmDeleteLink).removeClass("disabled");
});