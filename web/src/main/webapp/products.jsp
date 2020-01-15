<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<style>
.view {
	background: white;
	border-radius: 1em;
	padding: 2em;
	box-shadow: 0px 0px 3px 0.8em rgb(18, 102, 144);
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
}
</style>
</head>
<body>
	<div class="view">
		<form>
			<div class="bd-example">

				<input id="search" type="search" placeholder="Search...">

				<div class="btn-group">
					<select class="form-control" id="category"></select>
				</div>
				<div class="btn-group">
					<select id="unit" class="form-control">
						<option value="" selected><c:out value="---" /></option>
						<c:forEach items="${sessionScope.units}" var="item">
							<option value="<c:out value="${item.name}" />"><c:out
									value="${item.name}" /></option>
						</c:forEach>
					</select>
				</div>

				<div class="btn-group">
					<input type="hidden" id="currentPage" value="1"> <select
						class="form-control" id="recordsPerPage">
						<option selected>5</option>
						<option>10</option>
						<option>20</option>
						<option>50</option>
					</select>
				</div>

				<button class="btn btn-primary" id="refreshbutton" type="submit"
					onclick="callServlet()">Refresh</button>

			</div>
			<br>
			<table class="table table-hover table-striped table-responsive-lg">
				<thead>
					<tr>
						<th>Name</th>
						<th>Category</th>
						<th>Unit</th>
						<th>Quantity</th>
						<th>Purchase Price</th>
						<th>Sale Price</th>
						<th>Description</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${sessionScope.products}" var="item">
						<tr>
							<td class="name"><c:out value="${item.name}" /></td>
							<td class="category"><c:out value="${item.category.name}" /></td>
							<td class="unit"><c:out value="${item.unit.name}" /></td>
							<td class="quantity"><c:out value="${item.quantity}" /></td>
							<td class="purchasePrice"><c:out
									value="${item.purchasePrice}" /></td>
							<td class="salePrice"><c:out value="${item.salePrice}" /></td>
							<td class="description"><c:out value="${item.description}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<button class="btn btn-primary" type="submit" onclick="first()">First</button>
			<button class="btn btn-primary" id="Previous" type="submit"
				onclick="previous()">Previous</button>
			<button class="btn btn-primary" id="Next" type="submit"
				onclick="next()">Next</button>
			<button class="btn btn-primary" type="submit" onclick="last()">Last</button>
		</form>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossOrigin="anonymous"></script>
	<script src="resources/scripts/products.js"></script>

	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="resources/styles/main.css">

	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</body>
</html>