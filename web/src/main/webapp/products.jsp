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
				<td><c:out value="${item.name}" /></td>
				<td><c:out value="${item.category}" /></td>
				<td><c:out value="${item.unit}" /></td>
				<td><c:out value="${item.purchasePrice}" /></td>
				<td><c:out value="${item.salePrice}" /></td>
				<td><c:out value="${item.description}" /></td>
			</tr>
		</c:forEach>
	</table>
	<form id="productsform" action="/warehouse/api/ProductService/getProducts" method="get">
		<button id="refreshbutton" type="submit">Refresh</button>
	</form>
	<script src="resources/scripts/products.js"></script>
</body>
</html>