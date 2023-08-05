<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<meta charset="ISO-8859-1">

<head>
<title>Login Page</title>
<style>
input {
	border-radius: 12px;
	border: 2px solid #808080;
	padding: 10px;
	width: 200px;
	height: 15px;
}

.button-9 {
	appearance: button;
	backface-visibility: hidden;
	background-color: #405cf5;
	border-radius: 6px;
	border-width: 0;
	box-shadow: rgba(50, 50, 93, .1) 0 0 0 1px inset, rgba(50, 50, 93, .1) 0
		2px 5px 0, rgba(0, 0, 0, .07) 0 1px 1px 0;
	box-sizing: border-box;
	color: #fff;
	cursor: pointer;
	font-family: -apple-system, system-ui, "Segoe UI", Roboto,
		"Helvetica Neue", Ubuntu, sans-serif;
	font-size: 100%;
	height: 25px;
	line-height: 1.15;
	margin: 12px 0 0;
	outline: none;
	overflow: hidden;
	padding: 0 25px;
	position: relative;
	text-align: center;
	text-transform: none;
	transform: translateZ(0);
	transition: all .2s, box-shadow .08s ease-in;
	user-select: none;
	-webkit-user-select: none;
	touch-action: manipulation;
	width: 60%;
}

.button-9:disabled {
	cursor: default;
}

.button-9:focus {
	box-shadow: rgba(50, 50, 93, .1) 0 0 0 1px inset, rgba(50, 50, 93, .2) 0
		6px 15px 0, rgba(0, 0, 0, .1) 0 2px 2px 0, rgba(50, 151, 211, .3) 0 0
		0 4px;
}
</style>
</head>
<body>
	<form action="<%=request.getContextPath()%>/loginServlet" method="post">
		<Center>
			<h1>Assignment</h1>
			<h2>Login Page</h2>
			<table>
				<tr>
					<td><input type="text" id="login_id" placeholder="login id"
						name="login_id" required></td>
				</tr>
				<tr>
					<td><br> <input type="password" id="password"
						placeholder="password" name="password" required></td>
				</tr>
				<tr>
					<td><button class="button-9" role="button">Submit</button></td>
				</tr>
			</table>
		</Center>
	</form>
</body>
</html>



