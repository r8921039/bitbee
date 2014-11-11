import bitbee.Role
import bitbee.User
import bitbee.UserRole

class BootStrap {

    def init = { servletContext ->
		def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
		def testRole = new Role(authority: 'ROLE_TEST').save(flush: true)	
		
		def adminUser = new User(username: 'admin@admin', password: 'admin', enabled: true).save(flush: true)
		def testUser = new User(username: 'test@test', password: 'test', enabled: true).save(flush: true)
	
		UserRole.create(adminUser, adminRole, true)
		UserRole.create(testUser, testRole, true)
    }
	
    def destroy = {
    
	}
}
