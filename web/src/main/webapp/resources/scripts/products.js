function callServlet() {

	event.preventDefault();
	
	var currentpage = $('#currentPage').val();
	var recordsperpage = $('#recordsPerPage').val();
	
	$.ajax({
		type : "GET",

		url : "api/ProductService/getProducts",
		
		data : {
			currentPage : currentpage,
			recordsPerPage : recordsperpage
		},

		datatype : "json",

		success : function(result) {

			$("tbody").find("tr").remove();

			data = result;
			str = ""
			for (i = 0; i < result.length; i++) {
				 console.log(result[i]);
				str = str.concat("<tr><td>"
						+ result[i].name + "</td><td>"
						+ result[i].category.name+ "</td><td>"
						+ result[i].unit.name+ "</td><td>"
						+ result[i].quantity + "</td><td>"
						+ result[i].purchasePrice + "</td><td>"
						+ result[i].salePrice + "</td><td>"
						+ result[i].description + "</td><tr>");
			}

			$("tbody").append(str);
			
			paginator();
		},

		error : function(e) {
			console.log("Nem sikerült lekérni az adatokat!:(");
		}

	});

}

$(function() {
	
	$.ajax({
		type : "GET",

		url : "api/ProductService/getCategories",

		datatype : "json",

		success : function(result) {
			str = "<option value='all' selected>összes</option>"
			for (i = 0; i < result.length; i++) {
				 console.log(result[i].name);
				str = str.concat(
						"<option value='result[i].name'>"+result[i].name+"</option>"
				);
			}

			$("#categorylist").append(str);
			$('#currentPage').val(1);
			paginator();

		},

		error : function(e) {
			console.log("Nem sikerült lekérni az adatokat!:(");
		}

	});

})

function previous() {
	
	event.preventDefault();
	
	var currentpage = $('#currentPage').val();
	
	if(currentpage>1){
		$('#currentPage').val(currentpage*1-1);
		callServlet();
	}

}

function next() {

	event.preventDefault();
	
	var currentpage = $('#currentPage').val();
	var recordsperpage = $('#recordsPerPage').val();
		
	$.ajax({
		type : "GET",

		url : "api/ProductService/getNumberOfPages",

		datatype : "json",
		
		data : {
			currentPage : currentpage,
			recordsPerPage : recordsperpage
		},

		success : function(result) {
				
				if(currentpage<result){
					$('#currentPage').val(currentpage*1+1);
					callServlet();
				}
		}
	});	

	

	callServlet();
}

function paginator() {
	
	event.preventDefault();
	
	var currentpage = $('#currentPage').val();
	var recordsperpage = $('#recordsPerPage').val();
	
	console.log(currentpage+" "+recordsperpage);
	
	$.ajax({
		type : "GET",

		url : "api/ProductService/getNumberOfPages",

		datatype : "json",
		
		data : {
			currentPage : currentpage,
			recordsPerPage : recordsperpage
		},

		success : function(result) {
			console.log("max number of pages "+result);
			buttons="";
			$("span").remove();
			for (i = 1; i < result+1; i++) {
				console.log(i);
				 
				buttons = buttons.concat("<span id='paginator'>");
				buttons = buttons.concat(
						" <button class='btn btn-primary' type='submit' onclick='page("+i+")'>"+i+"</button>\n"
				);
				buttons = buttons.concat("</span>");
				console.log(buttons);
				
			}
			
			$("#Previous").after(buttons);

		},

		error : function(e) {
			console.log("Nem sikerült lekérni az adatokat!:(");
		}

	});
}

function page(value) {
	
	event.preventDefault();

	$('#currentPage').val(value);
	
	callServlet();
}

function first() {
	
	event.preventDefault();

	$('#currentPage').val(1);
	
	callServlet();
}

function last() {
	
	event.preventDefault();
	
	var currentpage = $('#currentPage').val();
	var recordsperpage = $('#recordsPerPage').val();
	
	$.ajax({
		type : "GET",

		url : "api/ProductService/getNumberOfPages",

		datatype : "json",
		
		data : {
			currentPage : currentpage,
			recordsPerPage : recordsperpage
		},

		success : function(result) {
				
				$('#currentPage').val(result);
				callServlet();
		}
	});	
}



