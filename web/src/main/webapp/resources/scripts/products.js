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
				str = str.concat("<tr><td>" + result[i].id + "</td><td>"
						+ result[i].name + "</td><td>"
						+ result[i].category.name+ "</td><td>"
						+ result[i].unit.name+ "</td><td>"
						+ result[i].quantity + "</td><td>"
						+ result[i].purchasePrice + "</td><td>"
						+ result[i].salePrice + "</td><td>"
						+ result[i].description + "</td><tr>");
			}

			$("tbody").append(str);

		},

		error : function(e) {
			console.log("Nem sikerült lekérni az adatokat!:(");
		}

	});
	
	paginator();

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

		},

		error : function(e) {
			console.log("Nem sikerült lekérni az adatokat!:(");
		}

	});
	
	paginator();

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
		
	
		$('#currentPage').val(currentpage*1+1);
	

	callServlet();
}

function paginator() {
	
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
			
			console.log("paginator");
			
			buttons="";
			
			for (i = 1; i < result+1; i++) {
				 //console.log($("form").find("#paginator"));
				 $("form").find("#paginator").remove();
				 buttons = buttons.concat("<span id='paginator'>");
				 buttons = buttons.concat(
						" <button class='btn btn-primary "+i+"' type='submit'>"+i+"</button>\n"
				 );
				 buttons = buttons.concat("</span>");
				 //console.log(i);
				
				 
			}
			 console.log(currentpage);
			
			$("#Previous").after(buttons);
			
			

		},

		error : function(e) {
			console.log("Nem sikerült lekérni az adatokat!:(");
		}

	});
}



