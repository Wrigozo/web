function callServlet() {

	event.preventDefault();

	$.ajax({
		type : "GET",

		url : "api/ProductService/getProducts",

		datatype : "json",

		success : function(result) {

			$("tbody").find("tr").remove();

			data = result;
			str = ""
			for (i = 0; i < result.length; i++) {
				 console.log(result);
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

}
