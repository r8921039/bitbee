<html>
	<head>
		<meta name="layout" content="main" />
		<title>login</title>
		<link href="${resource(dir: 'css', file: 'signin.css')}" rel="stylesheet">
	</head>
	
	<body>
		<div class="container">		
			<h2 class="form-signin-heading">Yeh, Logged In!</h2>
			<g:form class="form-signin" role="form" url="/bitbee/login/logout" method="POST">
				<button class="btn btn-lg btn-primary btn-block" type="submit">Log out</button>
			</g:form>
		</div>
	</body>
</html>