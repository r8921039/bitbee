package bitbee

import grails.plugins.springsecurity.SpringSecurityService
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import org.springframework.security.core.Authentication

class LoginController {
	def springSecurityService
	def authenticationTrustResolver
	
	def index() {
		log.info "login index"
		auth()
	}
	
    def auth() { 
		log.info "login auth"
		
		def config = SpringSecurityUtils.securityConfig
		
		if (springSecurityService.loggedIn) {
			log.info "user already logged in"
			redirect uri: config.successHandler.defaultTargetUrl
			return
		}

		log.info "log user in"
		def authentication = springSecurityService.getAuthentication()
		log.info "isRememberMe: " + authenticationTrustResolver.isRememberMe(authentication)
		[
			postURL: "${request.contextPath}${config.apf.filterProcessesUrl}", 
			rememberMeParameter: config.rememberMe.parameter, 
			hasCookie: authenticationTrustResolver.isRememberMe(authentication)
		]
	}	
	
	def logout() {
		redirect uri: SpringSecurityUtils.securityConfig.logout.filterProcessesUrl
	}
	
	def failed() {
		log.info "login failed"
	}
	
	def error() {
		log.info "login error"
	}
}
