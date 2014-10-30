package bitbee

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.encoding.PasswordEncoder
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.GrantedAuthorityImpl
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsChecker
import org.springframework.security.core.userdetails.UsernameNotFoundException

class AuthProvider implements AuthenticationProvider {

	@Override
	Authentication authenticate(Authentication authentication) throws AuthenticationException {
		 // use withTransaction to avoid lazy loading exceptions when retrieving the roles
        UserDetails userDetails = User.withTransaction {			
			// Auth doing nothing
//            authentication = (Auth) authentication
			
            User user = User.findByEmail(authentication.name)
            if (!user) {
                String email = authentication.name
                log.warn "LoginId '$email' NOT found"
                throw new UsernameNotFoundException("LoginId '$email' not found")
            }
        }

        Authentication authResult = new Authentication(userDetails)
        authResult.details = authentication.details
        authResult
	}
	
	@Override
	boolean supports(Class<? extends Object> authenticationClass) {
		return false;
	}
	
	
}
