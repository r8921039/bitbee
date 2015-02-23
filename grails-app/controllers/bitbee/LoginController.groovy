package bitbee

import grails.plugins.springsecurity.SpringSecurityService
import javax.net.ssl.HttpsURLConnection
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import org.springframework.security.core.Authentication
import grails.converters.JSON

class LoginController {
	def springSecurityService
	def authenticationTrustResolver
	
	def index() {
		log.info "login index"
		auth()
	}
	
	def oauth() {
		def authURL = "https://www.coinbase.com/oauth/authorize?" + \
			"response_type=code&" + \
			"client_id=f860d2772dd41f820f7baee3444ba86ce94da1a1322916060711beb30e469892&" + \
			"redirect_uri=https://bitbee.ddns.net:8443/bitbee/login/aaa&" + \
			"scope=user+balance"
		log.info "authURL: " + authURL
		redirect uri: authURL
	}
	
	def aaa() {
		def code = params['code']
		log.debug "return code from coinbase: " + code
		
		def codeToTokenURL = "https://www.coinbase.com/oauth/token"
		log.debug "codeTotokenURL:" + codeToTokenURL
		
		URL obj = new URL(codeToTokenURL);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
//		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		
		String urlParameters = "grant_type=authorization_code&" + \
			"code=" + code + "&" + \
			"redirect_uri=https://bitbee.ddns.net:8443/bitbee/login/aaa&" + \
			"client_id=f860d2772dd41f820f7baee3444ba86ce94da1a1322916060711beb30e469892&" + \
			"client_secret=225872ba1557b02ed9fd74dcdd32d580f18f32450767ec1de76899d35eca5bf1"
 
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
 
		log.debug "Sending 'POST' request to URL : " + codeToTokenURL
		log.debug "Post parameters : " + urlParameters
		log.debug "response code: " + con.getResponseCode();
		log.debug "response msg: " + con.getResponseMessage();
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer strBuffer = new StringBuffer();
 
		log.debug "hihi2"
		
		while ((inputLine = bufferedReader.readLine()) != null) {
			strBuffer.append(inputLine);
		}
		bufferedReader.close();
 
		log.debug "hihi3"
		
		//print result
		String result = strBuffer.toString()
		log.debug "result: " + result
		render result
	}
	
	def bbb() {
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
