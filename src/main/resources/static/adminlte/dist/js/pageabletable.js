class CardBasedPageableTable {
		
	constructor(tableCardId, tableResource) {
		// Container ID to place generated HTML content
		this.tableCardId = tableCardId;
		
		// URL of table generator
		this.tableResource = tableResource;
		
		// Initialize pageable table to default values
		this.page = 0;
		this.size = 20;
		
		// Sort initialized to first sortable field of table (with data-column-field attr)
		var sortCol = $(tableCardId).find("table > thead > tr > th.sortable").data("column-field");
		this.sort = sortCol + ",asc";
		
		this.initComponents();
		
		this.render();
	}
	
	initComponents() {
		var table = this;
		// Bind change event to size select
		$(this.tableCardId).on("change", "select[name='pageSize']", function() {
			table.size = parseInt($(this).val());
			table.render();
		});
		
		// Bind page click events
		$(this.tableCardId).on("click", ".page-number", function() {
			table.page = parseInt($(this).data("page-number"));
			table.render();
    	});
    	
    	// Previous Page Click Event
    	$(this.tableCardId).on("click", "#prev-page", function() {
    		table.page--;
    		table.render();
    	});
    	
    	// Next Page Click Event
    	$(this.tableCardId).on("click", "#next-page", function() {
    		table.page++;
    		table.render();
    	});
		
		// Bind keyup event to search input (with resetting 300ms delay for each key)
		var searchTimeoutCall;
		
		$(this.tableCardId).on("keyup", "input[name='search']", function() {
			clearTimeout(searchTimeoutCall);
			
			searchTimeoutCall = setTimeout(function() {
				search = $(this).val();
				render();
			});
		});
	}
	
	render() {
		var table = this;
		
		$.ajax({
			type: "GET",
			url: this.tableResource,
			dataType: "html",
			data: {
				"page": this.page,
				"size": this.size,
				"sort": this.sort,
				"search": this.search
			},
			success: function(data) {
				$(table.tableCardId + " > div.card-body").remove();
				$(table.tableCardId + " > div.card-footer").remove();
				$(table.tableCardId).append(data);
			}
		})
	}
	
}