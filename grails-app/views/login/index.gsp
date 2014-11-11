<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main" />
<title>login</title>
<link href="${resource(dir: 'css', file: 'signin.css')}" rel="stylesheet">
</head>

<body>
	<div class="container">
   		<g:form class="form-signin" role="form" url="${postURL}" method="POST">
			<h2 class="form-signin-heading">Please sign in</h2>
			<input type="email" name="username" class="form-control" placeholder="email address" required autofocus> <input type="password" name="password" class="form-control" placeholder="password" required>
 
			<div class="checkbox">
				<label> 
					<input name='${rememberMeParameter}' type="checkbox" <g:if test='${hasCookie}'>checked='checked'</g:if>>Remember me</input>
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
		</g:form>

	</div>
</html>
