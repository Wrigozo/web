<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
</head>
<body>
	<table>
		<c:forEach items="${sessionScope.products}" var="item">
			<tr>
				<td class="name"><c:out value="${item.name}" /></td>
				<td class="category"><c:out value="${item.category}" /></td>
				<td class="unit"><c:out value="${item.unit}" /></td>
				<td class="purchasePrice"><c:out value="${item.purchasePrice}" /></td>
				<td class="salePrice"><c:out value="${item.salePrice}" /></td>
				<td class="description"><c:out value="${item.description}" /></td>
			</tr>
		</c:forEach>
	</table>

	<button id="refreshbutton" type="submit"  onclick="callServlet()">Refresh</button>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script src="resources/scripts/products.js"></script>
</body>
</html>