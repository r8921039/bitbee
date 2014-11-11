package bitbee

import bitbee.Role;
import bitbee.User;

import grails.plugins.springsecurity.SpringSecurityService;
import grails.transaction.Transactional

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
}
