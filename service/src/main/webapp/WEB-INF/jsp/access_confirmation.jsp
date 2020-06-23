<html>
<body>
	<h1>OAuth Approval</h1>
	<p>Do you authorize "clientapp" to access your protected resources?</p>
	<form id="confirmationForm" name="confirmationForm"
		action="/oauth/authorize" method="post">
		<input name="user_oauth_approval" value="true" type="hidden" />
		<ul>
			<li><div class="form-group">
					scope.read_profile: <input type="radio" name="scope.read_profile"
						value="true">Approve</input> <input type="radio"
						name="scope.read_profile" value="false" checked>Deny</input>
				</div></li>
		</ul>
		<label><input name="authorize" value="Authorize" type="submit" /></label>
	</form>
</body>
</html>