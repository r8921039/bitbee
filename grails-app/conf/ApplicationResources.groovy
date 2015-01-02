modules = {
    application {
        resource url:'js/application.js'
    }
	
	highCharts {
		dependsOn 'jquery'
		resource url: 'css/charts.css', attrs: [type: 'css']
		resource url: 'js/chart/Line.js', attrs: [type: 'js']
		resource url: 'js/charts-lib/highcharts.js', attrs: [type: 'js']
		resource url: 'js/charts-lib/highcharts-more.js', attrs: [type: 'js']
	}
}