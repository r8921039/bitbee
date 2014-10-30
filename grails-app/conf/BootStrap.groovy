class BootStrap {

    def init = { servletContext ->
		def adminRole = new Role(role: 'ROLE_ADMIN').save(flush: true)
		def userRole = new Role(authority: 'ROLE_USER').save(flush: true)
	
		def testUser = new User(username: 'admin', enabled: true, password: 'admin')
		testUser.save(flush: true)
	
		SecAppUserSecAppRole.create testUser, adminRole, true
    }
    def destroy = {
    }
}
