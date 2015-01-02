package bitbee

import grails.plugins.springsecurity.Secured
import grails.converters.JSON

class ChartController {

	def newTime = new Date().getTime();
	def rand = new Random();
	
	@Secured(['ROLE_ADMIN', 'ROLE_TEST'])
    def index() { 
		log.info "chart index"
	}
	
	@Secured(['ROLE_ADMIN'])
	def adminOnly() {
		log.info "admin only!!"
	}
	
	def retrieveData() {
		newTime = new Date().getTime();
		def data = [timestamp:newTime,value:rand.nextInt()]
		
		log.info data
		render data as JSON;
	}
	
	def getLineParameter() {
		def lineParams = [
			caption:"Random Number Per Second", 
			numberPrefix:null,
			primaryColor:"#080808",
			secondaryColor:"#e80c0c",
			series:[[newTime-11000,0],[newTime-10000,0],[newTime-9000,0],[newTime-8000,0],[newTime-7000,0],[newTime-6000,0],[newTime-5000,0],[newTime-4000,0],[newTime-3000,0],[newTime-2000,0],[newTime-1000,0],[newTime,0]],
			tooltipDecimals:0,
			xAxisTitle:"Time",
			yAxisTitle:"Random Number"] as JSON
		log.info lineParams
		render lineParams
	}
	
}
