class CardBasedPageableTable {
		
	constructor(tableCardId, tableResource, initSortCol, initSortDir) {
		// Container ID to place generated HTML content
		this.tableCardId = tableCardId;
		
		// URL of table generator
		this.tableResource = tableResource;
		
		// Initialize pageable table to default values
		this.page = 0;
		this.size = 20;
		$(this.tableCardId).find("select[name='pageSize']").val(this.size);
		
		// Sort initialized to first sortable field of table (with data-column-field attr)
		this.sortCol = initSortCol;
		this.sortDir = initSortDir;
		
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
    	
    	// Sortable Column Click Event
    	$(this.tableCardId).on("click", "th.sortable", function() {
    		var clickedField = $(this).data("column-field");
    		
    		if(table.sortCol === clickedField)
    			if(table.sortDir === "asc")
    				table.sortDir = "desc";
    			else
    				table.sortDir = "asc";
    		
    		else {
    			table.sortCol = clickedField;
    			table.sortDir = "asc";
    		}
    		
    		table.render();
    	});
		
		// Bind keyup event to search input (with resetting 300ms delay for each key)
		var searchTimeoutCall;
		
		$(this.tableCardId).on("keyup", "input[name='search']", function() {
			clearTimeout(searchTimeoutCall);
			
			var inputSearch = this;
			
			searchTimeoutCall = setTimeout(function() {
				table.search = $(inputSearch).val();
				table.render();
			}, 300);
		});
	}
	
	render() {
		var table = this;
		
		// Add Loading overlay
		addLoadingOverlay(table.tableCardId);
		
		$.ajax({
			type: "GET",
			url: this.tableResource,
			dataType: "html",
			data: {
				"page": this.page,
				"size": this.size,
				"sort": this.sortCol + "," + this.sortDir,
				"search": this.search
			},
			success: function(data) {
				$(table.tableCardId + " > div.card-content").empty();
				$(table.tableCardId + " > div.card-content").append(data);
				removeOverlay(table.tableCardId);
			}
		});
	}
	
}