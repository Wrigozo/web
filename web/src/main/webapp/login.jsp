<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
<script src="https://kit.fontawesome.com/a9139e4db1.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="resources/styles/login.css">

</head>
<body>

	<div id=table>
		<form class="form-signin" action="/warehouse/login" method="post">


			<h1 id="title">
				<i class="fas fa-user"><span> User Login</span></i>
			</h1>

			<input name="username" type="text" id="inputUser"
				class="form-control" placeholder="&#xf007; user name" autofocus>
			<input name="password" type="password" id="inputPassword"
				class="form-control" placeholder="&#xf084; password" required>
			<p>
				<%
					if (session.getAttribute("authenticated") != null) {
						if (session.getAttribute("authenticated").equals(false)) {
							out.print("Érvénytelen belépési adatok!");
						}
					}
				%>
			</p>
			<a href="#" id="forgot">forgot password</a>
			<button id="loginbutton" class="btn btn-lg btn-primary btn-block"
				type="submit">Login</button>

		</form>
	</div>

</body>
</html>