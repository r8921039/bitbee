package bitbee

import grails.plugins.springsecurity.Secured

class ChartController {

	@Secured(['ROLE_ADMIN', 'ROLE_TEST'])
    def index() { 
		log.info "chart index"
	}
	
	@Secured(['ROLE_ADMIN'])
	def adminOnly() {
		log.info "admin only!!"
	}
}
