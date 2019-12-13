function callServlet() {

	event.preventDefault();

	$.ajax({
		type : "GET",

		url : "api/ProductService/getProducts",

		datatype : "json",

		success : function(result) {
			document.write("<table>");
			for (i = 0; i < 3; i++) {
				document.write("<tr>");

				document.write("<td>", result[i].name, "</td>");
				document.write("<td>", result[i].category, "</td>");
				document.write("<td>", result[i].unit, "</td>");
				document.write("<td>", result[i].purchasePrice, "</td>");
				document.write("<td>", result[i].salePrice, "</td>");
				document.write("<td>", result[i].description, "</td>");

				document.write("</tr>");
			}
			document.write("</table>");

		},

		error : function(e) {
			console.log("Error");
		}

	});

}
