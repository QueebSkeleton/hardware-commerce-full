// Page Utils JS
// For adding utility elements such as loading overlays, etc.

/*
 * DEPENDENCIES
 * jQuery
 * Font Awesome CSS
 */

// Add loading overlay to specified element
var addLoadingOverlay = function(element) {
	var overlay = "<div class='overlay'><i class='fas fa-2x fa-sync-alt fa-spin'></i></div>";
	$(element).append(overlay);
}

// Remove overlay from element
var removeOverlay = function(element) {
	$(element + " > div.overlay").remove();
}