$(document).ready(function() {
	$("[name='delete']").on("click", function() {
		if(!confirm("Are you sure?")) return;

		$.get("/item/delete/" + $(this).attr("sn"), function() {
			alert("Item delete!.");
			location.reload();
		});
	});

	$("[name='edit']").on("click", function() {
		$.get( "/item/" + $(this).attr("sn"), function(item) {
			$("#serialNumber").val(item.serialNumber);
			$("#description").val(item.description);
			
			var d = moment(item.buyDate);
			$("#buyDate").val(d.format("D/M/YYYY"));
			
			$('#item').modal('show') 
		});
	});
	
	$("[name='new']").on("click", function() {
		$("#serialNumber").val("");
		$("#description").val("");
		
		var d = moment();
		$("#buyDate").val(d.format("D/M/YYYY"));
		
		$('#item').modal('show') 
	});

	$("#save").on("click", function() {
		$('#item').modal('hide');

		var d = moment($("#buyDate").val(), "D/M/YYYY");
		$("#buyDate").val(d.format("M/D/YYYY"));
		
		if($("#serialNumber").val().length <= 0) {
			// New Item
			$.post("/item/new", $("#itemData").serialize(), function() {
				alert("Item Saved.");
				location.reload();
			});
		} else {
			// Update Item
			$.post("/item/edit", $("#itemData").serialize(), function() {
				alert("Item Saved.");
				location.reload();
			});
		}
	});
});