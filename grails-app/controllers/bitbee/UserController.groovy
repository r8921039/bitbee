package bitbee

import java.util.Map;

import bitbee.SecurityService;

class UserController {

   SecurityService securityService

    def notAuthorized() {
        def user = securityService.currentUser
        log.warn "access denied: ${user.email}"
        forward controller: 'login', action: 'index'
    }

    def loginFailure() {
        flash.loginFailed = true
        redirect controller: 'login', action: 'login'
    }

    def login() {
        getLoginModelIfNotAuthenticated()
    }

    private Map getLoginModelIfNotAuthenticated() {
        def config = SpringSecurityUtils.securityConfig

        if (securityService.loggedIn) {
            redirect uri: config.successHandler.defaultTargetUrl
            return
        }

        String loginFormHandler = "${request.contextPath}${config.apf.filterProcessesUrl}"
        [postUrl: loginFormHandler]
    }

    def logout() {
        // redirect to the spring security logout handler - defaults to /j_spring_security_logout
        redirect uri: SpringSecurityUtils.securityConfig.logout.filterProcessesUrl
    }
}
