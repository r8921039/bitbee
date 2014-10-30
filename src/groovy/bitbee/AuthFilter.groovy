package bitbee

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession

import org.codehaus.groovy.grails.plugins.springsecurity.RequestHolderAuthenticationFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.util.TextEscapeUtils

class AuthFilter extends RequestHolderAuthenticationFilter {

	@Override
	Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) 
			throws AuthenticationException {
		String username = obtainUsername(request)?.trim()
		String password = obtainPassword(request)

		Authentication auth = new Auth(username, password)

		HttpSession session = request.getSession(false)

		if (session || getAllowSessionCreation()) {
			request.session[SPRING_SECURITY_LAST_USERNAME_KEY] = TextEscapeUtils.escapeEntities(username)
		}

		setDetails(request, auth)
		authenticationManager.authenticate(auth)
	}

}
