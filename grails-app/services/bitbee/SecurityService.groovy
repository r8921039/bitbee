package bitbee

import java.util.Set;

import bitbee.Role;
import bitbee.User;

import grails.plugins.springsecurity.SpringSecurityService;
import grails.transaction.Transactional

@Transactional
class SecurityService {
	static transactional = false

	SpringSecurityService springSecurityService

	User getCurrentUser() {
		def currentUser = springSecurityService.currentUser
		currentUser ? currentUser as User : null
	}

	boolean isLoggedIn() {
		springSecurityService.isLoggedIn()
	}

	boolean currentUserHasRole(RoleDef role) {
		// alternatively, we could use: SpringSecurityUtils.ifAnyGranted()
		User currentUser = getCurrentUser()
		currentUser?.hasRole(role)
	}
}
